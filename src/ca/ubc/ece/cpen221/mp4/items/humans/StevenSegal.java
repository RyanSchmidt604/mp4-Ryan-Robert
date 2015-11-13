package ca.ubc.ece.cpen221.mp4.items.humans;




import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.OvertakeCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class StevenSegal extends AbstractHumans {
    
    public StevenSegal(Location initialLocation){
        this.setSTRENGTH(20000);
        this.setCOOLDOWN(0);
        this.setImage(Util.loadImage("segal.jpg"));
        this.setIsDead(false);
        this.setMEAT_CALORIES(400);
        this.setMOVING_RANGE(10);
        this.setlocation(initialLocation);
    }

    @Override
    public String getName() {
        return "Steven Segal";
    }

    @Override
    public Command getNextAction(World world) {
        Location target = new Location(this.getLocation(), Util.getRandomDirection());
        while(!Util.isValidLocation(world, target)){
            target = new Location(this.getLocation(), Util.getRandomDirection());
        }
        
        if(Util.isLocationEmpty(world, target)){
            return new MoveCommand(this, target);
        }else{
            Set<Item> nearby = world.searchSurroundings(this.getLocation(), 1);
            for(Item item : nearby){
                if(target.equals(item.getLocation())){
                    return new OvertakeCommand(this, item);
                }
            }
        }
        return new WaitCommand();
    }

}
