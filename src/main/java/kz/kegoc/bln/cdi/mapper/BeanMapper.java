package kz.kegoc.bln.cdi.mapper;

import org.dozer.DozerBeanMapper;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Arrays;

@Startup
@Singleton
public class BeanMapper {
    private DozerBeanMapper mapper;

    public DozerBeanMapper getMapper() {
        return mapper;
    }

    @PostConstruct
    public void init() {
        mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(
            "mapping/MappingConfig.xml",
            "mapping/meta/AdmDtoDefaultMapping.xml",
            "mapping/meta/DictDtoDefaultMapping.xml",
            "mapping/meta/DictGroupDtoDefaultMapping.xml",
            "mapping/meta/MediaDtoDefaultMapping.xml",
            "mapping/meta/ModuleDtoDefaultMapping.xml"
        ));
    }
}
