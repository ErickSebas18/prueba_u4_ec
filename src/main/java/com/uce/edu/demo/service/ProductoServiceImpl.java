package com.uce.edu.demo.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoRepository iProductoRepository;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void insertarProducto(Producto producto){
		// TODO Auto-generated method stub
		
		Producto productoBuscado = this.iProductoRepository.buscar(producto.getCodigoBarras());
		
			if(productoBuscado == null) {
				this.iProductoRepository.insertar(producto);
			} else {
				productoBuscado.setStock(productoBuscado.getStock() + producto.getStock());
				this.iProductoRepository.actualizar(productoBuscado);
			}
		}

	@Override
	public ProductoSencillo buscarProductoSencillo(String codigoBarras) {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarProductoSencillo(codigoBarras);
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		return this.iProductoRepository.buscarTodos();
	}

}
