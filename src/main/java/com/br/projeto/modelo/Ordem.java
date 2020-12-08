package com.br.projeto.modelo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Ordem implements Serializable{
	
	
	//Nome das Label
	private String rotulo;
	private String atributo;
	
	public Ordem(String rotulo, String atributo) {
		this.rotulo = rotulo;
		this.atributo = atributo;
	}
	
	
	public String getRotulo() {
		return rotulo;
	}
	
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	
	public String getAtributo() {
		return atributo;
	}
	
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rotulo == null) ? 0 : rotulo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordem other = (Ordem) obj;
		if (rotulo == null) {
			if (other.rotulo != null)
				return false;
		} else if (!rotulo.equals(other.rotulo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		
		return rotulo;
	}
	
	

}
