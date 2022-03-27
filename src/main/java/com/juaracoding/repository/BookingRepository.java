package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.model.BookingInsertCustome;
import com.juaracoding.model.BookingModel;

public interface BookingRepository extends JpaRepository<BookingModel, Long> {
	
	@Query(value = "SELECT `booking`.`id`, `penumpang`.`nik`, `penumpang`.`nama`, `keberangkatan`.`id`, `keberangkatan`.`tanggal`, `bus`.`nama_perusahaan`, `keberangkatan`.`kelas`, `keberangkatan`.`harga` FROM `booking`\r\n"
			+ "INNER JOIN `penumpang` ON `penumpang`.`nik` = `booking`.`nik`\r\n"
			+ "INNER JOIN `keberangkatan` ON `keberangkatan`.`id` = `booking`.`id_keberangkatan`\r\n"
			+ "INNER JOIN `bus` ON `bus`.`no_polisi` = `booking`.`no_polisi`\r\n"
			+ "INNER JOIN `perusahaan` ON `perusahaan`.`nama` = `bus`.`nama_perusahaan`\r\n"
			+ "WHERE `keberangkatan`.`id` = ?1 AND `penumpang`.`nik` = ?2 ", nativeQuery = true)
	List<BookingModel> getAllDataIdAndNik(long id_keberangkatan, String nik);

	

}
