package kz.kegoc.bln.entity.adm;

import kz.kegoc.bln.entity.common.HasCode;
import kz.kegoc.bln.entity.common.HasId;
import kz.kegoc.bln.entity.common.HasName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(of= {"id"})
public class Role implements HasId, HasCode, HasName {
	private Long id;
	
	@NotNull @Size(max = 15)
	private String code;
	
	@NotNull @Size(max = 100)
	private String name;
	
	private List<RoleFunc> funcs;
	private List<RoleModule> modules;
	private List<RoleDict> dicts;
}
