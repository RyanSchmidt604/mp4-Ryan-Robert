package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Bear extends AbstractArenaAnimal {

    Bear(Location initialLocation){
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
        child.setEnergy(this.getEnergy()/2);
        this.setEnergy(this.getEnergy()/2);
        return child;    }

    @Override
    public String getName() {
        return "Bear";
    }




    @Override
    public Command getNextAction(World world){
        return new WaitCommand();
    }
}
