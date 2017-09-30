package kz.kegoc.bln.entity.meta;

import javax.validation.constraints.*;
import kz.kegoc.bln.entity.common.*;
import lombok.*;

@Data
@EqualsAndHashCode(of= {"id"})
public class Module implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 15)
	private String code;
	
	@NotNull @Size(max = 100)
	private String name;
	
	@NotNull @Size(max = 30)
	private String shortName;
}
