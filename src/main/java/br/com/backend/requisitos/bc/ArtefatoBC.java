package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.ArtefatoDAO;
import br.com.backend.requisitos.dao.CasoDeUsoDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.RequisitoDAO;
import br.com.backend.requisitos.dto.interfaces.ArtefatoDTOInterface;
import br.com.backend.requisitos.dto.model.ArtefatoDTOModel;
import br.com.backend.requisitos.entity.Artefato;
import br.com.backend.requisitos.entity.CasoDeUso;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;
import br.com.backend.requisitos.utils.Util;

public class ArtefatoBC extends AbstractBusiness<Artefato, Integer>{
	
	@Inject
	private ArtefatoDAO artefatoDAO;
	
	@Inject
	private RequisitoDAO requisitoDAO;

	@Inject 
	private CasoDeUsoDAO casoDeUsoDAO;

	@Inject
	private IntegranteDAO integranteDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;

	@Transactional
	public void create(
		Integer idUsuario,
		Integer idProjeto,
		ArtefatoDTOInterface a
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");
	
			Artefato artefato = new Artefato();
			artefato.setInclusao(Util.logger(criador.getId()));
			artefato.setProjeto(projeto);
			artefato.setDescricao(a.getDescricao());
			artefato.setNome(a.getNome());
			
			if(a.getIdCasoDeUso() == null && a.getIdRequisito() == null) {
				throw new Exception("Requisito e Caso de uso não encontrado");
			}

			if(a.getIdRequisito() != null) {
				Requisito requisito = requisitoDAO.find(idProjeto, a.getIdRequisito());
				if (requisito == null)
					throw new Exception("Requisito não encontrado");

				artefato.setRequisito(requisito);
			}
			
			if(a.getIdCasoDeUso() != null) {
				CasoDeUso casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, a.getIdCasoDeUso());
				if(casoDeUso == null)
					throw new Exception("Caso de uso não encontrado");

				artefato.setCasoDeUso(casoDeUso);
			}

			artefatoDAO.create(artefato);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ArtefatoDTOModel> listar(
		Integer idUsuario,
		Integer idProjeto
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
			for (Artefato artefato : artefatoDAO.list(idProjeto)) {
				if(artefato.getArquivo() != null) {
					artefatosModel.add(
						new ArtefatoDTOModel(
							artefato.getNome(),
							artefato.getDescricao(),
							artefato.getRequisito() != null ? artefato.getRequisito().getId() : null,
							artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId() : null,
							artefato.getArquivo().getId() ,
							artefato.getId()
						)
					);
				}
			}
			
			return artefatosModel;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<ArtefatoDTOModel> listarPorRequisito(
			Integer idUsuario,
		Integer idProjeto
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
			for (Artefato artefato : artefatoDAO.listRequisito(idProjeto)) {
				if(artefato.getArquivo() != null) {
					artefatosModel.add(
						new ArtefatoDTOModel(
							artefato.getNome(),
							artefato.getDescricao(),
							artefato.getRequisito() != null ? artefato.getRequisito().getId() : null,
							artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId() : null,
							artefato.getArquivo().getId() ,
							artefato.getId()
						)
					);
				}
			}
			
			return artefatosModel;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ArtefatoDTOModel> listarPorCasoDeUso(
		Integer idUsuario,
		Integer idProjeto
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			List<ArtefatoDTOModel> artefatosModel = new ArrayList<>();
			for (Artefato artefato : artefatoDAO.listCasoDeUso(idProjeto)) {
				if(artefato.getArquivo() != null) {
					artefatosModel.add(
						new ArtefatoDTOModel(
							artefato.getNome(),
							artefato.getDescricao(),
							artefato.getRequisito() != null ? artefato.getRequisito().getId() : null,
							artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId() : null,
							artefato.getArquivo().getId() ,
							artefato.getId()
						)
					);
				}
			}
			
			return artefatosModel;
		} catch (Exception e) {
			throw e;
		}
	}

	public ArtefatoDTOModel buscaEspecifica(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if(artefato == null)
				throw new Exception("Artefato não encontrado");
			
			if(artefato.getArquivo() == null)
				throw new Exception("Artefato não contém arquivo anexado");
			
			return new ArtefatoDTOModel(
				artefato.getNome(),
				artefato.getDescricao(),
				artefato.getRequisito() != null ? artefato.getRequisito().getId() : null,
				artefato.getCasoDeUso() != null ? artefato.getCasoDeUso().getId() : null,
				artefato.getArquivo().getId() ,
				artefato.getId()
			);
		} catch (Exception e) {
			throw e;
		}
	}	
	
	@Transactional
	public void alterar(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato,
		ArtefatoDTOInterface a
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if(artefato == null)
				throw new Exception("Atividade não encontrada");

			artefato.setAlteracao(Util.logger(criador.getId()));
			artefato.setProjeto(projeto);
			artefato.setDescricao(a.getDescricao());
			artefato.setNome(a.getNome());
			
			if(a.getIdCasoDeUso() == null && a.getIdRequisito() == null) {
				throw new Exception("Requisito e Caso de uso não encontrado");
			}

			Requisito requisito = null;
			if(a.getIdRequisito() != null) {				
				requisito = requisitoDAO.find(idProjeto, a.getIdRequisito());
				artefato.setRequisito(requisito);
			}
			

			CasoDeUso casoDeUso = null;
			if(a.getIdCasoDeUso() != null) {
				casoDeUso = casoDeUsoDAO.findByIdProjetoAndICasoDeUso(idProjeto, a.getIdCasoDeUso());				
				artefato.setCasoDeUso(casoDeUso);
			}

			artefatoDAO.mergeHalf(idArtefato, artefato);
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional
	public void excluir(Integer idUsuario, Integer idProjeto, Integer idArtefato) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");
			
			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");
			
			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if(artefato == null)
				throw new Exception("Artefato não encontrada");

			artefatoDAO.remove(idArtefato);
		} catch (Exception e) {
			throw e;
		}
	}
}
