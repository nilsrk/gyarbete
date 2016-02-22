package mapping;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import entities.Enemy;
import objects.Block;

public class Map {
	
	private String path;
	private String line;
	private int width, height;
	
	private Block[][] blocks;
	private ArrayList<Enemy> enemies;
	
	public Map(String loadPath){
		path = loadPath;
		
		loadMap();
		
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < blocks.length; i++){
			for(int j = 0; j < blocks[0].length; j++){
				blocks[i][j].draw(g);
			}
		}
		
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).draw(g);
		}
	}
	
	public void tick(){
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).tick();
		}
	}
	
	public void loadMap(){
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		try {
			width = Integer.parseInt(br.readLine());
			height = Integer.parseInt(br.readLine());
			
			blocks = new Block[height][width];
			
			for (int y = 0; y < height; y++){
				line = br.readLine();
				
				String[] tokens = line.split("\\s+");
				
				for(int x = 0; x < width; x++){
					blocks[y][x] = new Block(x*Block.BlockSize, y*Block.BlockSize, Integer.parseInt(tokens[x]));
				}
			}
			
			// how many enemies
			line = br.readLine();
			line = br.readLine();
			int length = Integer.parseInt(line);
			
			enemies = new ArrayList<Enemy>();
			
			for(int i = 0; i < length; i++){
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				
				enemies.add(new Enemy(Integer.parseInt(tokens[0]) * Block.BlockSize, Integer.parseInt(tokens[1]) * Block.BlockSize, 
						Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]) * Block.BlockSize, Integer.parseInt(tokens[4]) * Block.BlockSize ));
			}
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public Block[][] getBlocks() {
		return blocks;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}

}
