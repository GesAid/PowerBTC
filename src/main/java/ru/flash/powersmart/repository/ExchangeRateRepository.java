package ru.flash.powersmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.flash.powersmart.model.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
	List<ExchangeRate> findById(long id);

}
