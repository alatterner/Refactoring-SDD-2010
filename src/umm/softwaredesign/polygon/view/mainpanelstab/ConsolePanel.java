package umm.softwaredesign.polygon.view.mainpanelstab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class ConsolePanel extends JPanel {

	public JTextArea consoleOutput;
	public ConsolePanel cPanel;

	public ConsolePanel() {
		super();
		this.setName("Console Panel");
		JScrollPane scrollPane = createConsole();
		PrintStream out = new PrintStream(new TextAreaOutputStream());
		System.setOut(out);
		System.setErr(out);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add(scrollPane);
		this.setPreferredSize(new Dimension(490, 180));
		this.setVisible(true);
		cPanel = this;
	}

	private JScrollPane createConsole() {
		consoleOutput = new JTextArea();
		consoleOutput.setName("Console Output");
		consoleOutput.setEditable(false);
		consoleOutput.setAutoscrolls(true);
		DefaultCaret caret = (DefaultCaret) consoleOutput.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		JScrollPane scrollPane = new JScrollPane(consoleOutput);
		// consoleOutput.setBounds(0, 0, 500, 140);
		scrollPane.setAutoscrolls(true);
		// scrollPane.setPreferredSize(new Dimension(490, 130));
		return scrollPane;
	}

	public class TextAreaOutputStream extends OutputStream {

		@Override
		public void write(final int character) throws IOException {
			consoleOutput.append(String.valueOf((char) character));
		}
	}
}
