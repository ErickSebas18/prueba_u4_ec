package com.uce.edu.demo.repository.modelo;

public class ProductoSencillo {

	private String codigBarras;
	private String nombre;
	private Integer stock;
	
	public ProductoSencillo() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductoSencillo(String codigBarras, String nombre, Integer stock) {
		this.codigBarras = codigBarras;
		this.nombre = nombre;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "ProductoSencillo [codigBarras=" + codigBarras + ", nombre=" + nombre + ", stock=" + stock + "]";
	}

	public String getCodigBarras() {
		return codigBarras;
	}

	public void setCodigBarras(String codigBarras) {
		this.codigBarras = codigBarras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
}
