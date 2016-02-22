package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class WinState extends GameState {
	
	private String[] wtext = {"Du vann!", "Tryck på enter för att fortsätta"};

	public WinState(GameStateManager gsm) {
		super(gsm);
		// TODO Auto-generated constructor stub
	}

	
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		g.setColor(new Color(30, 144, 255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for(int i = 0; i < wtext.length; i++){
			g.setColor(Color.WHITE);
			
			g.setFont(new Font("Verdana", Font.PLAIN, 24));
			g.drawString(wtext[i], GamePanel.WIDTH / 2 - 400, 100 + i * 150);
		}
		
	}

	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER){
			gsm.states.push(new MenuState(gsm));
		}
	}

	
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
