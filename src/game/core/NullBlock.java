package game.core;

import java.awt.Graphics;

public class NullBlock extends AbstractBlock {

	public NullBlock(Board board, int row, int col) {
		super(board, row, col);
	}

	public NullBlock(AbstractBlock block) {
		super(block);
		
		this.row = block.row;
		this.col = block.col;
		this.board = block.board;
		this.color = block.color;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
	}

	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return false;
	}

}
