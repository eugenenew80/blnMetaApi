package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import org.apache.commons.lang3.StringUtils;

import kz.kegoc.bln.entity.adm.Role;
import kz.kegoc.bln.entity.adm.dto.RoleDto;
import kz.kegoc.bln.repository.common.query.*;
import kz.kegoc.bln.service.adm.RoleService;


@RequestScoped
@Path("/adm/admRole")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RoleResourceImpl {
	
	public RoleResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/RoleDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		Query query = QueryImpl.builder()			
			.setParameter("code", StringUtils.isNotEmpty(code) ? new MyQueryParam("code", code + "%", ConditionType.LIKE) : null)	
			.setParameter("name", StringUtils.isNotEmpty(name) ? new MyQueryParam("name", name + "%", ConditionType.LIKE) : null)	
			.setOrderBy("t.id")
			.build();		
		
		List<RoleDto> list = service.find(query)
			.stream()
			.map( it-> mapper.map(it, RoleDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<RoleDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Role entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, RoleDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Role entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, RoleDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Role entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, RoleDto.class))
			.build();
	}

	
	@POST
	public Response create(RoleDto accountingTypeDto) {
		Role newEntity = service.create(mapper.map(accountingTypeDto, Role.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, RoleDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, RoleDto accountingTypeDto ) {
		Role newEntity = service.update(mapper.map(accountingTypeDto, Role.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, RoleDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	
	
	@Path("/{roleId : \\d+}/admRoleModule")
	public RoleModuleResourceImpl getModules(@PathParam("roleId") Long id) {
		return roleModuleResource;
	}	

	
	@Path("/{roleId : \\d+}/admRoleFunc")
	public RoleFuncResourceImpl getFuncs(@PathParam("roleId") Long id) {
		return roleFuncResource;
	}	
	

	@Path("/{roleId : \\d+}/admRoleDict")
	public RoleDictResourceImpl getDicts(@PathParam("roleId") Long id) {
		return roleDictResource;
	}	
	
	
	@Inject private RoleService service;
	@Inject private RoleModuleResourceImpl roleModuleResource;
	@Inject private RoleFuncResourceImpl roleFuncResource;
	@Inject private RoleDictResourceImpl roleDictResource;
	private DozerBeanMapper mapper;
}
