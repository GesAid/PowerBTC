package ru.flash.powersmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.flash.powersmart.model.IndicatorJson;

public interface IndicatorRepository extends JpaRepository<IndicatorJson, Long> {

	 List<IndicatorJson> findByTime(Long time);
	  List<IndicatorJson> findByTemperatura(long temperatura);

}
