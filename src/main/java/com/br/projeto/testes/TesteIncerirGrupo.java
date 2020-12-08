package com.br.projeto.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import com.br.projeto.bean.Funcionario;
import com.br.projeto.bean.Grupo;
import com.br.projeto.bean.Projeto;
import com.br.projeto.bean.ProjetoFuncionario;
import com.br.projeto.bean.Setor;
import com.br.projeto.jpa.EntityManagerUtil;

public class TesteIncerirGrupo {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		
//		Grupo g = new Grupo();
//		
//		g.setNome("Gestores");
//		em.getTransaction().begin();
//		em.persist(g);
//		em.getTransaction().commit();
		
//		Setor setor = new Setor();
//		
//		setor.setNome("Administrativo");
//		em.getTransaction().begin();
//			em.persist(setor);
//		em.getTransaction().commit();
		
		//##cadastrando um Usuário
		
//		Grupo grupo = em.find(Grupo.class, 1);
//		Setor setor = em.find(Setor.class,1); 
//		
//		
//		
//		Funcionario funcionario = new Funcionario();
//		
//			funcionario.setAtivo(true);
//			funcionario.setCpf("529.328.230-19");
//			funcionario.setEmail("giom.01@gmail.com");
//			funcionario.setGrupo(grupo);
//			funcionario.setNascimento(Calendar.getInstance());
//			funcionario.setNome("Luciano");
//			funcionario.setNomeUsuario("LucianoMendel");
//			funcionario.setSalario(500.00);
//			funcionario.setSenha("12345");
//			funcionario.setSetor(setor);
//			
//			em.getTransaction().begin();
//				em.persist(funcionario);
//			em.getTransaction().commit();
//		
		
		//Inserir ProJeto Funcionario
		
		Setor setor = em.find(Setor.class, 1);
		Funcionario funcionario = em.find(Funcionario.class, 3);
		
		
		Projeto projeto = new Projeto();
			projeto.setAtivo(true);
			projeto.setDescriacao("Novo projeto em JSF");
			projeto.setInicio(Calendar.getInstance());
			projeto.setFim(Calendar.getInstance());
			projeto.setNome("Sistema de Funcionário");
			projeto.setSetor(setor);
			
			
			ProjetoFuncionario pf = new ProjetoFuncionario();
				pf.setCargaHoraria(100);
				pf.setFimParticipacao(Calendar.getInstance());
				pf.setFuncionario(funcionario);
				pf.setGestor(true);
				pf.setInicioParticipacao(Calendar.getInstance());
				
				projeto.adicionarFuncionario(pf); // Adicionando Funcionario
				
		System.out.println("Classe projeto  Nome :"+projeto.getNome());
		System.out.println("Classe projeto  Descrição :"+projeto.getDescriacao());
		System.out.println("Classe projeto  Inicio :"+projeto.getInicio());
		System.out.println("Classe projeto  Fim :"+projeto.getFim());
		System.out.println("Classe projeto  Ativo :"+projeto.isAtivo());
		System.out.println("Classe projeto  Setor :"+projeto.getSetor().getNome());
		System.out.println("Classe projeto  funcionarios :"+projeto.getFuncionarios().size());
		
		
		em.getTransaction().begin();
		em.persist(projeto);
		em.getTransaction().commit();
				
			
			
		

	}

}
