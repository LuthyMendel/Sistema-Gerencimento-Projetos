package com.br.projeto.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.br.projeto.bean.Funcionario;
import com.br.projeto.bean.Grupo;
import com.br.projeto.bean.Setor;
import com.br.projeto.jpa.EntityManagerUtil;

@FacesConverter(value = "converterFuncionario")
public class ConverterFuncionario implements Converter{

	//Converte Uma String em um Objeto
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value == null || value.equals("Selecione um Funcionário")) {
			return null;
		}		
		return EntityManagerUtil.getEntityManager().find(Funcionario.class,Integer.parseInt(value));
	}

	
	//Converte um Objeto em um Integer
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;
		}		
		Funcionario obj = (Funcionario) value;
		
		return obj.getId().toString();
	}

}
