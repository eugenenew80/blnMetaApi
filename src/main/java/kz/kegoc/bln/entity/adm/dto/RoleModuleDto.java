package kz.kegoc.bln.entity.adm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleModuleDto {
	private Long roleId;
	private Long moduleId;
	private String moduleName;
	private Date startDate;
	private Date endDate;
}
