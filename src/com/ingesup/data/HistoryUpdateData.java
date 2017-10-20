package com.ingesup.data;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
// TEST
@Data
@NoArgsConstructor
public class HistoryUpdateData {
	private Date dateEvent;
	private String machineIp;
	private Integer cpuPercentage;
	private Integer ramPercentage;
	private Integer storagePercentage;
}
