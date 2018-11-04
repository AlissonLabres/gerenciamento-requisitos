package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Arquivo;

public class ArquivoDAO extends AbstractDAO<Arquivo, Integer>{
	@PersistenceContext
	protected EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(Arquivo arquivo) {
		try {
			getEntityManager().persist(arquivo);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Arquivo> list(Integer idProjeto) {
		try {
			return getEntityManager().createNamedQuery("Arquivo.findAll", Arquivo.class)
					.setParameter("idProjeto", idProjeto)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Arquivo findById(Integer idProjeto, Integer idArquivo) {
		try {
			List<Arquivo> arquivos = getEntityManager().createNamedQuery("Arquivo.findById", Arquivo.class)
					.setParameter("idProjeto", idProjeto)
					.setParameter("idArquivo", idArquivo)
					.getResultList();

			return arquivos.size() > 0 ? (Arquivo) arquivos.get(0) : null;
		} catch (Exception e) {
			throw e;
		}
	}
}
