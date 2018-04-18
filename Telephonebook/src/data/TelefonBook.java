package data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class TelefonBook {

	// final == Liste kann geändert werden. nur keine neue Referenz
	// private final List<TelefonNumber> telefonNumbers =
	// Collections.unmodifiableList(new ArrayList<>());
	private final ObservableList<TelefonEntry> observableTelefonEntrys = FXCollections
			.observableArrayList(new ArrayList<>());

	private final FilteredList<TelefonEntry> filteredList = new FilteredList(observableTelefonEntrys);

	public TelefonBook() {
//		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland", "123456789"));
//		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland 2", "987654321"));
	}

	public static void SaveTelefonbook(TelefonBook telefonBook, Path path) {
		 JsonFactory factory = new JsonFactory();
         try (OutputStream os = Files.newOutputStream(path); 
        		 JsonGenerator jg = factory.createGenerator(os)) {
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
	}
	
	public static TelefonBook loadTelefonbook(Path path) {
		if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS))
			try {
				Files.createFile(path);
				return new TelefonBook();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else
            try (InputStream is = Files.newInputStream(path)) {
            	TelefonBook telefonBook = new TelefonBook();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode roots = mapper.readTree(is);
                // Verwenden Sie den ObjectMapper hier , um
                // die Daten aus der Datei auszulesen.
                for (JsonNode j : roots) {
                    telefonBook.createEntry(new TelefonEntry(j.path("LastName").asText(), j.path("FirstName").asText(),
                            j.path("Number").asText()));
                }
                return telefonBook;
            } catch (IOException e) {
                e.printStackTrace();
            }
		return null;
	}


	public void createEntry(TelefonEntry e) {
		observableTelefonEntrys.add(e);
	}

	public void search(String string) {
		filteredList.setPredicate(n -> {
			return (n.getFirstName().toUpperCase().contains(string.toUpperCase())
					|| n.getLastName().toUpperCase().contains(string.toUpperCase())
					|| n.getNumber().toUpperCase().contains(string.toUpperCase()));
		});
	}

	public ObservableList<TelefonEntry> getNumbers() {
		return filteredList;
	}

	public void removeAll(ObservableList<TelefonEntry> selectedEntries) {
		observableTelefonEntrys.removeAll(selectedEntries);
	}

	public TelefonEntry[] getAllEntrysAsArray() {
		return observableTelefonEntrys.toArray(new TelefonEntry[0]);
	}
}
