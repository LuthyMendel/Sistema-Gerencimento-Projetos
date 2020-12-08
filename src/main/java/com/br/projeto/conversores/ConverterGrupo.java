package com.br.projeto.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.br.projeto.bean.Grupo;
import com.br.projeto.jpa.EntityManagerUtil;

@FacesConverter(value = "converterGrupo")
public class ConverterGrupo implements Converter{

	//Converte Uma String em um Objeto
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value == null || value.equals("Selecione um Grupo")) {
			return null;
		}		
		return EntityManagerUtil.getEntityManager().find(Grupo.class,Integer.parseInt(value));
	}

	
	//Converte um Objeto em um Integer
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;
		}		
		Grupo obj = (Grupo) value;
		
		return obj.getId().toString();
	}

}
