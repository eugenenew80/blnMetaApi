package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.adm.dto.UserDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.adm.UserService;


@RequestScoped
@Path("/adm/admUser")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UserResourceImpl {
	
	public UserResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/UserDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<UserDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, UserDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<UserDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		User entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, UserDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		User entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, UserDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		User entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, UserDto.class))
			.build();
	}

	
	@POST
	public Response create(UserDto accountingTypeDto) {
		User newEntity = service.create(mapper.map(accountingTypeDto, User.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, UserDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, UserDto accountingTypeDto ) {
		User newEntity = service.update(mapper.map(accountingTypeDto, User.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, UserDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Path("/{userId : \\d+}/admUserRole")
	public UserRoleResourceImpl getModules(@PathParam("userId") Long id) {
		return userRoleResource;
	}
	
	
	@Inject private UserService service;
	@Inject private UserRoleResourceImpl userRoleResource;
	private DozerBeanMapper mapper;
}
