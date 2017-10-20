package com.ingesup.controller.utils;

import java.util.Date;
import java.util.Map;
import com.ingesup.data.HistoryUpdateData;

public abstract class MapperUtils {
	
	/**
	 * Return a HistoryUpdateData with all the parameters contained in the paramsMap
	 * @param paramsMap from the Request.getParametersMap();
	 * @return
	 */
	public static HistoryUpdateData historyUpdateDataMapper(Map<String, String[]> paramsMap){
		
		HistoryUpdateData data = new HistoryUpdateData();
		data.setDateEvent(new Date());
		data.setMachineIp(paramsMap.get("machineIp") != null ? paramsMap.get("machineIp")[0] : null);
		data.setCpuPercentage(paramsMap.get("cpuPercentage") != null ? Integer.valueOf(paramsMap.get("cpuPercentage")[0]) : null);
		data.setRamPercentage(paramsMap.get("ramPercentage") != null ? Integer.valueOf(paramsMap.get("ramPercentage")[0]) : null);
		data.setStoragePercentage(paramsMap.get("storagePercentage") != null ? Integer.valueOf(paramsMap.get("storagePercentage")[0]) : null);
		
		return data;
	}
	
}
