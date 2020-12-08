package com.br.projeto.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;


import com.br.projeto.conversores.ConverterOrdem;
import com.br.projeto.util.UtilErros;
import com.br.projeto.util.UtilMensagens;

@SuppressWarnings("serial")
public class GenericDao<T> implements Serializable {
	
	private Class classe;
	private EntityManager em;
	private String filtro = "";
	private List<Ordem> listaOrdem = new ArrayList<Ordem>();
	private Ordem ordemAtual;
	private int maximoObjetos = 10;
	private int posicao = 0;
	private int totalObjetos =0;
	private ConverterOrdem converterOrdem;
	
	
	
	//Iniciando uma transacao 
	public void iniciarTrancacao() {
		if(em.getTransaction().isActive() ==false) {
			em.getTransaction().begin();
			
		}
		
	}
	
	
	//Callback
	public void rollbackTransacao() {
		iniciarTrancacao();
		em.getTransaction().rollback();
		
	}
	
	//Comitando
	public void commitTansacao() {
		iniciarTrancacao();
		em.getTransaction().commit();
		
	}
	
	//Salvando
	public boolean persist(T objeto) {
		
		try {
			iniciarTrancacao();
			em.persist(objeto);
			commitTansacao();
			UtilMensagens.mensagemInformacao("Objeto persistido com Sucesso");
			return true;
			
		} catch (Exception e) {
			rollbackTransacao();
			UtilMensagens.mensagemErro("Erro ao persistir Objeto"+UtilErros.getMensagemError(e));
			return false;
		}
	}
	
	
	//Editando
		public boolean merge(T objeto) {
			
			try {
				iniciarTrancacao();
					em.merge(objeto);
				commitTansacao();
				UtilMensagens.mensagemInformacao("Objeto persistido com Sucesso");
				return true;
				
			} catch (Exception e) {
				rollbackTransacao();
				UtilMensagens.mensagemErro("Erro ao persistir Objeto"+UtilErros.getMensagemError(e));
				return false;
			}
		}
		
		
		//Removendo
		public boolean remove(T objeto) {
			
			try {
				iniciarTrancacao();
					em.remove(objeto);
				commitTansacao();
				UtilMensagens.mensagemInformacao("Objeto Removido com Sucesso");
				return true;
				
			} catch (Exception e) {
				rollbackTransacao();
				UtilMensagens.mensagemErro("Erro ao Remover Objeto"+UtilErros.getMensagemError(e));
				return false;
			}
		}
		
		
		//Listar Todos
		public List<T> listarTodos(){
			
			String jpql = "FROM "+classe.getSimpleName();
			
			if(ordemAtual !=null) {
				System.out.println("==>"+ordemAtual);
				
				jpql+= " order by "+ordemAtual.getAtributo();
				
			}
			
			return em.createQuery(jpql).getResultList();
			
		}
		
		//######################################################
		//############## Paginação  ############################ 
		//
	
		
		//Remove aspas simples ponto e vírgula e hífem. Evita sql Injecton
		public String protegeFiltro(String filtro) {
			filtro = filtro.replaceAll("[';-]", ""); //subistitui estes por vazio, aspas Simples ponto e vírgula e hífen;
		
			return filtro;
		}
		
		
		@SuppressWarnings("unchecked")
		public List<T> listar(){
			String jpql = "FROM "+classe.getSimpleName();
			
			String where = "";
			
			if(filtro !=null && filtro.length() >0) {
				
				filtro = protegeFiltro(filtro);
				if(ordemAtual.getAtributo().equals("id")) {
					
					try {
						Integer.parseInt(filtro);
						where = "where "+ordemAtual.getAtributo() + " = '"+filtro+"'";
						
					} catch (Exception e) {
						
					}
					
				}else {
					
					where = " where upper ("+ordemAtual.getAtributo()+ ") like '"+filtro.toUpperCase()+ "%' ";
					
				}
				
			}
			
			jpql+= where;
			if(ordemAtual!=null) {
				
				jpql += " order by "+ordemAtual.getAtributo();
				
			}
			
			totalObjetos = em.createQuery("SELECT id FROM "+classe.getSimpleName()).getResultList().size();
			
			if(maximoObjetos == 0) {
				
				maximoObjetos = totalObjetos;
			}
			
			return em.createQuery(jpql).setMaxResults(maximoObjetos).setFirstResult(posicao).getResultList();
		}
		
		
		///Metodos para navegação Paginação
		
		public void primeiro() {
			posicao =0;
			
		}
		
		
		public void anterior() {
			
			posicao-=maximoObjetos;
			if(posicao <0) {
				posicao =0;
				
			}
		}
		
		public void proximo() {
			
			if(posicao + maximoObjetos < totalObjetos) {
				
				posicao += maximoObjetos;
				
			}
		}
		
		public void ultimo() {
			int resto = totalObjetos % maximoObjetos;
			
			if(resto > 0) {
				
				posicao = totalObjetos -resto;
			}else {
				posicao = totalObjetos - maximoObjetos;
			}
			
		}
		
		// Exibir na Tela
		public String getMensagemNavegacao() {

			int ate = posicao + maximoObjetos;
			if (ate > totalObjetos) {
				ate = totalObjetos;
			}

			return "Listando de " + (posicao + 1) + " até " + ate + " de " + totalObjetos + " Registros";
		}
		
	
	
	@SuppressWarnings("rawtypes")
	public Class getClasse() {
		return classe;
	}
	public void setClasse(Class classe) {
		this.classe = classe;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<Ordem> getListaOrdem() {
		return listaOrdem;
	}
	public void setListaOrdem(List<Ordem> listaOrdem) {
		this.listaOrdem = listaOrdem;
	}
	public Ordem getOrdemAtual() {
		return ordemAtual;
	}
	public void setOrdemAtual(Ordem ordemAtual) {
		this.ordemAtual = ordemAtual;
	}
	public int getMaximoObjetos() {
		return maximoObjetos;
	}
	public void setMaximoObjetos(int maximoObjetos) {
		this.maximoObjetos = maximoObjetos;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public int getTotalObjetos() {
		return totalObjetos;
	}
	public void setTotalObjetos(int totalObjetos) {
		this.totalObjetos = totalObjetos;
	}
	public ConverterOrdem getConverterOrdem() {
		return converterOrdem;
	}
	public void setConverterOrdem(ConverterOrdem converterOrdem) {
		this.converterOrdem = converterOrdem;
	}
	

}
