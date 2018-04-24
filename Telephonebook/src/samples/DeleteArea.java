package samples;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class DeleteArea {
	private Button deleteButton = new Button("Delete");
	private Button addButton = new Button("New");
	private Button saveButton = new Button("Save");
	private Button loadButton = new Button("Load");
	private Button copyButton = new Button("Copy");
	private final AnchorPane anchorPane = new AnchorPane();

	public DeleteArea(CallableWithoutException<Void> onDelete, CallableWithoutException<Void> onAdd,
			CallableWithoutException<Void> onSave, CallableWithoutException<Void> onLoad,
			CallableWithoutException<Void> onCopy) {

		AnchorPane.setBottomAnchor(deleteButton, 10.0);
		AnchorPane.setRightAnchor(deleteButton, 10.0);

		AnchorPane.setLeftAnchor(addButton, 10.0);
		AnchorPane.setBottomAnchor(addButton, 10.0);

		AnchorPane.setBottomAnchor(saveButton, 10.0);
		AnchorPane.setLeftAnchor(saveButton, 60.0);

		AnchorPane.setBottomAnchor(loadButton, 10.0);
		AnchorPane.setLeftAnchor(loadButton, 110.0);

		AnchorPane.setBottomAnchor(copyButton, 10.0);
		AnchorPane.setLeftAnchor(copyButton, 160.0);

		anchorPane.getChildren().addAll(deleteButton, addButton, saveButton, loadButton, copyButton);

		deleteButton.setOnMouseClicked(e -> onDelete.call());
		addButton.setOnMouseClicked(e -> onAdd.call());
		saveButton.setOnMouseClicked(e -> onSave.call());
		loadButton.setOnMouseClicked(e -> onLoad.call());
		copyButton.setOnMouseClicked(e -> onCopy.call());
	}

	public Node getPane() {
		return anchorPane;
	}
}
