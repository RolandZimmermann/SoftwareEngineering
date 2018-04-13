package samples;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
	private Button deleteButton = new Button("Delete");
	private Button addButton = new Button("New Entry");
	private Button saveButton = new Button("Save");
	private final AnchorPane anchorPane = new AnchorPane();

	public DeleteArea(CallableWithoutException<Void> onDelete, 
			CallableWithoutException<Void> onAdd, CallableWithoutException<Void> onSave) {

		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 10.0);
		
		AnchorPane.setLeftAnchor(addButton, 10.0);
		AnchorPane.setBottomAnchor(addButton, 10.0);
		
		AnchorPane.setBottomAnchor(saveButton, 10.0);
		AnchorPane.setLeftAnchor(saveButton, 200.0);
		AnchorPane.setRightAnchor(saveButton, 200.0);
		
		
		anchorPane.getChildren().addAll(deleteButton, addButton, saveButton);
		
		deleteButton.setOnMouseClicked(e -> onDelete.call());
		addButton.setOnMouseClicked(e -> onAdd.call());
		saveButton.setOnMouseClicked(e -> onSave.call());
	}

	public Node getPane() {
		return anchorPane;
	}
}
