/**
 * Firework 1 Class
 * @author: Kyle Wilson
 * This class extends the firework abstract class, it is
 * the first type of firework (the standard version). It
 * uses the explosion method to differentiate with other
 * fireworks.
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;

//Extends parent class
public class Firework_1 extends Firework{

    /**
     * Firework 1 method
     * Gets variables necessary to create firework 1
     */
    public Firework_1(double x, double y, int size, double elasticity, double dx, double dy, int type) {
        super(x, y, size, elasticity, dx, dy, type);
    }

    /**
     * Explode Method
     * Standard explosion - creates set amount of particles based off of
     * type of firework (firework type 2 creates set amount of firework 1)
     */
    @Override
    public void explode(){
        //100 particles if originally type 1
        if (type == 1){
            for(int i = 0; i < 100; i++){
                particle_list.add(new Particles(x, y, 0.3, 1));
            }
        }
        //20 particles if originally type 2
        if (type == 2){
            for(int i = 0; i < 20; i++){
                particle_list.add(new Particles(x, y, 0.3, 1));
            }
        }

        //Hides after particles are created
        hide = true;
    }

    /**
     * Update firework abstract Method
     * Not used with this firework type
     */
    @Override
    public void update_fireworks(GraphicsContext g) {}

}
