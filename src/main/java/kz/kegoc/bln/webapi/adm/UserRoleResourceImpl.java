package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.adm.UserRole;
import kz.kegoc.bln.entity.adm.UserRoleId;
import kz.kegoc.bln.entity.adm.dto.UserRoleDto;
import kz.kegoc.bln.service.adm.UserService;


@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UserRoleResourceImpl {
		
	public UserRoleResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/UserRoleDtoDefaultMapping.xml"));
	}


	@GET
	public Response getAll(@PathParam("userId") Long userId) {
		User entity = service.findById(userId);

		List<UserRoleDto> list = entity.getRoles()
			.stream()
			.map( it-> mapper.map(it, UserRoleDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<UserRoleDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("userId") Long userId, @PathParam("id") Long id) {
		UserRole entity = service.findRoleById(new UserRoleId(userId, id));
		return Response.ok()
			.entity(mapper.map(entity, UserRoleDto.class))
			.build();		
	}
	
		
	@POST
	public Response create(@PathParam("userId") Long userId, UserRoleDto userRoleDto ) {
		UserRole newEntity = service.addRole(userId, mapper.map(userRoleDto, UserRole.class));
		return Response.ok()
			.entity(mapper.map(newEntity, UserRoleDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("userId") Long userId, @PathParam("id") Long id, UserRoleDto userRoleDto ) {
		UserRole newEntity = service.updateRole(userId, mapper.map(userRoleDto, UserRole.class));
		return Response.ok()
			.entity(mapper.map(newEntity, UserRoleDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("userId") Long userId, @PathParam("id") Long id) {
		service.deleteRole(userId, id);
		return Response.noContent()
			.build();
	}
	
		
	@Inject private UserService service;
	private DozerBeanMapper mapper;
}
