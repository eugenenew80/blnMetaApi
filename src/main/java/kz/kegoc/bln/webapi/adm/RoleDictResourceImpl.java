package kz.kegoc.bln.webapi.adm;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.adm.Role;
import kz.kegoc.bln.entity.adm.RoleDict;
import kz.kegoc.bln.entity.adm.RoleDictId;
import kz.kegoc.bln.entity.adm.dto.RoleDictDto;
import kz.kegoc.bln.service.adm.RoleService;


@RequestScoped
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class RoleDictResourceImpl {
		
	public RoleDictResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/adm/RoleDictDtoDefaultMapping.xml"));
	}


	@GET
	public Response getAll(@PathParam("roleId") Long roleId) {
		Role entity = service.findById(roleId);

		List<RoleDictDto> list = entity.getDicts()
			.stream()
			.map( it-> mapper.map(it, RoleDictDto.class) )
			.collect(Collectors.toList());		
	
		return Response.ok()
			.entity(new GenericEntity<Collection<RoleDictDto>>(list){})
			.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("roleId") Long roleId, @PathParam("id") Long id) {
		RoleDict entity = service.findDictById(new RoleDictId(roleId, id));
		return Response.ok()
			.entity(mapper.map(entity, RoleDictDto.class))
			.build();		
	}
	
		
	@POST
	public Response create(@PathParam("roleId") Long roleId, RoleDictDto roleDictDto ) {
		RoleDict newEntity = service.addDict(roleId, mapper.map(roleDictDto, RoleDict.class));
		return Response.ok()
			.entity(mapper.map(newEntity, RoleDictDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("roleId") Long roleId, @PathParam("id") Long id, RoleDictDto roleDictDto ) {
		RoleDict newEntity = service.updateDict(roleId, mapper.map(roleDictDto, RoleDict.class));
		return Response.ok()
			.entity(mapper.map(newEntity, RoleDictDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("roleId") Long roleId, @PathParam("id") Long id) {
		service.deleteDict(roleId, id);
		return Response.noContent()
			.build();
	}
	
		
	@Inject private RoleService service;
	private DozerBeanMapper mapper;
}
