package view;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JToolBar;

import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class WheelGameToolbar extends JToolBar implements PropertyChangeListener {
	private JButton addPlayerButton;
	private JButton removePlayerButton;
	private JButton placeBetButton;
	private JButton spinButton;
	private WheelGameViewModel wheelGameViewModel;

	public WheelGameToolbar(WheelGameViewModel wheelGameViewModel) {
		super("Wheel Game ToolBar");

		this.wheelGameViewModel = wheelGameViewModel;
		this.wheelGameViewModel.addPropertyChangeListener(this);
		this.addPlayerButton = new AddPlayerButton(wheelGameViewModel);
		this.removePlayerButton = new RemovePlayerButton(wheelGameViewModel);
		this.placeBetButton = new PlaceBetButton(wheelGameViewModel);
		this.spinButton = new SpinButton(wheelGameViewModel);

		add(addPlayerButton);
		add(Box.createRigidArea(new Dimension(5, 5)));
		add(removePlayerButton);
		add(Box.createRigidArea(new Dimension(5, 5)));
		add(placeBetButton);
		add(Box.createRigidArea(new Dimension(5, 5)));
		add(spinButton);
		this.wheelGameViewModel.disableBet();
		this.wheelGameViewModel.disableSpin();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_BET) {
			this.placeBetButton.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_BET) {
			this.placeBetButton.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_SPIN) {
			this.spinButton.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_SPIN) {
			this.spinButton.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_ADD_PLAYER) {
			this.addPlayerButton.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_ADD_PLAYER) {
			this.addPlayerButton.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_REMOVE_PLAYER) {
			this.removePlayerButton.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_REMOVE_PLAYER) {
			this.removePlayerButton.setEnabled(true);
		}
	}
}
