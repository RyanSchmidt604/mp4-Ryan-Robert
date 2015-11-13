package ca.ubc.ece.cpen221.mp4.items.Vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Panzer extends AbstractVehicle {
    private int STRENGTH = 10000;
    private int MASS = 5;
    private int COOLDOWN_PERIOD = 1;
    private int VIEW_RANGE = 3;
    private String name = "Mystery Machine";
    private ImageIcon image = Util.loadImage("panzer.jpg");
    private Location location;

    public Panzer(Location initialLocation){
        this.location = initialLocation;

    }
}
