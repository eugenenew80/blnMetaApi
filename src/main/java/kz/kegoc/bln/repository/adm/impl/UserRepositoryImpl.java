package kz.kegoc.bln.repository.adm.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import kz.kegoc.bln.repository.adm.UserRepository;
import kz.kegoc.bln.repository.common.AbstractRepository;
import kz.kegoc.bln.entity.adm.User;


@Stateless
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
	public UserRepositoryImpl() { setClazz(User.class); }

	public UserRepositoryImpl(EntityManager entityManager) {
		this();
		setEntityManager(entityManager);
	}
}
