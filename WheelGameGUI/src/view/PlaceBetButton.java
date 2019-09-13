package view;

import javax.swing.JButton;

import controller.PlaceBetListener;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class PlaceBetButton extends JButton {
	public PlaceBetButton(WheelGameViewModel wheelGameViewModel) {
		super("Place Bet");
		addActionListener(new PlaceBetListener(wheelGameViewModel));
	}
}
