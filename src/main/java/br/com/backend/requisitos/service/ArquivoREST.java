package br.com.backend.requisitos.service;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.demoiselle.jee.crud.AbstractREST;

import br.com.backend.requisitos.auth.Auth;
import br.com.backend.requisitos.bc.ArquivoBC;
import br.com.backend.requisitos.entity.Arquivo;
import br.com.backend.requisitos.utils.Util;

@Path("usuario/{idUsuario}/projeto/{idProjeto}/artefato/{idArtefato}/arquivo")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ArquivoREST extends AbstractREST<Arquivo, Integer>{
		private static final Logger LOG = Logger.getLogger(ArtefatoREST.class.getName());

		@POST
		@Auth
		@Consumes({ MediaType.MULTIPART_FORM_DATA })
		public Response anexar(
			@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto,
			@PathParam("idArtefato") Integer idArtefato,
			byte[] a
		) {
			try {
				((ArquivoBC) bc).anexar(idUsuario, idProjeto, idArtefato, a);
				return Response.ok().build();
			} catch (Exception e) {
				return Util.handlerError(e, LOG);
			}
		}
		
		@GET
		@Path("/{idArquivo}")
		@Auth
		@Produces({ MediaType.MULTIPART_FORM_DATA })
		public Response buscar(
			@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto,
			@PathParam("idArtefato") Integer idArtefato,
			@PathParam("idArquivo") Integer idArquivo
		) {
			try {
				return Response.ok(((ArquivoBC) bc).buscarArquivoEspecifico(idUsuario, idProjeto, idArtefato, idArquivo))
						.build();
			} catch (Exception e) {
				return Util.handlerError(e, LOG);
			}
		}

		@PUT
		@Path("/{idArquivo}")
		@Auth
		@Consumes({ MediaType.MULTIPART_FORM_DATA })
		public Response alterar(
			@PathParam("idUsuario") Integer idUsuario,
			@PathParam("idProjeto") Integer idProjeto,
			@PathParam("idArtefato") Integer idArtefato,
			@PathParam("idArquivo") Integer idArquivo,
			byte[] a
		) {
			try {
				((ArquivoBC) bc).alterar(idUsuario, idProjeto, idArtefato, idArquivo, a);
				return Response.ok().build();
			} catch (Exception e) {
				return Util.handlerError(e, LOG);
			}
		}
}
