package kz.kegoc.bln.entity.adm;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class RoleFunc  {
	private RoleFuncId id;
	
	@NotNull
	private Role role;

	@NotNull
	private Func func;
	
	private Date startDate;
	private Date endDate;
}
