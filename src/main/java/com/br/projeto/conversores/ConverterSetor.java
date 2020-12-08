package com.br.projeto.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.br.projeto.bean.Grupo;
import com.br.projeto.bean.Setor;
import com.br.projeto.jpa.EntityManagerUtil;

@FacesConverter(value = "converterSetor")
public class ConverterSetor implements Converter{

	//Converte Uma String em um Objeto
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value == null || value.equals("Selecione um Setor")) {
			return null;
		}		
		return EntityManagerUtil.getEntityManager().find(Setor.class,Integer.parseInt(value));
	}

	
	//Converte um Objeto em um Integer
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;
		}		
		Setor obj = (Setor) value;
		
		return obj.getId().toString();
	}

}
