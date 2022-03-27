package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.model.CustomGetKeberangkatan;
import com.juaracoding.model.KeberangkatanModel;

public interface KeberangkatanRepository extends JpaRepository<KeberangkatanModel, Long>{

	@Query(value = "SELECT k.id,b.nama_perusahaan,k.kelas,k.harga,k.tanggal,j.deskripsi "
			+ "from keberangkatan k "
			+ "inner join jurusan j on j.id=k.id_jurusan "
			+ "INNER JOIN bus b ON b.no_polisi=k.no_polisi "
			+ "WHERE j.terminal_awal=?1 AND k.tanggal LIKE ?2% ",nativeQuery = true)
	List<CustomGetKeberangkatan> getKeberangkatan(String terminalawal,String tanggal);
}
