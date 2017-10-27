package com.ingesup.dto;

import java.util.Date;

import com.ingesup.state.ComponentState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HistoryUpdateDto {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class GetOutput {
		private Integer id;
		private Float cpu;
		private Float ram;
		private Date dateEvent;
		private String machineIp;
		private ComponentState cpuPercentage;
		private ComponentState ramPercentage;
		private ComponentState storagePercentage;
	}

	
}
