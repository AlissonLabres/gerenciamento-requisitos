package br.com.backend.requisitos.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.backend.requisitos.entity.Atividade;
import br.com.backend.requisitos.entity.Requisito;

public class RequisitoDetalhadoDTO {
	private Integer id;
	private String descricao;
	private String importancia;
	private String fonte;
	private String categoria;
	private ProjetoDTO projeto;
	private IntegranteDTO integrante;
	private List<AtividadeDTO> atividades;

	public RequisitoDetalhadoDTO() {
	}

	public RequisitoDetalhadoDTO(Requisito requisito) {
		id = requisito.getId();
		descricao = requisito.getDescricao();
		importancia = requisito.getImportancia().getValue();
		fonte = requisito.getFonte();
		categoria = requisito.getCategoria().getValue();
		projeto = new ProjetoDTO(requisito.getProjeto());
		integrante = new IntegranteDTO(requisito.getIntegrante());
		atividades = listaAtividades(requisito.getAtividades());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImportancia() {
		return importancia;
	}

	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ProjetoDTO getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDTO projeto) {
		this.projeto = projeto;
	}

	public IntegranteDTO getIntegrante() {
		return integrante;
	}

	public void setIntegrante(IntegranteDTO integrante) {
		this.integrante = integrante;
	}

	public List<AtividadeDTO> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeDTO> atividades) {
		this.atividades = atividades;
	}

	private List<AtividadeDTO> listaAtividades(List<Atividade> atividades) {
		if (atividades.isEmpty()) {
			return null;
		}
		List<AtividadeDTO> atividadesDTO = new ArrayList<AtividadeDTO>();
		for (Atividade atividade : atividades) {
			atividadesDTO.add(new AtividadeDTO(atividade));
		}
		return atividadesDTO;
	}
}
