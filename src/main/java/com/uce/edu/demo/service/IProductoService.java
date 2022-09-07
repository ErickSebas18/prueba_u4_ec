package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

public interface IProductoService {

	public void insertarProducto(Producto producto) throws Exception;
	
	public ProductoSencillo buscarProductoSencillo(String codigoBarras);
	
	public List<Producto> buscarTodos();
}
