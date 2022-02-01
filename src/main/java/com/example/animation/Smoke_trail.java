/**
 * Smoke trail Class
 * @author: Kyle Wilson
 * This class entails the updating, collision, painting,
 * and dispersion of firework smoke trail particles.
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Smoke_trail {

    //Declares variables
    private double trail_length;
    private double trail_angle;
    private double max_r;
    private double min_r;
    private double range_r;
    private double max_a;
    private double min_a;
    private double range_a;
    private double x;
    private double y;
    private double dx;
    public double dy;
    private double radius;
    private double angle;
    private int height = 600;
    private int width = 1000;
    private double ceiling = 0;
    private double wall_l = 0;
    public boolean hide = false;
    private double size;
    private double color_R = 0.80859375;
    private double color_G = 0.27734375;
    private double color_B = 0.14453125;
    private double final_color_R = 0.62890625;
    private double final_color_G = 0.5625;
    private double final_color_B = 0.546875;
    private double change_speed = 100;
    private double change_R = (color_R - final_color_R) / change_speed;
    private double change_G = (final_color_G - color_G) / change_speed;
    private double change_B = (final_color_B - color_B) / change_speed;
    private double opacity = 1;
    private double opacity_change = 0.01;

    /**
     * Smoke trail method
     * Gets variables necessary to create smoke trail. Also
     * produces random values for trail dispersion
     */
    public Smoke_trail(double x, double y, double trail_length, double trail_angle, double size){
        this.x = x;
        this.y = y;
        this.trail_length = trail_length;
        this.trail_angle = trail_angle;
        this.size = size;

        max_r = trail_length/2;
        min_r = max_r/4;
        range_r = max_r - min_r + 1;
        max_a = trail_angle + (Math.PI/9);
        min_a = trail_angle - (Math.PI/9);
        range_a = max_a - min_a + 0.0001;
        radius = (double)(Math.random() * range_r) + min_r;
        angle = (double)(Math.random() * range_a) + min_a;

        if (angle < 0){
            angle += 2*Math.PI;
        }
        if (angle > 2*Math.PI){
            angle -= 2*Math.PI;
        }

        disperse_range();
    }

    /**
     * Paint Method
     * Paints smoke trail particles if not hidden
     */
    public void paint(GraphicsContext g){
        if (!hide){
            g.setFill(new Color(color_R, color_G, color_B, opacity));
            g.fillOval(x, y, size, size);
        }
    }

    /**
     * Update method
     * Updates smoke particle collision, position, and dispersion
     */
    public void update(){
        double floor = height - size;
        double wall_r = width - size;

        disperse();

        x += dx;
        y += dy;

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
     * Collision Method
     * Detects collision with surfaces
     */
    public void collision(){
        double floor = height - size;
        double wall_r = width - size;

        if (y == floor || y == ceiling){
            dy = 0;
        }
        if (x == wall_l || x == wall_r){
            dx = 0;
        }
    }

    /**
     * Disperse Method
     * Disperses smoke particles with added trail velocity
     * while changing color and reducing particle opacity
     */
    public void disperse(){
        dx *= 0.99;
        dy *= 0.99;

        //Reduces opacity until final color is achieved
        if (color_R <= final_color_R && color_G >= final_color_G && color_B >= final_color_B && opacity >= opacity_change){
            opacity -= opacity_change;
        }

        //Hides when opacity change in completed
        if (opacity < opacity_change){
            hide = true;
        }

        //Changes color of smoke particle over time
        if (color_R > final_color_R){
            color_R -= change_R;
        }
        if (color_G < final_color_G){
            color_G += change_G;
        }
        if (color_B < final_color_B){
            color_B += change_B;
        }
    }

    /**
     * Disperse range Method
     * Randomly generates an angle and creates a corresponding
     * random x and y velocity for smoke particles within
     * trail angle range
     */
    public void disperse_range(){
        if (angle > 0 && angle < Math.PI/2){
            dx = radius * Math.sin(angle);
            dy = -1 * radius * Math.cos(angle);
        }

        if (angle == Math.PI/2){
            dx = radius;
            dy = 0;
        }
        if (angle > Math.PI/2 && angle < Math.PI){
            dx = radius * Math.cos(angle - (Math.PI/2));
            dy = radius * Math.sin(angle - (Math.PI/2));
        }

        if (angle == Math.PI){
            dx = 0;
            dy = radius;
        }
        if (angle > Math.PI && angle < 3*Math.PI/2){
            dx = -1 * radius * Math.sin(angle - (2*Math.PI/2));
            dy = radius * Math.cos(angle - (2*Math.PI/2));
        }

        if (angle == 3*Math.PI/2){
            dx = -1 * radius;
            dy = 0;
        }
        if (angle > 3*Math.PI/2 && angle < 2*Math.PI){
            dx = -1 * radius * Math.cos(angle - (3*Math.PI/2));
            dy = -1 * radius * Math.sin(angle - (3*Math.PI/2));
        }
        if (angle == 2*Math.PI){
            dx = 0;
            dy = -1 * radius;
        }
    }

}
