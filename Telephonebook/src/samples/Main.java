package samples;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import data.TelefonBook;
import data.TelefonEntry;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.EntryArea;

public class Main extends Application {
	static final Path path1 = Paths.get("src/telefonbook.json");
	static final Path path2 = Paths.get("src/telefonbook2.json");

	private ObservableList<TelefonEntry> copy = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Pane tb1 = createTelefonBookArea(path1);
		Pane tb2 = createTelefonBookArea(path2);

		root.setLeft(tb1);
		root.setCenter(tb2);

		primaryStage.setTitle("TelephoneBook");
		primaryStage.setScene(new Scene(root, 600, 600));
		primaryStage.show();
	}

	private Pane createTelefonBookArea(Path path) {
		BorderPane root = new BorderPane();

		TelefonBook telefonBook = new TelefonBook(path);

		EntryArea entryArea = new EntryArea(telefonBook.getNumbers());
		entryArea.setItems(telefonBook.getNumbers());
		SearchArea searchArea = new SearchArea(searchText -> {
			telefonBook.search(searchText);
			return null;
		});
		DeleteArea deleteArea = new DeleteArea(() -> {
			telefonBook.removeAll(entryArea.getSelectedEntries());
			return null;
		}, () -> {
			if (copy != null) {
				telefonBook.createEntry(copy.get(0));
				copy = null;
			} else {
				telefonBook.createEntry(new TelefonEntry());
			}
			return null;
		}, () -> {
			telefonBook.save();
			return null;
		}, () -> {
			FileLoader fileLoader = new FileLoader(new Stage());
			File file = fileLoader.chooseFile();
			if (file != null) {
				telefonBook.loadFrom(Paths.get(file.getAbsolutePath()));
			}
			return null;
		}, () -> {
			copy = entryArea.getSelectedEntries();
			return null;
		});
		
		//another Way to implement lambda expressions
//		root.setOnDragEntered(new EventHandler<DragEvent>() {
//
//			@Override
//			public void handle(DragEvent e) {
//				File file = getFileFromDraggedEvent(e);
//
//				if (file == null || !file.getName().endsWith(".json")) {
//					return;
//				}
//
//				e.acceptTransferModes(TransferMode.LINK);
//			}
//			
//		});
//		
		root.setOnDragEntered(e -> {
			File file = getFileFromDraggedEvent(e);

			if (file == null || !file.getName().endsWith(".json")) {
				return;
			}

			e.acceptTransferModes(TransferMode.LINK);
		});
		
		root.setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.LINK);
		});

		root.setOnDragDropped(e -> {
			File file = getFileFromDraggedEvent(e);
			if (file != null) {
				telefonBook.loadFrom((Paths.get(file.getAbsolutePath())));
			}
		});

		root.setTop(searchArea.getPane());
		root.setBottom(deleteArea.getPane());
		root.setCenter(entryArea.getAnchorPane());

		return root;
	}

	private File getFileFromDraggedEvent(DragEvent e) {
		final Dragboard dragboard = e.getDragboard();
		@SuppressWarnings("unchecked")
		final List<File> files = (List<File>) dragboard.getContent(DataFormat.FILES);

		if (files == null || files.size() != 1) {
			return null;
		}

		final Path newPath = Paths.get(files.get(0).getAbsolutePath());

		File file = files.get(0);
		return file;
	}

	public static void main(String[] args) {

		launch(args);
	}
}
