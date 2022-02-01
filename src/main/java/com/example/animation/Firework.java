/**
 * Firework Abstract Class
 * @author: Kyle Wilson
 * This abstract class is a blueprint for all firework types. It contains
 * paint, update, collision, and gravity logic. It also updates explosion
 * particles and updates/creates smoke particles. New firework types are
 * extended from this class.
 */

package com.example.animation;

//Imports necessary libraries
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.lang.Math;

//Firework abstract class begins, implements firework interface
public abstract class Firework implements Firework_Interface{

    //Variables are declared
    public double size;
    public double x;
    public double y;
    public double dx;
    public double dy;
    public int height = 600;
    public int width = 1000;
    public int ceiling = 0;
    public int wall_l = 0;
    public double gravity = 0.025;
    public double elasticity;
    public boolean hide = false;
    public boolean delete = false;
    public double opacity = 0.99;
    public double opacity_change = 0.01;
    public double trail_angle;
    public double trail_length;
    public double timer = 3;
    public int amount = 0;
    public int type;


    //Array list of explosion particles and smoke particles
    public ArrayList<Particles> particle_list = new ArrayList<Particles>();
    public ArrayList<Smoke_trail> smoke_trail_list = new ArrayList<Smoke_trail>();

    /**
     * Firework method
     * Gets variables necessary to create firework
     */
    public Firework(double x, double y, int size, double elasticity, double dx, double dy, int type){
        this.x = x;
        this.y = y;
        this.size = size;
        this.elasticity = elasticity;
        this.dx = dx;
        this.dy = dy;
        this.type = type;
    }

    /**
     * Paint method
     * Paints the firework if it is not hidden
     */
    public void paint(GraphicsContext g){
        if (!hide && !delete){
            Color c = Color.rgb(0, 0,0, opacity);
            g.setFill(c);
            g.fillOval(x, y, size, size);
        }
    }

    /**
     * Update method
     * Updates the firework's position based off its
     * physics and changes. Also calculates trail angle
     * from its velocity.
     */
    public void update(GraphicsContext g){

        //Establishes floor and right wall position
        int floor = height - (int)size;
        int wall_r = width - (int)size;

        //Calls gravity and collision method
        gravity();
        collision();

        //Calls smoke trail update/creation method if firework type uses it
        if(type == 1){
            smoke_trail(g);
        }

        //Updates particles
        particles(g);

        //Incrementally lower firework opacity
        if(opacity >= opacity_change && opacity < 1){
            opacity -= opacity_change;
        }

        //When firework opacity is 0, explode
        if (opacity < opacity_change){
            explode();
            opacity = 1;
        }

        //Update position by change in position
        x += dx;
        y += dy;

        //Calculate trail length
        trail_length = Math.sqrt(dx*dx + dy*dy);

        //Calculate trail angle
        if (dx == 0 && dy >= 0){
            trail_angle = 0;
        }
        if (dx == 0 && dy < 0){
            trail_angle = Math.PI;
        }
        if (dx > 0 && dy == 0){
            trail_angle = 3*Math.PI/2;
        }
        if (dx < 0 && dy == 0){
            trail_angle = Math.PI/2;
        }
        if (dx > 0 && dy > 0){
            trail_angle = Math.atan(dy/dx) + 3*Math.PI/2;
        }
        if (dx > 0 && dy < 0){
            trail_angle = Math.PI/2 - Math.atan(-dy/dx) + Math.PI;
        }
        if (dx < 0 && dy < 0){
            trail_angle = Math.atan(dy/dx) + Math.PI/2;
        }
        if (dx < 0 && dy > 0){
            trail_angle = Math.PI/2 - Math.atan(-dy/dx);
        }

        //Logic for out of bounds

        //For walls
        if (x < wall_l){
            x = wall_l;
        }
        if (x > wall_r){
            x = wall_r;
        }

        //For floor/ceiling
        if (y > floor){
            y = floor;
        }
        if (y < ceiling){
            y = ceiling;
        }
    }

    /**
     * Gravity method
     * Physics constantly simulating effect of gravity
     * on firework
     */
    public void gravity(){
        int floor = height - (int)size;

        if (y < floor){
            dy += gravity;
        }
    }

    /**
     * Collision method
     * Detects collision with surfaces and reverses velocity
     * while applying elasticity value
     */
    public void collision(){
        int floor = height - (int)size;
        int wall_r = width - (int)size;

        //For floor/ceiling
        if (y == floor || y == ceiling){
            dy *= -1 * elasticity;
        }

        //For walls
        if (x == wall_l || x == wall_r){
            dx *= -1 * elasticity;
        }
    }

    /**
     * Smoke trail method
     * Updates, deletes and creates smoke particle objects.
     */
    public void smoke_trail(GraphicsContext g){

        //Timer for creating smoke particle objects
        if (timer == 1 && amount > 0 && !hide){
            smoke_trail_list.add(new Smoke_trail(x + (size/3/2), y + (size/3/2), trail_length, trail_angle, (size*2/3) ));
        }

        //Updates all smoke particles in smoke particle array list
        for(int i = 0; i < smoke_trail_list.size(); i++)
        {
            smoke_trail_list.get(i).paint(g);
            smoke_trail_list.get(i).update();
        }

        //Deletes finished smoke particles
        for(int i = 0; i < smoke_trail_list.size(); i++){
            if (smoke_trail_list.get(i).hide){
                smoke_trail_list.remove(i);
            }
        }

        //Decreases timer (therefore increasing amount of smoke particles)
        //based off of velocity of firework
        amount = (int)trail_length;
        timer -= 1;
        if (timer == 0){
            timer = Math.abs(8-amount);
        }
    }

    /**
     * Particles method
     * Updates and deletes explosion particle objects. Also
     * tells firework class to delete once particles have
     * all deleted after explosion.
     */
    public void particles(GraphicsContext g){

        //Update particles
        for(int i = 0; i < particle_list.size(); i++)
        {
            particle_list.get(i).paint(g);
            particle_list.get(i).update();
        }

        //Delete particles
        for(int i = 0; i < particle_list.size(); i++){
            if (particle_list.get(i).hide){
                particle_list.remove(i);
            }
        }

        //Set firework to delete
        if(hide && particle_list.size() == 0){
            delete = true;
        }
    }

    /**
     * Abstract methods
     * Empty to be used in child firework type classes
     * (necessary here as it is used in base update method as well
     * as universal firework update in main method)
     */
    public abstract void explode();
    public abstract void update_fireworks(GraphicsContext g);
}
