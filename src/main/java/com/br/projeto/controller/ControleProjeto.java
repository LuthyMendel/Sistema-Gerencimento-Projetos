package com.br.projeto.controller;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.br.projeto.bean.Funcionario;
import com.br.projeto.bean.Grupo;
import com.br.projeto.bean.Projeto;
import com.br.projeto.bean.ProjetoFuncionario;
import com.br.projeto.conversores.ConverterFuncionario;
import com.br.projeto.conversores.ConverterSetor;
import com.br.projeto.modelo.FuncionarioDao;
import com.br.projeto.modelo.GrupoDao;
import com.br.projeto.modelo.ProjetoDao;
import com.br.projeto.modelo.SetorDao;

@ManagedBean(name = "controleProjeto")
@SessionScoped
public class ControleProjeto  implements Serializable{
	
	private ProjetoDao dao;
	private Projeto objeto;
	private FuncionarioDao daoFuncionario;
	private ConverterFuncionario converterFuncionario;
	private SetorDao daoSetor;
	private ConverterSetor converterSetor;
	private Funcionario funcionario;//
	private Integer cargaHoraria;//
	private Boolean gestor;//
	private Calendar inicioParticipacao;//
	private Calendar fimParticipacao;//
	private Boolean addFuncioinario = false;//
	
		public ControleProjeto() {
		
			dao = new ProjetoDao();
			daoFuncionario = new FuncionarioDao();
			converterFuncionario = new ConverterFuncionario();
			daoSetor =  new SetorDao();
			converterSetor = new ConverterSetor();			
		}
		
		
		public String listar() {
			
			return "/privado/projeto/listar?faces-redirect=true";
			
			
		}
		
		public void  removerFuncinonario(ProjetoFuncionario obj) {//
			
			objeto.removerFuncnionario(obj);
			
		}
		
		public void adicionarFuncionario() {//
			addFuncioinario = true;
		}
		
		public void cancelarFuncionario() {//
			addFuncioinario = false;
		}
		
		public void salvarFuncionario() {//
			
			
			ProjetoFuncionario obj = new ProjetoFuncionario();
				obj.setCargaHoraria(cargaHoraria);
				obj.setFuncionario(funcionario);
				obj.setInicioParticipacao(inicioParticipacao);
				obj.setFimParticipacao(fimParticipacao);
				obj.setGestor(gestor);
				
			objeto.adicionarFuncionario(obj);
			addFuncioinario= false;
			
			cargaHoraria =null;
			funcionario =null;
			inicioParticipacao = null;
			fimParticipacao = null;
			gestor = null;
			
			
		}
		
		public String novo() {
			
			objeto = new Projeto();
			addFuncioinario = false;//
			
			return "form";
			
		}
		
		public String cancelar() {
			addFuncioinario =false;//
			dao.rollback();//
			
			return "listar";
			
		}
		
		public String gravar() {
			if(dao.gravar(objeto)) {
				
				addFuncioinario = false;//
				return "listar";
				
			}else {
				
				return "form";
			}
			
		}
		
		public String alterar(Projeto obj) {
			
			objeto = obj;
			addFuncioinario = false;//
			
			return "form";
			
		}
		
		public String excluir(Projeto obj) {
			dao.excluir(obj);
			
			return "listar";
			
		}


		public ProjetoDao getDao() {
			return dao;
		}


		public void setDao(ProjetoDao dao) {
			this.dao = dao;
		}


		public Projeto getObjeto() {
			return objeto;
		}


		public void setObjeto(Projeto objeto) {
			this.objeto = objeto;
		}


		public FuncionarioDao getDaoFuncionario() {
			return daoFuncionario;
		}


		public void setDaoFuncionario(FuncionarioDao daoFuncionario) {
			this.daoFuncionario = daoFuncionario;
		}


		public ConverterFuncionario getConverterFuncionario() {
			return converterFuncionario;
		}


		public void setConverterFuncionario(ConverterFuncionario converterFuncionario) {
			this.converterFuncionario = converterFuncionario;
		}


		public SetorDao getDaoSetor() {
			return daoSetor;
		}


		public void setDaoSetor(SetorDao daoSetor) {
			this.daoSetor = daoSetor;
		}


		public ConverterSetor getConverterSetor() {
			return converterSetor;
		}


		public void setConverterSetor(ConverterSetor converterSetor) {
			this.converterSetor = converterSetor;
		}


		public Funcionario getFuncionario() {
			return funcionario;
		}


		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
		}


		public Integer getCargaHoraria() {
			return cargaHoraria;
		}


		public void setCargaHoraria(Integer cargaHoraria) {
			this.cargaHoraria = cargaHoraria;
		}


		public Boolean getGestor() {
			return gestor;
		}


		public void setGestor(Boolean gestor) {
			this.gestor = gestor;
		}


		


		public Boolean getAddFuncioinario() {
			return addFuncioinario;
		}


		public void setAddFuncioinario(Boolean addFuncioinario) {
			this.addFuncioinario = addFuncioinario;
		}


		public Calendar getInicioParticipacao() {
			return inicioParticipacao;
		}


		public void setInicioParticipacao(Calendar inicioParticipacao) {
			this.inicioParticipacao = inicioParticipacao;
		}


		public Calendar getFimParticipacao() {
			return fimParticipacao;
		}


		public void setFimParticipacao(Calendar fimParticipacao) {
			this.fimParticipacao = fimParticipacao;
		}
		
		

}
