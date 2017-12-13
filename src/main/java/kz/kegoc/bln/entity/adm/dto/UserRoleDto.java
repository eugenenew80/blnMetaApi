package kz.kegoc.bln.entity.adm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDto {
	private Long userId;
	private Long roleId;
	private String roleName;
	private Date startDate;
	private Date endDate;
}
