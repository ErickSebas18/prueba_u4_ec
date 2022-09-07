package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;


@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.merge(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscar(String codigoBarras) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Producto> query = this.entityManager.createQuery("Select p from Producto p where p.codigoBarras = :codigoBarras", Producto.class);
			query.setParameter("codigoBarras", codigoBarras);
			return query.getSingleResult();
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarProductoSimple(String codigoBarras, String nombre, Integer stock) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Producto> criteriaQuery = builder.createQuery(Producto.class);
		Root<Producto> root = criteriaQuery.from(Producto.class);
		Predicate predicateCodigoBarras = builder.equal(root.get("codigoBarras"), codigoBarras);
		Predicate predicateNombre = builder.equal(root.get("nombre"), nombre);
		Predicate predicateCantidad = builder.greaterThan(root.get("stock"), stock);
		criteriaQuery.select(root).where(predicateCodigoBarras,predicateNombre,predicateCantidad);
		TypedQuery<Producto> query = this.entityManager.createQuery(criteriaQuery);
		return query.getSingleResult();
		
	}

	@Override
	public ProductoSencillo buscarProductoSencillo(String codigoBarras) {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductoSencillo> criteriaQuery = builder.createQuery(ProductoSencillo.class);
		Root<ProductoSencillo> root = criteriaQuery.from(ProductoSencillo.class);
		Predicate predicateCodigoBarras = builder.equal(root.get("codigoBarras"), codigoBarras);
		criteriaQuery.select(root).where(predicateCodigoBarras);
		TypedQuery<ProductoSencillo> query = this.entityManager.createQuery(criteriaQuery);
		return query.getSingleResult();
	}

	@Override
	public List<Producto> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Producto> query = this.entityManager.createQuery("Select p from Producto p", Producto.class);
		return query.getResultList();
	}
	
	

}
