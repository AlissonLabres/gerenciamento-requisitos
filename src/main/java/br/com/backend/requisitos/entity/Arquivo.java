package br.com.backend.requisitos.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@NamedQueries({
	@NamedQuery(
		name = "Arquivo.findAll",
		query = "SELECT arq FROM Arquivo arq "
				+ "INNER JOIN arq.idArtefato as art "
				+ "INNER JOIN art.requisito as req "
				+ "INNER JOIN req.projeto as p "
				+ "WHERE p.id = :idProjeto"
	),
	@NamedQuery(
		name = "Arquivo.findById",
		query = "SELECT a FROM Arquivo a "
				+ "INNER JOIN a.idArtefato as ar "
				+ "INNER JOIN ar.projeto as p "
				+ "WHERE p.id = :idProjeto "
				+ "AND a.id = :idArquivo"
	)
})
@Entity
public class Arquivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "arquivo_id", nullable = false)
	private Integer id;
	
	@Lob
    @Column(name = "arquivo_arquivo", nullable = true, columnDefinition = "mediumblob")
	private byte[] arquivo;
	
	@OneToOne
	@Column(name = "arquivo_artefato", nullable = false)
	private Artefato idArtefato;
	
	public Arquivo() {
	}

	public Arquivo(Integer id, byte[] arquivo, Artefato idArtefato) {
		this.id = id;
		this.arquivo = arquivo;
		this.idArtefato = idArtefato;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Artefato getIdArtefato() {
		return idArtefato;
	}

	public void setIdArtefato(Artefato idArtefato) {
		this.idArtefato = idArtefato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(arquivo);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idArtefato == null) ? 0 : idArtefato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arquivo other = (Arquivo) obj;
		if (!Arrays.equals(arquivo, other.arquivo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idArtefato == null) {
			if (other.idArtefato != null)
				return false;
		} else if (!idArtefato.equals(other.idArtefato))
			return false;
		return true;
	}
}
