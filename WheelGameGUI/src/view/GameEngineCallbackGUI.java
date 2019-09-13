package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;
import viewmodel.WheelGameViewModel;

public class GameEngineCallbackGUI implements GameEngineCallback {
	private WheelGameViewModel wheelGameViewModel;

	public GameEngineCallbackGUI(WheelGameViewModel wheelGameViewModel) {
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@Override
	public void nextSlot(Slot slot, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				wheelGameViewModel.updateStatus(slot.toString());
				wheelGameViewModel.updateWheelDot(slot.getPosition() + 28);
			}
		});
	}

	@Override
	public void result(Slot winningSlot, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				wheelGameViewModel.updateStatus(winningSlot.toString());
				wheelGameViewModel.updateWheelDot(winningSlot.getPosition() + 28);
				engine.calculateResult(winningSlot);

				for (Player player : engine.getAllPlayers()) {
					player.resetBet();
				}

				wheelGameViewModel.updateSummary(winningSlot);
				if (wheelGameViewModel.getAllPlayer().size() != 0) {
					wheelGameViewModel.enableBet();
					wheelGameViewModel.enableSpin();
					wheelGameViewModel.enableAddPlayer();
					wheelGameViewModel.enableRemovePlayer();
					wheelGameViewModel.enableExit();
				}
				new WinSlotDialogBox(winningSlot);
			}
		});
	}
}
