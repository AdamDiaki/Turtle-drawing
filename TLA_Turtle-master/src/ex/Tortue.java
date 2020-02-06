package ex;

import javafx.scene.canvas.GraphicsContext;

public class Tortue {
	private double direction;
	private double x;
	private double y;
	private GraphicsContext gc;

	/**
	 * Constructeur
	 * @param direction
	 * @param x
	 * @param y
	 * @param gc
	 */
	public Tortue(double direction, double x, double y, GraphicsContext gc) {
		super();
		this.direction = direction;
		this.x = x;
		this.y = y;
		this.gc = gc;
	}

	/**
	 * @return double
	 */
	public double getDirection() {
		return direction;
	}

	/**
	 * @param direction
	 */
	public void setDirection(double direction) {
		this.direction = direction;
	}

	/**
	 * @return double
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return double
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return double
	 */
	public GraphicsContext getGc() {
		return gc;
	}

	/**
	 * @param gc
	 */
	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}

	/** methode qui permet de faire avancer la tortue
	 * @param longueur
	 */
	void avance(double longueur) {
		double cibleX = x + Math.sin(direction * Math.PI * 2 / 360) * longueur;
		double cibleY = y + Math.cos(direction * Math.PI * 2 / 360) * longueur;
		gc.strokeLine(x, y, cibleX, cibleY);
		x = cibleX;
		y = cibleY;
	}

	/**
	 * Permet de touner la tortue à droite
	 * @param longueur
	 */
	void right(double longueur) {
		direction = direction - longueur;
	}

	/**
	 * Permet de touner la tortue à droite
	 * @param longueur
	 */
	void left(double longueur) {
		direction = direction + longueur;
	}

	@Override
	public String toString() {
		return "Tortue [direction=" + direction + ", x=" + x + ", y=" + y + "]";
	}

}
