package kz.kegoc.bln.service.adm.impl;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Validator;
import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.adm.UserRole;
import kz.kegoc.bln.entity.adm.UserRoleId;
import kz.kegoc.bln.exception.EntityNotFoundException;
import kz.kegoc.bln.repository.common.Repository;
import kz.kegoc.bln.service.adm.UserService;
import kz.kegoc.bln.service.common.AbstractEntityService;

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


	@Override
	public UserRole addRole(Long userId, UserRole userRole) {
		User user = findById(userId);
		user.getRoles().add(userRole);
		user = update(user); 		
		return findRoleById(new UserRoleId(userId, userRole.getRole().getId()));
	}


	@Override
	public UserRole updateRole(Long userId, UserRole userRole) {
		User user = findById(userId);
		user.getRoles().add(userRole);
		user = update(user); 		
		return findRoleById(new UserRoleId(userId, userRole.getRole().getId()));
	}


	@Override
	public boolean deleteRole(Long userId, Long roleId) {
		UserRole entity = findRoleById(new UserRoleId(userId, roleId));		
		entity.getUser().getRoles().remove(entity);
		update(entity.getUser()); 
		return true;
	}
}
