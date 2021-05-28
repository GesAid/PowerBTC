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
@Table(name = "farmer")
public class Farmer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonProperty("group_name")
	private String gName;
	@JsonProperty("worker_id")
	private String wId;
	@JsonProperty("worker_name")
	private String wName;
	@JsonProperty("shares_1d")
	private String shares;
	@JsonProperty("shares_1d_unit")
	private String sharesUnit;
	private String date_shtamp;
	
	public String getDate_shtamp() {
		return date_shtamp;
	}

	public void setDate_shtamp(String date_shtamp) {
		this.date_shtamp = date_shtamp;
	}

	public Farmer() {
	}

	public Farmer(JSONObject json) {
			gName = json.optString("group_name", "none");
		wId = json.getString("worker_id");
		wName = json.getString("worker_name");
		shares = json.getString("shares_1d");
		sharesUnit = json.getString("shares_1d_unit");
		date_shtamp = SmartMacUtils.DATE;
	}
	public long getId() {
		return id;
	} 
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getwId() {
		return wId;
	}
	public void setwId(String wId) {
		this.wId = wId;
	}
	public String getwName() {
		return wName;
	}
	public void setwName(String wName) {
		this.wName = wName;
	}
	public String getShares() {
		return shares;
	}
	public void setShares(String shares) {
		this.shares = shares;
	}
	public String getSharesUnit() {
		return sharesUnit;
	}
	public void setSharesUnit(String sharesUnit) {
		this.sharesUnit = sharesUnit;
	}
	
	
	
	
}
