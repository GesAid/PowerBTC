package ru.flash.powersmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.flash.powersmart.model.EarnStats;

public interface EarnStatsRepository extends JpaRepository<EarnStats, Long> {
	List<EarnStats> findById(long id);

}
