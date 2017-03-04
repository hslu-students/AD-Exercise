package ch.hslu.AD.SW2.ArrayStack;

public class StackFullException extends Exception {
	/**
	 * Set Serial Version UID for serialization.
	 */
	private static final long serialVersionUID = -3281767409237941691L;

	public StackFullException(int size) {
		super(String.format("Stack with size of %d is full", size));
	}
}
