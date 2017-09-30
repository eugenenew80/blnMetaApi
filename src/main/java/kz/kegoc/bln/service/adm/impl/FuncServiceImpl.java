package kz.kegoc.bln.service.adm.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.adm.Func;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.adm.FuncService;
import kz.kegoc.bln.service.common.AbstractEntityService;


@Stateless
public class FuncServiceImpl extends AbstractEntityService<Func> implements FuncService {
    
	@Inject
    public FuncServiceImpl(Repository<Func> repository, Validator validator) {
        super(repository, validator);
    }
}
