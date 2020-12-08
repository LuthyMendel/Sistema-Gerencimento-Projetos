package com.br.projeto.testes;

import java.util.List;

import com.br.projeto.bean.Setor;
import com.br.projeto.modelo.DAOSetor;

public class TesteDaoGeneric {
	
	public static void main (String[] args) {
		
		DAOSetor<Setor> dao = new DAOSetor<Setor>();
		
		List<Setor> lista = dao.listar();
		dao.setMaximoObjetos(8);
		
		for(Setor o : lista) {
			System.out.println(" ID :"+o.getId());
			System.out.println(" Nome :"+o.getNome());
		}
		
		
	}

}
