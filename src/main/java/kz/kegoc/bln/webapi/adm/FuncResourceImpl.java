package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;
import kz.kegoc.bln.entity.adm.Func;
import kz.kegoc.bln.entity.adm.dto.FuncDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.adm.FuncService;


@RequestScoped
@Path("/adm/admFunc")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class FuncResourceImpl {
	
	public FuncResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/FuncDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<FuncDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, FuncDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<FuncDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Func entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, FuncDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Func entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, FuncDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Func entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, FuncDto.class))
			.build();
	}

	
	@POST
	public Response create(FuncDto accountingTypeDto) {
		Func newEntity = service.create(mapper.map(accountingTypeDto, Func.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, FuncDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, FuncDto accountingTypeDto ) {
		Func newEntity = service.update(mapper.map(accountingTypeDto, Func.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, FuncDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private FuncService service;
	private DozerBeanMapper mapper;
}
