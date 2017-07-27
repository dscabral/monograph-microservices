package com.monografia.faturamento.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.monografia.faturamento.model.Faturamento;

import com.monografia.movimentarestoque.model.PedidoEstoque;
import com.monografia.servicoemitirnf.model.NotaFiscal;

@RestController
public class FaturarService {

	private static final Logger LOG = LoggerFactory.getLogger(FaturarService.class);

    @Autowired
    Faturar integration;

    @Autowired
    Util util;

    @RequestMapping("/")
    public String faturarPedido() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"API para Faturamento de Pedido Estoque\"}";
    }
    
    @RequestMapping("/faturarpedido/{pedidoId}")
    public ResponseEntity<Faturamento> faturarPedido(@PathVariable int pedidoId) {

        // 1. Tenta emitir nota fiscal para o pedido
		ResponseEntity<NotaFiscal> notaFiscalResult;
    	try{
    		notaFiscalResult = integration.emitirNFSaida(pedidoId);

            if (!notaFiscalResult.getStatusCode().is2xxSuccessful()) {
                // We can't proceed, return whatever fault we got from the getProduct call
                return util.createResponse(null, notaFiscalResult.getStatusCode());
            }
    	} catch (Throwable t) {
    		LOG.error("emitirNFSaida erro", t);
    		throw t;
    	}
    	
    	ResponseEntity<PedidoEstoque> pedidoEstoqueResult;
    	try{
    		pedidoEstoqueResult = integration.movimentarEstoque(pedidoId);

            if (!pedidoEstoqueResult.getStatusCode().is2xxSuccessful()) {
                // We can't proceed, return whatever fault we got from the getProduct call
//                return util.createResponse(null, pedidoEstoqueResult.getStatusCode());
            	LOG.debug("Chamado ao movimentarEstoque falhou: {}", pedidoEstoqueResult.getStatusCode());
            }
    	} catch (Throwable t) {
    		LOG.error("movimentarEstoque erro", t);
    		throw t;
    	}
		
        return util.createOkResponse(new Faturamento(notaFiscalResult.getBody(), pedidoEstoqueResult.getBody()));
    }
}