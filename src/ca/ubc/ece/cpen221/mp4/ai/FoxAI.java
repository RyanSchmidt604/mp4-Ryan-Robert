package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
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
		        boolean isValid = true;
		        if(!Util.isValidLocation(world, target)){
		            isValid = false;
		        }
		        for(Item item : nearbyItems){
		            if(item.getLocation().equals(target)){
		                isValid = false;
		            }
		        }
		        if(isValid){
		            return new MoveCommand(animal, target);
		        }
		    }
		}
		if(energy > (animal.getMaxEnergy()/2)){
		    Location[] adjacentLocations = new Location[4];
		    adjacentLocations[0] = new Location(animal.getLocation(), Direction.NORTH);
		    adjacentLocations[1] = new Location(animal.getLocation(), Direction.SOUTH);
		    adjacentLocations[2] = new Location(animal.getLocation(), Direction.EAST);
		    adjacentLocations[3] = new Location(animal.getLocation(), Direction.WEST);
		    
		    for(int i = 0; i <4; i++){
		        boolean isValid = true;
		        if(!Util.isValidLocation(world, adjacentLocations[i])){
		            isValid = false;
		        }
		        for(Item item : nearbyItems){
		            if(adjacentLocations[i].equals(item.getLocation())){
		               isValid = false;
		            }
		        }
		        if(isValid){
		            return new BreedCommand(animal, adjacentLocations[i]);
		        }
		    }
		}
		
		Item closestPrey = null;
        for(Item currentPrey : prey){
            if(closestPrey == null){
                closestPrey = currentPrey;
            }else if(closestPrey.getLocation().getDistance(animal.getLocation()) > 
                    currentPrey.getLocation().getDistance(animal.getLocation())){
                closestPrey = currentPrey;
            }
        }
        if(closestPrey != null){
            Direction towardsFood = Util.getDirectionTowards(animal.getLocation(), 
                    closestPrey.getLocation());
            int distanceToFood = animal.getLocation().getDistance(closestPrey.getLocation());
            if(distanceToFood == 1){
                return new EatCommand(animal, closestPrey);
            }else{
                
                for(int i = animal.getMovingRange(); i > 0; i--){
                    Location target = new Location(animal.getLocation());
                    for(int j = 1; j <= i; i++){
                        target = new Location(target, towardsFood);
                    }
                    boolean isValid = true;
                    if(!Util.isValidLocation(world, target)){
                        isValid = false;
                    }
                    for(Item item : nearbyItems){
                        if(item.getLocation().equals(target)){
                            isValid = false;
                        }
                    }
                    if(isValid){
                        return new MoveCommand(animal, target);
                    }
                }
            }
        }
        
        
		
		return new WaitCommand();
	}

}
