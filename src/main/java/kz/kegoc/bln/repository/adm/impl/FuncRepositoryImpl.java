package kz.kegoc.bln.repository.adm.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.repository.adm.FuncRepository;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.entity.adm.Func;


@Stateless
public class FuncRepositoryImpl extends AbstractRepository<Func> implements FuncRepository {
	public FuncRepositoryImpl() { setClazz(Func.class); }

	public FuncRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
