package br.com.backend.requisitos.dto.model;

import br.com.backend.requisitos.dto.interfaces.ArtefatoDTOInterface;

public class ArtefatoDTOModel extends ArtefatoDTOInterface {


	private Integer id;
	
	private Integer idArquivo;

	public ArtefatoDTOModel(String nome, String descricao, Integer idRequisito, Integer idCasoDeUso, Integer idArquivo, Integer id) {
		super(nome, descricao, idRequisito, idCasoDeUso);
		this.idArquivo = idArquivo;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
	}
}
