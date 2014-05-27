package br.com.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.lojaonline.model.Endereco;

@Component
public class EnderecoDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EnderecoDAO(){
		
	}
	
	 public EntityManager getEntityManager() {
	        return entityManager;
	}
	 
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}

	public Endereco find(Integer idEndereco) {
		return entityManager.find(Endereco.class, idEndereco);
	}
	
	@Transactional
	public void persist(Endereco endereco) {
		entityManager.persist(endereco);
	}
	
	@Transactional
	public void merge(Endereco endereco) {
		entityManager.merge(endereco);
	}
	
	@Transactional
	public void remove(Endereco endereco) {
		entityManager.remove(endereco);
	}
/*
	@SuppressWarnings("unchecked")
	public List<Endereco> listarEndereco() {
		return entityManager.createQuery("SELECT endereco FROM endereco;").getResultList();
	}   */
	
	 public List<Endereco> listarEndereco() {
	        TypedQuery<Endereco> query = entityManager.createQuery("SELECT e FROM Endereco e ORDER BY e.idEndereco",Endereco.class);
	        return query.getResultList();
	    }
		   
}
