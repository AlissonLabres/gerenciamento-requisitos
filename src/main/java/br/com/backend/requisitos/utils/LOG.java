package br.com.backend.requisitos.utils;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LOG {
	
	private Integer idIntegrate;
	
	@JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
	private Calendar data;
	
	public LOG(Integer idIntegrante, Calendar data) {
		this.idIntegrate = idIntegrante;
		this.data = data;
	}

	public Integer getIdIntegrate() {
		return idIntegrate;
	}

	public void setIdIntegrate(Integer idIntegrate) {
		this.idIntegrate = idIntegrate;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
}
