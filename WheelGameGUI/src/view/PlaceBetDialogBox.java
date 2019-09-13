package view;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BetTypeChangeListener;
import model.enumeration.BetType;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class PlaceBetDialogBox extends JPanel {
	private BetType selectedValue;

	public PlaceBetDialogBox(WheelGameViewModel wheelGameViewModel) {
		setLayout(new GridLayout(4, 1));
		JTextField bet = new JTextField();
		JLabel betDescription = new JLabel("Your Bet");
		JComboBox<BetType> betTypeChoices = new JComboBox<>(BetType.values());
		JLabel betTypeDescription = new JLabel("Your Bet Type");
		this.selectedValue = BetType.RED;

		add(betDescription);
		add(bet);
		add(betTypeDescription);
		add(betTypeChoices);

		betTypeChoices.addItemListener(new BetTypeChangeListener(this));

		int result = JOptionPane.showConfirmDialog(null, this, "Place player's bet", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (result == 0 && wheelGameViewModel.getCurrentSelectedPlayer() != null) {
			try {
				int betPlaced = Integer.parseInt(bet.getText());
				if (!wheelGameViewModel.placeBet(betPlaced, selectedValue)) {
					throw new NumberFormatException();
				} else {
					wheelGameViewModel.updateSummary();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showConfirmDialog(this,
						"Bet placed needs to be a positive integer\nand less than player's point", "Warning",
						JOptionPane.PLAIN_MESSAGE);
			}
		} else if (result == 2) {
			JOptionPane.showConfirmDialog(this, "Operation Cancelled", "Warning", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void setBetTypeSelectedValue(BetType betType) {
		this.selectedValue = betType;
	}
}
