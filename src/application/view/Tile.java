package application.view;

import application.State;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
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
    static int currentTileX;
    static int currentTileY;

    Tile(int x, int y) {
        pane = new Pane();                
        positionX = x;
        positionY = y;
        pane.setOnMouseClicked(e -> {
                    System.out.println("y: " + y + " x: " + x);
                    
                    if(MainViewController.board.getState()==State.MOVE) {
                    	
                    }

                    
                    pane.setBackground(new Background(new BackgroundFill(Color.web("#48d1cc"), CornerRadii.EMPTY, Insets.EMPTY)));
                    pane.setOpacity(0.5);
                    
                    setCurrentTileX(x);
                    setCurrentTileY(y);


                }
        );
    }
    

	private void setCurrentTileY(int y) {
		currentTileY = y;
		
	}


	private void setCurrentTileX(int x) {
		currentTileX = x;
		
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


}
