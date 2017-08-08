package com.monografia.faturamento.model;

import com.monografia.movimentarestoque.model.PedidoEstoque;
import com.monografia.servicoemitirnf.model.NotaFiscal;

public class Faturamento {
	private int idPedidoEstoque;
	private int idNotaFiscal;
	private String numeroNotaFiscal;
	private String xml;
	private String status;
	
	public Faturamento(NotaFiscal notaFiscal, PedidoEstoque pedidoEstoque){
		// Dados da Nota Fiscal
		if(notaFiscal != null){
			this.idNotaFiscal 		= notaFiscal.getId();
			this.numeroNotaFiscal 	= notaFiscal.getNumeroNotaFiscal();
			this.xml 				= notaFiscal.getXml();	
		}else{
			this.idNotaFiscal 		= 0;
			this.numeroNotaFiscal 	= "S/N";
			this.xml 				= "NAO_EMITIU_NOTA_FISCAL";
		}
		
		// Status do Pedido Estoque
		if(pedidoEstoque != null){
			this.idPedidoEstoque 	= pedidoEstoque.getId();
			this.status 			= pedidoEstoque.getStatus();
		}else{
			this.idPedidoEstoque 	= notaFiscal.getIdPedidoEstoque();
			this.status 			= "NAO_MOVIMENTOU_ESTOQUE";
		}
		
	}

	public int getIdPedidoEstoque() {
		return idPedidoEstoque;
	}

	public int getIdNotaFiscal() {
		return idNotaFiscal;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public String getXml() {
		return xml;
	}

	public String getStatus() {
		return status;
	}
	
}
