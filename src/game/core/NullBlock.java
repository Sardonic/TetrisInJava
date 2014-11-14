package game.core;

import java.awt.Graphics;

public class NullBlock extends AbstractBlock {

	public NullBlock(Board board, int row, int col) {
		super(board, row, col);
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
