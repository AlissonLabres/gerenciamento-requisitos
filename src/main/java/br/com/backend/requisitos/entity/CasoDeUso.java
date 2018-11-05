package br.com.backend.requisitos.entity;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import br.com.backend.requisitos.enums.Status;

@Entity
@NamedQueries({
	@NamedQuery(
			name="CasoDeUso.findAll",
	        query="SELECT c FROM CasoDeUso c INNER JOIN c.projeto p WHERE p.id = :idProjeto"
	),
	@NamedQuery(
			name="CasoDeUso.findById",
			query="SELECT c FROM CasoDeUso c INNER JOIN c.projeto p WHERE p.id = :idProjeto AND c.id = :idCasoDeUso"
	    ),
	@NamedQuery(
			name="CasoDeUso.findByNameInProject", 
			query="SELECT c FROM CasoDeUso c WHERE c.nome = :casoDeUsoNome"
	)
})
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

	@Column(name = "caso_de_uso_ator_principal", nullable = false)
	private String atorPrincipal;

	@Column(name = "caso_de_uso_pre_condicao", nullable = false)
	private String preCondicao;

	@Column(name = "caso_de_uso_pos_condicao", nullable = false)
	private String posCondicao;

	@Column(name = "caso_de_uso_cenario_principal", nullable = false)
	private String cenarioPrincipal;

	@Column(name = "caso_de_uso_extensao", nullable = false)
	private String extensao;

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

	@OneToMany(mappedBy = "casoDeUso", fetch = FetchType.EAGER)
	@Column(name = "caso_de_uso_artefatos", nullable = true)
	private List<Artefato> artefatos;

	@Enumerated
	@Column(name = "caso_de_uso_status", nullable = false)
	private Status status;

	public CasoDeUso() {
	}

	public CasoDeUso(Integer id, String nome, String escopo, String nivel, String atorPrincipal,
			String preCondicao, Calendar dataInclusao, Calendar dataAlteracao, Projeto projeto, Integrante integrante) {
		this.id = id;
		this.nome = nome;
		this.escopo = escopo;
		this.nivel = nivel;
		this.atorPrincipal = atorPrincipal;
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

	public String getAtorPrincipal() {
		return atorPrincipal;
	}

	public void setAtorPrincipal(String atorPrincipal) {
		this.atorPrincipal = atorPrincipal;
	}

	public String getPreCondicao() {
		return preCondicao;
	}

	public void setPreCondicao(String preCondicao) {
		this.preCondicao = preCondicao;
	}

	public String getPosCondicao() {
		return posCondicao;
	}

	public void setPosCondicao(String posCondicao) {
		this.posCondicao = posCondicao;
	}

	public String getCenarioPrincipal() {
		return cenarioPrincipal;
	}

	public void setCenarioPrincipal(String cenarioPrincipal) {
		this.cenarioPrincipal = cenarioPrincipal;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
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

	public List<Artefato> getArtefatos() {
		return artefatos;
	}

	public void setArtefatos(List<Artefato> artefatos) {
		this.artefatos = artefatos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artefatos == null) ? 0 : artefatos.hashCode());
		result = prime * result + ((atorPrincipal == null) ? 0 : atorPrincipal.hashCode());
		result = prime * result + ((cenarioPrincipal == null) ? 0 : cenarioPrincipal.hashCode());
		result = prime * result + ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result + ((dataInclusao == null) ? 0 : dataInclusao.hashCode());
		result = prime * result + ((escopo == null) ? 0 : escopo.hashCode());
		result = prime * result + ((extensao == null) ? 0 : extensao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((integrante == null) ? 0 : integrante.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((posCondicao == null) ? 0 : posCondicao.hashCode());
		result = prime * result + ((preCondicao == null) ? 0 : preCondicao.hashCode());
		result = prime * result + ((projeto == null) ? 0 : projeto.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (artefatos == null) {
			if (other.artefatos != null)
				return false;
		} else if (!artefatos.equals(other.artefatos))
			return false;
		if (atorPrincipal == null) {
			if (other.atorPrincipal != null)
				return false;
		} else if (!atorPrincipal.equals(other.atorPrincipal))
			return false;
		if (cenarioPrincipal == null) {
			if (other.cenarioPrincipal != null)
				return false;
		} else if (!cenarioPrincipal.equals(other.cenarioPrincipal))
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
		if (escopo == null) {
			if (other.escopo != null)
				return false;
		} else if (!escopo.equals(other.escopo))
			return false;
		if (extensao == null) {
			if (other.extensao != null)
				return false;
		} else if (!extensao.equals(other.extensao))
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
		if (posCondicao == null) {
			if (other.posCondicao != null)
				return false;
		} else if (!posCondicao.equals(other.posCondicao))
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
		if (status != other.status)
			return false;
		return true;
	}
}
