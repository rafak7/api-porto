package br.com.fiap.portoapi.controller;


import java.net.URI;
import java.util.List;

import br.com.fiap.portoapi.model.Porto;
import br.com.fiap.portoapi.model.repository.PortoRepository;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.UriBuilder;


@Path("/porto")
public class PortoResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {

		List<Porto> retorno = PortoRepository.findAll();

		ResponseBuilder response = Response.ok();
		response.entity(retorno);

		return response.build();

	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Porto porto) {
		
		return Response.status(Response.Status.CREATED)
				.entity("Salvo com sucesso")
				.build();
		
	}

	
	@DELETE
	@Path("/{ID_CLIENTE}")
	public Response delete (@PathParam("ID_CLIENTE") Long PortoId ) {
	
		if (PortoRepository.delete(PortoId) ) {
			ResponseBuilder response = Response.noContent();	
			return response.build();
		}else {
			System.out.println("Nao foi possivel remover: " + PortoId);
			ResponseBuilder response = Response.notModified();
			return response.build();
		}
		
	}
	
	
	@PUT
	@Path("/{ID_CLIENTE}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("ID_CLIENTE") Long id, @Valid Porto porto ) {
		
		Porto velho = PortoRepository.findById(id);
		Porto novo = null;
		
		if(velho == null || velho.getId() != porto.getId() ) {
			novo = PortoRepository.save(porto);
			
			final URI portoUri = UriBuilder
					.fromResource(PortoResource.class)
					.path("/porto/{ID_CLIENTE}")
					.build(novo.getId());
			
			ResponseBuilder response = Response.created(portoUri);
			response.entity(novo);
			
			return response.build();
			
		}
		
		
		novo = PortoRepository.update(porto);
		
		return Response.ok(novo).build();

		
	}
	

	@GET
	@Path("/{ID_CLIENTE}")
	public Response findById(@PathParam("ID_CLIENTE") Long portoId ) {
		Porto porto = PortoRepository.findById(portoId);
		
		if(porto!=null) {
			ResponseBuilder response = Response.ok();
			response.entity(porto);
			
			return response.build();
		}else {
			ResponseBuilder response = Response.noContent();
			return response.build();
		}
		
	}
	
	
	
	
}
