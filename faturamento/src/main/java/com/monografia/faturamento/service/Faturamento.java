package com.monografia.faturamento.service;

import java.io.IOException;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.monografia.servicoemitirnf.model.NotaFiscal;



@Component
public class Faturamento {
	
	private static final Logger LOG = LoggerFactory.getLogger(Faturamento.class);

    @Autowired
    Util util;

    private RestTemplate restTemplate = new RestTemplate();
    
    // Emissão NotaFiscal //
    
    public ResponseEntity<NotaFiscal> emitirNFSaida(int pedidoId) {
    	
    	URI uri = util.getServiceUrl("notafiscal", "http://localhost:8081/notafiscal");
        String url = uri.toString() + "/emitirnfsaida/" + pedidoId;
        LOG.debug("EmitirNFSaida da URL: {}", url);

        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        LOG.debug("EmitirNFSaida http-status: {}", resultStr.getStatusCode());
        LOG.debug("EmitirNFSaida body: {}", resultStr.getBody());

        NotaFiscal notaFiscal = response2Product(resultStr);
        LOG.debug("EmitirNFSaida.id: {}", notaFiscal.getId());

        return util.createOkResponse(notaFiscal);
    	
    }
    
    private ObjectReader notaFiscalReader = null;
    private ObjectReader getProductReader() {

        if (notaFiscalReader != null) return notaFiscalReader;

        ObjectMapper mapper = new ObjectMapper();
        return notaFiscalReader = mapper.reader(NotaFiscal.class);
    }
    
    public NotaFiscal response2Product(ResponseEntity<String> response) {
        try {
            return getProductReader().readValue(response.getBody());	
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}