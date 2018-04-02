package data;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelefonBook {

	// final == Liste kann geändert werden. nur keine neue Referenz
	//private final List<TelefonNumber> telefonNumbers = Collections.unmodifiableList(new ArrayList<>());
	private final ObservableList<TelefonEntry> observableTelefonEntrys = FXCollections
			.observableArrayList(new ArrayList<>());

	public TelefonBook() {
		observableTelefonEntrys.add(new TelefonEntry("Zimmermann", "Roland", "123456789"));
	}

	public ObservableList<TelefonEntry> getNumbers() {
		return observableTelefonEntrys;
	}
}
