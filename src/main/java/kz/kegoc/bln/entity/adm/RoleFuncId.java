package kz.kegoc.bln.entity.adm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(of= {"funcId"})
public class RoleFuncId implements Serializable {
	private static final long serialVersionUID = -8325468866043884564L;
	private Long roleId;
	private Long funcId;
	
	public RoleFuncId() {
		super();
	}
	
	public RoleFuncId(Long roleId, Long funcId) {
		super();
		this.roleId = roleId;
		this.funcId = funcId;
	}
}
