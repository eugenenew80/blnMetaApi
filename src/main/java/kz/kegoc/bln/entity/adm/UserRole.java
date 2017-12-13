package kz.kegoc.bln.entity.adm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(of= {"id"})
public class UserRole  {
	private UserRoleId id;
	
	@NotNull
	private Role role;

	@NotNull
	private User user;
	
	private Date startDate;
	private Date endDate;
}
