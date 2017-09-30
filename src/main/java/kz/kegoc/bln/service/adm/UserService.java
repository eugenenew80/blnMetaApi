package kz.kegoc.bln.service.adm;

import kz.kegoc.bln.entity.adm.User;
import kz.kegoc.bln.entity.adm.UserRole;
import kz.kegoc.bln.entity.adm.UserRoleId;
import kz.kegoc.bln.service.common.EntityService;

public interface UserService extends EntityService<User> {
	UserRole findRoleById(UserRoleId id);
	UserRole addRole(Long userId, UserRole userRole);
	UserRole updateRole(Long userId, UserRole userRole);
	boolean deleteRole(Long userId, Long roleId);
}
