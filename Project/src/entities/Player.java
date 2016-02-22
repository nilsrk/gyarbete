package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import gamestate.GameState;
import main.GamePanel;
import objects.Block;
import physics.Collisions;
import entities.Enemy;

public class Player {
	
	private int width, height;
	private double x, y;
	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	public boolean lose = false;
	
	private double JumpSpeed = 5;
	private double currentJumpSpeed = JumpSpeed;
	
	private double FallSpeed = 5;
	private double currentFallSpeed = 0.1;
	
	public Player(int width, int height){
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.width = width;
		this.height = height;
	}
	
	public void tick(Block[][] b, ArrayList<Enemy> enemies){
		
		int iX = (int)x;
		int iY = (int)y;
		
		for (int i = 0; i < b.length; i++){
			
			for(int j = 0; j < b[0].length; j++){
				
			if(b[i][j].getId() != 0){
				
					//right
					if (Collisions.PlayerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i][j]) 
							|| Collisions.PlayerBlock(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset - 1), b[i][j])){
						right = false;
					};
					
					//left
					if (Collisions.PlayerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), b[i][j]) 
							|| Collisions.PlayerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), b[i][j])){
						left = false;
					}
					
					//top
					if (Collisions.PlayerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i][j]) 
							|| Collisions.PlayerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), b[i][j])){
						jumping = false;
						falling = true;
					}
					
					//bottom
					if (Collisions.PlayerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i][j]) 
							|| Collisions.PlayerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), b[i][j])){
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
					} else{
						if (!topCollision && !jumping){
							falling = true;
						}
					}
				}
			}
		}
		
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).getID() != 0){
				
					
					//right
					if (Collisions.PlayerEnemy(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), enemies.get(i)) 
							|| Collisions.PlayerEnemy(new Point(iX + width + (int)GameState.xOffset, iY + height + (int)GameState.yOffset - 1), enemies.get(i))){
						lose = true;
					};
					
					//left
					if (Collisions.PlayerEnemy(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), enemies.get(i)) 
							|| Collisions.PlayerEnemy(new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), enemies.get(i))){
						lose = true;
					}
					
					//top
					if (Collisions.PlayerEnemy(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), enemies.get(i)) 
							|| Collisions.PlayerEnemy(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), enemies.get(i))){
						lose = true;
					}
					
					//bottom
					if (Collisions.PlayerEnemy(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), enemies.get(i)) 
							|| Collisions.PlayerEnemy(new Point(iX + width + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), enemies.get(i))){
						y = enemies.get(i).getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
						lose = true;
						GameState.xOffset += enemies.get(i).getMove();
					} else{
						if (!topCollision && !jumping){
							falling = true;
						}
					}

				
			}
		}
		
		topCollision = false;
		
		if(right){
			GameState.xOffset += 2.5;
		}
		
		if(left){
			GameState.xOffset -= 2.5;
		}
		
		if(jumping){
			GameState.yOffset -= currentJumpSpeed;
			
			currentJumpSpeed -= 0.1;
			
			if (currentJumpSpeed <= 0){
				currentJumpSpeed = JumpSpeed;
				
				jumping = false;
				falling = true;
			}
		}
		
		if (falling){
			GameState.yOffset += currentFallSpeed;
			
			if (currentFallSpeed < FallSpeed){
				currentFallSpeed += 0.1;
			}
		}
		
		if (!falling){
			currentFallSpeed = 0.1;
		}
		
		
	}
		
	
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void keyPressed(int k){
		if (k == KeyEvent.VK_RIGHT) right = true;
		if (k == KeyEvent.VK_LEFT) left = true;
		if (k == KeyEvent.VK_UP && !jumping && !falling) jumping = true;
	}
	
	public void keyReleased(int k){
		if (k == KeyEvent.VK_LEFT) left = false;
		if (k == KeyEvent.VK_RIGHT) right = false;
	}
	
	public boolean getLoss(){
		return lose;
	}
	
}
