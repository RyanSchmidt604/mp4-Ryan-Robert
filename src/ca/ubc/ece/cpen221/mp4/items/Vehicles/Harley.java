package ca.ubc.ece.cpen221.mp4.items.Vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Util;

public class Harley extends AbstractVehicle {
    private int STRENGTH = 300;
    private int MASS = 2;
    private int COOLDOWN_PERIOD = 1;
    private int VIEW_RANGE = 7;
    private String name = "Mystery Machine";
    private ImageIcon image = Util.loadImage("motorcycles.gif");
}
