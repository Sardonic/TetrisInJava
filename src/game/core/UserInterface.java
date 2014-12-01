package game.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class UserInterface {
	private static final Font FONT = new Font("Courrier", Font.BOLD, 18);
	private static final Color TEXT_COLOR = new Color(0, 0, 0);
	
	private int score;
	private Piece nextPiece;
	private int lines; // Not sure what this is for?
	private int highScore;
	private final int blockSize = 24;
	
	private final int xOffset = 5;
	private final int yOffset = 30;
	
	public UserInterface() {
		score = 0;
		lines = 0;
		highScore = 0;
		nextPiece = null;
	}
	
	public void restart() {
		update(0,0);
	}

	public void draw(Graphics g) {
		if (nextPiece != null) {
			nextPiece.fakeDraw(g, blockSize, new Point(xOffset, yOffset * 5));
		}
		
		g.setFont(FONT);
		g.setColor(TEXT_COLOR);
		g.drawString("Score: " + score, xOffset, yOffset);
		g.drawString("Lines: " + lines, xOffset, yOffset * 2);
		g.drawString("High Score: " + highScore, xOffset, yOffset * 3);
		g.drawString("Next Block:", xOffset, yOffset * 4);
	}

	public void update(int score, int lines) { 
		this.score = score;
		this.lines = lines;
	}

	public void newPiece(Piece nextPiece) {
		this.nextPiece = nextPiece;
	}

	public void endGameUpdate(int highScore) {
		this.highScore = highScore;
	}
}
