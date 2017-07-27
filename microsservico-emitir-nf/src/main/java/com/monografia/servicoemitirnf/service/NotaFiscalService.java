package com.monografia.servicoemitirnf.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monografia.servicoemitirnf.model.NotaFiscal;

@RestController
public class NotaFiscalService {
	
	NotaFiscal notaFiscal;

	@RequestMapping("/emitirnfsaida/{pedidoId}")
	public NotaFiscal emitirNFSaida(@PathVariable int pedidoId) {
		Random gerador = new Random();
		notaFiscal = new NotaFiscal(gerador.nextInt(20),Integer.toString(gerador.nextInt(100)), pedidoId, "Exemplo de xml");
		return notaFiscal;
	}
}
