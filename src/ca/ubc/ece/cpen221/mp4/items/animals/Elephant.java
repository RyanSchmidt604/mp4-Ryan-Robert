package ca.ubc.ece.cpen221.mp4.items.animals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Elephant implements LivingItem {

	private static final int INITIAL_ENERGY = 100;
	private static final int MAX_ENERGY = 200;
	private static final int STRENGTH = Integer.MAX_VALUE - 1;
	private static final int MIN_BREEDING_ENERGY = MAX_ENERGY;
	private static final int VIEW_RANGE = 15;
	private static final int COOLDOWN = 0;
	private static final ImageIcon elephantImage = Util.loadImage("unknown.gif");

	private Location location;
	private int energy;

	public Elephant(Location initialLocation) {
		energy = INITIAL_ENERGY;
		location = initialLocation;
	}

	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;
	}

	@Override
	public int getMovingRange() {
		return 1;
	}

	@Override
	public ImageIcon getImage() {
		return elephantImage;
	}

	@Override
	public String getName() {
		return "Elephant";
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public int getStrength() {
		return STRENGTH;
	}

	@Override
	public void loseEnergy(int energyLost) {
		this.energy = -energyLost;
	}

	@Override
	public boolean isDead() {
		return energy <= 0;
	}

	@Override
	public int getPlantCalories() {
		return 0;
	}

	@Override
	public int getMeatCalories() {
		return energy;
	}

	@Override
	public int getCoolDownPeriod() {
		return COOLDOWN;
	}

	@Override
	public Command getNextAction(World world) {
		Set<Item> surroundings = world.searchSurroundings(location, VIEW_RANGE);
		List<Item> foods = new ArrayList<Item>();
		for (Item item : surroundings) {
			if (item.getPlantCalories() != 0 && item.getStrength() < STRENGTH) {
				foods.add(item);
			}
			Item closestFood = foods.get(0);
			for (Item food : foods) {
				if (food.getLocation().getDistance(location) < closestFood.getLocation()
						.getDistance(this.getLocation())) {
					closestFood = food;
				}
				if (closestFood.getLocation().getDistance(location) == 1) {
					return new EatCommand(this, food);
				} else {
					Direction closestFoodDirection = Util.getDirectionTowards(location,
							closestFood.getLocation());
					Location newLocation = new Location(location, closestFoodDirection);
					if (Util.isValidLocation(world, newLocation)) {
						return new MoveCommand(this, newLocation);
					}

				}
			}
			return new MOveCommand();
		}
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public LivingItem breed() {
		Elephant calf = new Elephant(location);
		calf.energy /= 2;
		this.energy /= 2;
		return calf;
	}

	@Override
	public void eat(Food food) {
		energy = Math.min(MAX_ENERGY, energy + food.getPlantCalories());
	}

}
