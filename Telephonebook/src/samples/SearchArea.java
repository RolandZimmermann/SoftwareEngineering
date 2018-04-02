package samples;

import data.TelefonBook;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;

public class SearchArea {
	private final AnchorPane anchorPane = new AnchorPane();
	private final Button searchButton = new Button("Search");
	private final TextField textField = new TextField();

	public SearchArea(TelefonBook telefonbook, EntryArea entryArea) {

		AnchorPane.setTopAnchor(textField, 10.0);
		AnchorPane.setLeftAnchor(textField, 10.0);
		AnchorPane.setRightAnchor(textField, 90.0);

		AnchorPane.setRightAnchor(searchButton, 10.0);
		AnchorPane.setTopAnchor(searchButton, 10.0);

		anchorPane.getChildren().addAll(searchButton, textField);

		searchButton.setOnMouseClicked(e -> {
			if (textField.getText() != "") {
				entryArea.setItems(telefonbook.search(textField.getText()));
			} else {
				entryArea.setItems(telefonbook.getNumbers());
			}
			;
		});
	}

	public Node getPane() {
		return anchorPane;
	}

}
