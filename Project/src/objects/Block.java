package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import gamestate.GameState;
import resources.Images;

public class Block extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public static final int BlockSize = 64;
	private int id;
	
	public Block(int x, int y, int id){
		setBounds(x, y, BlockSize, BlockSize);
		
		this.id = id;
	}
	
	public void tick(){
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		if(id != 0){
			g.drawImage(Images.blocks[id - 1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
		}
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}
