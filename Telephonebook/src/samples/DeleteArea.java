package samples;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
	private Button deleteButton = new Button("Delete");
	private Button addButton = new Button("New Entry");
	private final AnchorPane anchorPane = new AnchorPane();

	public DeleteArea(CallableWithoutException<Void> onDelete, 
			CallableWithoutException<Void> onAdd) {

		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 10.0);
		AnchorPane.setLeftAnchor(addButton, 10.0);
		AnchorPane.setBottomAnchor(addButton, 10.0);
		anchorPane.getChildren().addAll(deleteButton, addButton);
		// TODO: Lose Kopplung mit :: nicht möglich?
		deleteButton.setOnMouseClicked(e -> onDelete.call());
		addButton.setOnMouseClicked(e -> onAdd.call());
	}

	public Node getPane() {
		return anchorPane;
	}
}
