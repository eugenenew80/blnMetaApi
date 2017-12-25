package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.ejb.mapper.BeanMapper;
import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.service.adm.UserService;
import kz.kegoc.bln.webapi.common.CustomPrincipal;
import kz.kegoc.bln.webapi.common.SessionContext;
import kz.kegoc.bln.entity.meta.Dict;
import kz.kegoc.bln.entity.meta.dto.DictDto;
import kz.kegoc.bln.service.meta.DictService;


@RequestScoped
@Path("/meta/metaDict")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaDictResourceImpl {

	@GET 
	public Response getAll(@HeaderParam("lang") Lang lang) {
		List<DictDto> list = service.findAll()
			.stream()
			.map( it-> mapper.getMapper().map(it, DictDto.class))
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<DictDto>>(list){})
				.build();
	}

	@GET
	@Path("/byUser")
	public Response getByUser(@HeaderParam("lang") Lang lang) {
		SessionContext context = buildSessionContext(lang);
		User user = userService.findById(context.getUser().getId());

		List<Dict> dicts = user.getRoles().stream()
			.flatMap(u -> u.getRole().getDicts().stream())
			.map(roleDict -> roleDict.getDict())
			.distinct()
			.collect(Collectors.toList());

		List<DictDto> list = service.findAll()
			.stream()
			.filter(it -> dicts.contains(it))
			.map( it-> mapper.getMapper().map(it, DictDto.class))
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
			.entity(mapper.getMapper().map(entity, DictDto.class))
			.build();		
	}

	
	@POST
	public Response create(DictDto dictDto) {
		Dict newEntity = service.create(mapper.getMapper().map(dictDto, Dict.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, DictDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, DictDto dictDto ) {
		Dict newEntity = service.update(mapper.getMapper().map(dictDto, Dict.class));
		return Response.ok()
			.entity(mapper.getMapper().map(newEntity, DictDto.class))
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
	private DictService service;

	@Inject
	private UserService userService;

	@Inject
	private BeanMapper mapper;

	@Context
	private SecurityContext securityContext;

	@Inject
	private Lang defLang;
}
