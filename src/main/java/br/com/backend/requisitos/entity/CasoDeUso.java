package br.com.backend.requisitos.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CasoDeUso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "caso_de_uso_id", nullable = false)
	private Integer id;
	@Column(name = "caso_de_uso_nome", nullable = false)
	private String nome;
	@Column(name = "caso_de_uso_escopo", nullable = false)
	private String escopo;
	@Column(name = "caso_de_uso_nivel", nullable = false)
	private String nivel;
	@Column(name = "caso_de_uso_interesses_interessados", nullable = false)
	private String interessesInteressados;
	@Column(name = "caso_de_uso_pre_condicao", nullable = false)
	private String preCondicao;
	@Column(name = "caso_de_uso_data_inclusao", nullable = false)
	private Calendar dataInclusao;
	@Column(name = "caso_de_uso_data_alteracao", nullable = true)
	private Calendar dataAlteracao;
	@ManyToOne
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;
	@ManyToOne
	@JoinColumn(name = "integrante_id", nullable = false)
	private Integrante integrante;

	public CasoDeUso() {
	}

	public CasoDeUso(Integer id, String nome, String escopo, String nivel, String interessesInteressados,
			String preCondicao, Calendar dataInclusao, Calendar dataAlteracao, Projeto projeto, Integrante integrante) {
		this.id = id;
		this.nome = nome;
		this.escopo = escopo;
		this.nivel = nivel;
		this.interessesInteressados = interessesInteressados;
		this.preCondicao = preCondicao;
		this.dataInclusao = dataInclusao;
		this.dataAlteracao = dataAlteracao;
		this.projeto = projeto;
		this.integrante = integrante;
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

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getInteressesInteressados() {
		return interessesInteressados;
	}

	public void setInteressesInteressados(String interessesInteressados) {
		this.interessesInteressados = interessesInteressados;
	}

	public String getPreCondicao() {
		return preCondicao;
	}

	public void setPreCondicao(String preCondicao) {
		this.preCondicao = preCondicao;
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

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Integrante getIntegrante() {
		return integrante;
	}

	public void setIntegrante(Integrante integrante) {
		this.integrante = integrante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((escopo == null) ? 0 : escopo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((integrante == null) ? 0 : integrante.hashCode());
		result = prime * result + ((interessesInteressados == null) ? 0 : interessesInteressados.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preCondicao == null) ? 0 : preCondicao.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
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
		CasoDeUso other = (CasoDeUso) obj;
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
		if (escopo == null) {
			if (other.escopo != null)
				return false;
		} else if (!escopo.equals(other.escopo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (integrante == null) {
			if (other.integrante != null)
				return false;
		} else if (!integrante.equals(other.integrante))
			return false;
		if (interessesInteressados == null) {
			if (other.interessesInteressados != null)
				return false;
		} else if (!interessesInteressados.equals(other.interessesInteressados))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preCondicao == null) {
			if (other.preCondicao != null)
				return false;
		} else if (!preCondicao.equals(other.preCondicao))
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		return true;
	}
}
