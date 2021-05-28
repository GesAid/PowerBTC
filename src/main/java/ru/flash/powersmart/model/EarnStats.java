package ru.flash.powersmart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.flash.powersmart.util.SmartMacUtils;

/**
 * @author aid
 *
 */
@Entity
@Table(name = "earn_stats")
public class EarnStats {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonProperty("earnings_yesterday")
	private long e_yesterday;
	private String date_shtamp;
	private double h_yesterday_size;

	private String h_yesterday_unit;
	
	public EarnStats() {
		
	}
	/**
	 * 
	 * @param json - объект из запроса
	 */
	public EarnStats(JSONObject json) {
		e_yesterday = json.getJSONObject("data").getLong("earnings_yesterday");
		h_yesterday_size = json.getJSONObject("data").getJSONObject("hashrate_yesterday")
				.getDouble("size");
		h_yesterday_unit = json.getJSONObject("data").getJSONObject("hashrate_yesterday")
				.getString("unit");
		date_shtamp = SmartMacUtils.DATE;
	}
	public long getE_yesterday() {
		return e_yesterday;
	}
	public void setE_yesterday(long e_yesterday) {
		this.e_yesterday = e_yesterday;
	}
	
	public String getDate_shtamp() {
		return date_shtamp;
	}
	public void setDate_shtamp(String date_shtamp) {
		this.date_shtamp = date_shtamp;
	}

	public double getH_yesterday_size() {
		return h_yesterday_size;
	}

	public void setH_yesterday_size(double h_yesterday_size) {
		this.h_yesterday_size = h_yesterday_size;
	}

	public String getH_yesterday_unit() {
		return h_yesterday_unit;
	}

	public void setH_yesterday_unit(String h_yesterday_unit) {
		this.h_yesterday_unit = h_yesterday_unit;
	}
	
}
