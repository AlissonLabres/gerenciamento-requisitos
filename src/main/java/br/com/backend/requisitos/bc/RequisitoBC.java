package br.com.backend.requisitos.bc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.demoiselle.jee.crud.AbstractBusiness;

import br.com.backend.requisitos.dao.IntegranteDAO;
import br.com.backend.requisitos.dao.ProjetoDAO;
import br.com.backend.requisitos.dao.RequisitoDAO;
import br.com.backend.requisitos.dto.interfaces.RequisitoDTOInteface;
import br.com.backend.requisitos.dto.model.RequisitoDTODetalhadoModel;
import br.com.backend.requisitos.dto.model.RequisitoDTOModel;
import br.com.backend.requisitos.entity.Integrante;
import br.com.backend.requisitos.entity.Projeto;
import br.com.backend.requisitos.entity.Requisito;
import br.com.backend.requisitos.enums.CategoriaRequisito;
import br.com.backend.requisitos.enums.ImportanciaRequisito;
import br.com.backend.requisitos.utils.Util;

public class RequisitoBC extends AbstractBusiness<Requisito, Integer> {

	@Inject
	private RequisitoDAO requisitoDAO;

	@Inject
	private ProjetoDAO projetoDAO;

	@Inject
	private IntegranteDAO integranteDAO;

	public RequisitoBC() {
	}

	@Transactional
	public void create(Integer idUsuario, Integer idProjeto, RequisitoDTOInteface r) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null) {
				throw new Exception("Projeto não encontrado");
			}
			Integrante integrante = integranteDAO.findByIdUsuarioAndIdProjeto(idUsuario, idProjeto);
			if (integrante == null)
				throw new Exception("Usuario não encontrado");

			Requisito requisito = new Requisito();
			Calendar date = Calendar.getInstance();

			requisito.setDataInclusao(date);
			requisito.setIdRequisito(r.getIdRequisito());
			requisito.setNome(r.getNome());
			requisito.setDescricao(r.getDescricao());
			requisito.setImportancia(ImportanciaRequisito.valueString(r.getImportancia()));
			requisito.setCategoria(CategoriaRequisito.valueString(r.getCategoria()));
			requisito.setFonte(r.getFonte());

			requisito.setProjeto(projeto);
			requisito.setIntegrante(integrante);

			List<Requisito> projetoHasRequisitos = projeto.getRequisitos();
			projetoHasRequisitos.add(requisito);
			projeto.setRequisitos(projetoHasRequisitos);

			List<Requisito> integranteHasRequisitos = integrante.getRequisitos();
			integranteHasRequisitos.add(requisito);
			integrante.setRequisitos(integranteHasRequisitos);

			requisitoDAO.create(requisito);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<RequisitoDTOModel> listarTudo(Integer idUsuario, Integer idProjeto) {
		try {
			List<RequisitoDTOModel> requisitosDTO = new ArrayList<RequisitoDTOModel>();
			for (Requisito r : requisitoDAO.list(idUsuario, idProjeto)) {
				requisitosDTO.add(new RequisitoDTOModel(r.getId(), r.getIdRequisito().toString(), r.getNome(),
						r.getDescricao(), r.getImportancia().getValue(), r.getFonte(), r.getCategoria().getValue()));
			}

			return requisitosDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	public RequisitoDTODetalhadoModel buscarPorEspecifico(Integer idUsuario, Integer idProjeto, Integer idRequisito)
			throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			Requisito requisito = requisitoDAO.find(idUsuario, idProjeto, idRequisito);
			if (requisito == null)
				throw new Exception("Requisito não encontrado");

			Integrante integrante = (Integrante) integranteDAO.find(requisito.getIntegrante().getId());
			if (integrante == null)
				throw new Exception("Usuario não encontrado");

			return new RequisitoDTODetalhadoModel(requisito.getId(), requisito.getIdRequisito().toString(),
					requisito.getNome(), requisito.getDescricao(), requisito.getImportancia().getValue(),
					requisito.getFonte(), requisito.getCategoria().getValue(),
					requisito.getIntegrante().getUsuario().getId(), integrante, projeto);
		} catch (Exception e) {
			throw e;
		}
	}

	public void alterar(Integer idUsuario, Integer idProjeto, Integer idRequisito, RequisitoDTOInteface r)
			throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			Requisito requisito = requisitoDAO.find(idUsuario, idProjeto, idRequisito);
			if (requisito == null)
				throw new Exception("Requisito não encontrado");

			requisito.setDataAlteracao(Util.currentDate());

			requisitoDAO.mergeHalf(idRequisito, requisito);
		} catch (Exception e) {
			throw e;
		}
	}

	public void remover(Integer idUsuario, Integer idProjeto, Integer idRequisito) throws Exception {
		try {
			Projeto projeto = (Projeto) projetoDAO.find(idProjeto);
			if (projeto == null)
				throw new Exception("Projeto não encontrado");

			Requisito requisito = requisitoDAO.find(idUsuario, idProjeto, idRequisito);
			if (requisito == null)
				throw new Exception("Requisito não encontrado");

			requisitoDAO.remove(idRequisito);
		} catch (Exception e) {
			throw e;
		}
	}
}
