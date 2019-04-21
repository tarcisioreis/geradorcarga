package com.geradorcarga.app.bean;

import java.io.Serializable;
import java.util.List;

public class Venda implements Serializable{

	private static final long serialVersionUID = 2314830174348272995L;

	private int id;
	private String nameSalesMan; 
	private List<ItemVenda> listaItensVenda;
	
	public Venda() {}

	public Venda(int id, String nameSalesMan, List<ItemVenda> listaItensVenda) {
		this.id = id;
		this.nameSalesMan = nameSalesMan;
		this.listaItensVenda = listaItensVenda;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNameSalesMan() {
		return nameSalesMan;
	}
	public void setNameSalesMan(String nameSalesMan) {
		this.nameSalesMan = nameSalesMan;
	}

	public List<ItemVenda> getListaItensVenda() {
		return listaItensVenda;
	}
	public void setListaItensVenda(List<ItemVenda> listaItensVenda) {
		this.listaItensVenda = listaItensVenda;
	}

}
