package br.com.backend.requisitos.dto.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Calendar;

public class ProjetoDTOInterface {
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataInicio;
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	private Calendar dataFim;

	public ProjetoDTOInterface() {
	}

	public ProjetoDTOInterface(String nome, Calendar dataInicio, Calendar dataFim) {
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
}
