package application.view;



import java.io.IOException;

import application.AI;
import application.Board;
import application.Human;
import application.Player;
import application.State;
import application.model.PieceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {


	
	@FXML
	private Label message;
	
	@FXML
	private GridPane boardTable;
	
	private Stage primaryStage;
	
	Board board = new Board();
	



		Player player1 = new AI(board, Board.PIECE_X);
		Player player2 = new Human(board, Board.PIECE_O);
		Player currentPlayer = player1;

		
	
	
	@FXML
	private void select() {
		
		

	}

	public void nextAction() {

		
		State state = board.getState();
		
		switch (state) {
		case DROP:
			if(currentPlayer instanceof Human) {
				
				
			} else {
				currentPlayer.nextDrop();
			}
			message.setTextFill(Color.web("#00ff00"));
			message.setText("Dropping Phase.");

			loadData();
			break;
		case MOVE:
			if(currentPlayer instanceof Human) {
				
				
			} else {
				currentPlayer.nextMove();
			}
			message.setTextFill(Color.web("#00ff00"));
			message.setText("Moving Phase.");
			loadData();
			break;
		case O_WON:
			System.out.println("Spieler O hat gewonnen.");
			message.setTextFill(Color.web("#ff0000"));
			message.setText("Player " + board.PIECE_O + " has won.");
			loadData();
			break;
		case X_WON:
			System.out.println("Spieler X hat gewonnen.");
			message.setTextFill(Color.web("#ff0000"));
			message.setText("Player " + board.PIECE_X + " has won.");
		loadData();
			break;
		case DRAW:
			System.out.println("1000 Züge übertroffen. Es ist unentschieden.");
			message.setTextFill(Color.web("#ff0000"));
			message.setText("It's a draw.");
			loadData();
			break;
		}
		
		if (currentPlayer != player2) {
			currentPlayer = player2;
		} else {
			currentPlayer = player1;
		}
		
	}
	

	

	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            return node;
	        }
	    }

	    return result;
	}
	
	public void loadData() {
		
//		board.setPiece(1, 1, board.PIECE_X);
		clearData();
		
		for (int y = 0; y < board.field.length; y++) {
			for (int x = 0; x < board.field[y].length; x++) {
				
					Character fieldChar = board.getFieldChar(y, x);
					Label label = new Label(fieldChar.toString());
					label.setFont(new Font("Arial", 40));

					label.setTextAlignment(TextAlignment.CENTER);
					boardTable.add(label, x, y);

	                Tile tile = new Tile(y, x);
	                GridPane.setConstraints(tile.getPane(), y, x);
	                boardTable.getChildren().add(tile.getPane());
	                Position.state = board.getState();
	       
			}
		}
		

	}

	public void clearData() {
		boardTable.getChildren().clear();
	}
	
	
	public void setPrimaryStage(Stage s) {
		this.primaryStage = s;
	}


	

}