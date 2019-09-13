package view;

import javax.swing.JButton;

import controller.AddPlayerListener;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class AddPlayerButton extends JButton {

	public AddPlayerButton(WheelGameViewModel wheelGameViewModel) {
		super("Add Player");
		addActionListener(new AddPlayerListener(wheelGameViewModel));
	}
}
