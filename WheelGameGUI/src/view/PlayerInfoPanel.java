package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class PlayerInfoPanel extends JPanel {
	private SummaryPanel summaryPanel;
	private PlayerPanel playerPanel;

	public PlayerInfoPanel(WheelGameViewModel wheelGameViewModel, WheelMainFrame wheelMainFrame) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.playerPanel = new PlayerPanel(wheelGameViewModel);
		this.summaryPanel = new SummaryPanel(wheelGameViewModel, wheelMainFrame);
		JScrollPane summaryPanelScroll = new JScrollPane(summaryPanel);
		JLabel summaryPanelLabel = new JLabel("SUMMARY PANEL");

		add(playerPanel);
		add(summaryPanelLabel);
		add(summaryPanelScroll);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int preferredMaxWidth = getParent().getWidth() / 3;
		int preferredMaxHeight = getParent().getHeight();
		int preferredMinWidth = getParent().getWidth() / 4;
		int preferredMinHeight = getParent().getHeight();

		this.setMinimumSize(new Dimension(preferredMinWidth, preferredMinHeight));
		this.setMaximumSize(new Dimension(preferredMaxWidth, preferredMaxHeight));
		this.setPreferredSize(new Dimension(preferredMinWidth, preferredMinHeight));
		revalidate();
	}
}
