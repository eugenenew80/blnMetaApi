package kz.kegoc.bln.service.adm.impl;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.adm.UserRole;
import kz.kegoc.bln.entity.adm.UserRoleId;
import kz.kegoc.bln.exception.EntityNotFoundException;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.adm.UserService;
import kz.kegoc.bln.service.common.AbstractEntityService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.Optional;

@Stateless
public class UserServiceImpl extends AbstractEntityService<User> implements UserService {
    
	@Inject
    public UserServiceImpl(Repository<User> repository, Validator validator) {
        super(repository, validator);
    }

	@Override
	public UserRole findRoleById(UserRoleId id) {
		User user = findById(id.getUserId());
		Optional<UserRole> optional = user.getRoles()
			.stream()
		    .filter(m -> m.getRole().getId().equals(id.getRoleId()) )
		    .findAny();
			
		if (!optional.isPresent())
			throw new EntityNotFoundException(id.getRoleId());
		
		return optional.get();
	}
}
