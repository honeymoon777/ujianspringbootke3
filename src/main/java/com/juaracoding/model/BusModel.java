package com.juaracoding.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="bus")
public class BusModel {
	
	@Id
	@Column(length = 10)
	private String noPolisi;
	private int kapasitas;
	private String namaSupir;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "namaPerusahaan", referencedColumnName = "nama")
	private PerusahaanModel namaPerusahaan;

}
