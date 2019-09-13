package view;

import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.Player;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class AddPlayerDialogBox extends JOptionPane {
	public AddPlayerDialogBox(WheelGameViewModel wheelGameViewModel) {
		JTextField UsernameField = new JTextField();
		JTextField initialPointField = new JTextField();

		Object[] fields = { "Username", UsernameField, "Initial Point", initialPointField };

		int input = JOptionPane.showConfirmDialog(null, fields, "Add new player", JOptionPane.OK_CANCEL_OPTION);
		if (input == 0) {
			try {
				String name = UsernameField.getText();
				if (name.isBlank())
					throw new Exception("Invalid Name, name is blank.\nInput something.");
				int initialPoint = Integer.parseInt(initialPointField.getText());
				if (initialPoint < 0)
					throw new NumberFormatException();
				Player newPlayer = new SimplePlayer(this.generateUUID(), name, initialPoint);
				wheelGameViewModel.addPlayer(newPlayer);
			} catch (NumberFormatException e) {
				JOptionPane.showConfirmDialog(this, "Initial point needs to be a positive integer", "Warning",
						JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this, e.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private String generateUUID() {
		return UUID.randomUUID().toString();
	}
}
