package com.ingesup.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

public class MachineSwitchDto {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = false)
	public static class PostInput {
		private List<SwitchInfo> switchInfoList;
		
		public static class SwitchInfo {
			private Integer machineId;
			private Integer targetRoomId;
		}
	}

}
