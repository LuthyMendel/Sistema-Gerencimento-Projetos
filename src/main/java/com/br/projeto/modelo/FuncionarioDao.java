package com.br.projeto.modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.br.projeto.bean.Funcionario;
import com.br.projeto.bean.Grupo;
import com.br.projeto.bean.Setor;
import com.br.projeto.jpa.EntityManagerUtil;
import com.br.projeto.util.UtilErros;
import com.br.projeto.util.UtilMensagens;

public class FuncionarioDao {
	
	private  EntityManager em;
	
	
	public FuncionarioDao() {
		em = EntityManagerUtil.getEntityManager();
	}
	
	
	public List<Funcionario> listarTtodos(){
		
		return em.createQuery("FROM Funcionario ORDER by nome").getResultList();
		
	}
	
	
	public boolean gravar(Funcionario obj) {
		
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
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao Persistir Objeto :"+UtilErros.getMensagemError(e));
			return false;
		}
		
	}
	
	
	
	public boolean excluir(Funcionario obj) {
		
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
	
	public Funcionario localizar(Integer id) {
		
		return em.find(Funcionario.class,id);
	}
	
	
	public boolean login(String usuario, String senha) {
		
		Query query = em.createQuery("FROM Funcionario where upper(nomeUsuario) = :usuario" + " and upper(senha) = :senha and ativo = true");
		
		query.setParameter("usuario",usuario.toUpperCase());
		query.setParameter("senha", senha.toUpperCase());
		
		if(!query.getResultList().isEmpty()) {
			
			return true;
		}else {
			
			return false;
		}
		
	}
	
	public Funcionario localizaPorNome(String usuario) {
		return (Funcionario) em.createQuery("FROM Funcionario where upper(nomeUsuario)="+":usuario").setParameter("usuario", usuario.toUpperCase()).getSingleResult();
		
	}
	

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
