package gamestate;

import java.awt.Color;
import java.awt.Graphics;

import entities.Enemy;
import entities.Player;
import main.GamePanel;
import mapping.Map;
import objects.Block;

public class Level1State extends GameState {

	private Player player;
	private Enemy enemy;
	private Map map;

	
	public Level1State(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		player = new Player(30, 30);
		map = new Map("/Maps/map1.map");
		
		xOffset = -300;
		yOffset = 0;
	}

	
	public void tick() {
		player.tick(map.getBlocks(), map.getEnemies());
		map.tick();
		
		
		if(yOffset >= 400) gsm.states.push(new GameOverState(gsm));
		if(player.getLoss()) gsm.states.push(new GameOverState(gsm));
		if(xOffset >= 2000) gsm.states.push(new WinState(gsm));
	}

	
	public void draw(Graphics g) {
		g.setColor(new Color(30, 144, 255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		player.draw(g);
		map.draw(g);
	}

	
	public void keyPressed(int k) {
		player.keyPressed(k);
	}

	
	public void keyReleased(int k) {
		player.keyReleased(k);
	}

}
