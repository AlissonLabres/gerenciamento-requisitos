package br.com.backend.requisitos.dto.interfaces;

public class ArquivoDTOInteface {

	private byte[] arquivo;

	public ArquivoDTOInteface(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
}
