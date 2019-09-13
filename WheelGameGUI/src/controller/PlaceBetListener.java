package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.PlaceBetDialogBox;
import viewmodel.WheelGameViewModel;

public class PlaceBetListener implements ActionListener {
	private WheelGameViewModel wheelGameViewModel;

	public PlaceBetListener(WheelGameViewModel wheelGameViewModel) {
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int initialDelay = 1;
		int finalDelay = 200;
		int delayIncrement = 4;
		boolean allPlacedBet = true;
		
		new PlaceBetDialogBox(wheelGameViewModel).setVisible(true);
		for (Player player : wheelGameViewModel.getAllPlayer()) {
			if (player.getBetType() == null) {
				allPlacedBet = false;
				break;
			}
		}

		if (allPlacedBet) {
			wheelGameViewModel.disableBet();
			wheelGameViewModel.disableSpin();
			wheelGameViewModel.disableAddPlayer();
			wheelGameViewModel.disableRemovePlayer();
			wheelGameViewModel.disableExit();
			wheelGameViewModel.spin(initialDelay, finalDelay, delayIncrement);
		}
	}
}
