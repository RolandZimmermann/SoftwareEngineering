package samples;

import java.nio.file.Path;
import java.nio.file.Paths;


import data.TelefonBook;
import data.TelefonEntry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.EntryArea;

public class Main extends Application {
    static final Path path1 = Paths.get("src/telefonbook.json");
    static final Path path2 = Paths.get("src/telefonbook2.json");

    private TelefonBook telefonBook = new TelefonBook();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
    	Pane tb1 = createTelefonBookArea(path1);
        
    	root.setLeft(tb1);
    	root.setCenter(createTelefonBookArea(path2));
    	
        primaryStage.setTitle("TelephoneBook");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }



	private Pane createTelefonBookArea(Path path) {
		BorderPane root = new BorderPane();

        EntryArea entryArea = new EntryArea(telefonBook.getNumbers());
        SearchArea searchArea = new SearchArea(searchText -> {
            telefonBook.search(searchText);
            return null;
        });
        DeleteArea deleteArea = new DeleteArea(() -> {
            telefonBook.removeAll(entryArea.getSelectedEntries());
            return null;
        }, () -> {
            telefonBook.createEntry(new TelefonEntry());
            return null;
        }, () -> {
        	TelefonBook.SaveTelefonbook(telefonBook, path);
            return null;
        }, () ->  {
        	telefonBook = TelefonBook.loadTelefonbook(path);
        	entryArea.setItems(telefonBook.getNumbers());
        	return null;
        });

        root.setTop(searchArea.getPane());
        root.setBottom(deleteArea.getPane());
        root.setCenter(entryArea.getAnchorPane());
		return root;
	}
    
    

    public static void main(String[] args) {

        launch(args);
    }
}
