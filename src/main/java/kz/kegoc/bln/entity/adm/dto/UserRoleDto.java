package kz.kegoc.bln.entity.adm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleDto {
	private Long userId;
	private Long roleId;
	private String roleName;
	private Date startDate;
	private Date endDate;
}
