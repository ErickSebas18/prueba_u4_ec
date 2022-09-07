package com.uce.edu.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Compras;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.service.IProductoService;
import com.uce.edu.demo.service.IVentaService;

@Controller
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	private IVentaService iVentaService;
	
	@Autowired
	private IProductoService iProductoService;
	
	//GET
    /*@GetMapping("/buscar")
    public String buscarTodos(Model modelo){
        List<Persona> lista = this.personaService.buscarTodos();
        modelo.addAttribute("personas", lista);
        return "vistaListaPersonas";
    }
    
    @GetMapping("/buscarPersona/{idPersona}")
    public String buscarPersona(@PathVariable("idPersona") Integer id, Model modelo) {
    	Persona persona = this.personaService.buscarId(id);
    	modelo.addAttribute("persona", persona);
    	return "vistaPersona";
    }
    
    @PutMapping("/actualizar/{idPersona}")
    public String actualizarPersona(Persona persona, Integer id) {
    	persona.setId(id);
    	this.personaService.actualizar(persona);
    	return "redirect:/personas/buscar";
    }
    
    @DeleteMapping("/borrar/{idPersona}")
    public String eliminarPersona(@PathVariable("idPersona") Integer id) {
    	this.personaService.eliminar(id);
    	return "redirect:/personas/buscar";
    }*/
    
    @PostMapping("/insertarProducto")
    public String insertarProducto(Producto producto) throws Exception {
        this.iProductoService.insertarProducto(producto);
        return "redirect:/ventas/buscar";
    }

    // Metodos de redireccionamientos a paginas
    @GetMapping("/nuevoProducto")
    public String nuevoProducto(Producto producto) {
        return "vistaNuevoProducto";
    }
    
    @PostMapping("/insertarVenta")
    public String insertarVenta(Venta venta){
    	Compras compras = new Compras();
    	for(DetalleVenta d : venta.getDetalleVentas()) {
    		compras.setCantidad(d.getCantidad());
    		compras.setCodigoBarras(d.getProducto().getCodigoBarras());
    	}
    	
    	List<Compras> lista = new ArrayList<>();
    	lista.add(compras);
    	
        this.iVentaService.insertarVenta(lista, venta.getCedulaCliente(), venta.getNumero());
        return "redirect:/personas/buscar";
    }

    // Metodos de redireccionamientos a paginas
    @GetMapping("/nuevaVenta")
    public String nuevaVenta(Venta venta) {
        return "vistaNuevaVenta";
    }
    
    @GetMapping("/buscarProducto/{codigoBarras}")
    public String buscarPersona(@PathVariable("codigoBarras") String codigoBarras, Model modelo) {
    	ProductoSencillo productoSencillo = this.iProductoService.buscarProductoSencillo(codigoBarras);
    	modelo.addAttribute("productoSencillo", productoSencillo);
    	return "vistaProducto";
    }
    
    @GetMapping("/buscar")
    public String buscarTodos(Model modelo){
        List<Producto> lista = this.iProductoService.buscarTodos();
        modelo.addAttribute("productos", lista);
        return "vistaTodosProductos";
    }
    
}
