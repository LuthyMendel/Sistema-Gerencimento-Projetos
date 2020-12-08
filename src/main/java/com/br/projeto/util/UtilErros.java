package com.br.projeto.util;


//Tornando mais clara as mensagens de erros
public class UtilErros {
	
	public static String getMensagemError(Exception e) {
		
		while (e.getCause() !=null) {
			e = (Exception) e.getCause();
			
		}
		String retorno = e.getMessage();
		return retorno;
		
	}

}
