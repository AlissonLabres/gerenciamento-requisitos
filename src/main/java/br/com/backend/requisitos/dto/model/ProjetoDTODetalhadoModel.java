package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.backend.requisitos.dto.CasoDeUsoDTO;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Requisito;

public class ProjetoDTODetalhadoModel extends ProjetoDTOModel {
	private List<IntegranteDTOModel> integrantes;
	private List<RequisitoDTOModel> requisito;
	private List<CasoDeUsoDTO> casosDeUso;

	public ProjetoDTODetalhadoModel() {
	}

	public ProjetoDTODetalhadoModel(Integer id, String nome, Calendar dataInicio, Calendar dataFim,
			List<Requisito> requisitos, List<CasoDeUso> casosDeUso, List<Integrante> integrantes) {
		super(id, nome, dataInicio, dataFim);
		requisito = listarRequisitos(requisitos);
		this.casosDeUso = listarCasosDeUso(casosDeUso);
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

	public List<CasoDeUsoDTO> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUsoDTO> casosDeUso) {
		this.casosDeUso = casosDeUso;
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

	private List<CasoDeUsoDTO> listarCasosDeUso(List<CasoDeUso> listaCasosDeUso) {
		if (listaCasosDeUso.isEmpty()) {
			return null;
		}
		List<CasoDeUsoDTO> casoDeUsoDTOs = new ArrayList<CasoDeUsoDTO>();
		for (CasoDeUso casoDeUso : listaCasosDeUso) {
			casoDeUsoDTOs.add(new CasoDeUsoDTO(casoDeUso));
		}
		return casoDeUsoDTOs;
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
