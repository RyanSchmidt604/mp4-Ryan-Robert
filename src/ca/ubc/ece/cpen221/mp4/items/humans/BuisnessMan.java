package ca.ubc.ece.cpen221.mp4.items.humans;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class BuisnessMan extends AbstractHumans {

    public BuisnessMan(Location initialLocation) {
        this.setSTRENGTH(100);
        this.setCOOLDOWN(2);
        this.setImage(Util.loadImage("man.gif"));
        this.setIsDead(false);
        this.setMEAT_CALORIES(100);
        this.setMOVING_RANGE(1);
        this.setlocation(initialLocation);
    }

    @Override
    public String getName() {
        return "Buisness_man";
    }

    @Override
    public Command getNextAction(World world) {
       Set<Item> surroudings = world.searchSurroundings((ArenaAnimal) this);
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
            world.addItem(new Grass(this.getLocation()));
            return new MoveCommand(this, targetLocation);
        }else{
            for(Item i : surroudings){
                if(i.getLocation().equals(targetLocation)){
                    i.loseEnergy(5000);
                    return new MoveCommand(this,targetLocation);
                }
            }
        }

        return new WaitCommand();
    }

}
