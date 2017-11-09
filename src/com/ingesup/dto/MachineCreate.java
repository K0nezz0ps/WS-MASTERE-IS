package com.ingesup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MachineCreate {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PostInput {
		private String machineIp;
		private String machineCpu;
		private String machineRam;
		private Integer roomId;
	}
}
