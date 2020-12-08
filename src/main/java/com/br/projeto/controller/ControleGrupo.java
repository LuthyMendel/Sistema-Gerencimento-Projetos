package com.br.projeto.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.br.projeto.bean.Grupo;
import com.br.projeto.modelo.GrupoDao;
import com.br.projeto.util.UtilMensagens;

@ManagedBean(name = "controleGrupo")
@SessionScoped
public class ControleGrupo  implements Serializable{
	
	private GrupoDao dao;
	private Grupo objeto;
	
	@ManagedProperty(value = "#{controleLogin}")
	private ControleLogin controleLogin;
	
		public ControleGrupo() {
		
			dao = new GrupoDao();
			
		}
		
		
		public String listar() {
			
			return "/privado/grupo/listar?faces-redirect=true";
			
			
		}
		
		public String novo() {
			
			objeto = new Grupo();
			
			return "form";
			
		}
		
		public String cancelar() {
			
			return "listar";
			
		}
		
		public String gravar() {
			if(dao.gravar(objeto)) {
				
				return "listar";
				
			}else {
				
				return "form";
			}
			
		}
		
		public String alterar(Grupo obj) {
			
			objeto = obj;
			
			return "form";
			
		}
		
		
		
		public String excluir(Grupo obj) {
			
			if(controleLogin.getUsuarioLogado().getGrupo().getNome().equals("Administrador")) {
				dao.excluir(obj);	
			}else {
				UtilMensagens.mensagemErro("Usuário não possui autorização para Exclusão");
				
			}
			
			
			return "listar";
			
		}


		public GrupoDao getDao() {
			return dao;
		}


		public void setDao(GrupoDao dao) {
			this.dao = dao;
		}


		public Grupo getObjeto() {
			return objeto;
		}


		public void setObjeto(Grupo objeto) {
			this.objeto = objeto;
		}


		public ControleLogin getControleLogin() {
			return controleLogin;
		}


		public void setControleLogin(ControleLogin controleLogin) {
			this.controleLogin = controleLogin;
		}
	

}
