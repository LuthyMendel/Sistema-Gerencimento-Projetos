package com.br.projeto.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import com.br.projeto.bean.Grupo;
import com.br.projeto.bean.Setor;
import com.br.projeto.jpa.EntityManagerUtil;
import com.br.projeto.util.UtilErros;
import com.br.projeto.util.UtilMensagens;

public class SetorDao {
	
	private  EntityManager em;
	
	
	public SetorDao() {
		em = EntityManagerUtil.getEntityManager();
	}
	
	
	public List<Setor> listarTtodos(){
		
		return em.createQuery("FROM Setor ORDER by nome").getResultList();
		
	}
	
	
	public boolean gravar(Setor obj) {
		
		try {
			
			em.getTransaction().begin();
			
			if(obj.getId() == null) {				
				em.persist(obj);	
				
			}else {					
				em.merge(obj);
			}	
			
			em.getTransaction().commit();
			
			UtilMensagens.mensagemInformacao("Grupo persistido com Sucesso !");
			
			return true;
			
		} catch (Exception e) {
			
			if(em.getTransaction().isActive() == false) {
				
				em.getTransaction().begin();
			}em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao Persistir Objeto :"+UtilErros.getMensagemError(e));
			return false;
		}
		
	}
	
	
	
	public boolean excluir(Setor obj) {
		
		try {
			
			em.getTransaction().begin();			
				em.remove(obj);			
			em.getTransaction().commit();
			
			UtilMensagens.mensagemInformacao("Objeto removido com Sucesso !");
			
			return true;
			
		} catch (Exception e) {
			
			if(em.getTransaction().isActive() == false) {
				
				em.getTransaction().begin();
			}em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover Objeto :"+UtilErros.getMensagemError(e));
			return false;
		}
		
	}
	
	public Setor localizar(Integer id) {
		
		return em.find(Setor.class,id);
	}
	
	

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
