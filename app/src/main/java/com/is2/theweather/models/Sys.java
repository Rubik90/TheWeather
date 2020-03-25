package com.is2.theweather.models;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Sys{

	@SerializedName("country")
	private String country;

	@SerializedName("sunrise")
	private int sunrise;

	@SerializedName("sunset")
	private int sunset;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private int type;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setSunrise(int sunrise){
		this.sunrise = sunrise;
	}

	public int getSunrise(){
		return sunrise;
	}

	public void setSunset(int sunset){
		this.sunset = sunset;
	}

	public int getSunset(){
		return sunset;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	@Override
	public String toString(){
		return
				"sunrise: " + this.getOraAlba(this.getSunrise()) + ", " +
				"sunset: "+ this.getOraTramonto(this.getSunset());
	}

	public String getOraAlba(long timestamp){

		SimpleDateFormat mytime = new SimpleDateFormat("HH:mm", Locale.getDefault());
		return mytime.format(timestamp*1000);
	}

	public String getOraTramonto(long timestamp){

		SimpleDateFormat mytime = new SimpleDateFormat("HH:mm", Locale.getDefault());
		return mytime.format(timestamp*1000);
	}

}