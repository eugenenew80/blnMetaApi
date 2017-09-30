package kz.kegoc.bln.entity.adm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleFuncDto {
	private Long roleId;
	private Long funcId;
	private String funcName;
	private Date startDate;
	private Date endDate;
}
