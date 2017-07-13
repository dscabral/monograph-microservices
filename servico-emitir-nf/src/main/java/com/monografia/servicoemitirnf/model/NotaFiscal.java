package com.monografia.servicoemitirnf.model;

public class NotaFiscal {
	private int id;
	private String numeroNotaFiscal;
	private String xml;
	
	
	public NotaFiscal(){
		
	}
	
	public NotaFiscal(int i, String numeroNotaFiscal, String xml) {
		this.id = i;
		this.numeroNotaFiscal = numeroNotaFiscal;
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

}
