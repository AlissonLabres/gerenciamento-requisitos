package br.com.backend.requisitos.bc;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.ArquivoDAO;
import br.com.backend.requisitos.dao.ArtefatoDAO;
import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.entity.Arquivo;
import br.com.backend.requisitos.entity.Artefato;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;

@Default
public class ArquivoBC extends AbstractBusiness<Arquivo, Integer>{

	@Inject
	private ArquivoDAO arquivoDAO;
	
	@Inject
	private ArtefatoDAO artefatoDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;

	@Inject
	private IntegranteDAO integranteDAO;

	
	@Transactional
	public void anexar(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato,
		byte[] a
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if (artefato == null)
				throw new Exception("Artefato não encontrado");
			
			Arquivo arquivo = new Arquivo();
			arquivo.setArquivo(a);
			arquivo.setIdArtefato(artefato);
			final Arquivo arquivoPersistido = arquivoDAO.persist(arquivo);

			artefato.setArquivo(arquivoPersistido);
			artefatoDAO.mergeHalf(idArtefato, artefato);
		} catch (Exception e) {
			throw e;
		}
	}

	public byte[] buscarArquivoEspecifico(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato,
		Integer idArquivo
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if (artefato == null)
				throw new Exception("Artefato não encontrado");
			
			Arquivo arquivo = arquivoDAO.findById(idProjeto, idArquivo);
			if(arquivo == null)
				throw new Exception("Arquivo não encontrado");
			
			return arquivo.getArquivo();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional
	public void alterar(
		Integer idUsuario,
		Integer idProjeto,
		Integer idArtefato,
		Integer idArquivo,
		byte[] a
	) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) 
				throw new Exception("Projeto não encontrado");

			Integrante criador = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if(criador == null)
				throw new Exception("Usuário não encontrado");

			Artefato artefato = artefatoDAO.findById(idProjeto, idArtefato);
			if (artefato == null)
				throw new Exception("Artefato não encontrado");

			Arquivo arquivo = arquivoDAO.findById(idProjeto, idArquivo);
			if (arquivo == null)
				throw new Exception("Arquivo não encontrado");
			
			arquivo.setArquivo(a);
			arquivo.setIdArtefato(artefato);
			final Arquivo arquivoPersistido = arquivoDAO.mergeHalf(idArquivo, arquivo);

			artefato.setArquivo(arquivoPersistido);
			artefatoDAO.mergeHalf(idArtefato, artefato);
		} catch (Exception e) {
			throw e;
		}
	}
}
