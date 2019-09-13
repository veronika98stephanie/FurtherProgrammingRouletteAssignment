package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewmodel.WheelGameViewModel;

public class SpinWheelListener implements ActionListener {
	private WheelGameViewModel wheelGameViewModel;

	public SpinWheelListener(WheelGameViewModel wheelGameViewModel) {
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int initialDelay = 1;
		int finalDelay = 200;
		int delayIncrement = 4;

		wheelGameViewModel.disableBet();
		wheelGameViewModel.disableSpin();
		wheelGameViewModel.disableAddPlayer();
		wheelGameViewModel.disableRemovePlayer();
		wheelGameViewModel.disableExit();
		wheelGameViewModel.spin(initialDelay, finalDelay, delayIncrement);
	}
}
