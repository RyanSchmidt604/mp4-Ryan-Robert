package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.Vehicles.Harley;
import ca.ubc.ece.cpen221.mp4.items.Vehicles.MysteryMachine;
import ca.ubc.ece.cpen221.mp4.items.Vehicles.Panzer;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.humans.BuisnessMan;
import ca.ubc.ece.cpen221.mp4.items.humans.Johnny_AppleSeed;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

	static final int X_DIM = 40;
	static final int Y_DIM = 40;
	static final int SPACES_PER_GRASS = 7;
	static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
	static final int INITIAL_GNATS = INITIAL_GRASS / 4;
	static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
	static final int INITIAL_FOXES = INITIAL_GRASS / 32;
	static final int INITIAL_TIGERS = INITIAL_GRASS / 32;
	static final int INITIAL_BEARS = INITIAL_GRASS / 40;
	static final int INITIAL_HYENAS = INITIAL_GRASS / 32;
	static final int INITIAL_CARS = INITIAL_GRASS / 100;
	static final int INITIAL_TRUCKS = INITIAL_GRASS / 150;
	static final int INITIAL_MOTORCYCLES = INITIAL_GRASS / 64;
	static final int INITIAL_MANS = INITIAL_GRASS / 150;
	static final int INITIAL_WOMANS = INITIAL_GRASS / 100;
	static final int INITIAL_HUNTERS = INITIAL_GRASS / 150;
	static final int INITIAL_ELEPHANTS = 1;
    static final int INITIAL_JOHNNY = 1;
    static final int INITIAL_HARLEY = 1;
    static final int INITIAL_PANZER = 1;
    static final int INITIAL_MYSTERYMACHINE = 1;
    static final int INITIAL_MAN = 1;


    

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().createAndShowWorld();
			}
		});
	}

	public void createAndShowWorld() {
		World world = new WorldImpl(X_DIM, Y_DIM);
		initialize(world);
		new WorldUI(world).show();
	}

	public void initialize(World world) {
		addGrass(world);
		world.addActor(new Gardener());

		addGnats(world);
		addRabbits(world);
		addFoxes(world);
		addElephants(world);
		addBears(world);
		addTigers(world);
		addJohnnyAppleSeed(world);
		addPanzer(world);
		addHarley(world);
		addMysteryMachine(world);
		addBuisnessMan(world);
		
		// TODO: You may add your own creatures here!
	}

	

    private void addBuisnessMan(World world) {
        for (int i = 0; i < INITIAL_MAN; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            BuisnessMan man = new BuisnessMan(loc);
            world.addItem(man);
            world.addActor(man);
        }        
    }

    private void addGrass(World world) {
		for (int i = 0; i < INITIAL_GRASS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			world.addItem(new Grass(loc));
		}
	}

	private void addGnats(World world) {
		for (int i = 0; i < INITIAL_GNATS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Gnat gnat = new Gnat(loc);
			world.addItem(gnat);
			world.addActor(gnat);
		}
	}

	private void addFoxes(World world) {
		FoxAI foxAI = new FoxAI();
		for (int i = 0; i < INITIAL_FOXES; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Fox fox = new Fox(foxAI, loc);
			world.addItem(fox);
			world.addActor(fox);
		}
	}

	private void addRabbits(World world) {
		RabbitAI rabbitAI = new RabbitAI();
		for (int i = 0; i < INITIAL_RABBITS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Rabbit rabbit = new Rabbit(rabbitAI, loc);
			world.addItem(rabbit);
			world.addActor(rabbit);
		}
	}
	private void addElephants(World world) {
        for (int i = 0; i < INITIAL_ELEPHANTS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Elephant elephant = new Elephant(loc);
            world.addItem(elephant);
            world.addActor(elephant);
        }
    }
	private void addBears(World world){
	    for (int i = 0; i < INITIAL_BEARS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bear bear = new Bear(loc);
            world.addItem(bear);
            world.addActor(bear);
        }
	    
	}
	private void addTigers(World world) {
	    FoxAI foxAI = new FoxAI();
        for (int i = 0; i < INITIAL_TIGERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Tiger tiger = new Tiger(foxAI, loc);
            world.addItem(tiger);
            world.addActor(tiger);
        }
    }
	private void addJohnnyAppleSeed(World world){
	    for (int i = 0; i < INITIAL_JOHNNY; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Johnny_AppleSeed johnny = new Johnny_AppleSeed(loc);
            world.addItem(johnny);
            world.addActor(johnny);
        }
	}
	   private void addHarley(World world){
	        for (int i = 0; i < INITIAL_HARLEY; i++) {
	            Location loc = Util.getRandomEmptyLocation(world);
	            Harley harley = new Harley(loc);
	            world.addItem(harley);
	            world.addActor(harley);
	        }
	    }
	    private void addPanzer(World world){
	        for (int i = 0; i < INITIAL_PANZER; i++) {
	            Location loc = Util.getRandomEmptyLocation(world);
	            Panzer panzer = new Panzer(loc);
	            world.addItem(panzer);
	            world.addActor(panzer);
	        }
	    }
	    private void addMysteryMachine(World world){
	        for (int i = 0; i < INITIAL_MYSTERYMACHINE; i++) {
	            Location loc = Util.getRandomEmptyLocation(world);
	            MysteryMachine machine = new MysteryMachine(loc);
	            world.addItem(machine);
	            world.addActor(machine);
	        }
	    }
}