package com.is2.theweather.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Wind{

	@SerializedName("deg")
	private int deg;

	@SerializedName("speed")
	private double speed;

	public void setDeg(int deg){
		this.deg = deg;
	}

	public int getDeg(){
		return deg;
	}

	public void setSpeed(double speed){
		this.speed = speed;
	}

	public double getSpeed(){
		return speed;
	}

	@NonNull
	@Override
 	public String toString(){
		return 
			"deg: " + deg + ", " + " speed: " + speed + "m/s";
		}
}