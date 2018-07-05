package br.com.backend.requisitos.dto.model;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.requisitos.dto.AtividadeDTO;
import br.com.backend.requisitos.dto.CasoDeUsoDTO;
import br.com.backend.requisitos.dto.ProjetoDTO;
import br.com.backend.requisitos.dto.RequisitoDTO;
import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;

public class IntegranteDTODetalhadoModel extends IntegranteDTOModel {
	private ProjetoDTO projeto;
	private List<RequisitoDTO> requisitos;
	private List<CasoDeUsoDTO> casosDeUso;
	private List<AtividadeDTO> atividades;

	public IntegranteDTODetalhadoModel() {
	}

	public IntegranteDTODetalhadoModel(Integer id, Integer idUsuario, String perfilIntegrante, String nome,
			Projeto projeto, List<Requisito> requisitos, List<CasoDeUso> casosDeUso, List<Atividade> atividades) {
		super(perfilIntegrante, nome, id);
		this.projeto = new ProjetoDTO(projeto);
		this.requisitos = listarRequisitos(requisitos);
		this.casosDeUso = listarCasosDeUso(casosDeUso);
		this.atividades = listarAtividades(atividades);
	}

	public ProjetoDTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDTO projeto) {
		this.projeto = projeto;
	}

	public List<RequisitoDTO> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<RequisitoDTO> requisitos) {
		this.requisitos = requisitos;
	}

	public List<CasoDeUsoDTO> getCasosDeUso() {
		return casosDeUso;
	}

	public void setCasosDeUso(List<CasoDeUsoDTO> casosDeUso) {
		this.casosDeUso = casosDeUso;
	}

	public List<AtividadeDTO> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeDTO> atividades) {
		this.atividades = atividades;
	}

	private List<RequisitoDTO> listarRequisitos(List<Requisito> listaRequisitos) {
		List<RequisitoDTO> requisitoDTOs = new ArrayList<RequisitoDTO>();
		for (Requisito requisito : listaRequisitos) {
			requisitoDTOs.add(new RequisitoDTO(requisito));
		}
		return requisitoDTOs;
	}

	private List<CasoDeUsoDTO> listarCasosDeUso(List<CasoDeUso> listaCasosDeUso) {
		List<CasoDeUsoDTO> casoDeUsoDTOs = new ArrayList<CasoDeUsoDTO>();
		for (CasoDeUso casoDeUso : listaCasosDeUso) {
			casoDeUsoDTOs.add(new CasoDeUsoDTO(casoDeUso));
		}
		return casoDeUsoDTOs;
	}

	private List<AtividadeDTO> listarAtividades(List<Atividade> listaAtividades) {
		List<AtividadeDTO> atividadeDTOs = new ArrayList<AtividadeDTO>();
		for (Atividade atividade : listaAtividades) {
			atividadeDTOs.add(new AtividadeDTO(atividade));
		}
		return atividadeDTOs;
	}
}
