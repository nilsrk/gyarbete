package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class HelpState extends GameState {
	
	private String[] helptext = {"Vänster- och höger piltangent flyttar spelaren", "Övre piltangent för att hoppa", "Akta dig för stora svarta rutor!"};
	

	public HelpState(GameStateManager gsm) {
		super(gsm);
		
	}

	
	public void init() {
		
		
	}

	
	public void tick() {
		
		
	}

	
	public void draw(Graphics g) {
		g.setColor(new Color(30, 144, 255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for(int i = 0; i < helptext.length; i++){
			g.setColor(Color.WHITE);
			
			g.setFont(new Font("Verdana", Font.PLAIN, 24));
			g.drawString(helptext[i], GamePanel.WIDTH / 2 - 400, 100 + i * 150);
		}
		
	}

	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER){
			gsm.states.push(new MenuState(gsm));
		}
		
	}

	
	public void keyReleased(int k) {
		
		
	}

}
