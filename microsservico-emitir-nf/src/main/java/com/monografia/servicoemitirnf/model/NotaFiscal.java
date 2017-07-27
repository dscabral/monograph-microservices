package com.monografia.servicoemitirnf.model;


public class NotaFiscal {
	private int id;
	private String numeroNotaFiscal;
	private int idPedidoEstoque;
	private String xml;
	
	
	public NotaFiscal(){
		
	}
	
	public NotaFiscal(int i, String numeroNotaFiscal, int idPedidoEstoque, String xml) {
		this.id = i;
		this.numeroNotaFiscal = numeroNotaFiscal;
		this.idPedidoEstoque = idPedidoEstoque;
		this.xml = xml;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public int getIdPedidoEstoque() {
		return idPedidoEstoque;
	}

	public void setIdPedidoEstoque(int idPedidoEstoque) {
		this.idPedidoEstoque = idPedidoEstoque;
	}

}
