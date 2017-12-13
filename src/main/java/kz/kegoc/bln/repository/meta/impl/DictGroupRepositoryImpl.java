package kz.kegoc.bln.repository.meta.impl;

import kz.kegoc.bln.entity.meta.DictGroup;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.meta.DictGroupRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

@Stateless
public class DictGroupRepositoryImpl extends AbstractRepository<DictGroup> implements DictGroupRepository {
	public DictGroupRepositoryImpl() { setClazz(DictGroup.class); }

	public DictGroupRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
