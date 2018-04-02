package data;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelefonBook {

	// final == Liste kann geändert werden. nur keine neue Referenz
	// private final List<TelefonNumber> telefonNumbers =
	// Collections.unmodifiableList(new ArrayList<>());
	private final ObservableList<TelefonEntry> observableTelefonEntrys = FXCollections
			.observableArrayList(new ArrayList<>());

	public TelefonBook() {
		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland", "123456789"));
		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland 2", "987654321"));
	}

	public void createEntry() {
		observableTelefonEntrys.add(new TelefonEntry());
	}

	public List<TelefonEntry> search(String string) {
		ObservableList<TelefonEntry> list = FXCollections.observableArrayList(new ArrayList<>());

		for (TelefonEntry n : observableTelefonEntrys) {
			if (n.getFirstName().contains(string) || n.getLastName().contains(string)
					|| n.getNumber().contains(string)) {
				list.add(n);
			}
		}
		return list;
	}

	public ObservableList<TelefonEntry> getNumbers() {
		return observableTelefonEntrys;
	}
}
