package kz.kegoc.bln.entity.adm;

import java.util.Date;
import javax.validation.constraints.NotNull;
import kz.kegoc.bln.entity.meta.Module;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"module"})
public class RoleModule  {
	private RoleModuleId id;

	@NotNull
	private Role role;

	@NotNull
	private Module module;
	
	private Date startDate;
	private Date endDate;
}
