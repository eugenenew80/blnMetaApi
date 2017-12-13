package kz.kegoc.bln.webapi.meta;

import kz.kegoc.bln.entity.meta.DictGroup;
import kz.kegoc.bln.entity.meta.dto.DictGroupDto;
import kz.kegoc.bln.service.meta.DictGroupService;
import org.dozer.DozerBeanMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("/meta/metaDictGroup")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaDictGroupResourceImpl {
	
	public MetaDictGroupResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/meta/DictGroupDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll() {
		List<DictGroupDto> list = service.findAll()
			.stream()
			.map( it-> mapper.map(it, DictGroupDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
			.entity(new GenericEntity<Collection<DictGroupDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		DictGroup entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, DictGroupDto.class))
			.build();		
	}

	
	@POST
	public Response create(DictGroupDto dictGroupDto) {
		DictGroup newEntity = service.create(mapper.map(dictGroupDto, DictGroup.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, DictGroupDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, DictGroupDto dictGroupDto ) {
		DictGroup newEntity = service.update(mapper.map(dictGroupDto, DictGroup.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, DictGroupDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private DictGroupService service;
	private DozerBeanMapper mapper;
}
