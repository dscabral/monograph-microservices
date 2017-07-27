package com.monografia.movimentarestoque.model;

public class PedidoEstoque {
	private int id;
	private String status;
	
	public PedidoEstoque(){
		
	}
	
	public PedidoEstoque(int id, String status){
		this.id = id;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
