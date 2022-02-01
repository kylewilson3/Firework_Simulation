/**
 * Particles Class
 * @author: Kyle Wilson
 * This class entails the updating, collision, gravity,
 * painting, and dispersion of explosion particles.
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.lang.Math;

public class Particles {

    //Declares variables
    private double max_r = 15;
    private double min_r = 1;
    private double range_r = max_r - min_r + 1;
    private double pointer_angle;
    private double max_a;
    private double min_a;
    private double range_a;
    private double x;
    private double y;
    private double dx = 0;
    private double dy = 0;
    private double radius = (double)(Math.random() * range_r) + min_r;;
    private double angle;
    private double ex;
    private double ey;
    private int height = 600;
    private int width = 1000;
    private double ceiling = 0;
    private double wall_l = 0;
    private double elasticity;
    private int disperse_type;
    private double gravity = 0.03;
    public boolean hide = false;
    private double size = 10;
    private double size_change = 0.075;
    private double opacity = 1;
    private double opacity_change = 0.01;
    private double particle_color_R = 0;
    private double particle_color_G = 0;
    private double particle_color_B = 0;

    //Particle class begins, taking in relevant information
    public Particles(double x, double y, double elasticity, int disperse_type){
        this.x = x;
        this.y = y;
        this.elasticity = elasticity;
        this.disperse_type = disperse_type;
        this.particle_color_R = Main.particle_color_R;
        this.particle_color_G = Main.particle_color_G;
        this.particle_color_B = Main.particle_color_B;
        this.pointer_angle = Main.angle * Math.PI/180;

        //Standard explosion random angles
        if (disperse_type == 1){
            max_a = 2*Math.PI;
            min_a = 0;
            range_a = max_a - min_a + 0.017;
            angle = (double)(Math.random() * range_a) + min_a;
        }

        //Sparkler explosion random angles between given angles
        if (disperse_type == 2){
            max_a = pointer_angle + (Math.PI/6);
            min_a = pointer_angle - (Math.PI/6);
            range_a = max_a - min_a + 0.017;
            angle = (double)(Math.random() * range_a) + min_a;

            this.x = Main.launch_x;
            this.y = Main.launch_y;

            if (angle < 0){
                angle += 2*Math.PI;
            }
            if (angle > 2*Math.PI){
                angle -= 2*Math.PI;
            }
        }

        disperse_range();
    }

    /**
     * Paint Method
     * Paints particles if they are visible
     */
    public void paint(GraphicsContext g){
        if (!hide){
            g.setFill(new Color(particle_color_R, particle_color_G, particle_color_B, opacity));
            g.fillOval(x, y, size, size);
        }
    }

    /**
     * Update Method
     * Updates particle position based off of dispersion, gravity,
     * collision, and velocity
     */
    public void update(){
        double floor = height - size;
        double wall_r = width - size;

        gravity();
        disperse();

        x += dx + ex;
        y += dy + ey;

        if (x < wall_l){
            x = wall_l;
        }
        if (x > wall_r){
            x = wall_r;
        }
        if (y > floor){
            y = floor;
        }
        if (y < ceiling){
            y = ceiling;
        }

        collision();
    }

    /**
     * Gravity Method
     * Simulates gravity on particle
     */
    public void gravity(){
        double floor = height - size;

        if (y < floor){
            dy += gravity;
        }
    }

    /**
     * Collision Method
     * Detects collision with surfaces and reverses velocity
     * while applying elasticity value
     */
    public void collision(){
        double floor = height - size - size_change;
        double wall_r = width - size - size_change;

        if (y == floor || y == ceiling){
            dy *= -1 * elasticity;
            ey *= -1;
        }
        if (x == wall_l || x == wall_r){
            dx *= -1 * elasticity;
            ex *= -1;
        }
    }

    /**
     * Disperse Method
     * Disperses particles with added explosion velocity
     * while shrinking and reducing particle opacity
     */
    public void disperse(){
        ex *= 0.93;
        ey *= 0.93;


        size -= size_change;

        if (opacity >= opacity_change){
            opacity -= opacity_change;
        }

        if (size <= 0){
            hide = true;
        }
    }

    /**
     * Disperse range Method
     * Randomly generates an angle and creates a corresponding
     * random x and y velocity for particles
     */
    public void disperse_range(){
        if (angle > 0 && angle < Math.PI/2){
            ex = radius * Math.sin(angle);
            ey = -1 * radius * Math.cos(angle);
        }

        if (angle == Math.PI/2){
            ex = radius;
            ey = 0;
        }
        if (angle > Math.PI/2 && angle < Math.PI){
            ex = radius * Math.cos(angle - (Math.PI/2));
            ey = radius * Math.sin(angle - (Math.PI/2));
        }

        if (angle == Math.PI){
            ex = 0;
            ey = radius;
        }
        if (angle > Math.PI && angle < 3*Math.PI/2){
            ex = -1 * radius * Math.sin(angle - (2*Math.PI/2));
            ey = radius * Math.cos(angle - (2*Math.PI/2));
        }

        if (angle == 3*Math.PI/2){
            ex = -1 * radius;
            ey = 0;
        }
        if (angle > 3*Math.PI/2 && angle < 2*Math.PI){
            ex = -1 * radius * Math.cos(angle - (3*Math.PI/2));
            ey = -1 * radius * Math.sin(angle - (3*Math.PI/2));
        }
        if (angle == 2*Math.PI){
            ex = 0;
            ey = -1 * radius;
        }
    }
}
