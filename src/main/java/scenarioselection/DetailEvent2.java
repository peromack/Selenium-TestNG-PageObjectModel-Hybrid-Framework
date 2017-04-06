package scenarioselection;

import java.util.EventObject;

@SuppressWarnings("serial")
public class DetailEvent2 extends EventObject {

	private String msg;

	public DetailEvent2(Object source, String msg) {

		super(source);

		this.msg = msg;

	}

	public String getText() {
		return msg;

	}

}
