package kz.kegoc.bln.entity.adm;

import kz.kegoc.bln.entity.meta.Dict;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

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
