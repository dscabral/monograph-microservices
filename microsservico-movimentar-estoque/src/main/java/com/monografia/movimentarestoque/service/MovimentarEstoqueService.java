package com.monografia.movimentarestoque.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monografia.movimentarestoque.model.PedidoEstoque;

@RestController
public class MovimentarEstoqueService {
	
	PedidoEstoque pedidoEstoque;
	
	@RequestMapping("/movimentarestoque/{pedidoId}")
	public PedidoEstoque movimentarEstoque(@PathVariable int pedidoId) {
		return pedidoEstoque = new PedidoEstoque(pedidoId, "MOVIMENTOU_ESTOQUE");
	}
}
