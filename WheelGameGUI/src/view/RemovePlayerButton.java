package view;

import javax.swing.JButton;

import controller.RemovePlayerListener;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class RemovePlayerButton extends JButton {
	public RemovePlayerButton(WheelGameViewModel wheelGameViewModel) {
		super("Remove Player");
		addActionListener(new RemovePlayerListener(wheelGameViewModel));
	}
}
