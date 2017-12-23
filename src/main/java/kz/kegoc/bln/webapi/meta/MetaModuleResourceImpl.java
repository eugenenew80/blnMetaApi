package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.cdi.mapper.BeanMapper;
import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.service.adm.UserService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import kz.kegoc.bln.webapi.common.SessionContext;
import kz.kegoc.bln.entity.meta.Module;
import kz.kegoc.bln.entity.meta.dto.ModuleDto;
import kz.kegoc.bln.service.meta.ModuleService;


@RequestScoped
@Path("/meta/metaModule")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaModuleResourceImpl {

	@GET 
	public Response getAll(@HeaderParam("lang") Lang lang) {
		List<ModuleDto> list = service.findAll()
			.stream()
			.map( it-> mapper.getMapper().map(it, ModuleDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<ModuleDto>>(list){})
				.build();
	}

	@GET
	@Path("/byUser")
	public Response getByUser(@HeaderParam("lang") Lang lang) {
		SessionContext context = buildSessionContext(lang);
		User user = userService.findById(context.getUser().getId());

		List<Module> modules = user.getRoles().stream()
			.flatMap(u -> u.getRole().getModules().stream())
			.map(roleModule -> roleModule.getModule())
			.distinct()
			.collect(Collectors.toList());

		List<ModuleDto> list = service.findAll()
			.stream()
			.filter(it -> modules.contains(it))
			.map( it-> mapper.getMapper().map(it, ModuleDto.class) )
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
			.entity(mapper.getMapper().map(entity, ModuleDto.class))
			.build();		
	}

	
	@POST
	public Response create(ModuleDto moduleDto) {
		Module newEntity = service.create(mapper.getMapper().map(moduleDto, Module.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, ModuleDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, ModuleDto moduleDto ) {
		Module newEntity = service.update(mapper.getMapper().map(moduleDto, Module.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, ModuleDto.class))
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
	private ModuleService service;

	@Inject
	private UserService userService;

	@Inject
	private BeanMapper mapper;

	@Context
	private SecurityContext securityContext;

	@Inject
	private Lang defLang;
}
