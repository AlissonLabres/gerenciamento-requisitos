package br.com.backend.requisitos.entity;

import br.com.backend.requisitos.enums.CategoriaRequisito;
import br.com.backend.requisitos.enums.ImportanciaRequisito;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Requisito.findAll", query = "SELECT r FROM Requisito r INNER JOIN r.projeto p INNER JOIN r.integrante i INNER JOIN i.usuario u WHERE p.id = :idProjeto AND u.id = :idUsuario"),
		@javax.persistence.NamedQuery(name = "Requisito.findById", query = "SELECT r FROM Requisito r INNER JOIN r.projeto p INNER JOIN r.integrante i INNER JOIN i.usuario u WHERE p.id = :idProjeto AND u.id = :idUsuario AND r.id = :idRequisito") })
public class Requisito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "requisito_id", nullable = false)
	private Integer id;
	@Column(name = "requisito_idRequisito", nullable = false)
	private Double idRequisito;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "requisito_data_inclusao", nullable = false)
	private Calendar dataInclusao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "requisito_data_alteracao", nullable = true)
	private Calendar dataAlteracao;
	@Column(name = "requisito_nome", nullable = false)
	private String nome;
	@Column(name = "requisito_descricao", nullable = true)
	private String descricao;
	@Column(name = "requisito_importancia", nullable = false)
	private ImportanciaRequisito importancia;
	@Column(name = "requisito_fonte", nullable = false)
	private String fonte;
	@Enumerated
	@Column(name = "requisito_categoria", nullable = false)
	private CategoriaRequisito categoria;
	@ManyToOne
	@JoinColumn(name = "projeto_id", nullable = false)
	private Projeto projeto;
	@ManyToOne
	@JoinColumn(name = "integrante_id", nullable = false)
	private Integrante integrante;
	@JsonIgnore
	@OneToMany(mappedBy = "requisito", fetch = FetchType.EAGER)
	@Column(name = "requisito_atividades", nullable = true)
	private List<Atividade> atividades;

	public Requisito() {
	}

	public Requisito(Integer id, String nome, String descricao, ImportanciaRequisito importancia, String fonte,
			CategoriaRequisito categoria, Calendar dataInclusao, Calendar dataAlteracao, Projeto projeto,
			Integrante integrante, List<Atividade> atividades) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.importancia = importancia;
		this.fonte = fonte;
		this.categoria = categoria;
		this.dataInclusao = dataInclusao;
		this.dataAlteracao = dataAlteracao;
		this.projeto = projeto;
		this.integrante = integrante;
		this.atividades = atividades;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(Double idRequisito) {
		this.idRequisito = idRequisito;
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

	public ImportanciaRequisito getImportancia() {
		return importancia;
	}

	public void setImportancia(ImportanciaRequisito importancia) {
		this.importancia = importancia;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public CategoriaRequisito getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaRequisito categoria) {
		this.categoria = categoria;
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

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atividades == null) ? 0 : atividades.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fonte == null) ? 0 : fonte.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idRequisito == null) ? 0 : idRequisito.hashCode());
		result = prime * result + ((importancia == null) ? 0 : importancia.hashCode());
		result = prime * result + ((integrante == null) ? 0 : integrante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Requisito other = (Requisito) obj;
		if (atividades == null) {
			if (other.atividades != null)
				return false;
		} else if (!atividades.equals(other.atividades))
			return false;
		if (categoria != other.categoria)
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
		if (fonte == null) {
			if (other.fonte != null)
				return false;
		} else if (!fonte.equals(other.fonte))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idRequisito == null) {
			if (other.idRequisito != null)
				return false;
		} else if (!idRequisito.equals(other.idRequisito))
			return false;
		if (importancia != other.importancia)
			return false;
		if (integrante == null) {
			if (other.integrante != null)
				return false;
		} else if (!integrante.equals(other.integrante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (projeto == null) {
			if (other.projeto != null)
				return false;
		} else if (!projeto.equals(other.projeto))
			return false;
		return true;
	}
}
