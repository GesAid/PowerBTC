package ru.flash.powersmart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import ru.flash.powersmart.util.SmartMacUtils;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonProperty("BTC2USD")
	private double btc2usd;
	private String date_shtamp;
	public String getDate_shtamp() {
		return date_shtamp;
	}
	public void setDate_shtamp(String date_shtamp) {
		this.date_shtamp = date_shtamp;
	}
	public ExchangeRate() {
		// TODO Auto-generated constructor stub
	}
	public ExchangeRate(JSONObject joExchangeRate) {
		btc2usd = joExchangeRate.getJSONObject("data").getJSONObject("exchange_rate")
				.getDouble("BTC2USD");
		date_shtamp = SmartMacUtils.DATE;
	}
	public double getBtc2usd() {
		return btc2usd;
	}

	public void setBtc2usd(double btc2usd) {
		this.btc2usd = btc2usd;
	}
	
}
