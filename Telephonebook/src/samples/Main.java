package samples;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import data.TelefonBook;
import data.TelefonEntry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.EntryArea;

public class Main extends Application {
	static Path path1 = Paths.get("src/telefonbook.json");
	static Path path2 = Paths.get("src/telefonbook2.json");
	
	private static Path usedPath1 = path1;
	private static Path usedPath2 = path2;

	private TelefonBook telefonBook = new TelefonBook();
	private TelefonBook telefonBook2 = new TelefonBook();
	
	private Pane tb1 = createTelefonBookArea(path1);
	private Pane tb2 = createTelefonBookArea(path2);

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		root.setLeft(tb1);
		root.setCenter(tb2);
		
		
		primaryStage.setTitle("TelephoneBook");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();
	}

	private Pane createTelefonBookArea(Path path) {
		if (path == usedPath1) {
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

				TelefonBook.SaveTelefonbook(telefonBook, path1);
				return null;
			}, () -> {
				FileLoader fileLoader = new FileLoader(new Stage());
				File file = fileLoader.chooseFile();
				if (file != null) {
					telefonBook = TelefonBook.loadTelefonbook(Paths.get(file.getAbsolutePath()));
					entryArea.setItems(telefonBook.getNumbers());
					path1 = Paths.get(file.getAbsolutePath());
				}
				return null;
			});

			telefonBook = TelefonBook.loadTelefonbook(path);
			entryArea.setItems(telefonBook.getNumbers());
			
			root.setOnDragEntered(e-> {
				final Dragboard dragboard = e.getDragboard();
			    @SuppressWarnings("unchecked")
				final List<File> files = (List<File>) dragboard.getContent(DataFormat.FILES);

			    if (files == null || files.size() != 1) {
			        return;
			    }

			    final Path newPath = Paths.get(files.get(0).getAbsolutePath());
			    
			    File file = files.get(0);
			    
			    if(!file.getName().endsWith(".json")) {
			    	return;
			    }
			
				if (file != null) {
					telefonBook = TelefonBook.loadTelefonbook(Paths.get(file.getAbsolutePath()));
					entryArea.setItems(telefonBook.getNumbers());
					path1 = newPath;
				}
			});

			root.setTop(searchArea.getPane());
			root.setBottom(deleteArea.getPane());
			root.setCenter(entryArea.getAnchorPane());

			return root;
		}
		
		if (path == usedPath2) {
			BorderPane root = new BorderPane();

			EntryArea entryArea = new EntryArea(telefonBook2.getNumbers());
			SearchArea searchArea = new SearchArea(searchText -> {
				telefonBook2.search(searchText);
				return null;
			});
			DeleteArea deleteArea = new DeleteArea(() -> {
				telefonBook2.removeAll(entryArea.getSelectedEntries());
				return null;
			}, () -> {
				telefonBook2.createEntry(new TelefonEntry());
				return null;
			}, () -> {

				TelefonBook.SaveTelefonbook(telefonBook2, path2);
				return null;
			}, () -> {
				FileLoader fileLoader = new FileLoader(new Stage());
				File file = fileLoader.chooseFile();
				if (file != null) {
					telefonBook2 = TelefonBook.loadTelefonbook(Paths.get(file.getAbsolutePath()));
					entryArea.setItems(telefonBook2.getNumbers());
					path2 = Paths.get(file.getAbsolutePath());
				}
				return null;
			});

			telefonBook2 = TelefonBook.loadTelefonbook(path);
			entryArea.setItems(telefonBook2.getNumbers());
			
			root.setOnDragEntered(e-> {
				final Dragboard dragboard = e.getDragboard();
			    @SuppressWarnings("unchecked")
				final List<File> files = (List<File>) dragboard.getContent(DataFormat.FILES);

			    if (files == null || files.size() != 1) {
			        return;
			    }

			    final Path newPath = Paths.get(files.get(0).getAbsolutePath());

			    File file = files.get(0);
			    
			    if(!file.getName().endsWith(".json")) {
			    	return;
			    }
			    
				if (file != null) {
					telefonBook2 = TelefonBook.loadTelefonbook(Paths.get(file.getAbsolutePath()));
					entryArea.setItems(telefonBook2.getNumbers());
					path2 = newPath;
				}
			});

			root.setTop(searchArea.getPane());
			root.setBottom(deleteArea.getPane());
			root.setCenter(entryArea.getAnchorPane());

			return root;
		}
		return null;
	}

	public static void main(String[] args) {

		launch(args);
	}
}
