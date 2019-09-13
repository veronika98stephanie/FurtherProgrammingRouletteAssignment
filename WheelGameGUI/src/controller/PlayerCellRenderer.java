package controller;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.interfaces.Player;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class PlayerCellRenderer extends JLabel implements ListCellRenderer<Player> {
	private WheelGameViewModel wheelGameViewModel;

	public PlayerCellRenderer(WheelGameViewModel wheelGameViewModel) {
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Player> list, Player value, int index,
			boolean isSelected, boolean cellHasFocus) {
		Player entry = (Player) value;
		setText(entry.getPlayerName());

		if (isSelected) {
			setOpaque(true);
			this.wheelGameViewModel.setCurrentSelectedPlayer(value);
		} else {
			setOpaque(false);
		}
		return this;
	}
}
