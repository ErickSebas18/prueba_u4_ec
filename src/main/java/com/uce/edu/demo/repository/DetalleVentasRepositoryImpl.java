package com.uce.edu.demo.repository;

import java.time.LocalDateTime;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Reporte;

@Repository
@Transactional
public class DetalleVentasRepositoryImpl implements IDetalleVentaRepository{

	
	@Override
	public Reporte buscar(LocalDateTime fecha, String categoria, Integer cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}
