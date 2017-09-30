package kz.kegoc.bln.entity.meta.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DictDto {
	private Long id;
	private String code;
	private String name;
	private String shortName;
	private String imagePath;
}
