/**
 * Firework 2 Class
 * @author: Kyle Wilson
 * This class extends the firework abstract class, it is
 * the second type of firework (explosion shoots set amount
 * of firework type 1). Uses both abstract methods, first for
 * explosion, then to update/delete created firework objects.
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

//Extends parent class
public class Firework_2 extends Firework{

    //Declared variables for random aspects and explosion's additions in velocity
    private double max_r = 1;
    private double min_r = 0.1;
    private double range_r = max_r - min_r + 1;
    private double max_a = 2*Math.PI;
    private double min_a = 0;
    private double range_a = max_a - min_a + 0.017;
    private double ex;
    private double ey;

    //Array list of firework 1 objects
    public ArrayList<Firework_1> firework_1_list = new ArrayList<Firework_1>();

    /**
     * Firework 2 method
     * Gets variables necessary to create firework 2
     */
    public Firework_2(double x, double y, int size, double elasticity, double dx, double dy, int type) {
        super(x, y, size, elasticity, dx, dy, type);
    }

    /**
     * Explode Method
     * Creates set amount of firework type 1 randomly in dispersion range
     */
    @Override
    public void explode(){

        //Creates 10 firework 1 objects
        for(int i = 0; i < 10; i++){
            disperse_range();
            firework_1_list.add(new Firework_1(x, y, 10, 0.5, ex, ey, 2));
            firework_1_list.get(i).gravity = 0.0025;
        }

        //Hides firework after explosion
        hide = true;
    }

    /**
     * Explode Method
     * Updates, paints, and deletes set of firework types 1 created from explosion
     */
    @Override
    public void update_fireworks(GraphicsContext g){

        //for fireworks in array list
        for(int i = 0; i < firework_1_list.size(); i++)
        {

            //Paints and updates
            firework_1_list.get(i).paint(g);
            firework_1_list.get(i).update(g);

            //Deletes
            if (firework_1_list.get(i).delete && firework_1_list.get(i).particle_list.size() == 0){
                firework_1_list.remove(i);
                delete = true;
            }
        }
    }

    /**
     * Disperse Range Method
     * Randomly generates an angle and creates a corresponding
     * random x and y velocity for firework type 1
     */
    public void disperse_range(){
        double radius = (double)(Math.random() * range_r) + min_r;
        double angle = (double)(Math.random() * range_a) + min_a;

        if (angle == 0){
            ex = 0;
            ey = radius;
        }
        if (angle > 0 && angle < Math.PI/2){
            ex = radius * Math.cos(angle);
            ey = radius * Math.sin(angle);
        }

        if (angle == Math.PI/2){
            ex = radius;
            ey = 0;
        }
        if (angle > Math.PI/2 && angle < Math.PI){
            ex = radius * Math.cos(angle - (Math.PI/2));
            ey = -1 * radius * Math.sin(angle - (Math.PI/2));
        }

        if (angle == Math.PI){
            ex = 0;
            ey = -1 * radius;
        }
        if (angle > Math.PI && angle < 3*Math.PI/2){
            ex = -1 * radius * Math.cos(angle - (2*Math.PI/2));
            ey = -1 * radius * Math.sin(angle - (2*Math.PI/2));
        }

        if (angle == 3*Math.PI/2){
            ex = -1 * radius;
            ey = 0;
        }
        if (angle > 3*Math.PI/2 && angle < 2*Math.PI){
            ex = -1 * radius * Math.cos(angle - (3*Math.PI/2));
            ey = radius * Math.sin(angle - (3*Math.PI/2));
        }
        if (angle == 2*Math.PI){
            ex = 0;
            ey = radius;
        }
    }

}
