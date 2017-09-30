package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;

import kz.kegoc.bln.entity.meta.Module;
import kz.kegoc.bln.entity.meta.dto.ModuleDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.meta.ModuleService;


@RequestScoped
@Path("/meta/metaModule")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaModuleResourceImpl {
	
	public MetaModuleResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/meta/ModuleDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<ModuleDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, ModuleDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<ModuleDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Module entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, ModuleDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Module entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, ModuleDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Module entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, ModuleDto.class))
			.build();
	}

	
	@POST
	public Response create(ModuleDto accountingTypeDto) {
		Module newEntity = service.create(mapper.map(accountingTypeDto, Module.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, ModuleDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, ModuleDto accountingTypeDto ) {
		Module newEntity = service.update(mapper.map(accountingTypeDto, Module.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, ModuleDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private ModuleService service;
	private DozerBeanMapper mapper;
}
