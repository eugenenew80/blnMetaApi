package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;

import kz.kegoc.bln.entity.meta.Media;
import kz.kegoc.bln.entity.meta.dto.MediaDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.meta.MediaService;


@RequestScoped
@Path("/meta/metaMedia")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaMediaResourceImpl {
	
	public MetaMediaResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/meta/MediaDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<MediaDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, MediaDto.class) )
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
			.entity(mapper.map(entity, MediaDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Media entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, MediaDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Media entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, MediaDto.class))
			.build();
	}

	
	@POST
	public Response create(MediaDto entityDto) {
		Media newEntity = service.create(mapper.map(entityDto, Media.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, MediaDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, MediaDto entityDto ) {
		Media newEntity = service.update(mapper.map(entityDto, Media.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, MediaDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private MediaService service;
	private DozerBeanMapper mapper;
}
