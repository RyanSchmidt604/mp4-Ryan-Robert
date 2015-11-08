package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
	private int closest = 2; // max number; greater than fox's view range

	public FoxAI() {

	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		Set<Item> nearbyItems = world.searchSurroundings(animal);
		Set<Item> enemies = new HashSet<>();
		Set<Item> friendlies = new HashSet<>();
		Set<Item> prey = new HashSet<>();
		
		for(Item item : nearbyItems){
		    if(item.equals(animal)){
		        
		    }else if(item.getStrength() > animal.getStrength()){
		        enemies.add(item);
		    }else if(item.getStrength() == animal.getStrength()){
		        friendlies.add(item);
		    }else if(item.getMeatCalories() > 0){
		            prey.add(item);
		    }
		}
		
		int energy = animal.getEnergy();
		Item closestEnemy = null;
		for(Item enemy : enemies){
		    if(closestEnemy == null){
		        closestEnemy = enemy;
		    }else if(closestEnemy.getLocation().getDistance(animal.getLocation()) > 
		            enemy.getLocation().getDistance(animal.getLocation())){
		        
		        closestEnemy = enemy;
		    }
		}
		if(energy <= animal.getMovingRange()){
		    for(Item food : prey){
		        if(food.getLocation().getDistance(animal.getLocation()) == 1){
		            return new EatCommand(animal, food);
		        }
		    }
		}
		if(closestEnemy != null && closestEnemy.getLocation().getDistance(animal.getLocation()) <
		        animal.getMovingRange()){
		    Direction oppositeDir = Util.getDirectionTowards(
		            closestEnemy.getLocation(), animal.getLocation());
		    Location target = new Location(animal.getLocation());
		    for(int i = animal.getMovingRange(); i >=1; i--){
		        for(int j = 0; j <= i; j++){
		            target = new Location(target, oppositeDir);
		            
		        }
		        if(Util.isValidLocation(world, target)){
		            
		        }
		    }
		}
		
		return new WaitCommand();
	}

}
