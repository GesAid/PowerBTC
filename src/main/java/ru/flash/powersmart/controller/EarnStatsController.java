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

import ru.flash.powersmart.model.EarnStats;
import ru.flash.powersmart.repository.EarnStatsRepository;
import ru.flash.powersmart.util.SmartMacUtils;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EarnStatsController {
	@Autowired
	EarnStatsRepository earnStatsRepository;
	
    private static final Logger log = LoggerFactory.getLogger(EarnStatsController.class);
	
	@Scheduled(cron = " 0 2 6 * * * ")
	@PostMapping("/earnstats")
	public ResponseEntity<EarnStats> setEarnStats(){
		log.info("Start EarnStatsController");
		try {
			SmartMacUtils sm = new SmartMacUtils();
			EarnStats es = earnStatsRepository.save(sm.readEarnStatsJsonFromUrl(SmartMacUtils.URL_EARNSTATS));
			log.info("Finish EarnStatsController OK");
		return new ResponseEntity<>(es, HttpStatus.CREATED);
	} catch (Exception e) {
		log.info("Finish EarnStatsController BAD");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

}
