package ru.flash.powersmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.flash.powersmart.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
	 List<Farmer> findById(long id);
	  List<Farmer> findBywName(String wName);
}
