package samples;

import data.TelefonBook;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;

public class DeleteArea {
	private Button deleteButton = new Button("Delete");
	private Button addButton = new Button("New Entry");
	private final AnchorPane anchorPane = new AnchorPane();

	public DeleteArea(TelefonBook telefonBook, EntryArea entryArea) {

		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 10.0);
		AnchorPane.setLeftAnchor(addButton, 10.0);
		AnchorPane.setBottomAnchor(addButton, 10.0);
		anchorPane.getChildren().addAll(deleteButton, addButton);
		// TODO: Lose Kopplung mit :: nicht möglich?
		deleteButton.setOnMouseClicked(e -> {
			telefonBook.getNumbers().removeAll(entryArea.getSelectedEntries());
			entryArea.setItems(telefonBook.getNumbers());
		});
		addButton.setOnMouseClicked(e -> {
			telefonBook.createEntry();
			entryArea.setItems(telefonBook.getNumbers());
		});
	}

	public Node getPane() {
		return anchorPane;
	}
}
