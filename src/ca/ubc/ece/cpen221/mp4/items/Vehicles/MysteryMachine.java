package ca.ubc.ece.cpen221.mp4.items.Vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Util;

public class MysteryMachine extends AbstractVehicle {
    private int STRENGTH = 500;
    private int MASS = 3;
    private int COOLDOWN_PERIOD = 1;
    private int VIEW_RANGE = 5;
    private String name = "Mystery Machine";
    private ImageIcon image = Util.loadImage("mysterymachine.jpg");

}
