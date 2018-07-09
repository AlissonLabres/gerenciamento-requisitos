package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Requisito;

public class ProjetoDTODetalhadoModel extends ProjetoDTOModel {
	private String perfilIntegranteProjeto;
	private List<IntegranteDTOModel> integrantes;
	private List<RequisitoDTOModel> requisito;
	private List<AtividadeDTOModel> atividades;

	public ProjetoDTODetalhadoModel() {
	}

	public ProjetoDTODetalhadoModel(Integer id, String nome, Calendar dataInicio, Calendar dataFim, Integrante integranteProjeto, 
			List<Requisito> requisitos, List<Integrante> integrantes) {
		super(id, nome, dataInicio, dataFim);
		this.perfilIntegranteProjeto = setIntegrante(integranteProjeto);
		this.requisito = listarRequisitos(requisitos);
		this.atividades = listarAtividades(requisitos);
		this.integrantes = listarIntegrantes(integrantes);
	}

	public List<IntegranteDTOModel> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<IntegranteDTOModel> integrantes) {
		this.integrantes = integrantes;
	}

	public List<RequisitoDTOModel> getRequisito() {
		return requisito;
	}

	public void setRequisito(List<RequisitoDTOModel> requisito) {
		this.requisito = requisito;
	}

	public List<AtividadeDTOModel> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeDTOModel> atividades) {
		this.atividades = atividades;
	}

	public String getPerfilIntegranteProjeto() {
		return perfilIntegranteProjeto;
	}

	public void setPerfilIntegranteProjeto(String perfilIntegranteProjeto) {
		this.perfilIntegranteProjeto = perfilIntegranteProjeto;
	}

	private String setIntegrante(Integrante i) {
		return i.getPerfilIntegranteProjeto().getValue();
	}

	private List<RequisitoDTOModel> listarRequisitos(List<Requisito> listaRequisitos) {
		if (listaRequisitos.isEmpty()) {
			return null;
		}
		List<RequisitoDTOModel> requisitosDTO = new ArrayList<RequisitoDTOModel>();
		for (Requisito r : listaRequisitos) {
			requisitosDTO.add(new RequisitoDTOModel(r.getId(), r.getIdRequisito().toString(), r.getNome(),
					r.getDescricao(), r.getImportancia().getValue(), r.getFonte(), r.getCategoria().getValue()));
		}

		return requisitosDTO;
	}

	private List<AtividadeDTOModel> listarAtividades(List<Requisito> requisitos) {
		if (requisitos.isEmpty())
			return null;

		List<AtividadeDTOModel> atividadeDTO = new ArrayList<AtividadeDTOModel>();
		for(Requisito r : requisitos) {
			for(Atividade a : r.getAtividades()) {
				atividadeDTO.add(
					new AtividadeDTOModel(
						a.getId(),
						a.getNome(),
						a.getDescricao(),
						a.getStatus().getValue(),
						a.getDataInicio(),
						a.getDataFim(),
						a.getDataConclusao(),
						a.getCriador(),
						a.getDesenvolvedores().get(a.getDesenvolvedores().size() - 1)
					)
				);
			}
		}

		return atividadeDTO;
	}

	private List<IntegranteDTOModel> listarIntegrantes(List<Integrante> listaIngrantes) {
		if (listaIngrantes.isEmpty()) {
			return null;
		}
		List<IntegranteDTOModel> integrantesDTOModel = new ArrayList<IntegranteDTOModel>();
		for (Integrante i : listaIngrantes) {
			integrantesDTOModel.add(new IntegranteDTOModel(i.getPerfilIntegranteProjeto().getValue(),
					i.getUsuario().getNome(), i.getId()));
		}

		return integrantesDTOModel;
	}
}
