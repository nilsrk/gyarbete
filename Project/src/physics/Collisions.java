package physics;

import java.awt.Point;

import entities.Enemy;
import objects.Block;

public class Collisions {
	
	public static boolean PlayerBlock(Point p, Block b){
		return b.contains(p);
	}
	
	public static boolean PlayerEnemy(Point p, Enemy b){
		return b.contains(p);
	}
	
}
