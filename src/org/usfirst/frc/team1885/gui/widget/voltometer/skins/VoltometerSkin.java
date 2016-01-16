package org.usfirst.frc.team1885.gui.widget.voltometer.skins;

import org.usfirst.frc.team1885.gui.widget.voltometer.Voltometer;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VoltometerSkin extends SkinBase<Voltometer>{

	private static final int REG_HEIGHT = 100, REG_WIDTH = 400; 
	private Text mid, min, max, value; 
	
	private VBox background; 
	private Region arm; 
	
	private StackPane track, textBackground;
	
	
	public VoltometerSkin(Voltometer control) {
		super(control);
		
		control.getStyleClass().setAll("voltometer"); 
		control.getStylesheets().add("/org/usfirst/frc/team1885/gui/css/Voltometer.css");
		
		initGraphics(); 
		setUpListeners(); 
	}
	public void initGraphics(){
		
	
		background = new VBox(); 
		getSkinnable().getStyleClass().setAll("background"); 
		getChildren().add(background);
		
		mid = new Text("0"); 
		min = new Text("-1");
		max = new Text("1");
		value = new Text("0"); 
		
		
		textBackground = new StackPane(min, mid, max); 
		background.getChildren().add(textBackground); 
		textBackground.getStyleClass().setAll("textBackground");
		StackPane.setAlignment(min, Pos.BOTTOM_LEFT);
		StackPane.setAlignment(mid, Pos.BOTTOM_CENTER);
		StackPane.setAlignment(max, Pos.BOTTOM_RIGHT);
		
		
		
		
		track = new StackPane(); 
		track.getStyleClass().setAll("track"); 
		background.getChildren().add(track); 
		track.setMinSize(REG_WIDTH, REG_HEIGHT);
		track.setMaxSize(REG_WIDTH, REG_HEIGHT);
		track.getChildren().add(value); 
		
		arm = new Region(); 
		arm.getStyleClass().setAll("arm"); 
		track.getChildren().add(arm); 
		
		
	}
	public void setUpListeners()
	{ 
		getSkinnable().getVoltageProperty().addListener(changeListener -> {
			TranslateTransition move = new TranslateTransition (Duration.millis(333), arm);
			value.setText(String.format("%1.2f", getSkinnable().getVoltage()) + "v"); 
			move.setToX(getSkinnable().getVoltage()*(REG_WIDTH>>1));			
			move.play(); 
		});
		getSkinnable().setOnMouseClicked(observable -> {
			getSkinnable().setVoltage(getSkinnable().getVoltage() + 0.2f);
		});
		
	}
		
	
}