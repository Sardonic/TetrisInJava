package game.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

public class RealBlock extends AbstractBlock {

	private Color color;
	
	public RealBlock(Board board, int row, int col, Color color) {
		super(board, row, col);
		this.color = color;
	}
	
	
	@Override
	public void draw(Graphics g) {
		Point2D pos = board.getPixelPosAtIndex(row, col);
		
		int x = (int)pos.getX();
		int y = (int)pos.getY();
		
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);

		super.draw(g);
	}

	public boolean checkCollision() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
