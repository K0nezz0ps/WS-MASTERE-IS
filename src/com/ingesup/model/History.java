package com.ingesup.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ingesup.state.ComponentState;

import lombok.Data;

@Data
@Entity
@Table(name="history")
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Date dateEvent;
	
	@Column
	private Integer machineId;
	
	@Column
	private ComponentState cpuState; 

	@Column
	private ComponentState ramState;
	
	@Column
	private ComponentState storageState;
	

}
