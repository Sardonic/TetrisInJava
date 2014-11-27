package game.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UserInterface implements BoardObserver {
	private static final Font FONT = new Font("Courrier", Font.BOLD, 18);
	private static final Color TEXT_COLOR = new Color(0, 0, 0);
	
	private static final int POINTS_PER_ROW = 100;
	private static final int BONUS_SCORE = 300;
	
	private int score;
	private Piece nextPiece;
	private int lines; // Not sure what this is for?
	
	public UserInterface() {
		score = 0;
		lines = 0;
		nextPiece = null;
	}
	
	public void draw(Graphics g) {
		if (nextPiece != null) {
			//TODO: draw next piece here
		}
		
		g.setFont(FONT);
		g.setColor(TEXT_COLOR);
		g.drawString("Score: " + score, 5, 25);
		g.drawString("Lines: " + lines, 5, 45);
	}

	@Override
	public void update(int rowsRemoved) {
		score += rowsRemoved * POINTS_PER_ROW;
		if (rowsRemoved == 4) {
			score += BONUS_SCORE;
		}
		
		lines += rowsRemoved;
	}
}
