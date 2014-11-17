package game.core;

import game.GameManager;
import game.GameWindow;

import java.awt.Color;
import java.awt.Point;

public class Piece {

	private Board gameBoard;
	private boolean landed;
	private Point[] blockLocations;
	private Color color;
	
	// Movement timing
	private int framesUntilMove;
	private double secondsBetweenMoves;
	private boolean moveMaxSpeed;
	private static double MIN_SECONDS_BETWEEN_MOVES = 0.1;
	
	public Piece(Board board, Point[] blockLocations, Color color, double secondsBetweenMoves) {
		gameBoard = board;
		this.secondsBetweenMoves = secondsBetweenMoves;
		this.blockLocations = blockLocations;
		this.color = color;
		this.framesUntilMove = (int)(GameWindow.FPS * secondsBetweenMoves);
		this.landed = false;
		this.moveMaxSpeed = false;
		
		init();
	}

	public void init() {
		for(Point blockLoc : blockLocations) {
			int row = blockLoc.y;
			int col = blockLoc.x;
			gameBoard.placeBlock(new RealBlock(gameBoard, row, col, color), row, col);
		}
	}

	public void removeBlocks() {
		
		for(Point blockLoc : blockLocations) {
			int row = blockLoc.y;
			int col = blockLoc.x;
			gameBoard.removeBlock(row, col);
		}
			
	}
	
	public boolean update() {
		framesUntilMove--;

		if (framesUntilMove <= 0) {
			if (moveMaxSpeed || secondsBetweenMoves < MIN_SECONDS_BETWEEN_MOVES) {
				framesUntilMove = (int)(GameWindow.FPS * MIN_SECONDS_BETWEEN_MOVES);
			} else {
                framesUntilMove = (int)(GameWindow.FPS * secondsBetweenMoves);
			}
			return move(GameManager.DOWN);
		}
		
		return true;
	}

	public boolean move(Point direction) { 
		
		removeBlocks();

		boolean canMove = !checkCollision(direction);

		if(canMove) {
			for(Point blockLoc : blockLocations) {
                blockLoc.y += direction.y;
                blockLoc.x += direction.x;
            }
		}

		for(Point blockLoc : blockLocations) {
			int row = blockLoc.y;
			int col = blockLoc.x;
			gameBoard.placeBlock(new RealBlock(gameBoard, row, col, color), row, col);
		}
		
		if (direction == GameManager.DOWN) { 
			landed = !canMove;
		}

		return canMove;
	}
	
	public boolean isLanded() {
		return landed;
	}
	
	public void drop() {
		while (move(GameManager.DOWN))
		{
			// Empty. All logic is in the while loop!
		}
	}
	
	public void fallFast() {
		moveMaxSpeed = true;
		framesUntilMove = 0;
	}
	
	public void stopFallingFast() {
		moveMaxSpeed = false;
	}
	
	public void rotate(boolean clockwise) {
		Point[] newBlockLocs = new Point[4];
		boolean doRotation = true;
		Point oldLowestLoc = new Point(0,0);
		Point oldRightestLoc = new Point(0, 0);
		
		for(Point p : blockLocations) {
			if(p.y > oldLowestLoc.y){
				oldLowestLoc.y = p.y;
			}
			
			if(p.x > oldRightestLoc.x) {
				oldRightestLoc.x = p.x;
			}
		}
		
		int averageX = (blockLocations[0].x + blockLocations[1].x + blockLocations[2].x + blockLocations[3].x) / 4;
		int averageY = (blockLocations[0].y + blockLocations[1].y + blockLocations[2].y + blockLocations[3].y) / 4;
		
		int xTranslate = -averageX;
		int yTranslate = -averageY;

		if(clockwise) {
			xTranslate--;
		}
		
		removeBlocks();

		for (int i = 0; i < blockLocations.length; ++i) {
			Point blockLoc = blockLocations[i];
			Point newBlockLoc = new Point();
			
			newBlockLoc.x = blockLoc.x;
			newBlockLoc.y = blockLoc.y;

			newBlockLoc.x += xTranslate;
			newBlockLoc.y += yTranslate;
			
			int oldX = newBlockLoc.x;
			int oldY = newBlockLoc.y;

			if(clockwise) {
				newBlockLoc.x = -oldY;
				newBlockLoc.y = oldX;
			} else {
				newBlockLoc.x = oldY;
				newBlockLoc.y = -oldX;
			}
			
			newBlockLoc.x -= xTranslate;
			newBlockLoc.y -= yTranslate;
			
			boolean collides = !gameBoard.canPlaceAt(newBlockLoc.y, newBlockLoc.x);
			
			if (collides) {
				doRotation = false;
				break;
			} else {
				newBlockLocs[i] = newBlockLoc;
			}
		}
		
		if (doRotation) {
			blockLocations = newBlockLocs;
			
			Point newLowestLoc = new Point(0,0);
			Point newRightestLoc = new Point(0,0);
			
			for(Point p : blockLocations) {
				if(p.y > newLowestLoc.y) {
					newLowestLoc.y = p.y;
				}
				
				if(p.x > newRightestLoc.x) {
					newRightestLoc.x = p.x;
				}
			}
			
			for(int i = 0; i < oldLowestLoc.y - newLowestLoc.y; ++i) {
				move(GameManager.DOWN);
			}
			
			if(newRightestLoc.x > oldRightestLoc.x) {
				move(GameManager.LEFT);
			}
		}

		for(Point blockLoc : blockLocations) {
			int row = blockLoc.y;
			int col = blockLoc.x;
			gameBoard.placeBlock(new RealBlock(gameBoard, row, col, color), row, col);
		}
	}
	
	public boolean checkCollision(Point direction) { 

		boolean willCollide = false;

		for(Point blockLoc : blockLocations) {
			int row = blockLoc.y + direction.y;
			int col = blockLoc.x + direction.x;

			if (!gameBoard.canPlaceAt(row, col)) {
				willCollide = true;
			}
		}
		
		return willCollide;
	}

}
