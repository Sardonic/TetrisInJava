package game.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class AbstractBlock {
	public static int SIZE = 0;
	protected int row;
	protected int col;
	protected Board board;
	protected Color color;
	
	public AbstractBlock(Board board, int row, int col) {
		this.row = row;
		this.col = col;
		this.board = board;
		color = Color.WHITE;
	}

	public AbstractBlock(AbstractBlock block) {
		this.row = block.row;
		this.col = block.col;
		this.board = block.board;
		this.color = block.color;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		Point2D pos = board.getPixelPosAtIndex(row, col);
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		g.drawRect(x, y, SIZE, SIZE);
	}
	
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		
		if (!(other instanceof AbstractBlock)) {
			return false;
		}
		
		AbstractBlock otherBlock = (AbstractBlock)other;
		
		if (otherBlock.row != this.row) { 
			return false;
		}
		
		if (otherBlock.col != this.col) {
			return false;
		}
		
		if (!otherBlock.color.equals(this.color)) {
			return false;
		}
		
		return true;
	}

	public abstract AbstractBlock copy(Board board);

	public abstract boolean isSolid();
}
