package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gamestate.GameState;
import objects.Block;

public class Enemy extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	private int leftBound, rightBound;
	private int move = 1;
	private int id;
	
	public Enemy(int x, int y, int id, int leftBound, int rightBound){
		setBounds(x, y, Block.BlockSize, Block.BlockSize);
		this.id = id;
		this.rightBound = rightBound;
		this.leftBound = leftBound;
	}
	
	public void tick(){
		if(x + width - GameState.xOffset >= rightBound - GameState.xOffset && move != -1){
			move *= -1;
		}
		
		if(x - GameState.xOffset <= leftBound - GameState.xOffset && move != 1){
			move *= -1;
		}
		
		x += move;
	}
	
	public void draw(Graphics g){
		if (id != 0){
			g.setColor(Color.BLACK);
			g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, Block.BlockSize, Block.BlockSize);
		}
	}
	
	public int getMove(){
		return move;
	}
	
	public int getID(){
		return id;
	}

}
