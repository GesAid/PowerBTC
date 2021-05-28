package ru.flash.powersmart.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.flash.powersmart.model.ExchangeRate;
import ru.flash.powersmart.repository.ExchangeRateRepository;
import ru.flash.powersmart.util.SmartMacUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ExchangeRateController {
	
	 private static final Logger log = LoggerFactory.getLogger(ExchangeRateController.class);
	@Autowired
	ExchangeRateRepository exchangeRateRepository;
	@Scheduled(cron = " 0 4 6 * * * ")
	@PostMapping("/exchangerate")
	public ResponseEntity<ExchangeRate> setExchangeRate(){
		log.info("Start ExchangeRateController");
		try {
			SmartMacUtils sm = new SmartMacUtils();
			ExchangeRate er = exchangeRateRepository.save(sm.readExchangeRateJsonFromUrl(SmartMacUtils.URL_EXCHANGERATE));
			log.info("Finish ExchangeRateController OK");
		return new ResponseEntity<>(er, HttpStatus.CREATED);
	} catch (Exception e) {
		log.info("Finish ExchangeRateController BAD");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
}
