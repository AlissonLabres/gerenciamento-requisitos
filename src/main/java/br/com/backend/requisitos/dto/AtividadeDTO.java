package br.com.backend.requisitos.dto;

import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.Integrante;

public class AtividadeDTO {
	private String nome;
	private String status;
	private String criadoPor;
	private String desenvolvidoPor;

	public AtividadeDTO(Atividade atividade) {
		nome = atividade.getNome();
		status = atividade.getStatus().getValue();
		criadoPor = atividade.getCriador().getUsuario().getNome();
		desenvolvidoPor = ((Integrante) atividade.getDesenvolvedores().get(atividade.getDesenvolvedores().size() - 1))
				.getUsuario().getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	public String getDesenvolvidoPor() {
		return desenvolvidoPor;
	}

	public void setDesenvolvidoPor(String desenvolvidoPor) {
		this.desenvolvidoPor = desenvolvidoPor;
	}
}
