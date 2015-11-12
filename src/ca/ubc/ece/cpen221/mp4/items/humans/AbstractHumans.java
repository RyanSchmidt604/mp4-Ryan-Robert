package ca.ubc.ece.cpen221.mp4.items.humans;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public abstract class AbstractHumans implements MoveableItem, Actor {

    private int STRENGTH;
    private int COOLDOWN;
    private ImageIcon image;
    private boolean isDead;
    private int MEAT_CALORIES;
    private int PLANT_CALORIES = 0;
    private int MOVING_RANGE;
    private Location location;

    protected void setlocation(Location l){
        this.location=l;
    }
    protected void setPLANT_CALORIES(int i) {
        this.PLANT_CALORIES = i;
    }

    protected void setMOVING_RANGE(int i) {
        this.MOVING_RANGE = i;
    }

    protected void setMEAT_CALORIES(int i) {
        this.MEAT_CALORIES = i;
    }

    protected void setImage(ImageIcon i) {
        this.image = i;
    }

    protected void setSTRENGTH(int i) {
        this.STRENGTH = i;
    }

    protected void setCOOLDOWN(int i) {
        this.COOLDOWN = i;
    }

    protected void setIsDead(boolean b) {
        this.isDead = b;
    }

    @Override
    public int getMovingRange() {
        return MOVING_RANGE;

    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public abstract String getName();

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getPlantCalories() {
        return PLANT_CALORIES;
    }

    @Override
    public int getMeatCalories() {
        // TODO Auto-generated method stub
        return MEAT_CALORIES;
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public abstract Command getNextAction(World world); 
     

    @Override
    public void loseEnergy(int energy) {
        isDead = true;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

}
