package game.core;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Board {
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLS = 10;
	private AbstractBlock[][] blocks;
	private Point2D pos;
	
	public Board(Point2D pos, int width, int height) {
		AbstractBlock.SIZE = height / NUM_ROWS;
		this.pos = pos;
		blocks = new AbstractBlock[NUM_ROWS][NUM_COLS];
	}
	
	public void init() {
		for(int i = 0; i < NUM_ROWS; ++i) {
			for(int j = 0; j < NUM_COLS; ++j) { 
				blocks[i][j] = new NullBlock(this, i, j);
			}
		}
		
	}
	
	public boolean canPlaceAt(int row, int col) {
		if (row < NUM_ROWS && row >= 0 && col < NUM_COLS && col >= 0) {
			return !(blocks[row][col].isSolid());
		} else {
			return false;
		}
	}

	public void placeBlock(AbstractBlock block, int row, int col) {
		blocks[row][col] = block;
	}
	
	public void removeBlock(int row, int col) {
		blocks[row][col] = new NullBlock(this, row, col);
	}
	
	public boolean isBlockAtLocation(int row, int col) {
		if (row < NUM_ROWS && row >= 0 && col < NUM_COLS && col >= 0) {
			return blocks[row][col].isSolid();
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g) {
		for(AbstractBlock[] row : blocks) {
			for(AbstractBlock block : row) {
				block.draw(g);
			}
		}
		
	}
	
	public ArrayList<Integer> checkRows() {
		
		ArrayList<Integer> rowsToClear = new ArrayList<Integer>();
		boolean isRowFull;
		
		for( int i = 0; i < NUM_ROWS; ++i) {
			isRowFull = true;
			
			AbstractBlock[] row = blocks[i];
			
			for(AbstractBlock block : row) {
				if(!block.isSolid()) {
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

	public int removeRows(ArrayList<Integer> rowsToRemove) {
		int lines = 0;
		for(Integer row : rowsToRemove) {
			for(int col = 0; col < NUM_COLS; ++col) {
				removeBlock(row, col);
			}
			lines++;
		}

		dropRows(rowsToRemove.get(0), rowsToRemove.get(rowsToRemove.size() -1));

		return lines;
	}

	private void dropRows(Integer highestRow, Integer lowestRow) {
		int highestRemainingRow = highestRow - 1;
		int translationAmount = lowestRow - highestRemainingRow;
		for(int i = highestRemainingRow; i >= 0; --i) {
			for(AbstractBlock block : blocks[i]) {
				AbstractBlock newBlock;
				
				if(block.isSolid()) {
					newBlock = new RealBlock(block.board, block.row + translationAmount, block.col, block.color);
				} else {
					newBlock = new NullBlock(block.board, block.row + translationAmount, block.col);
				}
				
				placeBlock(newBlock, newBlock.row, newBlock.col);
			}
		}
		
		for(int i = 0; i < translationAmount; ++i) {
			for(AbstractBlock block : blocks[i]) {
				removeBlock(block.row, block.col);
			}
		}
	}

	public boolean checkLoss() {
		for(int i = 0; i < NUM_COLS; ++i) {
			boolean firstRowNull = !blocks[0][i].isSolid();
			boolean secondRowNull = !blocks[1][i].isSolid();

			if(!firstRowNull || !secondRowNull) {
				return true;
			}
		}
		return false;
	}
}
