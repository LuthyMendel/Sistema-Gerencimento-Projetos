package com.br.projeto.bean;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable{
	
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_FUNC", sequenceName = "SEQ_FUNC_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FUNC")
	private Integer id;
	
	@NotEmpty(message = "Campo nome Obrigatório")
	@Length(max = 50, message="Nome não pode ultrapassar {max} caracteres")
	@Column(name = "NOME", length = 50, nullable =false)
	private String nome;
	
	@NotEmpty(message = "CPF deve ser Informado")
	@CPF(message = "Informe um CPF Válido")
	@Column(name="CPF", length = 14, nullable = false, unique = true)
	private String cpf;
	
	@NotEmpty(message = "E-mail deve ser Informado")	
	@Email(message = "O E-mail deve ser válido")
	@Column(name="EMAIL", length = 40, nullable = false)
	private String email;
	
	@NotNull(message = "O campo Salário deve ser informado")
	@Column(name = "SALARIO", nullable = false, columnDefinition = "numeric(10,2)")
	private Double salario;
	
	@NotNull(message = "Data de nascimento deve ser Informada")
	@Column(name = "NASCIMENTO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar nascimento;
	
	@NotNull(message = "O campo ativo deve ser informado")
	@Column(name = "ATIVO",nullable = false)
	private Boolean ativo;
	
	@Column(name = "FOTO")
	@Lob
	private byte[] foto;
	
	
	@NotEmpty(message = "O Nome de Usuário deve ser Informado")
	@Length(max = 20, message = "Nome do usuário não deve ultrapassar {max} caracteres")
	@Column(name = "NOME_USUARIO", length = 20, nullable = false, unique = true)
	private String nomeUsuario;
	
	@NotEmpty(message="Senha deve ser informada")
	@Length(max=10, message="Senha não pode ultrapassar {max} caracteres")
	@Column(name="senha", length=10, nullable=false)
	private String senha;
	
	@NotNull(message = "O Grupo deve ser Informado")
	@ManyToOne
	@JoinColumn(name="grupo", referencedColumnName = "ID", nullable = false)
	private Grupo grupo;
	
	
	@NotNull(message = "O Setor deve ser Informado")
	@ManyToOne
	@JoinColumn(name="setor", referencedColumnName = "ID", nullable = false)
	private Setor setor;
	
	@Transient
	private StreamedContent imagem;
	
	
	//gerando Imagen dinâmica
	public StreamedContent getImagem() {

		if (this.getFoto() != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(this.getFoto()), "");

		} else
			return new DefaultStreamedContent();
	}
	
	
	
	
	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Calendar getNascimento() {
		return nascimento;
	}
	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		
		return nome;
	}
	

	
}
