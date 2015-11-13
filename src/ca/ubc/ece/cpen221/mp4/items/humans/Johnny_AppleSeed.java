package ca.ubc.ece.cpen221.mp4.items.humans;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.*;
import ca.ubc.ece.cpen221.mp4.items.Grass;

public class Johnny_AppleSeed extends AbstractHumans {

    public Johnny_AppleSeed(Location initialLocation) {
        this.setSTRENGTH(150);
        this.setCOOLDOWN(2);
        this.setImage(Util.loadImage("man.gif"));
        this.setIsDead(false);
        this.setMEAT_CALORIES(400);
        this.setMOVING_RANGE(10);
        this.setlocation(initialLocation);
    }

    @Override
    public String getName() {
        return "Johnny_AppleSeed";
    }

    @Override
    public Command getNextAction(World world) {
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
            world.addItem(new Grass(this.getLocation()));
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }

}
