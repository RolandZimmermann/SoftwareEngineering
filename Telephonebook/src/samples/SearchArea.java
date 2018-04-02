package samples;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchArea {
	private final AnchorPane anchorPane = new AnchorPane();
	private final Button searchButton = new Button("Search");
	private final TextField textField = new TextField();
	
	public SearchArea() {
		
		AnchorPane.setTopAnchor(textField, 10.0);
		AnchorPane.setLeftAnchor(textField, 10.0);
		AnchorPane.setRightAnchor(textField, 90.0);
		
		AnchorPane.setRightAnchor(searchButton, 20.0);
		AnchorPane.setTopAnchor(searchButton, 10.0);
		
		anchorPane.getChildren().addAll(searchButton, textField);
	}

	public Node getPane() {
		return anchorPane;
	}
	

}
