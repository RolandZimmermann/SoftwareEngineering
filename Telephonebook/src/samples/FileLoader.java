package samples;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileLoader {
	public static final String DATA_FILE_EXTENSION = "*.json";
	private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
	private Stage callingStage;
	
	public FileLoader(Stage stage) {
		this.callingStage = stage;
	}
	
	public File chooseFile() {
		File dataFile = myChooser.showOpenDialog(callingStage);
		return dataFile;
	}
	
	private FileChooser makeChooser(String extensionAccepted) {
		FileChooser result = new FileChooser();
		result.setTitle("Choose a telefonbook.json file");
		
		result.setInitialDirectory(new File(System.getProperty("user.dir")));
		result.getExtensionFilters().setAll(new ExtensionFilter("JSON Files", extensionAccepted));
		return result;
	}
}
