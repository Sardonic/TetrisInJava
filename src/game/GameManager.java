package game;

import java.awt.Color;
import java.awt.Dimension;

import game.core.Board;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class GameManager extends JPanel {
	
	private final int width;
	private final int height;
	private final Color backgroundColor;
	
	Board gameBoard;

	public GameManager(int width, int height) {
		this.width = width;
		this.height = height;
		
		backgroundColor = Color.WHITE;
		
		Point2D boardPos = new Point2D.Double(width / 3, 16);
		gameBoard = new Board(boardPos, width/2, height/2);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		
		gameBoard.draw(g);
	}

	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		
		gameBoard.draw(g);
	}
	
	public void update(double time) {
	}
}