package com.uce.edu.demo.repository;

import java.time.LocalDateTime;

import com.uce.edu.demo.repository.modelo.Reporte;

public interface IDetalleVentaRepository {

	public Reporte buscar(LocalDateTime fecha, String categoria, Integer cantidad);
}
