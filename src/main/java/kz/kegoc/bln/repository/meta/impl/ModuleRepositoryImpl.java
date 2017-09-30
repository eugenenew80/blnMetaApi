package kz.kegoc.bln.repository.meta.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.entity.meta.Module;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.repository.meta.ModuleRepository;


@Stateless
public class ModuleRepositoryImpl extends AbstractRepository<Module> implements ModuleRepository {
	public ModuleRepositoryImpl() { setClazz(Module.class); }

	public ModuleRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
