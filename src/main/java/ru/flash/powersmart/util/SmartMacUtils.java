package ru.flash.powersmart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import ru.flash.powersmart.model.EarnStats;
import ru.flash.powersmart.model.ExchangeRate;
import ru.flash.powersmart.model.Farmer;
import ru.flash.powersmart.model.IndicatorJson;

@Controller
public class SmartMacUtils {

	public static String DATE;
	public static String URL_SMARTMAC = "https://dash.smart-mac.com/api?devid="+"ID"+"&period=day&apikey="+"APIKEY";
	public static String URL_EARNSTATS = "https://pool.api.btc.com/v1/account/earn-stats"
			+ "?access_key="+"KEY"+"&puid="+"PUID";
	public static String URL_EXCHANGERATE = "https://pool.api.btc.com/v1/pool/status"
			+ "?access_key="+"KEY"+"&puid="+"PUID";
	public static String URL_FARMER = "https://pool.api.btc.com/v1/worker"
			+ "?access_key="+"KEY"+"&puid="+"PUID"+"&page_size=60&order_by=shares_1d";

	@Scheduled(cron = " 0 0 6 * * * ")
	public void setTime() {
		long date = java.util.Calendar.getInstance().getTimeInMillis();
		DATE = date + "0";
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public IndicatorJson readIndicatorJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText.replace("[", "").replace("]", ""));
			IndicatorJson ij = new IndicatorJson(json);
			return ij;
		} finally {
			is.close();
		}

	}

	public List<Farmer> readFarmerJsonFromUrl(String url) throws IOException {
		List<Farmer> farmers = new ArrayList<Farmer>();
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject joFarmers = new JSONObject(jsonText);
			JSONArray arrFarmers = joFarmers.getJSONObject("data").getJSONArray("data");
			for (int i = 0; i < arrFarmers.length(); i++) {
				Farmer farmer = new Farmer(arrFarmers.getJSONObject(i));
				farmers.add(farmer);
			}
			return farmers;
		} finally {
			is.close();
		}
	}

	public EarnStats readEarnStatsJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject joFarmers = new JSONObject(jsonText);
			EarnStats es = new EarnStats(joFarmers);
			return es;

		} finally {
			is.close();
		}
	}

	public ExchangeRate readExchangeRateJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject joExchangeRate = new JSONObject(jsonText);
			ExchangeRate er = new ExchangeRate(joExchangeRate);
			return er;

		} finally {
			is.close();
		}
	}
}
