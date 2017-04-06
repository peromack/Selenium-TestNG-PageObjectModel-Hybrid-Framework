package scenarioselection;

import java.util.EventObject;

@SuppressWarnings("serial")
public class DetailEvent extends EventObject {

	private String text;

	public DetailEvent(Object source, String text) {
		super(source);

		this.text = text;
		// this.msg = msg;

	}

	public String getText() {
		return text;

	}

}
