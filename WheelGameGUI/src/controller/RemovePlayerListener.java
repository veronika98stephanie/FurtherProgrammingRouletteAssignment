package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewmodel.WheelGameViewModel;

public class RemovePlayerListener implements ActionListener {
	private WheelGameViewModel wheelGameViewModel;

	public RemovePlayerListener(WheelGameViewModel wheelGameViewModel) {
		super();
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.wheelGameViewModel.removePlayer();
	}
}
