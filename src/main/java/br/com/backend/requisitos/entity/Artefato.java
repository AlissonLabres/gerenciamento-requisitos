package br.com.backend.requisitos.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQueries({
	@NamedQuery(
		name = "Artefato.findAll",
		query = "SELECT a FROM Artefato a "
				+ "INNER JOIN a.projeto as p "
				+ "WHERE p.id = :idProjeto"
	),
	@NamedQuery(
		name = "Artefato.findById",
		query = "SELECT a FROM Artefato a "
				+ "INNER JOIN a.projeto as p "
				+ "WHERE p.id = :idProjeto "
				+ "AND a.id = :idArtefato"
	),
	@NamedQuery(
		name = "Artefato.findAllRequisito",
		query = "SELECT a FROM Artefato a "
				+ "INNER JOIN a.requisito as r "
				+ "INNER JOIN r.projeto as p "
				+ "WHERE p.id = :idProjeto"
	),
	@NamedQuery(
		name = "Artefato.findAllCasoDeUso",
		query = "SELECT a FROM Artefato a "
				+ "INNER JOIN a.casoDeUso as c "
				+ "INNER JOIN c.projeto as p "
				+ "WHERE p.id = :idProjeto"
	)
})
@Entity
public class Artefato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artefato_id", nullable = false)
	private Integer id;
	
	@Column(name = "artefato_nome", nullable = false)
	private String nome;
	
	@Column(name = "artefato_descricao", nullable = false)
	private String descricao;
	
	@Column(name = "artefato_data_inclusao", nullable = false)
	private Calendar dataInclusao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "requisito_id", nullable = true)
	private Requisito requisito;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caso_de_uso_id", nullable = true)
	private CasoDeUso casoDeUso;

	@ManyToOne
	@JoinColumn(name = "integrante_id", nullable = false)
	private Integrante criador;
	
	@Column(name = "artefato_data_alteracao", nullable = true)
	private Calendar dataAlteracao;
	
	@OneToOne(orphanRemoval = true)
    @JoinColumn(name = "arquivo_id", nullable = true)
    private Arquivo arquivo;

	public Artefato() {
	}	

	public Artefato(Integer id, String nome, String descricao, Calendar dataInclusao, Requisito requisito,
			CasoDeUso casoDeUso, Integrante criador, Integrante modificador, Calendar dataAlteracao, Arquivo arquivo) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataInclusao = dataInclusao;
		this.requisito = requisito;
		this.casoDeUso = casoDeUso;
		this.criador = criador;
		this.dataAlteracao = dataAlteracao;
		this.arquivo = arquivo;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Calendar getDataInclusao() {
		return dataInclusao;
	}


	public void setDataInclusao(Calendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}


	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Requisito getRequisito() {
		return requisito;
	}


	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}


	public CasoDeUso getCasoDeUso() {
		return casoDeUso;
	}


	public void setCasoDeUso(CasoDeUso casoDeUso) {
		this.casoDeUso = casoDeUso;
	}


	public Integrante getCriador() {
		return criador;
	}


	public void setCriador(Integrante criador) {
		this.criador = criador;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}


	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
		result = prime * result + ((casoDeUso == null) ? 0 : casoDeUso.hashCode());
		result = prime * result + ((criador == null) ? 0 : criador.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((requisito == null) ? 0 : requisito.hashCode());
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
		Artefato other = (Artefato) obj;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		if (casoDeUso == null) {
			if (other.casoDeUso != null)
				return false;
		} else if (!casoDeUso.equals(other.casoDeUso))
			return false;
		if (criador == null) {
			if (other.criador != null)
				return false;
		} else if (!criador.equals(other.criador))
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (requisito == null) {
			if (other.requisito != null)
				return false;
		} else if (!requisito.equals(other.requisito))
			return false;
		return true;
	}
}