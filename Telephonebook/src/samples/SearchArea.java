package samples;

import java.util.function.Function;

import data.TelefonBook;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ui.EntryArea;

public class SearchArea {
	private final AnchorPane anchorPane = new AnchorPane();
	private final Label searchLabel = new Label("Search");
	private final TextField textField = new TextField();

	public SearchArea(Function<String, Void> f) {

		AnchorPane.setTopAnchor(textField, 10.0);
		AnchorPane.setLeftAnchor(textField, 10.0);
		AnchorPane.setRightAnchor(textField, 90.0);

		AnchorPane.setRightAnchor(searchLabel, 10.0);
		AnchorPane.setTopAnchor(searchLabel, 15.0);

		anchorPane.getChildren().addAll(searchLabel, textField);

		textField.textProperty().addListener((o) -> {
			f.apply(textField.getText());							
		});
		
		// ButtonFunktion

		//searchButton.setOnMouseClicked(this::buttonClicked);
		
//		searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent arg0) {
//				f.apply(textField.getText());				
//			}
//			
//		});
//		
		searchLabel.setOnMouseClicked(e -> {
			f.apply(textField.getText());
			//telefonbook.search(textField.getText());
		});
	}

	public Node getPane() {
		return anchorPane;
	}
	
	private void buttonClicked(MouseEvent e) {
		
	}

}
