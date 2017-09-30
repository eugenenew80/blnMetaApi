package kz.kegoc.bln.webapi.config;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import kz.kegoc.bln.webapi.adm.*;
import kz.kegoc.bln.webapi.exception.mapper.*;
import kz.kegoc.bln.webapi.filters.BasicAuthentificationFilter;
import kz.kegoc.bln.webapi.meta.*;


@ApplicationPath("/webapi")
public class JaxRsConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> resources = new HashSet<Class<?>>();

		resources.add(FuncResourceImpl.class);
		resources.add(RoleResourceImpl.class);
		resources.add(UserResourceImpl.class);
		
		resources.add(MetaModuleResourceImpl.class);
		resources.add(MetaDictResourceImpl.class);
		resources.add(MetaAdmResourceImpl.class);
		
		resources.add(BasicAuthentificationFilter.class);
		
		resources.add(RepositryNotFoundExceptionMapperImpl.class);
		resources.add(EntityNotFoundExceptionMapperImpl.class);
		resources.add(DuplicateEntityExceptionMapperImpl.class);
		resources.add(ValidationExceptionMapperImpl.class);
		resources.add(InvalidArgumentExceptionMapperImpl.class);
		resources.add(EjbExceptionMapperImpl.class);
		resources.add(WebApplicationExceptionMapperImpl.class);
        resources.add(DefaultExceptionMapperImpl.class);
        
		return resources;
	}	
}
