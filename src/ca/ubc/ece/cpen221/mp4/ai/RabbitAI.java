package ca.ubc.ece.cpen221.mp4.ai;

import java.util.*;

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
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

	private int closest = 10; // max number; greater than rabbit's view range
	private int temp;
	private boolean foxFound;

	public RabbitAI() {
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		Set<Direction> directions = new HashSet<Direction>();
		Set<Item> surroundings = world.searchSurroundings(animal);
		List<Item> foods = new ArrayList<Item>();
		List<Item> notPredators = new ArrayList<Item>();
		List<Item> predators = new ArrayList<Item>();

		for (Item item : surroundings) {
			if (item.getPlantCalories() != 0 && item.getStrength() < animal.getStrength()) {
				foods.add(item);
			} else if (item.getStrength() > animal.getStrength()) {
				predators.add(item);
			} else if (item.getStrength() <= animal.getStrength()) {
				notPredators.add(item);
			}
		}

		if (animal.getEnergy() < animal.getMaxEnergy() / 4 && !(foods.isEmpty())) {
			Item closestFood = foods.get(0);
			for (Item food : foods) {
				if (food.getLocation().getDistance(animal.getLocation()) < closestFood.getLocation()
						.getDistance(animal.getLocation())) {
					closestFood = food;
				}
				if (closestFood.getLocation().getDistance(animal.getLocation()) == 1) {
					return new EatCommand(animal, food);
				} else {
					Direction closestFoodDirection = Util.getDirectionTowards(animal.getLocation(),
							closestFood.getLocation());
					Location newLocation = new Location(animal.getLocation(), closestFoodDirection);
					if (Util.isValidLocation(world, newLocation)) {
						return new MoveCommand(animal, newLocation);
					}

				}
			}
		}
		if(animal.getEnergy()>=3*animal.getMaxEnergy()/4){
			return new BreedCommand(animal,new Location(animal.getLocation(),Util.getRandomDirection()));
		}

		Location newLocation = new Location(animal.getLocation(), Util.getRandomDirection());
		if (Util.isValidLocation(world, newLocation)) {
			return new MoveCommand(animal, newLocation);
		}
		return new WaitCommand();
	}

	private Direction getOppositeDirection(Direction foxDirection) {
		if (foxDirection == Direction.NORTH) {
			foxDirection = Direction.SOUTH;
		}
		if (foxDirection == Direction.EAST) {
			foxDirection = Direction.WEST;
		}
		if (foxDirection == Direction.SOUTH) {
			foxDirection = Direction.NORTH;
		}
		if (foxDirection == Direction.WEST) {
			foxDirection = Direction.EAST;
		}
		return foxDirection;

	}
}
