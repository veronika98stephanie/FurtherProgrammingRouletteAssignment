package view;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enumeration.Color;
import model.interfaces.Player;
import model.interfaces.Slot;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel implements PropertyChangeListener {
	private final int GRID_ROW = 0;
	private final int GRID_COLUMN = 2;
	private WheelGameViewModel wheelGameViewModel;

	public SummaryPanel(WheelGameViewModel wheelGameViewModel, WheelMainFrame wheelMainFrame) {
		wheelGameViewModel.addPropertyChangeListener(this);
		setLayout(new GridLayout(GRID_ROW, GRID_COLUMN));
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Slot winningSlot = null;
		String playerNameLabel = "Player name";
		String playerPointsLabel = "Player point(s)";
		String winOrLossLabel = "Win or Loss";
		String notAvailableLabel = ": N/A";
		String winLabel = ": You Win!";
		String lossLabel = ": You Lose..";
		String playerBetTypeLabel = "Player Bet Type";
		String playerBetLabel = "Player Bet";

		if (evt.getPropertyName() == WheelGameViewModel.ADD_PLAYER
				|| evt.getPropertyName() == WheelGameViewModel.REMOVE_PLAYER
				|| evt.getPropertyName() == WheelGameViewModel.UPDATE_SUMMARY) {
			Collection<Player> players = (Collection<Player>) evt.getNewValue();

			if (evt.getOldValue() != null) {
				winningSlot = (Slot) evt.getOldValue();
			}
			removeAll();
			for (Player player : players) {
				add(new JLabel(playerNameLabel));
				add(new JLabel(String.format(": %s", player.getPlayerName())));
				add(new JLabel(playerPointsLabel));
				add(new JLabel(String.format(": %s", player.getPoints())));
				add(new JLabel(winOrLossLabel));
				if (winningSlot == null || player.getBetType() == null) {
					add(new JLabel(notAvailableLabel));
				} else if (winningSlot.getColor().name().equals(player.getBetType().name())
						|| ((winningSlot.getColor() == Color.GREEN0 || winningSlot.getColor() == Color.GREEN00)
								&& player.getBetType().name().equals("ZEROS"))) {
					add(new JLabel(String.format("%s (Amount: %d)", winLabel,
							this.wheelGameViewModel.calculateResult(player))));
					player.setBetType(null);
				} else {
					add(new JLabel(String.format("%s (Amount: %d)", lossLabel,
							this.wheelGameViewModel.calculateResult(player))));
					player.setBetType(null);
				}
				if (player.getBetType() != null) {
					add(new JLabel(playerBetTypeLabel));
					add(new JLabel(String.format(": %s", player.getBetType())));
					add(new JLabel(playerBetLabel));
					add(new JLabel(String.format(": %s", player.getBet())));
				} else {
					add(new JLabel(playerBetTypeLabel));
					add(new JLabel(notAvailableLabel));
					add(new JLabel(playerBetLabel));
					add(new JLabel(notAvailableLabel));
				}
				add(new JLabel(""));
				add(new JLabel(""));
			}
			this.wheelGameViewModel.removeParticipatingPlayers();
			revalidate();
			repaint();
		}
	}

}
