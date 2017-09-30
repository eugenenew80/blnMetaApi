package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.adm.Role;
import kz.kegoc.bln.entity.adm.RoleModule;
import kz.kegoc.bln.entity.adm.RoleModuleId;
import kz.kegoc.bln.entity.adm.dto.RoleModuleDto;
import kz.kegoc.bln.service.adm.RoleService;


@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RoleModuleResourceImpl {
		
	public RoleModuleResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/RoleModuleDtoDefaultMapping.xml"));
	}


	@GET
	public Response getAll(@PathParam("roleId") Long roleId) {
		Role entity = service.findById(roleId);

		List<RoleModuleDto> list = entity.getModules()
			.stream()
			.map( it-> mapper.map(it, RoleModuleDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<RoleModuleDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("roleId") Long roleId, @PathParam("id") Long id) {
		RoleModule entity = service.findModuleById(new RoleModuleId(roleId, id));
		return Response.ok()
			.entity(mapper.map(entity, RoleModuleDto.class))
			.build();		
	}
	
		
	@POST
	public Response create(@PathParam("roleId") Long roleId, RoleModuleDto roleModuleDto ) {
		RoleModule newEntity = service.addModule(roleId, mapper.map(roleModuleDto, RoleModule.class));
		return Response.ok()
			.entity(mapper.map(newEntity, RoleModuleDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("roleId") Long roleId, @PathParam("id") Long id, RoleModuleDto roleModuleDto ) {
		RoleModule newEntity = service.updateModule(roleId, mapper.map(roleModuleDto, RoleModule.class));
		return Response.ok()
			.entity(mapper.map(newEntity, RoleModuleDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("roleId") Long roleId, @PathParam("id") Long id) {
		service.deleteModule(roleId, id);
		return Response.noContent()
			.build();
	}
	
		
	@Inject private RoleService service;
	private DozerBeanMapper mapper;
}
