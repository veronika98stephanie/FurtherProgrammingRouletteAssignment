package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.interfaces.Slot;
import viewmodel.WheelGameViewModel;

@SuppressWarnings("serial")
public class WheelPanel extends JPanel implements PropertyChangeListener {
	private Image wheelScaled;
	private BufferedImage wheelImage;
	private int wheelImageWidth = 0;
	private int wheelImageHeight = 0;
	private int xCoordinate = 0;
	private int yCoordinate = 0;
	private int dotCoordinateX = 0;
	private int dotCoordinateY = 0;
	private int dotDiameter = 0;
	private double radius = 0;
	private WheelGameViewModel wheelGameViewModel;
	private Integer position = null;

	public WheelPanel(int initialWidth, int initialHeight, WheelGameViewModel wheelGameViewModel) {
		try {
			wheelImage = ImageIO.read(new FileInputStream("images\\Basic_roulette_wheel_1024x1024.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setWheelScaled(initialWidth, initialHeight);
		this.wheelGameViewModel = wheelGameViewModel;
		this.wheelGameViewModel.addPropertyChangeListener(this);
	}

	@Override
	public Dimension getPreferredSize() {
		return wheelScaled == null ? new Dimension(0, 0)
				: new Dimension(wheelScaled.getWidth(this), wheelScaled.getHeight(this));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		double dotDiameterPercentage = 0.03;
		double dotDividerStandardDeviation = 2.18;
		double numberOfSlotStandardDeviation = Slot.WHEEL_SIZE - 0.1;
		double wheelCenterStandardDeviation = 2.05;
		int fullAngle = 360;

		Dimension size = getSize();

		if (size.width > size.height) {
			wheelImageWidth = getHeight();
			wheelImageHeight = getHeight();
			radius = getHeight() / dotDividerStandardDeviation;
			xCoordinate = getWidth() / 2 - wheelImageWidth / 2;
			yCoordinate = 0;
			dotDiameter = (int) (getHeight() * dotDiameterPercentage);
		} else {
			wheelImageWidth = getWidth();
			wheelImageHeight = getWidth();
			radius = getWidth() / dotDividerStandardDeviation;
			yCoordinate = getHeight() / 2 - wheelImageHeight / 2;
			xCoordinate = 0;
			dotDiameter = (int) (getWidth() * dotDiameterPercentage);
		}
		g.drawImage(wheelScaled, xCoordinate, yCoordinate, wheelImageWidth, wheelImageHeight, this);

		if (position != null) {
			Graphics2D dotGraphics = (Graphics2D) g;
			dotCoordinateX = (int) (Math.round(xCoordinate) + wheelImageWidth / wheelCenterStandardDeviation
					+ (int) Math.round(radius * Math.cos(Math.toRadians((fullAngle / Slot.WHEEL_SIZE / 2)
							+ (fullAngle / numberOfSlotStandardDeviation * position)))));
			dotCoordinateY = (int) (Math.round(yCoordinate) + wheelImageHeight / wheelCenterStandardDeviation
					+ (int) Math.round(radius * Math.sin(Math.toRadians((fullAngle / Slot.WHEEL_SIZE / 2)
							+ (fullAngle / numberOfSlotStandardDeviation * position)))));

			dotGraphics.setColor(Color.CYAN);
			dotGraphics.fillOval(dotCoordinateX, dotCoordinateY, dotDiameter, dotDiameter);
		}
	}

	public void setWheelScaled(int initialWidth, int initialHeight) {
		this.wheelScaled = wheelImage.getScaledInstance(initialWidth, initialHeight, java.awt.Image.SCALE_SMOOTH);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == WheelGameViewModel.UPDATE_WHEEL) {
			position = (int) evt.getNewValue();
			repaint();
		}
	}

}
