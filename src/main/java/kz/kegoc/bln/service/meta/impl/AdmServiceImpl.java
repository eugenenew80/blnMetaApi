package kz.kegoc.bln.service.meta.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.meta.Adm;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.meta.AdmService;


@Stateless
public class AdmServiceImpl extends AbstractEntityService<Adm> implements AdmService {
    
	@Inject
    public AdmServiceImpl(Repository<Adm> repository, Validator validator) {
        super(repository, validator);
    }
}
