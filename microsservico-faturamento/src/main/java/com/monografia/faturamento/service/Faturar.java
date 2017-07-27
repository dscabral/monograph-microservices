package com.monografia.faturamento.service;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.monografia.movimentarestoque.model.PedidoEstoque;
import com.monografia.servicoemitirnf.model.NotaFiscal;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class Faturar {
	
	private static final Logger LOG = LoggerFactory.getLogger(Faturar.class);

    @Autowired
    Util util;

    private RestTemplate restTemplate = new RestTemplate();
    
    // Emissão NotaFiscal //
    @HystrixCommand(fallbackMethod = "emitirNFSaidaFallBack")
    public ResponseEntity<NotaFiscal> emitirNFSaida(int pedidoId) {
    	
    	URI uri = util.getServiceUrl("notafiscal", "http://localhost:8081/notafiscal");
        String url = uri.toString() + "/emitirnfsaida/" + pedidoId;
        LOG.debug("EmitirNFSaida da URL: {}", url);

        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        LOG.debug("EmitirNFSaida http-status: {}", resultStr.getStatusCode());
        LOG.debug("EmitirNFSaida body: {}", resultStr.getBody());

        NotaFiscal notaFiscal = response2NotaFiscal(resultStr);
        LOG.debug("EmitirNFSaida.id: {}", notaFiscal.getId());

        return util.createOkResponse(notaFiscal);
    	
    }
    
    public ResponseEntity<NotaFiscal> emitirNFSaidaFallBack(int pedidoId) {
    	return util.createResponse(null, HttpStatus.BAD_GATEWAY);
    }
    
    // Movimentar Estoque //
    @HystrixCommand(fallbackMethod = "movimentarEstoqueFallBack")
    public ResponseEntity<PedidoEstoque> movimentarEstoque(int pedidoId) {
    	
    	URI uri = util.getServiceUrl("estoque", "http://localhost:8081/estoque");
        String url = uri.toString() + "/movimentarestoque/" + pedidoId;
        LOG.debug("MovimentarEstoque da URL: {}", url);

        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        LOG.debug("MovimentarEstoque http-status: {}", resultStr.getStatusCode());
        LOG.debug("MovimentarEstoque body: {}", resultStr.getBody());

        PedidoEstoque pedidoEstoque = response2MovimentarEstoque(resultStr);
        LOG.debug("MovimentarEstoque: {}", pedidoEstoque);

        return util.createOkResponse(pedidoEstoque);
    	
    }
    
    public ResponseEntity<PedidoEstoque> movimentarEstoqueFallBack(int pedidoId) {
    	return util.createResponse(null, HttpStatus.BAD_GATEWAY);
    }
    
    private ObjectReader notaFiscalReader = null;
    private ObjectReader getNotaFiscalReader() {

        if (notaFiscalReader != null) return notaFiscalReader;

        ObjectMapper mapper = new ObjectMapper();
        return notaFiscalReader = mapper.reader(NotaFiscal.class);
    }
    
    private ObjectReader movimentarEstoqueReader = null;
    private ObjectReader getMovimentarEstoqueReader() {

        if (movimentarEstoqueReader != null) return movimentarEstoqueReader;

        ObjectMapper mapper = new ObjectMapper();
        return movimentarEstoqueReader = mapper.reader(PedidoEstoque.class);
    }
    
    public NotaFiscal response2NotaFiscal(ResponseEntity<String> response) {
        try {
            return getNotaFiscalReader().readValue(response.getBody());	
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public PedidoEstoque response2MovimentarEstoque(ResponseEntity<String> response) {
        try {
            return getMovimentarEstoqueReader().readValue(response.getBody());	
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
