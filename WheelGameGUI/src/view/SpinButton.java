package view;

import javax.swing.JButton;

import controller.SpinWheelListener;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class SpinButton extends JButton {
	public SpinButton(WheelGameViewModel wheelGameViewModel) {
		super("Spin");
		this.addActionListener(new SpinWheelListener(wheelGameViewModel));
	}
}
