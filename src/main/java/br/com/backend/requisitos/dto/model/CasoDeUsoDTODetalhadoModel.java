package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.entity.Integrante;

public class CasoDeUsoDTODetalhadoModel extends CasoDeUsoDTOModel {

	private IntegranteDTOModel integrante;

	public CasoDeUsoDTODetalhadoModel() {
		super();
	}

	public CasoDeUsoDTODetalhadoModel(Integer id, String nome, String escopo, String nivel, String preCondicao,
			String posCondicao, String cenarioPrincipal, String extensao, String atorPrincipal, Integrante integrante) {
		super(id, nome, escopo, nivel, preCondicao, posCondicao, cenarioPrincipal, extensao, atorPrincipal);
		this.integrante = new IntegranteDTOModel(integrante.getPerfilIntegranteProjeto().getValue(),
				integrante.getUsuario().getNome(), integrante.getId());
	}

	public IntegranteDTOModel getIntegrante() {
		return integrante;
	}

	public void setIntegrante(IntegranteDTOModel integrante) {
		this.integrante = integrante;
	}
}
