package kz.kegoc.bln.repository.adm.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.repository.adm.RoleRepository;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.entity.adm.Role;


@Stateless
public class RoleRepositoryImpl extends AbstractRepository<Role> implements RoleRepository {
	public RoleRepositoryImpl() { setClazz(Role.class); }

	public RoleRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
