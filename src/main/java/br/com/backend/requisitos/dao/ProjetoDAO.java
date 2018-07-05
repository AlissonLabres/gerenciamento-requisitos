package br.com.backend.requisitos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demoiselle.jee.crud.AbstractDAO;

import br.com.backend.requisitos.entity.Projeto;

public class ProjetoDAO extends AbstractDAO<Projeto, Integer> {
	@PersistenceContext
	protected EntityManager entityManager;

	public ProjetoDAO() {
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void create(Projeto projeto) {
		try {
			getEntityManager().persist(projeto);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Projeto> list(Integer idUsuario) {
		try {
			return

			getEntityManager().createNamedQuery("Projeto.findByAll", Projeto.class).setParameter("idUsuario", idUsuario)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public Projeto find(Integer idUsuario, Integer idProjeto) {
		try {
			List<Projeto> projeto = getEntityManager().createNamedQuery("Projeto.find", Projeto.class)
					.setParameter("idProjeto", idProjeto).setParameter("idUsuario", idUsuario).getResultList();

			return projeto.size() > 0 ? (Projeto) projeto.get(0) : null;
		} catch (Exception error) {
			throw error;
		}
	}
}
