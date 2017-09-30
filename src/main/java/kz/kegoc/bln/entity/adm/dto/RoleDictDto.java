package kz.kegoc.bln.entity.adm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDictDto {
	private Long roleId;
	private Long dictId;
	private String dictName;
	private Date startDate;
	private Date endDate;
}
