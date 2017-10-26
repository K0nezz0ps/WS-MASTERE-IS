package com.ingesup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="machine")
public class Machine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String machineIp;
	
	@Column
	private Integer id_room;
	
	@Column
	private Float ram;
	
	@Column
	private Float cpu;
	
	@Column
	private String storage;

}
