package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import viewmodel.WheelGameViewModel;

public class AddPlayerListener implements ActionListener {
	private WheelGameViewModel wheelGameViewModel;

	public AddPlayerListener(WheelGameViewModel wheelGameViewModel) {
		super();
		this.wheelGameViewModel = wheelGameViewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		wheelGameViewModel.showAddPlayerDialogBox();
	}
}
