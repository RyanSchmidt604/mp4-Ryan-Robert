package ca.ubc.ece.cpen221.mp4.items.animals;

import java.util.*;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Elephant extends AbstractArenaAnimal {

    public Elephant(Location initialLocation) {
        this.setINITIAL_ENERGY(200);
        this.setMAX_ENERGY(400);
        this.setSTRENGTH(Integer.MAX_VALUE);
        this.setVIEW_RANGE(10);
        this.setMIN_BREEDING_ENERGY(400);
        this.setCOOLDOWN(2);
        this.setImage(Util.loadImage("elephant..gif"));
        this.setLocation(initialLocation);
    }

    @Override
    public LivingItem breed() {
        Elephant child = new Elephant(this.getLocation());
        child.setEnergy(this.getEnergy()/2);
        this.setEnergy(this.getEnergy()/2);
        return child;
    }

    @Override
    public String getName() {
        return "Elephant";
    }

    @Override
    public Command getNextAction(World world) {
        Location actionLocation = null;
        Set<Item> surroundings = world.searchSurroundings(this);
        surroundings.remove(this);
        if (this.getEnergy() == this.getMaxEnergy()) {
            actionLocation = Util.getRandomEmptyAdjacentLocation(world, this.getLocation());
            if (Util.isValidLocation(world, actionLocation)) {
                return new BreedCommand(this, Util.getRandomEmptyAdjacentLocation(world, actionLocation));
            }
        }
        List<Item> food = new ArrayList<>();
        for (Item i : surroundings) {
            if (i.getPlantCalories() != 0) {
                food.add(i);
            }
        }
        for (Item i1 : food) {
            if (i1.getLocation().getDistance(this.getLocation()) == 1) {
                return new EatCommand(this, i1);
            } else {
                Direction dir = Util.getDirectionTowards(this.getLocation(), i1.getLocation());
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
