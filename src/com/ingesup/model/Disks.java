package com.ingesup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import lombok.Data;

@Data
@Entity
@Table(name = "disks")
public class Disks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private int id_history;
	
	@Column
	private String root;
	
	@Column
	private Double pourcentage;

	public Disks(int id_history, String root, Double pourcentage) {
		super();
		this.id_history = id_history;
		this.root = root;
		System.out.println(pourcentage);
		pourcentage=pourcentage.isNaN()? -1 : pourcentage;
		this.pourcentage = pourcentage;
	}

}
