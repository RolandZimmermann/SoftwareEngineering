package data;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class TelefonBook {

	// final == Liste kann ge�ndert werden. nur keine neue Referenz
	// private final List<TelefonNumber> telefonNumbers =
	// Collections.unmodifiableList(new ArrayList<>());
	private final ObservableList<TelefonEntry> observableTelefonEntrys = FXCollections
			.observableArrayList(new ArrayList<>());

	private final FilteredList<TelefonEntry> filteredList = new FilteredList(observableTelefonEntrys);

	public TelefonBook() {
//		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland", "123456789"));
//		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland 2", "987654321"));
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
