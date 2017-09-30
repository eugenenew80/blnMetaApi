package kz.kegoc.bln.entity.adm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {
	private Long id;
	private String code;
	private String name;
}
