package scenarioselection;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;

//This class will manage the main properties of the Automation Select Forms Window.

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private DetailsPanel detailsPanel;

	public MainWindow(String title) {
		super(title);

		// 1. The following section initializes the Layout Manager.
		setLayout(new BorderLayout());

		// 2. The following section Creates the Main Swing Components || Main
		// components are (Details & Text Area) Pane.
		final JTextArea textArea = new JTextArea();

		detailsPanel = new DetailsPanel();
		// //The next line of codes activates subclass of listeners to handle
		// all the event-driven components.

		detailsPanel.addDetailListener(new DetailListener() {
			@Override
			public void detailEventOccured(DetailEvent event) {
				String text = event.getText().toUpperCase();

				textArea.append(text);
			}
		});

		// 3. The following section adds the created components in section two
		// to the content pane.
		Container c = getContentPane();

		// c.add(textArea, BorderLayout.CENTER);
		// textArea.setBackground(Color.GRAY);
		c.add(detailsPanel, BorderLayout.WEST);

	}

}
