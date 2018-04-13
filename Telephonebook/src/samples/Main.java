package samples;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.TelefonBook;
import data.TelefonEntry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.EntryArea;

public class Main extends Application {
static final Path path = Paths.get("C:/Users/geiers/Desktop/test.json");

private TelefonBook telefonBook = new TelefonBook();
	@Override
	public void start(Stage primaryStage) throws Exception {
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
			JsonFactory factory = new JsonFactory();
			try (OutputStream os = Files.newOutputStream(path); JsonGenerator jg = factory.createGenerator(os)) {
				// Verwenden Sie jg um fuer jeden Eintrag im Telefonbuch
				// entsprechende Objekte im JSON zu erzeugen
				jg.writeStartArray();
				for (TelefonEntry e : telefonBook.getAllEntrysAsArray()) {
					jg.writeStartObject();
					jg.writeStringField("LastName", e.getLastName());
					jg.writeStringField("FirstName", e.getFirstName());
					jg.writeStringField("Number", e.getNumber());

					jg.writeEndObject();
				}
				jg.writeEndArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		});

		root.setTop(searchArea.getPane());
		root.setBottom(deleteArea.getPane());
		root.setCenter(entryArea.getAnchorPane());

		//LOAD
		
		try (InputStream is = Files.newInputStream(path)) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode roots = mapper.readTree(is);
			// Verwenden Sie den ObjectMapper hier , um
			// die Daten aus der Datei auszulesen.
			for (JsonNode j : roots) {
				telefonBook.createEntry(new TelefonEntry(j.path("LastName").asText(), j.path("FirstName").asText(),
						j.path("Number").asText()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		primaryStage.setTitle("TelephoneBook");
		primaryStage.setScene(new Scene(root, 500, 600));
		primaryStage.show();
	}
	
	
	@Override
	public void stop() throws Exception {
		
		//SAVE
		
		JsonFactory factory = new JsonFactory();
		try (OutputStream os = Files.newOutputStream(path); JsonGenerator jg = factory.createGenerator(os)) {
			// Verwenden Sie jg um fuer jeden Eintrag im Telefonbuch
			// entsprechende Objekte im JSON zu erzeugen
			jg.writeStartArray();
			for (TelefonEntry e : telefonBook.getAllEntrysAsArray()) {
				jg.writeStartObject();
				jg.writeStringField("LastName", e.getLastName());
				jg.writeStringField("FirstName", e.getFirstName());
				jg.writeStringField("Number", e.getNumber());

				jg.writeEndObject();
			}
			jg.writeEndArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.stop();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
