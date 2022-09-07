package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@NamedQuery(name = "DetalleVenta.buscar", query = "Select new package com.uce.edu.demo.repository.modelo.Reporte(d.) from DetalleVenta d join fetch d.venta, d.producto dv where (dv.fecha = :fecha and dv.categoria = :categoria) and dv.cantidad > :cantidad")
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deta_seq_id")
	@SequenceGenerator(name = "deta_seq_id", sequenceName = "deta_seq_id", allocationSize = 1)
	@Column(name = "prod_id")
	private Integer id;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal subtotal;
	@ManyToOne
	@JoinColumn(name = "deta_id_venta")
	private Venta venta;
	@ManyToOne
	@JoinColumn(name = "deta_id_producto")
	private Producto producto;
	
	//SET Y GET
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
