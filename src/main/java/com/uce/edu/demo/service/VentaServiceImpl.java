package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.Compras;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class VentaServiceImpl implements IVentaService{

	@Autowired
	private IProductoRepository iProductoRepository;
	
	@Autowired
	private IVentaRepository iVentaRepository;
	
	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void insertarVenta(List<Compras> listaProductos, String cedulaCliente, String numeroVenta) {
		// TODO Auto-generated method stub
		DetalleVenta detalle = new DetalleVenta();
		BigDecimal total = BigDecimal.ZERO;
		
		Venta venta = new Venta();
		venta.setCedulaCliente(cedulaCliente);
		venta.setFecha(LocalDateTime.now());
		venta.setNumero(numeroVenta);
		
		List<DetalleVenta> detalles = new ArrayList<>();
		
		for (Compras c: listaProductos) {
			try {
				Producto producto = this.iProductoRepository.buscar(c.getCodigoBarras());
				
				if(producto == null || producto.getStock() == 0) {
					throw new RuntimeException();
				}else if (producto.getStock() < c.getCantidad()) {
					detalle.setProducto(producto);
					detalle.setCantidad(producto.getStock());
					detalle.setPrecioUnitario(producto.getPrecio());
					producto.setStock(0);
					this.iProductoRepository.actualizar(producto);
					
					BigDecimal cantidad = new BigDecimal(producto.getStock());
					BigDecimal subtotal = producto.getPrecio().multiply(cantidad);
					detalle.setSubtotal(subtotal);
					total = total.add(subtotal);
					detalles.add(detalle);
					
				} else {
					detalle.setProducto(producto);
					detalle.setCantidad(c.getCantidad());
					producto.setStock(producto.getStock() - c.getCantidad());
					this.iProductoRepository.actualizar(producto);
					
					detalle.setPrecioUnitario(producto.getPrecio());
					BigDecimal cantidad = new BigDecimal(c.getCantidad());
					BigDecimal subtotal = producto.getPrecio().multiply(cantidad);
					detalle.setSubtotal(subtotal);
					total = total.add(subtotal);
					detalles.add(detalle);
				}
			
			venta.setTotalVenta(total);
			venta.setDetalleVentas(detalles);
			this.iVentaRepository.insertarVenta(venta);
			
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
