package com.br.projeto.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import com.br.projeto.bean.Grupo;
import com.br.projeto.jpa.EntityManagerUtil;
import com.br.projeto.util.UtilErros;
import com.br.projeto.util.UtilMensagens;

public class GrupoDao {
	
	private  EntityManager em;
	
	//Ordenação
	private String ordem =  "id";
	private String filtro = "";
	private Integer maximoObjetos = 2;
	private Integer posicaoAtual = 0;
	private Integer totalObjetos; 
	
	
	
	public GrupoDao() {
		em = EntityManagerUtil.getEntityManager();
	}
	
	
	// Consulta com paginação.
	@SuppressWarnings("unchecked")
	public List<Grupo> listar() {
		String where = "";
		if (filtro.length() > 0) {

			if (ordem.equals("id")) {
				try {
					Integer.parseInt(filtro);
					where = " where " + ordem + " = '" + filtro + "' ";
				} catch (Exception e) {

				}
			} else {

				where = " where upper("+ordem+") like '"+filtro.toUpperCase()+ "%' ";
			}

		}
		String jpql = "from Grupo "+ where + " order by "+ordem;
		totalObjetos = em.createQuery("SELECT id FROM Grupo " + where + " order by "+ordem).getResultList().size();

		return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
	}
	
	
	public void primeiro() {
		
		posicaoAtual = 0;

	}
	
	
	public void anterior() {
		posicaoAtual-= maximoObjetos;
		
		if(posicaoAtual < 0) {
			
			posicaoAtual = 0;
		}
	}
	
	
	public void proximo() {
		//ultima página não vai avançar
		if(posicaoAtual + maximoObjetos < totalObjetos) {
			posicaoAtual += maximoObjetos;
			
		}
	}
	
	public void ultimo() {
		int resto = totalObjetos % maximoObjetos;
		if (resto > 0) {

			posicaoAtual = totalObjetos - resto;
		} else {
			posicaoAtual = totalObjetos - maximoObjetos;
		}
	}

	// Exibir na Tela
	public String getMensagemNavegacao() {

		int ate = posicaoAtual + maximoObjetos;
		if (ate > totalObjetos) {
			ate = totalObjetos;
		}

		return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " Registros";
	}

	public List<Grupo> listarTtodos() {

		return em.createQuery("FROM Grupo ORDER by nome").getResultList();

	}

	public boolean gravar(Grupo grupo) {

		try {

			em.getTransaction().begin();

			if (grupo.getId() == null) {
				em.persist(grupo);

			} else {
				em.merge(grupo);
			}

			em.getTransaction().commit();

			UtilMensagens.mensagemInformacao("Objeto persistido com Sucesso !");

			return true;

		} catch (Exception e) {

			if (em.getTransaction().isActive() == false) {

				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao Persistir Objeto :" + UtilErros.getMensagemError(e));
			return false;
		}

	}

	public boolean excluir(Grupo grupo) {

		try {

			em.getTransaction().begin();
			em.remove(grupo);
			em.getTransaction().commit();

			UtilMensagens.mensagemInformacao("Objeto removido com Sucesso !");

			return true;

		} catch (Exception e) {

			if (em.getTransaction().isActive() == false) {

				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover Objeto :" + UtilErros.getMensagemError(e));
			return false;
		}

	}

	public Grupo localizar(Integer id) {

		return em.find(Grupo.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getMaximoObjetos() {
		return maximoObjetos;
	}

	public void setMaximoObjetos(Integer maximoObjetos) {
		this.maximoObjetos = maximoObjetos;
	}

	public Integer getPosicaoAtual() {
		return posicaoAtual;
	}

	public void setPosicaoAtual(Integer posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	public Integer getTotalObjetos() {
		return totalObjetos;
	}

	public void setTotalObjetos(Integer totalObjetos) {
		this.totalObjetos = totalObjetos;
	}

}
