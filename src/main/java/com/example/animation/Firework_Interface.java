/**
 * Firework interface Class
 * @author: Kyle Wilson
 * This interface provides firework with all necessary
 * empty methods to be implemented
 */

package com.example.animation;

import javafx.scene.canvas.GraphicsContext;

public interface Firework_Interface {
    void paint(GraphicsContext g);
    void update(GraphicsContext g);
    void gravity();
    void collision();
    void smoke_trail(GraphicsContext g);
    void particles(GraphicsContext g);
    void explode();
    void update_fireworks(GraphicsContext g);
}
