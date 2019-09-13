// Have not override 2 methods hashCode and Equals

package model;

import model.enumeration.Color;
import model.interfaces.Slot;

public class SlotImpl implements Slot {
	static final int WHEEL_SIZE = 38;
	private int position;
	private Color color;
	private int number;

	public SlotImpl(int position, Color color, int number) {
		this.position = position;
		this.color = color;
		this.number = number;
	}

	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return this.number;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public String toString() {
		String colorName = this.color.toString();
		colorName = colorName.toLowerCase();
		colorName = String.valueOf(colorName.charAt(0)).toUpperCase() + colorName.substring(1);
		return String.format("Position: %d, Color: %s, Number: %d", this.position, colorName, this.number);
	}

	@Override
	public boolean equals(Slot slot) {
		// TODO Auto-generated method stub
		if (this.color.equals(slot.getColor()) && this.number == slot.getNumber()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Slot && this.equals((Slot) obj)) {
			return true;
		}
		return false;
	}
}
