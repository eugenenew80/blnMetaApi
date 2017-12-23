package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.cdi.mapper.BeanMapper;
import kz.kegoc.bln.entity.meta.Adm;
import kz.kegoc.bln.entity.meta.dto.AdmDto;
import kz.kegoc.bln.service.meta.AdmService;


@RequestScoped
@Path("/meta/metaAdm")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaAdmResourceImpl {

	@GET
	public Response getAll() {
		List<AdmDto> list = service.findAll()
			.stream()
			.map( it-> mapper.getMapper().map(it, AdmDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<AdmDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Adm entity = service.findById(id);
		return Response.ok()
			.entity(mapper.getMapper().map(entity, AdmDto.class))
			.build();		
	}

	
	@POST
	public Response create(AdmDto admDto) {
		Adm newEntity = service.create(mapper.getMapper().map(admDto, Adm.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, AdmDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, AdmDto admDto ) {
		Adm newEntity = service.update(mapper.getMapper().map(admDto, Adm.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, AdmDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject
	private AdmService service;

	@Inject
	private BeanMapper mapper;
}
