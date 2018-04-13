package samples;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
	private Button deleteButton = new Button("Delete");
	private Button addButton = new Button("New Entry");
	private Button saveButton = new Button("Save");
	private Button loadButton = new Button("Load");
	private final AnchorPane anchorPane = new AnchorPane();

	public DeleteArea(CallableWithoutException<Void> onDelete, 
			CallableWithoutException<Void> onAdd, CallableWithoutException<Void> onSave, 
			CallableWithoutException<Void> onLoad) {

		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 10.0);
		
		AnchorPane.setLeftAnchor(addButton, 10.0);
		AnchorPane.setBottomAnchor(addButton, 10.0);
		
		AnchorPane.setBottomAnchor(saveButton, 10.0);
		AnchorPane.setLeftAnchor(saveButton, 100.0);
		
		AnchorPane.setBottomAnchor(loadButton, 10.0);
		AnchorPane.setLeftAnchor(loadButton, 150.0);
		
		anchorPane.getChildren().addAll(deleteButton, addButton, saveButton, loadButton);
		// TODO: Lose Kopplung mit :: nicht möglich?
		deleteButton.setOnMouseClicked(e -> onDelete.call());
		addButton.setOnMouseClicked(e -> onAdd.call());
		saveButton.setOnMouseClicked(e -> onSave.call());
		loadButton.setOnMouseClicked(e -> onLoad.call());
	}

	public Node getPane() {
		return anchorPane;
	}
}
