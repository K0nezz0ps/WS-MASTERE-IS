package com.ingesup.state;

public enum ComponentState {
	VERYGOOD,	// Less than 10% - Color: hard green 	#1ed000
	GOOD,		// 11% to 25%	 - Color: light green 	#b2ff00
	AVERAGE,	// 26% to 50%	 - Color: yellow 		#fff900
	RAISED,		// 51% to 65%	 - Color: light orange 	#ffe200
	HEAVEN,		// 66% to 85%	 - Color: hard orange 	#f5a000
	ALERT;		// More than 86% - Color: red 			#ff0000
	
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


