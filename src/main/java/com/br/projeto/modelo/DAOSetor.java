package com.br.projeto.modelo;

import java.io.Serializable;

import com.br.projeto.bean.Setor;
import com.br.projeto.jpa.EntityManagerUtil;
import com.br.projeto.conversores.ConverterOrdem;




public class DAOSetor<T> extends GenericDao<T> implements Serializable {
	
	
	public DAOSetor() {
		
		super.setClasse(Setor.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Código", "id"));
		super.getListaOrdem().add(new Ordem("Nome", "nome"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(2);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	
	}

}
