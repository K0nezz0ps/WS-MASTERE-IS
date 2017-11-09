package com.ingesup.dto;

import java.util.List;

import com.ingesup.model.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RoomDto {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GetOutput {
		
		private Integer id;
		private String name;
		private Integer idPark;
		private List<Machine> machineList;
		private Boolean hasAlert;
		
	}
	
}
