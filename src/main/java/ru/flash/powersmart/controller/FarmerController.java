package ru.flash.powersmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.flash.powersmart.model.Farmer;
import ru.flash.powersmart.repository.FarmerRepository;
import ru.flash.powersmart.util.SmartMacUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FarmerController {
	
	private static final Logger log = LoggerFactory.getLogger(FarmerController.class);
	
	@Autowired
	FarmerRepository farmerRepository;
	@Scheduled(cron = " 0 6 6 * * * ")
	@PostMapping("/farmers")
	public ResponseEntity<List<Farmer>> createFarmer() {
		log.info("Start FarmerController");
		try {
			SmartMacUtils sm = new SmartMacUtils();
			
			List<Farmer> listFarmers = new ArrayList<Farmer>();
			listFarmers = sm.readFarmerJsonFromUrl(SmartMacUtils.URL_FARMER);
			for (Farmer farmer : listFarmers) {
				farmerRepository
						.save(farmer);
			}
			log.info("Finish FarmerController OK");
			return new ResponseEntity<>(listFarmers, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Finish FarmerController BAD");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/farmers")
	public ResponseEntity<List<Farmer>> getAllFarmers() {
		try {
			List<Farmer> farmers = new ArrayList<Farmer>();
			//Long test = time;
			farmerRepository.findAll().forEach(farmers::add);
			
			if (farmers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(farmers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
