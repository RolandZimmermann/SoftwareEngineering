package samples;

import data.TelefonBook;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.EntryArea;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        
        TelefonBook telefonBook = new TelefonBook();
        EntryArea entryArea = new EntryArea(telefonBook.getNumbers());
        SearchArea searchArea = new SearchArea(searchText -> {
        	telefonBook.search(searchText);
        	return null;
        });
        DeleteArea deleteArea = new DeleteArea(
        		() -> {
        			telefonBook.removeAll(entryArea.getSelectedEntries());
        			return null;
        		},  () -> { 
        			telefonBook.createEntry();
					return null; 
    			}
		);        
        
        root.setTop(searchArea.getPane());
        root.setBottom(deleteArea.getPane());
        root.setCenter(entryArea.getAnchorPane());
        
        primaryStage.setTitle("TelephoneBook by Roland Zimmermann");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
