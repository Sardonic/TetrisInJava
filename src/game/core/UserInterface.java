package game.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UserInterface {
	private static final Font FONT = new Font("Courrier", Font.BOLD, 18);
	private static final Color TEXT_COLOR = new Color(0, 0, 0);
	
	private int score;
	private Piece nextPiece;
	private int lines; // Not sure what this is for?
	private int highScore;
	
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
			//TODO: draw next piece here
		}
		
		g.setFont(FONT);
		g.setColor(TEXT_COLOR);
		g.drawString("Score: " + score, 5, 25);
		g.drawString("Lines: " + lines, 5, 45);
		g.drawString("High Score: " + highScore, 5, 75);
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
