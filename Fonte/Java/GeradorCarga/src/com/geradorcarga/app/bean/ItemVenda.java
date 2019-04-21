package com.geradorcarga.app.bean;

import java.io.Serializable;

public class ItemVenda implements Serializable{

	private static final long serialVersionUID = -2938000536110361054L;

	private int id;
	private int quantidade;
	private Double preco;
	
	public ItemVenda() {}

	public ItemVenda(int id, int quantidade, Double preco) {
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
