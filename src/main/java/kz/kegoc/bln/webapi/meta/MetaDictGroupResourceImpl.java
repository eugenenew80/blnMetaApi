package kz.kegoc.bln.webapi.meta;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.meta.Dict;
import kz.kegoc.bln.entity.meta.DictGroup;
import kz.kegoc.bln.entity.meta.dto.DictDto;
import kz.kegoc.bln.entity.meta.dto.DictGroupDto;
import kz.kegoc.bln.service.adm.UserService;
import kz.kegoc.bln.service.meta.DictGroupService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import kz.kegoc.bln.webapi.common.SessionContext;
import org.dozer.DozerBeanMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
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
	@Path("/byUser")
	public Response getByUser(@HeaderParam("lang") Lang lang) {
		SessionContext context = buildSessionContext(lang);
		User user = userService.findById(context.getUser().getId());

		List<DictGroupDto> list = user.getRoles().stream()
			.flatMap(u -> u.getRole().getDicts().stream())
			.map(roleDict -> roleDict.getDict().getDictGroup())
			.distinct()
			.sorted(Comparator.comparing(DictGroup::getId))
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


	private SessionContext buildSessionContext(Lang lang) {
		SessionContext context = new SessionContext();
		context.setLang(lang!=null ? lang : defLang);
		context.setUser(((CustomPrincipal)securityContext.getUserPrincipal()).getUser());
		return context;
	}


	@Inject
	private DictGroupService service;

	private DozerBeanMapper mapper;

	@Context
	private SecurityContext securityContext;

	@Inject
	private UserService userService;

	@Inject
	private Lang defLang;
}
