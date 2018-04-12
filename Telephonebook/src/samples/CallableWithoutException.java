package samples;

import data.TelefonEntry;
import javafx.collections.ObservableList;

public interface CallableWithoutException<R> {
	 public R call();
}
