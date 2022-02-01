/**
 * Firework 3 Class
 * @author: Kyle Wilson
 * This class extends the firework abstract class, it is
 * the third type of firework (explosion shoots particles
 * like sparkler). Uses explosion abstract method, exploding
 * immediately with directional particles.
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;

//Extends parent class
public class Firework_3 extends Firework{

    //Declares variables used to create and count amount of particles
    private int count = 1;
    private int count_num = 100;
    private double launch_x;
    private double launch_y;

    /**
     * Firework 3 method
     * Gets variables necessary to create firework 3
     */
    public Firework_3(double x, double y, int size, double elasticity, double dx, double dy, int type, double launch_x, double launch_y) {
        super(x, y, size, elasticity, dx, dy, type);

        this.launch_x = launch_x;
        this.launch_y = launch_y;
    }

    /**
     * Update Method
     * Overrode update method to explode immediately
     */
    @Override
    public void update(GraphicsContext g){

        //Hides firework
        hide = true;

        //Explodes immediately
        explode();

        //Updates particles
        particles(g);
    }

    /**
     * Explode Method
     * Create particle objects over a period of time and hide
     * firework once particles are dissipated
     */
    @Override
    public void explode() {

        //Adds particle object
        if(count < count_num){
            particle_list.add(new Particles(launch_x, launch_y, 0.3, 2));
        }

        //When enough particles have been created, the firework initiates deletion
        if(count >= count_num){
            delete = true;
        }

        //Increase count
        count ++;
    }

    /**
     * Update firework abstract Method
     * Not used with this firework type
     */
    @Override
    public void update_fireworks(GraphicsContext g) {}
}
