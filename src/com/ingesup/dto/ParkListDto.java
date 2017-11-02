package com.ingesup.dto;

import java.util.List;

import com.ingesup.model.Park;
import com.ingesup.model.Room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class ParkListDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = false)
	public static class GetOutput {
		
		private Park park;
		private List<Room> rooms;
		
	}
	
}
