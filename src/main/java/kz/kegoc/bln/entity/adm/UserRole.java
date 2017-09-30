package kz.kegoc.bln.entity.adm;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.*;

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
