package com.ingesup.state;

public enum ComponentState {
	VERYGOOD,
	GOOD,
	AVERAGE,
	RAISED,
	HEAVEN,
	ALERT;
	
	public static ComponentState forValue(Integer percentage){
		
		if(percentage == null)
			return null;
		
		if(percentage <= 10)
			return ComponentState.VERYGOOD;
		if(percentage > 10 && percentage <= 25)
			return ComponentState.GOOD;
		if(percentage > 25 && percentage <= 50)
			return ComponentState.AVERAGE;
		if(percentage > 50 && percentage <= 65)
			return ComponentState.RAISED;
		if(percentage > 65 && percentage <= 85)
			return ComponentState.HEAVEN;
		
		return ComponentState.ALERT;
	}
}


