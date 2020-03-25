package com.is2.theweather.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("visibility")
	private int visib;

	@SerializedName("timezone")
	private int timezone;

	@SerializedName("main")
	private Main main;

	@SerializedName("sys")
	private Sys sys;

	@SerializedName("dt")
	private int dt;

	@SerializedName("coord")
	private Coord coord;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("name")
	private String name;

	@SerializedName("cod")
	private int cod;

	@SerializedName("id")
	private int id;

	@SerializedName("base")
	private String base;

	@SerializedName("wind")
	private Wind wind;

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	public void setSys(Sys sys){
		this.sys = sys;
	}

	public Sys getSys(){
		return sys;
	}

	public void setDt(int dt){
		this.dt = dt;
	}

	public int getDt(){
		return dt;
	}

	public void setCoord(Coord coord){
		this.coord = coord;
	}

	public Coord getCoord(){return coord; }

	public void setMeteo(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setWind(Wind wind){
		this.wind = wind;
	}

	public Wind getWind(){
		return wind;
	}

}