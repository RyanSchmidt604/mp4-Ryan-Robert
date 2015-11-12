package ca.ubc.ece.cpen221.mp4.items.animals;

import java.util.*;

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
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Bear extends AbstractArenaAnimal {

    public Bear(Location initialLocation) {
        this.setINITIAL_ENERGY(100);
        this.setMAX_ENERGY(150);
        this.setSTRENGTH(150);
        this.setVIEW_RANGE(2);
        this.setMIN_BREEDING_ENERGY(100);
        this.setCOOLDOWN(2);
        this.setImage(Util.loadImage("bear.gif"));
        this.setLocation(initialLocation);
    }

    @Override
    public LivingItem breed() {
        Bear child = new Bear(this.getLocation());
        child.setEnergy(this.getEnergy() / 2);
        this.setEnergy(this.getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Bear";
    }

    @Override
    public Command getNextAction(World world) {
        Location actionLocation = null;
        Set<Item> surroundings = world.searchSurroundings(this);
        List<Item> food = new LinkedList<>();
        for (Item i : surroundings) {
            if (i.getStrength() < this.getStrength()) {
                food.add(i);
            }
        }

        if (this.getEnergy() >= this.getMinimumBreedingEnergy()) {
            actionLocation = Util.getRandomEmptyAdjacentLocation(world, this.getLocation());
            if (Util.isValidLocation(world, actionLocation)) {
                return new BreedCommand(this, Util.getRandomEmptyAdjacentLocation(world, actionLocation));
            }
        }

        for (Item i : food) {
            if (i.getLocation().getDistance(this.getLocation()) == 1) {
                return new EatCommand(this, i);
            } else {
                Direction dir = Util.getDirectionTowards(this.getLocation(), i.getLocation());
                actionLocation = new Location(this.getLocation(), dir);
                if (Util.isValidLocation(world, actionLocation) && Util.isLocationEmpty(world, actionLocation)) {
                    return new MoveCommand(this, actionLocation);
                }
            }
        }

        Direction dir = Util.getRandomDirection();
        actionLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, actionLocation) && Util.isLocationEmpty(world, actionLocation)) {
            return new MoveCommand(this, actionLocation);
        }
        return new WaitCommand();
    }
}
