package com.is2.theweather.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	private double temp;

	@SerializedName("temp_min")
	private double tempMin;

	@SerializedName("humidity")
	private int umid;

	@SerializedName("pressure")
	private int press;

	@SerializedName("temp_max")
	private double tempMax;

	public void setTemp(double temp){
		this.temp = temp;
	}

	public double getTemp(){
		return temp;
	}

	public void setTempMin(double tempMin){
		this.tempMin = tempMin;
	}

	public double getTempMin(){
		return tempMin;
	}

	public void setUmid(int umid){
		this.umid = umid;
	}

	public int getUmid(){
		return umid;
	}

	public void setPress(int press){
		this.press = press;
	}

	public double getPress(){	return press;	}

	public void setTempMax(int tempMax){
		this.tempMax = tempMax;
	}

	public double getTempMax(){	return tempMax;	}

	@NonNull
 	public String toString(){
		return
			"   t_min: " + tempMin + "°, " +
			"t_max: " + tempMax + "° " +
			"humid: " + umid + "%, " +
			"press: " + press + "hPa";

	}
}