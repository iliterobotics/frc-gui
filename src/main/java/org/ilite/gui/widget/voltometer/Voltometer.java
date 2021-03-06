package org.ilite.gui.widget.voltometer;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import org.ilite.gui.widget.voltometer.skins.VoltometerSkin;

public class Voltometer extends Control{
	private FloatProperty voltageProperty;  
	
	public Voltometer( float startVolt ) {
		voltageProperty = new SimpleFloatProperty(startVolt); 
		
		setOnSwipeLeft(observable -> { 
			setVoltage(getVoltage() -1);
		});
		setOnSwipeRight(observable -> {
			setVoltage(getVoltage() +1); 
		});
	}
	public FloatProperty getVoltageProperty()
	{
		return voltageProperty; 
	}
	public float getVoltage()
	{
		return voltageProperty.floatValue();
	}
	public void setVoltage(float volt)
	{
		voltageProperty.set(volt);
	}
	public Skin<Voltometer> createDefaultSkin()
	{
		return new VoltometerSkin(this); 
	}

}
