package com.juaracoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.juaracoding.model.BookingModel;
import com.juaracoding.model.PenumpangModel;
import com.juaracoding.repository.BookingRepository;
import com.juaracoding.repository.KeberangkatanRepository;
import com.juaracoding.repository.PenumpangRepository;



@Controller
public class WebController {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PenumpangRepository penumpangRepository;
	
	@Autowired
	KeberangkatanRepository keberangkatanRepository;
	
	
	@GetMapping("/carikeberangkatan")
	private String cariKeberangkatan(Model model, @RequestParam(value = "tanggal", defaultValue = "") String tanggal,
			@RequestParam(value = "terminalawal", defaultValue = "")String terminalAwal) {
		model.addAttribute("listKeberangkatan", keberangkatanRepository.getKeberangkatan(terminalAwal, tanggal));
		return "carikeberangkatan";

	}
	
	@GetMapping("/detailpenumpang")
	private String detailpenumpang(Model model) {
		model.addAttribute("listPenumpang", penumpangRepository.findAll());
		
		return "detailpenumpang";

	}

	@GetMapping("/formlogin")
	private String login(Model model) {
		return "formlogin";
	}
	
	@PostMapping("/formlogin")
	private String loginsatu(Model model, @RequestParam(value = "nik", defaultValue = "") String nik,
			@RequestBody PenumpangModel penumpang) {
		if (nik.equals(penumpang.getNik())) {
			model.addAttribute("daftarpenumpang");
			return "daftarpenumpang";
		} else {
			model.addAttribute("belumterdaftar");
			return "belumterdaftar";
		}
	}
	
	@GetMapping("/kenihilan")
	private String belumdaftar(Model model) {
		return "kenihilian";
	}
	
	@GetMapping("/formbooking")
	private String inputBooking(Model model) {
		model.addAttribute("bookingModel", new BookingModel());
		return "formbooking";
	}
	
	@PostMapping("/detailbooking")
	private String saveBooking(@ModelAttribute BookingModel data) {
		bookingRepository.save(data);
		return "redirect:/detailbooking?id="+data.getId_keberangkatan().getId()+"&nik"+data.getNik().getNik();
	}
	
	@GetMapping("/detailbooking")
	private String detailBooking(Model model, 
			@RequestParam(value = "id", defaultValue = "")Long id_keberangkatan,
			@RequestParam(value = "nik", defaultValue = "")String nik) {
		model.addAttribute("listBooking", bookingRepository.getAllDataIdAndNik(id_keberangkatan, nik));
		return "detailbooking";

	}
	
	
	@PostMapping("/formcancel")
	private String deleteBooking(@RequestParam("id")long id) {
		bookingRepository.deleteById(id);
		return "redirect:/formcancel";
	}
	
	@GetMapping("/formpenumpangbaru")
	private String formpenumpangbaru(Model model) {
		model.addAttribute("penumpangModel", new PenumpangModel());
		
		return "formpenumpangbaru";

	}
	

	
	@PostMapping("/formpenumpangbaru")
	private String savePenumpang(@ModelAttribute PenumpangModel data) {
		penumpangRepository.save(data);
		return "redirect:/detailpenumpang";

	}
	
	
	@GetMapping("/listkeberangkatan")
	private String listkeberangkatan(Model model,
			@RequestParam(value = "tanggal", defaultValue = "")String tanggal,
			@RequestParam(value = "terminal_keberangkatan", defaultValue = "")String terminal_keberangkatan){
		model.addAttribute("listKeberangkatan",keberangkatanRepository.getKeberangkatan(terminal_keberangkatan, tanggal));
		
		return "listkeberangkatan";

	}

	@GetMapping("/listdetailkeberangkatan")
	private String listdetailkeberangkatan(Model model) {
		model.addAttribute("listdetailkeberangkatan");
		return "formpenumpangbaru";

	}
	


	
}
