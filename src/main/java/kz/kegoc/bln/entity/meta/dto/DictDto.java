package kz.kegoc.bln.entity.meta.dto;

import lombok.*;

@Data
public class DictDto {
	private Long id;
	private String code;
	private String name;
	private String shortName;
	private String imagePath;
}
