package kz.kegoc.bln.service.meta.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.meta.Dict;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.meta.DictService;


@Stateless
public class DictServiceImpl extends AbstractEntityService<Dict> implements DictService {
    
	@Inject
    public DictServiceImpl(Repository<Dict> repository, Validator validator) {
        super(repository, validator);
    }
}
