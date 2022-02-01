/**
 * Pointer Class
 * @author: Kyle Wilson
 * This class paint, update, and determine launch
 * velocity of firework from pointer
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class Pointer {

    //Declares variables
    public double length;
    public double width = 20;
    public double x;
    public double y;
    public double launch_x;
    public double launch_y;
    public double rotate_x;
    public double rotate_y;
    public double angle;
    public double dx;
    public double dy;

    /**
     * Pointer method
     * Required but contains nothing
     */
    public Pointer(){}

    /**
     * Paint method
     * Paints the pointer, rotating the screen, painting pointer,
     * then rotating screen back so as to not rotate all other
     * painted items on screen
     */
    public void paint(GraphicsContext g){
        g.setFill(Color.BLACK);
        g.transform(new Affine(new Rotate(angle, rotate_x, rotate_y)));
        g.fillRect(x, y, width, length + 20);
        g.transform(new Affine(new Rotate(-angle, rotate_x, rotate_y)));
    }

    /**
     * Update method
     * Updates the angle and position of the pointer
     */
    public void update(){
        this.angle = Main.angle;
        this.length = Main.length;

        x = 500 - width;
        y = 600 - length;
        rotate_x = x + (width/2);
        rotate_y = y + length;

        launch_range();
    }

    /**
     * Launch range method
     * Determines the x and y velocity of the firework depending on
     * the pointer length and angle
     */
    public void launch_range(){
        if (angle > 0 && angle < 90){
            dx = (length/20) * Math.sin(angle * Math.PI/180);
            dy = -1 * (length/20) * Math.cos(angle * Math.PI/180);

            launch_x = x + (dx * 20);
            launch_y = 600 + (dy * 20);
        }

        if (angle < 0 && angle > -90){
            dx = -1 * (length/20) * Math.sin(Math.abs(angle) * Math.PI/180);
            dy = -1 * (length/20) * Math.cos(Math.abs(angle) * Math.PI/180);

            launch_x = x + (dx * 20);
            launch_y = 600 + (dy * 20);
        }

        if (angle == 0){
            dx = 0;
            dy = -1 * (length/20);

            launch_x = x + (dx * 20);
            launch_y = 600 + (dy * 20);
        }
    }
}
