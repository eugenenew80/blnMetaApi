package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;

import kz.kegoc.bln.entity.meta.Dict;
import kz.kegoc.bln.entity.meta.dto.DictDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.meta.DictService;


@RequestScoped
@Path("/meta/metaDict")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaDictResourceImpl {
	
	public MetaDictResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/meta/DictDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<DictDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, DictDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<DictDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Dict entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, DictDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Dict entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, DictDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Dict entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, DictDto.class))
			.build();
	}

	
	@POST
	public Response create(DictDto accountingTypeDto) {
		Dict newEntity = service.create(mapper.map(accountingTypeDto, Dict.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, DictDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, DictDto accountingTypeDto ) {
		Dict newEntity = service.update(mapper.map(accountingTypeDto, Dict.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, DictDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private DictService service;
	private DozerBeanMapper mapper;
}
