package ca.ubc.ece.cpen221.mp4.ai;

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
		Set<Item> surroundings = world.searchSurroundings(animal);
		for (Item item : surroundings) {
			if (item instanceof Fox) {
				Direction foxDirection = Util.getDirectionTowards(animal.getLocation(), item.getLocation());
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
				Location newLocation = new Location(animal.getLocation(),
						Util.getDirectionTowards(animal.getLocation(), item.getLocation()));
				new MoveCommand(animal, newLocation);
			}
		}
		for (Item item : surroundings) {
			if (item instanceof Grass) {
				if (animal.getLocation().getDistance(item.getLocation()) == 1) {
					return new EatCommand(animal, item);
				} else {
					Location newLocation = new Location(animal.getLocation(),
							Util.getDirectionTowards(animal.getLocation(), item.getLocation()));
					new MoveCommand(animal, newLocation);
				}
			}
		}
		if (animal.getEnergy() >= animal.getMaxEnergy()) {
			Location breedLocation = Util.getRandomEmptyAdjacentLocation((World) world, animal.getLocation());
			if (Util.isValidLocation(world, breedLocation)) {
				return new BreedCommand(animal.breed(), breedLocation);
			}
		}
		return new MoveCommand(animal, Util.getRandomEmptyAdjacentLocation((World) world, animal.getLocation()));
	}
}
