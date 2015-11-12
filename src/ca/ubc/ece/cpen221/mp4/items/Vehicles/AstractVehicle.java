package ca.ubc.ece.cpen221.mp4.items.Vehicles;

import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public abstract class AstractVehicle implements MoveableItem, Actor {
    
    private int STRENGTH;
    private int energy;
    private int MASS;
    private int momentum = 0;
    private Direction currentDirection;
    private int COOLDOWN_PERIOD;
    private ImageIcon image;
    private boolean isDead;
    private int MOVING_RANGE;
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
        this.energy -= energy;
        if(this.energy <=0){
            isDead = true;
        }
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
        for(Item item : nearbyItems){
            if(item.getStrength() < STRENGTH){
                prey.add(item);
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
