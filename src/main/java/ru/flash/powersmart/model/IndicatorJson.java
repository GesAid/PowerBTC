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
@Table(name = "indicators")
public class IndicatorJson {
	
	
	public IndicatorJson() {
		
	}

	
	public IndicatorJson(JSONObject json) {
		energy1 = json.getLong("Wh1");
		energy2 = json.getLong("Wh2");
		energy3 =  json.getLong("Wh3");
		idintificator = json.getLong("ID");
		time = json.getLong("TIME");
		xzStat = json.getInt("STAT");
	    temperatura = json.getInt("T");
	    date_shtamp = SmartMacUtils.DATE;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonProperty("ID")
	private long idintificator;
	@JsonProperty("TIME")
	private long time;
	@JsonProperty("STAT")
	private int xzStat;
	private String date_shtamp;
	public String getDate_shtamp() {
		return date_shtamp;
	}


	public void setDate_shtamp(String date_shtamp) {
		this.date_shtamp = date_shtamp;
	}


	public void setTime(Long time) {
		this.time = time;
	}


	/*
	 * @JsonProperty("V1") // напряжение на первой фазе (линии) private double
	 * voltage1;
	 * 
	 * @JsonProperty("A1") // ток на первой фазе (линии) private double current1;
	 * 
	 * @JsonProperty("W1") // активная мощность на первой фазе, потребление private
	 * long actionPower1;
	 * 
	 * @JsonProperty("rW1") // активная реверсивная мощность, выработка (например,
	 * СЭС) private long revActionPower1;
	 */
	@JsonProperty("Wh1")  // энергия, накопительное потребление
	private long energy1;
	/*
	 * @JsonProperty("rWh1") // реверсивная энергия, накопительная выработка private
	 * long revEnergy1;
	 * 
	 * @JsonProperty("PF1") // Power Factor ( cos ф ) private double powerfactor1;
	 * 
	 * @JsonProperty("V2") // напряжение на первой фазе (линии) private double
	 * voltage2;
	 * 
	 * @JsonProperty("A2") // ток на первой фазе (линии) private double current2;
	 * 
	 * @JsonProperty("W2") // активная мощность на первой фазе, потребление private
	 * long actionPower2;
	 * 
	 * @JsonProperty("rW2") // активная реверсивная мощность, выработка (например,
	 * СЭС) private long revActionPower2;
	 */
	@JsonProperty("Wh2")  // энергия, накопительное потребление
	private long energy2;
	/*
	 * @JsonProperty("rWh2") // реверсивная энергия, накопительная выработка private
	 * long revEnergy2;
	 * 
	 * @JsonProperty("PF2") // Power Factor ( cos ф ) private double powerfactor2;
	 * 
	 * @JsonProperty("V3") // напряжение на первой фазе (линии) private double
	 * voltage3;
	 * 
	 * @JsonProperty("A3") // ток на первой фазе (линии) private double current3;
	 * 
	 * @JsonProperty("W3") // активная мощность на первой фазе, потребление private
	 * long actionPower3;
	 * 
	 * @JsonProperty("rW3") // активная реверсивная мощность, выработка (например,
	 * СЭС) private long revActionPower3;
	 */
	@JsonProperty("Wh3")  // энергия, накопительное потребление
	private long energy3;
/*	@JsonProperty("rWh3") // реверсивная энергия, накопительная выработка
	private long revEnergy3;
	@JsonProperty("PF3")  // Power Factor ( cos ф )
	private double powerfactor3;
*/	@JsonProperty("T")    // температура внутри устройства
	private long temperatura; 
	
	
	public long getId() {
		return id;
	}


	public long getIdintificator() {
		return idintificator;
	}


	public void setIdintificator(long idintificator) {
		this.idintificator = idintificator;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}


	public int getXzStat() {
		return xzStat;
	}


	public void setXzStat(int xzStat) {
		this.xzStat = xzStat;
	}


/*	public double getVoltage1() {
		return voltage1;
	}


	public void setVoltage1(double voltage1) {
		this.voltage1 = voltage1;
	}


	public double getCurrent1() {
		return current1;
	}


	public void setCurrent1(double current1) {
		this.current1 = current1;
	}


	public long getActionPower1() {
		return actionPower1;
	}


	public void setActionPower1(long actionPower1) {
		this.actionPower1 = actionPower1;
	}


	public long getRevActionPower1() {
		return revActionPower1;
	}


	public void setRevActionPower1(long revActionPower1) {
		this.revActionPower1 = revActionPower1;
	}

*/
	public long getEnergy1() {
		return energy1;
	}


	public void setEnergy1(long energy1) {
		this.energy1 = energy1;
	}

/*
	public long getRevEnergy1() {
		return revEnergy1;
	}


	public void setRevEnergy1(long revEnergy1) {
		this.revEnergy1 = revEnergy1;
	}


	public double getPowerfactor1() {
		return powerfactor1;
	}


	public void setPowerfactor1(double powerfactor1) {
		this.powerfactor1 = powerfactor1;
	}


	public double getVoltage2() {
		return voltage2;
	}


	public void setVoltage2(double voltage2) {
		this.voltage2 = voltage2;
	}


	public double getCurrent2() {
		return current2;
	}


	public void setCurrent2(double current2) {
		this.current2 = current2;
	}


	public long getActionPower2() {
		return actionPower2;
	}


	public void setActionPower2(long actionPower2) {
		this.actionPower2 = actionPower2;
	}


	public long getRevActionPower2() {
		return revActionPower2;
	}


	public void setRevActionPower2(long revActionPower2) {
		this.revActionPower2 = revActionPower2;
	}

*/
	public long getEnergy2() {
		return energy2;
	}


	public void setEnergy2(long energy2) {
		this.energy2 = energy2;
	}

/*
	public long getRevEnergy2() {
		return revEnergy2;
	}


	public void setRevEnergy2(long revEnergy2) {
		this.revEnergy2 = revEnergy2;
	}


	public double getPowerfactor2() {
		return powerfactor2;
	}


	public void setPowerfactor2(double powerfactor2) {
		this.powerfactor2 = powerfactor2;
	}


	public double getVoltage3() {
		return voltage3;
	}


	public void setVoltage3(double voltage3) {
		this.voltage3 = voltage3;
	}


	public double getCurrent3() {
		return current3;
	}


	public void setCurrent3(double current3) {
		this.current3 = current3;
	}


	public long getActionPower3() {
		return actionPower3;
	}


	public void setActionPower3(long actionPower3) {
		this.actionPower3 = actionPower3;
	}


	public long getRevActionPower3() {
		return revActionPower3;
	}


	public void setRevActionPower3(long revActionPower3) {
		this.revActionPower3 = revActionPower3;
	}
*/

	public long getEnergy3() {
		return energy3;
	}


	public void setEnergy3(long energy3) {
		this.energy3 = energy3;
	}

/*
	public long getRevEnergy3() {
		return revEnergy3;
	}


	public void setRevEnergy3(long revEnergy3) {
		this.revEnergy3 = revEnergy3;
	}


	public double getPowerfactor3() {
		return powerfactor3;
	}


	public void setPowerfactor3(double powerfactor3) {
		this.powerfactor3 = powerfactor3;
	}

*/
	public long getTemperatura() {
		return temperatura;
	}


	public void setTemperatura(long temperatura) {
		this.temperatura = temperatura;
	}
	
}
