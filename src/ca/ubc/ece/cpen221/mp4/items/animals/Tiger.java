package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Tiger extends AbstractArenaAnimal {

    public Tiger(AI foxAI, Location initialLocation) {
        this.setINITIAL_ENERGY(150);
        this.setMAX_ENERGY(200);
        this.setSTRENGTH(200);
        this.setVIEW_RANGE(10);
        this.setMIN_BREEDING_ENERGY(70);
        this.setCOOLDOWN(2);
        this.setImage(Util.loadImage("tiger.gif"));
        this.setLocation(initialLocation);
        this.setAi(foxAI);
    }

    @Override
    public LivingItem breed() {
        Tiger child = new Tiger(this.getAi(), this.getLocation());
        child.setEnergy(this.getEnergy() / 2);
        this.setEnergy(this.getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Tiger";
    }

}
