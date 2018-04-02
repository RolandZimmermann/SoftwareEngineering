package samples;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
	private Button deleteButton = new Button("Delete");
	private final AnchorPane anchorPane = new AnchorPane();
		
	public DeleteArea() {
		
		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 20.0);
		anchorPane.getChildren().addAll(deleteButton);
	}
	
	public Node getPane() {
		return anchorPane;
	}
}
