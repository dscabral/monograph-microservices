package com.monografia.servicoemitirnf.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monografia.servicoemitirnf.model.NotaFiscal;

@RestController
public class NotaFiscalService {
	
	@RequestMapping("/emitirnfsaida/{pedidoId}")
	public NotaFiscal emitirNFSaida(@PathVariable int pedidoId) {
		return new NotaFiscal(pedidoId, "10", "teste de xml");
	}

}