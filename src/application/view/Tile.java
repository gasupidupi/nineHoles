package application.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Tile {
    int positionX;
    int positionY;
    private Pane pane;

    Tile(int x, int y) {
        pane = new Pane();                
        positionX = x;
        positionY = y;
        pane.setOnMouseClicked(e -> {
                    System.out.println("y: " + y + " x: " + x);
                    Position.x = x;
                    Position.y = y;

                }
        );
    }
    

	public Node getPane() {
		
		return pane;
	}


}
