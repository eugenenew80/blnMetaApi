package kz.kegoc.bln.webapi.meta;

import java.util.*;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.dozer.DozerBeanMapper;
import kz.kegoc.bln.entity.meta.Adm;
import kz.kegoc.bln.entity.meta.dto.AdmDto;
import kz.kegoc.bln.service.meta.AdmService;


@RequestScoped
@Path("/meta/metaAdm")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MetaAdmResourceImpl {
	
	public MetaAdmResourceImpl() {
		mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("mapping/meta/AdmDtoDefaultMapping.xml"));
	} 


	@GET 
	public Response getAll(@QueryParam("code") String code, @QueryParam("name") String name) {		
		List<AdmDto> list = service.findAll()
			.stream()
			.map( it-> mapper.map(it, AdmDto.class) )
			.collect(Collectors.toList());
		
		return Response.ok()
				.entity(new GenericEntity<Collection<AdmDto>>(list){})
				.build();
	}
	
	
	@GET 
	@Path("/{id : \\d+}") 
	public Response getById(@PathParam("id") Long id) {
		Adm entity = service.findById(id);
		return Response.ok()
			.entity(mapper.map(entity, AdmDto.class))
			.build();		
	}
	

	@GET
	@Path("/byCode/{code}")
	public Response getByCode(@PathParam("code") String code) {		
		Adm entity = service.findByCode(code);
		return Response.ok()
			.entity(mapper.map(entity, AdmDto.class))
			.build();
	}
	
	
	@GET
	@Path("/byName/{name}")
	public Response getByName(@PathParam("name") String name) {		
		Adm entity = service.findByName(name);
		return Response.ok()
			.entity(mapper.map(entity, AdmDto.class))
			.build();
	}

	
	@POST
	public Response create(AdmDto admDto) {
		Adm newEntity = service.create(mapper.map(admDto, Adm.class));	
		return Response.ok()
			.entity(mapper.map(newEntity, AdmDto.class))
			.build();
	}
	
	
	@PUT 
	@Path("{id : \\d+}") 
	public Response update(@PathParam("id") Long id, AdmDto admDto ) {
		Adm newEntity = service.update(mapper.map(admDto, Adm.class)); 
		return Response.ok()
			.entity(mapper.map(newEntity, AdmDto.class))
			.build();
	}
	
	
	@DELETE 
	@Path("{id : \\d+}") 
	public Response delete(@PathParam("id") Long id) {
		service.delete(id);		
		return Response.noContent()
			.build();
	}
	

	@Inject private AdmService service;
	private DozerBeanMapper mapper;
}
