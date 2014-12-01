package game.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class UserInterface {
	private static final Font FONT = new Font("Courrier", Font.BOLD, 18);
	private static final Font BIG_FONT = new Font("Courrier", Font.BOLD, 36);
	private static final Color TEXT_COLOR = new Color(0, 0, 0);
	
	private int score;
	private Piece nextPiece;
	private int lines; // Not sure what this is for?
	private int highScore;
	private final int blockSize = 24;
	
	private final int leftXOffset = 5;
	private final int rightXOffset = 445;
	private final int yOffset = 30;
	
	private final static int TEMP_MESSAGE_FRAMES = 60;
	private int currentTempMessageFrames = TEMP_MESSAGE_FRAMES;
	private final Point tempMessagePos;
	private String tempMessage;
	
	public UserInterface() {
		score = 0;
		lines = 0;
		highScore = 0;
		nextPiece = null;
		
		tempMessagePos = new Point(260, 460);
	}
	
	public void restart() {
		update(0,0);
	}

	public void draw(Graphics g) {
		if (nextPiece != null) {
			nextPiece.fakeDraw(g, blockSize, new Point(leftXOffset, yOffset * 5));
		}
		
		g.setFont(FONT);
		g.setColor(TEXT_COLOR);
		g.drawString("Score: " + score, leftXOffset, yOffset);
		g.drawString("Lines: " + lines, leftXOffset, yOffset * 2);
		g.drawString("High Score: " + highScore, leftXOffset, yOffset * 3);
		g.drawString("Next Block:", leftXOffset, yOffset * 4);
		
		g.drawString("__CONTROLS__", rightXOffset, yOffset);
		g.drawString("Left/Right: left/right", rightXOffset, yOffset * 2);
		g.drawString("Up/Down: hard drop", rightXOffset, yOffset * 3);
		g.drawString("Down: soft drop", rightXOffset, yOffset * 4);
		g.drawString("Z/X: rotate", rightXOffset, yOffset * 5);
		g.drawString("W/R: save/load", rightXOffset, yOffset * 6);

		if(currentTempMessageFrames > 0 && tempMessage != null) {
			g.setFont(BIG_FONT);
			g.drawString(tempMessage, tempMessagePos.x, tempMessagePos.y);
			currentTempMessageFrames--;
		}
	}

	public void setTempMessage(String newMessage) {
		tempMessage = newMessage;
		currentTempMessageFrames = TEMP_MESSAGE_FRAMES;
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
