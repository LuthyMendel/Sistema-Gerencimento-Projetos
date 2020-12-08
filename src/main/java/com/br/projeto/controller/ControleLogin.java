package com.br.projeto.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.br.projeto.bean.Funcionario;
import com.br.projeto.modelo.FuncionarioDao;
import com.br.projeto.util.UtilMensagens;

@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable{
	
	private FuncionarioDao daoFuncionario;
	private Funcionario usuarioLogado;
	private String usuario;
	private String senha;
	
	
	public ControleLogin() {
		daoFuncionario = new FuncionarioDao();
	}

	
	public String paginaLogin() {
		
		return "/login";
	}
	
	public String efetuarLogin() {
		
		if(daoFuncionario.login(usuario, senha)) {
			
			usuarioLogado = daoFuncionario.localizaPorNome(usuario);
			
			UtilMensagens.mensagemInformacao("Login Efetuado com Sucesso");
			return "/index";
			
		}else {
			
			UtilMensagens.mensagemErro("Login não efetuado com sucesso!"
					
			+"Usuario ou senha invalidos!");
			return "/login";
		}
	}
	
	public String efetuarLogout() {
		usuarioLogado = null;
		return "/index";
		
	}

	public FuncionarioDao getDaoFuncionario() {
		return daoFuncionario;
	}


	public void setDaoFuncionario(FuncionarioDao daoFuncionario) {
		this.daoFuncionario = daoFuncionario;
	}


	public Funcionario getUsuarioLogado() {
		
		if(this.usuarioLogado == null) {
			System.out.println("Usuário Nullo");
			return usuarioLogado;
		}
		System.out.println("Usuário NÂO Nullo");
		return usuarioLogado;
	}


	public void setUsuarioLogado(Funcionario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

}
