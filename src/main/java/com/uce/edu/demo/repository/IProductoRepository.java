package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;


public interface IProductoRepository {

	public void insertar(Producto producto);
	
	public void actualizar(Producto producto);
	
	public Producto buscar(String codigoBarras);
	
	public Producto buscarProductoSimple(String codigoBarras, String nombre, Integer stock);
	
	public ProductoSencillo buscarProductoSencillo(String codigoBarras);
	
	public List<Producto> buscarTodos();
}
