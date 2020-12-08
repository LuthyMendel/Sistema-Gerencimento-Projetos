package com.br.projeto.conversores;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "converterCalendario")
public class ConverterCalendario  implements Converter{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	

	
	//Converte da Tela para o Objeto
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Calendar calendario = Calendar.getInstance();
		
		sdf.setLenient(false);
		
		
		try {
			
			calendario.setTime(sdf.parse(value));
			
		} catch (Exception e) {
			return null;

		}
		return calendario;
	}

	// Converte do Objeto para a Tela
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		Calendar c = (Calendar) obj;
		String str = sdf.format(c.getTime());
		
		return str;
	}

}
