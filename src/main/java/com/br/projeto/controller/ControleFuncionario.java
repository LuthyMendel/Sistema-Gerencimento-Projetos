package com.br.projeto.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.br.projeto.bean.Funcionario;
import com.br.projeto.conversores.ConverterGrupo;
import com.br.projeto.conversores.ConverterSetor;
import com.br.projeto.modelo.FuncionarioDao;
import com.br.projeto.modelo.GrupoDao;
import com.br.projeto.modelo.SetorDao;
import com.br.projeto.util.UtilErros;
import com.br.projeto.util.UtilMensagens;

@ManagedBean(name="controleFuncionario")
@SessionScoped
public class ControleFuncionario implements Serializable{

	private FuncionarioDao daoFuncionario;
	private Funcionario funcionario;
	private GrupoDao daoGrupo;
	private SetorDao daoSetor;
	private ConverterGrupo converterGrupo;
	private ConverterSetor converterSetor;
	
	
	public ControleFuncionario() {
		
		daoFuncionario = new FuncionarioDao();
		daoGrupo = new GrupoDao();
		daoSetor =  new SetorDao();
		converterGrupo = new ConverterGrupo();
		converterSetor =  new ConverterSetor();


	}
	

	public StreamedContent getImagemDinamica(){
		String strid = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id_imagem");
		if (strid != null){
			Integer id = Integer.parseInt(strid);
			Funcionario obj = daoFuncionario.localizar(id);
			return obj.getImagem();
		}
		return new DefaultStreamedContent();
	}
	
	
	public void enviarFoto(FileUploadEvent event){
		try {
			byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
			funcionario.setFoto(foto);
			UtilMensagens.mensagemInformacao("Arquivo enviado com sucesso! "+
			event.getFile().getFileName());
		} catch (Exception e){
			UtilMensagens.mensagemErro("Erro ao enviar arquivo:" +
					UtilErros.getMensagemError(e)) ;
		}
	}
	
	
	public String listar() {
		
		return "/privado/funcionario/listar?faces-redirect=true";
	}
	
	public String novo() {
		
		funcionario = new Funcionario();
		
		return "form";
		
	}
	
	public String cancelar() {
		
		return "listar";
	}
	
	public String gravar() {
		if(daoFuncionario.gravar(funcionario)) {
			return "listar";
			
		}else {
			return "form";
		}
		
	}
	
	public String alterar(Funcionario obj) {
		
		funcionario =obj;
		return "form";
	}
	
	public String excluir(Funcionario obj) {
		
		daoFuncionario.excluir(obj);
		
		return "listar";
	}
	
	
	public FuncionarioDao getDaoFuncionario() {
		return daoFuncionario;
	}
	public void setDaoFuncionario(FuncionarioDao daoFuncionario) {
		this.daoFuncionario = daoFuncionario;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public GrupoDao getDaoGrupo() {
		return daoGrupo;
	}
	public void setDaoGrupo(GrupoDao daoGrupo) {
		this.daoGrupo = daoGrupo;
	}
	public SetorDao getDaoSetor() {
		return daoSetor;
	}
	public void setDaoSetor(SetorDao daoSetor) {
		this.daoSetor = daoSetor;
	}
	public ConverterGrupo getConverterGrupo() {
		return converterGrupo;
	}
	public void setConverterGrupo(ConverterGrupo converterGrupo) {
		this.converterGrupo = converterGrupo;
	}
	public ConverterSetor getConverterSetor() {
		return converterSetor;
	}
	public void setConverterSetor(ConverterSetor converterSetor) {
		this.converterSetor = converterSetor;
	}

}
