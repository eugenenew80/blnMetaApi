package kz.kegoc.bln.entity.adm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleModuleDto {
	private Long roleId;
	private Long moduleId;
	private String moduleName;
	private Date startDate;
	private Date endDate;
}
