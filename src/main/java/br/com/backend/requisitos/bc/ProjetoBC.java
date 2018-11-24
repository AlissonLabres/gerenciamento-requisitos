package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.LogDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.UsuarioDAO;
import br.com.backend.requisitos.dto.interfaces.ProjetoDTOInterface;
import br.com.backend.requisitos.dto.model.ProjetoDTODetalhadoModel;
import br.com.backend.requisitos.dto.model.ProjetoDTOModel;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Log;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Usuario;
import br.com.backend.requisitos.enums.PerfilIntegranteProjeto;
import br.com.backend.requisitos.enums.Status;
import br.com.backend.requisitos.utils.Util;

public class ProjetoBC extends AbstractBusiness<Projeto, Integer> {

	@Inject
	private ProjetoDAO projetoDAO;

	@Inject
	private IntegranteDAO integranteDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private LogDAO logDAO;

	public ProjetoBC() {
	}

	@Transactional
	public void create(ProjetoDTOInterface p, Integer idUsuario) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null)
				throw new Exception("Usuário não encontrado");

			Projeto projeto = new Projeto();
			Integrante integrante = new Integrante();

			projeto.setNome(p.getNome());
			projeto.setDataInicio(p.getDataInicio());
			projeto.setDataFim(p.getDataFim());
			
			Log logPersistido = logDAO.persist(Util.logger(usuario.getId(), "INCLUSÃO"));
			projeto.setInclusao(logPersistido);
			projeto.setStatus(Status.valueString(p.getStatus()));

			integrante.setUsuario(usuario);
			integrante.setPerfilIntegranteProjeto(PerfilIntegranteProjeto.GERENTE);
			
			Log logPersistidoIntegrante = logDAO.persist(Util.logger(usuario.getId(), "INCLUSÃO"));
			integrante.setInclusao(logPersistidoIntegrante);
			integrante.setProjeto(projeto);

			List<Integrante> integrantes = new ArrayList<Integrante>();
			integrantes.add(integrante);
			projeto.setIntegrantes(integrantes);

			projetoDAO.create(projeto);
			integranteDAO.create(integrante);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<ProjetoDTOModel> listarProjetos(Integer idUsuario) throws Exception {
		try {
			List<Projeto> projetos = projetoDAO.list(idUsuario);
			if (projetos == null)
				throw new Exception("Projetos não encontrados");

			List<ProjetoDTOModel> projetosDTO = new ArrayList<ProjetoDTOModel>();
			for (Projeto p : projetos) {
				projetosDTO.add(
					new ProjetoDTOModel(
						p.getId(),
						p.getNome(),
						p.getDataInicio(),
						p.getDataFim(),
						p.getStatus().getValue()
					)
				);
			}

			return projetosDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	public ProjetoDTODetalhadoModel buscarProjeto(Integer idUsuario, Integer idProjeto) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null)
				throw new Exception("Usuario não encontrado");

			Projeto projeto = projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			List<Integrante> integrantes = projeto.getIntegrantes();
			Integrante integranteProjeto = null;
			for (Integrante i : integrantes)
				if (i.getUsuario().getId().equals(idUsuario))
					integranteProjeto = new Integrante(i);

			return new ProjetoDTODetalhadoModel(
				projeto.getId(),
				projeto.getNome(),
				projeto.getDataInicio(),
				projeto.getDataFim(),
				integranteProjeto,
				projeto.getRequisitos(),
				projeto.getIntegrantes(),
				projeto.getCasosDeUso(),
				projeto.getArtefatos(),
				projeto.getStatus().getValue()
			);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void alterar(ProjetoDTOInterface p, Integer idUsuario, Integer idProjeto) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null)
				throw new Exception("Usuario não encontrado");

			Projeto projeto = projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			projeto.setNome(p.getNome());
			projeto.setDataInicio(p.getDataInicio());
			projeto.setDataFim(p.getDataFim());
			projeto.setStatus(Status.valueString(p.getStatus()));
			
			Log logPersistido = logDAO.persist(Util.logger(usuario.getId(), "ALTERAÇÃO"));
			projeto.setAlteracao(logPersistido);
			projeto.setStatus(Status.valueString(p.getStatus()));

			projetoDAO.mergeFull(projeto);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void excluir(Integer idUsuario, Integer idProjeto) throws Exception {
		try {
			Usuario usuario = (Usuario) usuarioDAO.find(idUsuario);
			if (usuario == null)
				throw new Exception("Usuario não encontrado");

			Projeto projeto = projetoDAO.find(idUsuario, idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			if (projeto.getIntegrantes().size() > 1)
				throw new Exception("Este projeto ainda contém integrantes.");
			
			if (projeto.getRequisitos().size() > 0)
				throw new Exception("Este projeto ainda contém requisitos");

			if (projeto.getCasosDeUso().size() > 0)
				throw new Exception("Este projeto ainda contém casos de uso");
			
			integranteDAO.remove(projeto.getIntegrantes().get(0).getId());
			projetoDAO.remove(idProjeto);
		} catch (Exception e) {
			throw e;
		}
	}
}
