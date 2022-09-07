package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Compras;

public interface IVentaService {

	public void insertarVenta(List<Compras> listaProductos, String cedulaCliente , String numeroVenta);
}
