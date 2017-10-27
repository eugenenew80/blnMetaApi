package kz.kegoc.bln.service.meta.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;

import kz.kegoc.bln.entity.meta.Media;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.common.AbstractEntityService;
import kz.kegoc.bln.service.meta.MediaService;


@Stateless
public class MediaServiceImpl extends AbstractEntityService<Media> implements MediaService {
    
	@Inject
    public MediaServiceImpl(Repository<Media> repository, Validator validator) {
        super(repository, validator);
    }
}
