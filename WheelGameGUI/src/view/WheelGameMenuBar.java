package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerListener;
import controller.ExitListener;
import controller.PlaceBetListener;
import controller.RemovePlayerListener;
import controller.SpinWheelListener;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class WheelGameMenuBar extends JMenuBar implements PropertyChangeListener {
	private JMenuItem addPlayerMenu;
	private JMenuItem removePlayerMenu;
	private JMenuItem placeBetMenu;
	private JMenuItem spinMenu;
	private JMenuItem exitMenu;

	public WheelGameMenuBar(WheelGameViewModel wheelGameViewModel) {
		wheelGameViewModel.addPropertyChangeListener(this);
		JMenu menu = new JMenu("Game");
		addPlayerMenu = new JMenuItem("Add Player");
		removePlayerMenu = new JMenuItem("Remove Player");
		placeBetMenu = new JMenuItem("Place Bet");
		spinMenu = new JMenuItem("Spin");
		exitMenu = new JMenuItem("Exit");

		menu.add(addPlayerMenu);
		menu.add(removePlayerMenu);
		menu.add(placeBetMenu);
		menu.add(spinMenu);
		menu.add(exitMenu);

		wheelGameViewModel.disableBet();
		wheelGameViewModel.disableSpin();

		addPlayerMenu.addActionListener(new AddPlayerListener(wheelGameViewModel));
		removePlayerMenu.addActionListener(new RemovePlayerListener(wheelGameViewModel));
		placeBetMenu.addActionListener(new PlaceBetListener(wheelGameViewModel));
		spinMenu.addActionListener(new SpinWheelListener(wheelGameViewModel));
		exitMenu.addActionListener(new ExitListener());
		add(menu);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_BET) {
			this.placeBetMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_BET) {
			this.placeBetMenu.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_SPIN) {
			this.spinMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_SPIN) {
			this.spinMenu.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_ADD_PLAYER) {
			this.addPlayerMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_ADD_PLAYER) {
			this.addPlayerMenu.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_REMOVE_PLAYER) {
			this.removePlayerMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_REMOVE_PLAYER) {
			this.removePlayerMenu.setEnabled(true);
		}
		if (evt.getPropertyName() == WheelGameViewModel.DISABLE_EXIT) {
			this.exitMenu.setEnabled(false);
		}
		if (evt.getPropertyName() == WheelGameViewModel.ENABLE_EXIT) {
			this.exitMenu.setEnabled(true);
		}
	}
}
