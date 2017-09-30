package kz.kegoc.bln.repository.meta.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.meta.Dict;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.meta.DictRepository;


@Stateless
public class DictRepositoryImpl extends AbstractRepository<Dict> implements DictRepository {
	public DictRepositoryImpl() { setClazz(Dict.class); }

	public DictRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
