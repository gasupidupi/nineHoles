package application;
	
import application.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		
		
		
		
		
		
		
		

		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/MainView.fxml"));
			Parent rootLayout = loader.load();
			MainViewController mainController = loader.getController();
			mainController.loadData();
			mainController.setPrimaryStage(primaryStage);
			Scene scene = new Scene(rootLayout);
		    scene.getStylesheets().add(
		    	      getClass().getResource("application.css").toExternalForm()
		    	    );

			primaryStage.setScene(scene);
			primaryStage.setTitle("Nine Holes");
			primaryStage.show();
			


			
			
			

		} catch(Exception e) {
			e.printStackTrace();
		}

			

		
	}
}
