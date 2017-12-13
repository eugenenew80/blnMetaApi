package kz.kegoc.bln.service.meta.impl;

import kz.kegoc.bln.entity.meta.DictGroup;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.meta.DictGroupService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;


@Stateless
public class DictGroupServiceImpl extends AbstractEntityService<DictGroup> implements DictGroupService {

	@Inject
    public DictGroupServiceImpl(Repository<DictGroup> repository, Validator validator) {
        super(repository, validator);
    }
}
