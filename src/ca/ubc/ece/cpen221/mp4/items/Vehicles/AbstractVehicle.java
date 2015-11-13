package ca.ubc.ece.cpen221.mp4.items.Vehicles;

import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public abstract class AbstractVehicle implements MoveableItem, Actor {
    
    private int STRENGTH;
    private int MASS;
    private int momentum = 0;
    private Direction currentDirection;
    private int COOLDOWN_PERIOD;
    private ImageIcon image;
    private boolean isDead;
    private int MOVING_RANGE = 1;
    private int VIEW_RANGE;
    private Location location;
    private String name;

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public String getName() {
        return name;
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
        isDead = true;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public int getPlantCalories() {
        return 0;
    }

    @Override
    public int getMeatCalories() {
        return 0;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN_PERIOD;
    }

    @Override
    public Command getNextAction(World world) {
        Set<Item> nearbyItems = world.searchSurroundings(location, VIEW_RANGE);
        Set<Item> prey = new HashSet<>();
        //find prey
        for(Item item : nearbyItems){
            if(item.getStrength() < STRENGTH){
                prey.add(item);
            }
        }
        //continue moving
        if(momentum > 0){
            Location target = new Location(location, currentDirection);
            Item victim = null;
            if(!Util.isValidLocation(world, target)){
                momentum = 0;
                currentDirection = null;
                return new WaitCommand();
            }
            if(!Util.isLocationEmpty(world, target)){
                for(Item currentPrey: prey){
                    if(currentPrey.getLocation().equals(target)){
                        victim = currentPrey;
                    }
                }
                if(victim != null){
                    momentum--;
                    return new OvertakeCommand(this, victim);
                }else{//this will happen if the space is taken by a item with greater strength
                    momentum = 0;
                    currentDirection = null;
                    return new WaitCommand();
                }
            }
            momentum--;
            return new MoveCommand(this, target);
        }else{
            currentDirection = Util.getRandomDirection();
            Location target = new Location(location, currentDirection);
            while(!Util.isValidLocation(world, target)){
                currentDirection = Util.getRandomDirection();
                target = new Location(location, currentDirection);
            }
            if(!Util.isLocationEmpty(world, target)){
                Item victim = null;
                for(Item currentPrey : prey){
                    if(target.equals(currentPrey.getLocation())){
                        victim = currentPrey;
                    }
                }
                if(victim != null){
                    momentum = MASS;
                    return new OvertakeCommand(this, victim);
                }else{//will get here if the space is occupied by an item with greater strength
                    momentum = 0;
                    currentDirection = null;
                    return new WaitCommand();
                }
            }else{
                momentum = MASS;
                return new MoveCommand(this, target);
            }
        }
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;

    }

    @Override
    public int getMovingRange() {
        return MOVING_RANGE;
    }

}
