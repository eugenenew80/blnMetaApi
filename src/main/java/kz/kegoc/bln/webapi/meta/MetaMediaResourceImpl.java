package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.entity.meta.Media;
import kz.kegoc.bln.entity.meta.dto.MediaDto;
import kz.kegoc.bln.service.meta.MediaService;


@RequestScoped
@Path("/meta/metaMedia")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaMediaResourceImpl {

	@GET 
	public Response getAll() {
		List<MediaDto> list = service.findAll()
			.stream()
			.map( it-> mapper.getMapper().map(it, MediaDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<MediaDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Media entity = service.findById(id);
		return Response.ok()
			.entity(mapper.getMapper().map(entity, MediaDto.class))
			.build();		
	}

	
	@POST
	public Response create(MediaDto entityDto) {
		Media newEntity = service.create(mapper.getMapper().map(entityDto, Media.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MediaDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MediaDto entityDto ) {
		Media newEntity = service.update(mapper.getMapper().map(entityDto, Media.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, MediaDto.class))
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
	private MediaService service;

	@Inject
	private BeanMapper mapper;
}
