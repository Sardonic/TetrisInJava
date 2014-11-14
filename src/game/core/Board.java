package game.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

public class Board {
	private static final int NUM_ROWS = 20;
	private static final int NUM_COLS = 10;
	private AbstractBlock[][] blocks;
	private Point2D pos;
	
	public Board(Point2D pos, int width, int height) {
		AbstractBlock.SIZE = height / NUM_ROWS;
		this.pos = pos;
		blocks = new AbstractBlock[NUM_ROWS][NUM_COLS];
		
		for(int i = 0; i < NUM_ROWS; ++i) {
			for(int j = 0; j < NUM_COLS; ++j) { 
				blocks[i][j] = new NullBlock(this, i, j);
			}
		}
		
		blocks[1][2] = new RealBlock(this, 1, 2, Color.BLUE);
	}
	
	public void placeBlock(AbstractBlock block, int row, int col) {
		blocks[row][col] = block;
		
	}
	
	public void removeBlock(int row, int col) {
		blocks[row][col] = new NullBlock(this, row, col);
	}
	
	public boolean isBlockAtLocation(int row, int col) {
		if(blocks[row][col] instanceof RealBlock) {
			return true;
		}
		
		return false;
	}
	
	public void draw(Graphics g) {
		for(AbstractBlock[] row : blocks) {
			for(AbstractBlock block : row) {
				block.draw(g);
			}
		}
		
	}
	
	private Collection<Integer> checkRows() {
		
		ArrayList<Integer> rowsToClear = new ArrayList<Integer>();
		boolean isRowFull = true;
		
		for( int i = 0; i < NUM_ROWS; ++i) {
			
			AbstractBlock[] row = blocks[i];
			
			for(AbstractBlock block : row) {
				if(block instanceof NullBlock) {
					isRowFull = false;
					break;
				}
			}

			if(isRowFull) {
				rowsToClear.add(i);
			}
		}
		
		return rowsToClear;
	}

	public Point2D getPixelPosAtIndex(int row, int col) {
		Point2D pixel = new Point2D.Double(pos.getX() + col * AbstractBlock.SIZE, pos.getY() + row * AbstractBlock.SIZE);
		return pixel;
	}
}
