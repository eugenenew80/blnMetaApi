package kz.kegoc.bln.entity.adm;

import java.util.Date;
import javax.validation.constraints.NotNull;
import kz.kegoc.bln.entity.meta.Dict;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"dict"})
public class RoleDict  {
	private RoleDictId id;

	@NotNull
	private Role role;

	@NotNull
	private Dict dict;
	
	private Date startDate;
	private Date endDate;
}
