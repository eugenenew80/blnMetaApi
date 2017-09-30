package kz.kegoc.bln.entity.adm;

import java.io.Serializable;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"roleId", "dictId"})
public class RoleDictId implements Serializable {
	private static final long serialVersionUID = 4543003877310931919L;	
	private Long roleId;
	private Long dictId;
	
	public RoleDictId() {
		super();
	}
	
	public RoleDictId(Long roleId, Long dictId) {
		super();
		this.roleId = roleId;
		this.dictId = dictId;
	}
}
