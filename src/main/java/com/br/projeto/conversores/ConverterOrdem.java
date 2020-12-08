package com.br.projeto.conversores;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.br.projeto.modelo.Ordem;

@SuppressWarnings("serial")
public class ConverterOrdem implements Serializable, Converter{
	
	private List<Ordem> listaOrdems;

	public ConverterOrdem(List<Ordem> listaOrdems) {
		super();
		this.listaOrdems = listaOrdems;
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ordem retorno = null;
		
		for (Ordem o : listaOrdems) {
			if(o.getAtributo().equals(value)) {
				
				retorno = o;
			}			
		}
		
		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object ob) {
		if(ob == null) {
			
			return null;
		}else {
			Ordem ordem = (Ordem) ob;
			return ordem.getAtributo().toString();// Na classe Ordem foi definido o toString
		}
		
	}

	
	
	public List<Ordem> getListaOrdems() {
		return listaOrdems;
	}

	public void setListaOrdems(List<Ordem> listaOrdems) {
		this.listaOrdems = listaOrdems;
	}

}
