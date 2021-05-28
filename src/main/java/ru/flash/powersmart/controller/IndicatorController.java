package ru.flash.powersmart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.flash.powersmart.model.IndicatorJson;
import ru.flash.powersmart.repository.IndicatorRepository;
import ru.flash.powersmart.util.SmartMacUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class IndicatorController {
	@Autowired
	IndicatorRepository indicatorRepository;
	
	private static final Logger log = LoggerFactory.getLogger(IndicatorController.class);
	
	
	@Scheduled(cron = " 0 8 6 * * * ")
	@PostMapping("/indicators")
	public ResponseEntity<IndicatorJson> createIndicators() {
		log.info("Start IndicatorController");
		try {
			SmartMacUtils sm = new SmartMacUtils();
			
			

			IndicatorJson _indicators = indicatorRepository
					.save(sm.readIndicatorJsonFromUrl(SmartMacUtils.URL_SMARTMAC));
			log.info("Finish IndicatorController OK");
			return new ResponseEntity<>(_indicators, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Finish IndicatorController BAD");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/indicators")
	public ResponseEntity<List<IndicatorJson>> getAllIndicators(@RequestParam(required = false) Long  time) {
		try {
			List<IndicatorJson> indicators = new ArrayList<IndicatorJson>();
			//Long test = time;
			if (time == null)
				indicatorRepository.findAll().forEach(indicators::add);
			else
				indicatorRepository.findByTime(time).forEach(indicators::add);
			if (indicators.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(indicators, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/indicators/{id}")
	public ResponseEntity<IndicatorJson> getTutorialById(@PathVariable("id") long id)
			throws JSONException, IOException {
		Optional<IndicatorJson> indicatorData = indicatorRepository.findById(id);

		if (indicatorData.isPresent()) {
			return new ResponseEntity<>(indicatorData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

	@GetMapping("/indicators/temperatura")
	public ResponseEntity<List<IndicatorJson>> findByTemperatura(@RequestParam(required = false) long temperatura) {
		try {
			List<IndicatorJson> indicators = indicatorRepository.findByTemperatura(0);

			if (indicators.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(indicators, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
