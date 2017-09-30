package kz.kegoc.bln.service.meta.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.meta.Module;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.meta.ModuleService;


@Stateless
public class ModuleServiceImpl extends AbstractEntityService<Module> implements ModuleService {
    
	@Inject
    public ModuleServiceImpl(Repository<Module> repository, Validator validator) {
        super(repository, validator);
    }
}
