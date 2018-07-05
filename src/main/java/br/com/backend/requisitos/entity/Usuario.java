package br.com.backend.requisitos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :emailUsuario"),
		@javax.persistence.NamedQuery(name = "Usuario.findByCodeUpdatePassword", query = "SELECT u FROM Usuario u WHERE u.codigoAlteracaoSenha = :codigoDeValidacaoUsuario") })
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id", nullable = false)
	private Integer id;
	@Column(name = "usuario_nome", nullable = false)
	private String nome;
	@Column(name = "usuario_email", nullable = false, unique = true)
	private String email;
	@Column(name = "usuario_senha", nullable = false)
	private String senha;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "usuario_dataInclusao", nullable = false)
	private Calendar dataInclusao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "usuario_dataAlteracao", nullable = true)
	private Calendar dataAlteracao;
	@Column(name = "usuario_token", nullable = true)
	private String token;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "usuario_dataUltimoToken", nullable = true)
	private Calendar dataUltimoToken;
	@Column(name = "usuario_codigo_alteracao_senha", nullable = true)
	private String codigoAlteracaoSenha;

	public Usuario() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Calendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Calendar getDataUltimoToken() {
		return dataUltimoToken;
	}

	public void setDataUltimoToken(Calendar dataUltimoToken) {
		this.dataUltimoToken = dataUltimoToken;
	}

	public String getCodigoAlteracaoSenha() {
		return codigoAlteracaoSenha;
	}

	public void setCodigoAlteracaoSenha(String codigoAlteracaoSenha) {
		this.codigoAlteracaoSenha = codigoAlteracaoSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoAlteracaoSenha == null) ? 0 : codigoAlteracaoSenha.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((dataUltimoToken == null) ? 0 : dataUltimoToken.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		Usuario other = (Usuario) obj;
		if (codigoAlteracaoSenha == null) {
			if (other.codigoAlteracaoSenha != null)
				return false;
		} else if (!codigoAlteracaoSenha.equals(other.codigoAlteracaoSenha))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataInclusao == null) {
			if (other.dataInclusao != null)
				return false;
		} else if (!dataInclusao.equals(other.dataInclusao))
			return false;
		if (dataUltimoToken == null) {
			if (other.dataUltimoToken != null)
				return false;
		} else if (!dataUltimoToken.equals(other.dataUltimoToken))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
}
