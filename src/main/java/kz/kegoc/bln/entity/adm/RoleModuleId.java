package kz.kegoc.bln.entity.adm;

import java.io.Serializable;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"roleId", "moduleId"})
public class RoleModuleId implements Serializable {
	private static final long serialVersionUID = -8627707505992286207L;
	private Long roleId;
	private Long moduleId;
	
	public RoleModuleId() {
		super();
	}
	
	public RoleModuleId(Long roleId, Long moduleId) {
		super();
		this.roleId = roleId;
		this.moduleId = moduleId;
	}
}
