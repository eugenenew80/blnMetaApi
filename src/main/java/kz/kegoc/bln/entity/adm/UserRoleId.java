package kz.kegoc.bln.entity.adm;

import java.io.Serializable;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"roleId"})
public class UserRoleId implements Serializable {
	private static final long serialVersionUID = -7625920213603332085L;
	private Long userId;
	private Long roleId;
	
	public UserRoleId() {
		super();
	}	
	
	public UserRoleId(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}
}
