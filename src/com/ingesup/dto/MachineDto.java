package com.ingesup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class MachineDto {
	
	public static class GetInput {
		
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = false)
	public static class PostInput {
		private Integer id;
		private String name;
	}
	
	public static class DeleteInput {
		private Integer id;
	}

}
