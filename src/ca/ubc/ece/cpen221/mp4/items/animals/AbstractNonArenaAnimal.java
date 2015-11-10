package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class AbstractNonArenaAnimal implements LivingItem {

	protected static final int INITIAL_ENERGY = 0;
	protected static final int MAX_ENERGY = 0;
	protected static final int STRENGTH = 0;
	protected static final int MIN_BREEDING_ENERGY = 0;
	protected static final int VIEW_RANGE = 0;
	protected static final int MOVING_RANGE = 0;
	protected static final int COOLDOWN = 0;
	protected static final String NAME = "Name";
	protected static final ImageIcon IMAGE = Util.loadImage("unknown.gif");

	protected Location location;
	protected boolean isDead;
	protected int energy;

	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;
	}

	@Override
	public int getMovingRange() {
		return MOVING_RANGE;
	}

	@Override
	public ImageIcon getImage() {
		return IMAGE;
	}

	@Override
	public String getName() {
		return NAME;
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
	public void loseEnergy(int energy) {
		this.energy -= energy;
	}

	@Override
	public boolean isDead() {
		return isDead;
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
		return new WaitCommand();
	}

	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public LivingItem breed() {
		// TODO HOW ON EARTH IS THIS POSSIBLE????
		return null;
	}

	@Override
	public void eat(Food food) {
		// TODO SAME HERE

	}

}
