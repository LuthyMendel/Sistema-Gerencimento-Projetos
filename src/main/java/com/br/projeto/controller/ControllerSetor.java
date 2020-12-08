package com.br.projeto.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.br.projeto.bean.Setor;
import com.br.projeto.modelo.DAOSetor;

@SuppressWarnings("serial")
@ManagedBean(name = "controllerSetor")
@SessionScoped
public class ControllerSetor  implements Serializable{
	
	private DAOSetor<Setor> daoSetor;
	private Setor objetoSetor;
	
	public ControllerSetor() {
		daoSetor = new DAOSetor<Setor>();
		
	}
	
	
	public String listar() {
		
		return "/privado/setor/listar?faces-redirect=true/";
		
	}
	
	public String novo() {
		
		objetoSetor = new Setor();
		return "form";
		
	}
	
	public String cancelar() {
		return "listar";
		
	}
	
	
	public String gravar() {
		
		System.out.println("Método Gravar");
		
		boolean gravou = false;
		
		if(objetoSetor.getId() == null) {
			
			gravou = daoSetor.persist(objetoSetor);
			
		}else {
			
			gravou = daoSetor.merge(objetoSetor);
		}
		
		if(gravou) {
			return "listar";
		}else {
			return "form";
		}
	}
	
	public String alterar(Setor obj) {
		objetoSetor = obj;
		return "form";
		
	}
	
	
	public String excluir (Setor obj) {
		
		daoSetor.remove(obj);
		
		return "listar";
		
	}
	
	

	public DAOSetor<Setor> getDaoSetor() {
		return daoSetor;
	}

	public void setDaoSetor(DAOSetor<Setor> daoSetor) {
		this.daoSetor = daoSetor;
	}

	public Setor getObjetoSetor() {
		return objetoSetor;
	}

	public void setObjetoSetor(Setor objetoSetor) {
		this.objetoSetor = objetoSetor;
	}

}
