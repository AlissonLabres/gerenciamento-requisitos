package br.com.backend.requisitos.dto.interfaces;

public class ArtefatoDTOInterface {
	
	private String nome;

	private String descricao;
	
	private Integer idRequisito;
	
	private Integer idCasoDeUso;
	
	public ArtefatoDTOInterface() {
	}

	public ArtefatoDTOInterface(String nome, String descricao, Integer idRequisito, Integer idCasoDeUso) {
		this.nome = nome;
		this.descricao = descricao;
		this.idRequisito = idRequisito;
		this.idCasoDeUso = idCasoDeUso;
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

	public Integer getIdRequisito() {
		return idRequisito;
	}

	public void setIdRequisito(Integer idRequisito) {
		this.idRequisito = idRequisito;
	}

	public Integer getIdCasoDeUso() {
		return idCasoDeUso;
	}

	public void setIdCasoDeUso(Integer idCasoDeUso) {
		this.idCasoDeUso = idCasoDeUso;
	}
}
