package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.interfaces.Slot;

@SuppressWarnings("serial")
public class WinSlotDialogBox extends JPanel {

	public WinSlotDialogBox(Slot winningSlot) {
		JOptionPane
				.showConfirmDialog(null,
						String.format("Color: %s\nPosition: %s\nNumber: %s\n", winningSlot.getColor(),
								winningSlot.getPosition(), winningSlot.getNumber()),
						"Winning Slot", JOptionPane.PLAIN_MESSAGE);
	}
}
