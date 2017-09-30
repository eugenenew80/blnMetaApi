package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.adm.Role;
import kz.kegoc.bln.entity.adm.RoleFunc;
import kz.kegoc.bln.entity.adm.RoleFuncId;
import kz.kegoc.bln.entity.adm.dto.RoleFuncDto;
import kz.kegoc.bln.service.adm.RoleService;


@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RoleFuncResourceImpl {
		
	public RoleFuncResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/RoleFuncDtoDefaultMapping.xml"));
	}


	@GET
	public Response getAll(@PathParam("roleId") Long roleId) {
		Role entity = service.findById(roleId);

		List<RoleFuncDto> list = entity.getFuncs()
			.stream()
			.map( it-> mapper.map(it, RoleFuncDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<RoleFuncDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("roleId") Long roleId, @PathParam("id") Long id) {
		RoleFunc entity = service.findFuncById(new RoleFuncId(roleId, id));
		return Response.ok()
			.entity(mapper.map(entity, RoleFuncDto.class))
			.build();		
	}
	
		
	@POST
	public Response create(@PathParam("roleId") Long roleId, RoleFuncDto roleFuncDto ) {
		RoleFunc newEntity = service.addFunc(roleId, mapper.map(roleFuncDto, RoleFunc.class));
		return Response.ok()
			.entity(mapper.map(newEntity, RoleFuncDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("roleId") Long roleId, @PathParam("id") Long id, RoleFuncDto roleFuncDto ) {
		RoleFunc newEntity = service.updateFunc(roleId, mapper.map(roleFuncDto, RoleFunc.class));
		return Response.ok()
			.entity(mapper.map(newEntity, RoleFuncDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("roleId") Long roleId, @PathParam("id") Long id) {
		service.deleteFunc(roleId, id);
		return Response.noContent()
			.build();
	}
	
		
	@Inject private RoleService service;
	private DozerBeanMapper mapper;
}
