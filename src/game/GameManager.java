package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import game.core.Board;
import game.core.Piece;
import game.factories.PieceFactory;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class GameManager extends JPanel implements KeyListener {
	
	private final int width;
	private final int height;
	private final Color backgroundColor;
	private Piece currentPiece;
	
	public static final Point DOWN = new Point(0, 1);
	public static final Point LEFT = new Point(-1, 0);
	public static final Point RIGHT = new Point(1, 0);
	
	Board gameBoard;

	public GameManager(int width, int height) {
		this.width = width;
		this.height = height;
		
		backgroundColor = Color.WHITE;
		
		Point2D boardPos = new Point2D.Double(width / 3, 16);
		gameBoard = new Board(boardPos, (int)(width/1.5), (int)(height/1.5));
		
		currentPiece = PieceFactory.generateRandomPiece(gameBoard);
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

	public void update(double time) {
		currentPiece.update();
		if(currentPiece.isLanded()) {
			currentPiece = PieceFactory.generateRandomPiece(gameBoard);
		}
        
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			currentPiece.move(LEFT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			currentPiece.move(RIGHT);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentPiece.fallFast();
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) {
			currentPiece.rotate(true);
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			currentPiece.drop();
		}
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentPiece.stopFallingFast();
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// Empty
	}
}