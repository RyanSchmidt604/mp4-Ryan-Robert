package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public class OvertakeCommand implements Command {
    private MoveableItem killer;
    private Item victim;
    
    public OvertakeCommand(MoveableItem killer, Item victim){
        this.killer = killer;
        this.victim = victim;
    }

    @Override
    public void execute(World world) throws InvalidCommandException {
        victim.loseEnergy(500000);
        killer.moveTo(victim.getLocation());
    }

}
