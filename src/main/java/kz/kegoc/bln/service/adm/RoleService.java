package kz.kegoc.bln.service.adm;

import kz.kegoc.bln.entity.adm.Role;
import kz.kegoc.bln.entity.adm.RoleDict;
import kz.kegoc.bln.entity.adm.RoleDictId;
import kz.kegoc.bln.entity.adm.RoleFunc;
import kz.kegoc.bln.entity.adm.RoleFuncId;
import kz.kegoc.bln.entity.adm.RoleModule;
import kz.kegoc.bln.entity.adm.RoleModuleId;
import kz.kegoc.bln.service.common.EntityService;

public interface RoleService extends EntityService<Role> {
	RoleModule findModuleById(RoleModuleId id);
	RoleModule addModule(Long roleId, RoleModule roleModule);
	RoleModule updateModule(Long roleId, RoleModule roleModule);
	boolean deleteModule(Long roleId, Long moduleId);
	
	RoleFunc findFuncById(RoleFuncId id);	
	RoleFunc addFunc(Long roleId, RoleFunc roleFnc);
	RoleFunc updateFunc(Long roleId, RoleFunc roleFnc);
	boolean deleteFunc(Long roleId, Long funcId);
	
	RoleDict findDictById(RoleDictId id);
	RoleDict addDict(Long roleId, RoleDict roleDict);
	RoleDict updateDict(Long roleId, RoleDict roleDict);
	boolean deleteDict(Long roleId, Long dictId);	
}
