package kz.kegoc.bln.repository.meta.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.meta.Media;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.meta.MediaRepository;

@Stateless
public class MediaRepositoryImpl extends AbstractRepository<Media> implements MediaRepository {
	public MediaRepositoryImpl() { setClazz(Media.class); }

	public MediaRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
