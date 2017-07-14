package com.monografia.faturamento.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monografia.servicoemitirnf.model.NotaFiscal;

@RestController
public class FaturamentoService {

	private static final Logger LOG = LoggerFactory.getLogger(FaturamentoService.class);

    @Autowired
    Faturamento integration;

    @Autowired
    Util util;

    @RequestMapping("/")
    public String faturarPedido() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Hello from EmitirNFSaidaAPi\"}";
    }
    
    @RequestMapping("/faturarpedido/{pedidoId}")
    public ResponseEntity<NotaFiscal> faturarPedido(@PathVariable int pedidoId) {

        // 1. First get mandatory product information
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

        return util.createOkResponse(notaFiscalResult.getBody());
    }
}