package application.view;

import application.Main;
import application.State;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Tile {
    int positionX;
    int positionY;
    private Pane pane;
	private EventHandler<ActionEvent> clickHandler;
    static int currentTileX;
    static int currentTileY;
    static boolean currentClick;


    Tile(int x, int y) {
        pane = new Pane();                
        positionX = x;
        positionY = y;
        pane.setOnMouseClicked(e -> {
                    System.out.println("y: " + y + " x: " + x);
                    
                    currentClick = !currentClick;
                    
                    
                    
                    if(MainViewController.board.getState()==State.MOVE) {
                    	
                    }

                    if(currentClick == true) {
                    	pane.setBackground(new Background(new BackgroundFill(Color.web("#48d1cc"), CornerRadii.EMPTY, Insets.EMPTY)));
                        pane.setOpacity(0.5);

                    } else if(currentClick == false) {
                    	pane.setBackground(new Background(new BackgroundFill(Color.web("#ccd148"), CornerRadii.EMPTY, Insets.EMPTY)));
                        pane.setOpacity(0.5);
                    }

                    
                    pane.setOpacity(0.5);
                    
                    setCurrentTileX(x);
                    setCurrentTileY(y);

                    fireClick();
                }
        );
    }
    

	private void setCurrentTileY(int y) {
		currentTileY = y;
		
	}


	private void setCurrentTileX(int x) {
		currentTileX = x;
		
	}


	public void setOnClickHandler(EventHandler<ActionEvent> handler) {
		this.clickHandler = handler;
	}
	
	private void fireClick() {
		clickHandler.handle(new ActionEvent());
	}
	
	public Node getPane() {
		
		return pane;
	}


	public static int getCurrentTyleY() {
		// TODO Auto-generated method stub
		return currentTileX;
	}
	public static int getCurrentTyleX() {
		// TODO Auto-generated method stub
		return currentTileY;
	}
	
	public static boolean getCurrentClick() {
		// TODO Auto-generated method stub
		return currentClick;
	}
	
	


}
