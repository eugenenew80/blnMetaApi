package kz.kegoc.bln.repository.meta.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.meta.Adm;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.meta.AdmRepository;


@Stateless
public class AdmRepositoryImpl extends AbstractRepository<Adm> implements AdmRepository {
	public AdmRepositoryImpl() { setClazz(Adm.class); }

	public AdmRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
