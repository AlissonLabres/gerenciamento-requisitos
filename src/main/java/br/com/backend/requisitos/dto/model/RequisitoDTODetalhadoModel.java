package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

public class RequisitoDTODetalhadoModel extends RequisitoDTOModel {
	private IntegranteDTOModel integrante;
	private ProjetoDTOModel projeto;

	public RequisitoDTODetalhadoModel() {
	}

	public RequisitoDTODetalhadoModel(Integer id, String idRequisito, String nome, String descricao, String importancia,
			String fonte, String categoria, Integer idUsuario, Integrante integrante, Projeto projeto) {

		super(id, idRequisito, nome, descricao, importancia, fonte, categoria);

		this.integrante = new IntegranteDTOModel(integrante.getPerfilIntegranteProjeto().getValue(),
				integrante.getUsuario().getNome(), integrante.getId());

		this.projeto = new ProjetoDTOModel(projeto.getId(), projeto.getNome(), projeto.getDataInicio(),
				projeto.getDataFim());
	}

	public IntegranteDTOModel getIntegrante() {
		return integrante;
	}

	public void setIntegrante(IntegranteDTOModel integrante) {
		this.integrante = integrante;
	}

	public ProjetoDTOModel getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDTOModel projeto) {
		this.projeto = projeto;
	}
}
