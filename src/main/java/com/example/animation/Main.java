/**
 * Main Class
 * @author: Kyle Wilson
 * This Main class is where actions in each frame tick (loop) takes place. It creates/updates
 * the VBox, scene, canvas, and GUI. This is where buttons/sliders are initialized and
 * firework objects are created.
 * This is where the bulk of user interaction occurs.
 */

package com.example.animation;

//All required libraries are imported
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.util.Duration;

public class Main extends Application {

    //Main class variables are declared

    //RBG values of particles
    public static double particle_color_R;
    public static double particle_color_G;
    public static double particle_color_B;

    //Dimensions of pointer
    public static double angle;
    public static double length;
    public static double launch_x;
    public static double launch_y;

    //Timer variables
    public static int timer;
    public static int timer_length;

    //Bulk of Main class begins
    @Override
    public void start(Stage stage) throws IOException {

        //Array of all firework objects
        ArrayList<Firework> firework_list = new ArrayList<Firework>();

        //Dimensions of window
        int height = 600;
        int width = 1000;

        //Window title
        stage.setTitle("Fireworks");

        //Vbox
        VBox root = new VBox();
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setSpacing(4);
        Scene scene = new Scene(root);

        //Firework type class
        Type t = new Type();


        //HBox for standard launch and change firework buttons
        HBox button_box = new HBox();
        button_box.setAlignment(Pos.CENTER_RIGHT);
        button_box.setSpacing(5);
        root.getChildren().add(button_box);

        Label type_text = new Label("Firework Type: " + Type.type);

        //Launch firework button
        Button launch = new Button("Launch");
        launch.setPrefWidth(400);

        //Change firework type button
        Button change = new Button("Change Firework Type");
        change.setPrefWidth(400);

        button_box.getChildren().addAll(type_text, launch, change);


        //HBox for RGB sliders
        HBox RGB_box = new HBox();
        RGB_box.setAlignment(Pos.CENTER);
        RGB_box.setSpacing(20);
        root.getChildren().add(RGB_box);

        Label RGB_text = new Label("Firework RGB: ");

        //RBG Sliders
        Slider color_change_R = new Slider(0, 1, 0);
        color_change_R.setPrefWidth(200);
        Slider color_change_G = new Slider(0, 1, 0);
        color_change_G.setPrefWidth(200);
        Slider color_change_B = new Slider(0, 1, 0);
        color_change_B.setPrefWidth(200);

        //Current RBG color example
        Rectangle RGB_color = new Rectangle(10, 10);

        RGB_box.getChildren().addAll(RGB_color, RGB_text, color_change_R, color_change_G, color_change_B);


        //HBox for pointer angle
        HBox pointer_angle_box = new HBox();
        pointer_angle_box.setAlignment(Pos.CENTER);
        pointer_angle_box.setSpacing(20);
        root.getChildren().add(pointer_angle_box);

        Label pointer_angle_text = new Label("Launch Angle: ");

        //Pointer angle slider
        Slider pointer_angle = new Slider(-60, 60, 0);
        pointer_angle.setPrefWidth(700);

        pointer_angle_box.getChildren().addAll(pointer_angle_text, pointer_angle);


        //HBox for pointer length
        HBox pointer_length_box = new HBox();
        pointer_length_box.setAlignment(Pos.CENTER);
        pointer_length_box.setSpacing(20);
        root.getChildren().add(pointer_length_box);

        Label pointer_length_text = new Label("Launch Power: ");

        //Pointer length slider
        Slider pointer_length = new Slider(50, 150, 100);
        pointer_length.setPrefWidth(700);

        pointer_length_box.getChildren().addAll(pointer_length_text, pointer_length);


        //Visual divider for firework show buttons/sliders
        Rectangle divider = new Rectangle(900, 5);
        divider.setFill(Paint.valueOf("BLACK"));
        Label firework_show_text = new Label("Firework Show");
        Rectangle underline = new Rectangle(100, 2);
        underline.setFill(Paint.valueOf("BLACK"));

        root.getChildren().addAll(divider, firework_show_text, underline);


        //Hbox for firework show fireworks 1, 4 and 7 (more visually appealing in this order)
        HBox firework_147 = new HBox();
        firework_147.setAlignment(Pos.CENTER);
        firework_147.setSpacing(5);
        root.getChildren().add(firework_147);

        //Firework labels
        Label firework_1_type = new Label("Firework 1 Type: " + Type.type_1);
        Label firework_4_type = new Label("Firework 4 Type: " + Type.type_4);
        Label firework_7_type = new Label("Firework 7 Type: " + Type.type_7);

        //Firework type change buttons
        Button firework_1_change = new Button("Change Firework 1 Type");
        firework_1_change.setPrefWidth(200);
        Button firework_4_change = new Button("Change Firework 4 Type");
        firework_4_change.setPrefWidth(200);
        Button firework_7_change = new Button("Change Firework 7 Type");
        firework_7_change.setPrefWidth(200);

        firework_147.getChildren().addAll(firework_1_type, firework_1_change, firework_4_type, firework_4_change, firework_7_type, firework_7_change);


        //Hbox for firework show fireworks 2, 5 and 8
        HBox firework_258 = new HBox();
        firework_258.setAlignment(Pos.CENTER);
        firework_258.setSpacing(5);
        root.getChildren().add(firework_258);

        //Firework labels
        Label firework_2_type = new Label("Firework 2 Type: " + Type.type_2);
        Label firework_5_type = new Label("Firework 5 Type: " + Type.type_5);
        Label firework_8_type = new Label("Firework 8 Type: " + Type.type_8);

        //Firework type change buttons
        Button firework_2_change = new Button("Change Firework 2 Type");
        firework_2_change.setPrefWidth(200);
        Button firework_5_change = new Button("Change Firework 5 Type");
        firework_5_change.setPrefWidth(200);
        Button firework_8_change = new Button("Change Firework 8 Type");
        firework_8_change.setPrefWidth(200);

        firework_258.getChildren().addAll(firework_2_type, firework_2_change, firework_5_type, firework_5_change, firework_8_type, firework_8_change);


        //Hbox for firework show fireworks 3, 6 and 9
        HBox firework_369 = new HBox();
        firework_369.setAlignment(Pos.CENTER);
        firework_369.setSpacing(5);
        root.getChildren().add(firework_369);

        //Firework labels
        Label firework_3_type = new Label("Firework 3 Type: " + Type.type_3);
        Label firework_6_type = new Label("Firework 6 Type: " + Type.type_6);
        Label firework_9_type = new Label("Firework 9 Type: " + Type.type_9);

        //Firework type change buttons
        Button firework_3_change = new Button("Change Firework 3 Type");
        firework_3_change.setPrefWidth(200);
        Button firework_6_change = new Button("Change Firework 6 Type");
        firework_6_change.setPrefWidth(200);
        Button firework_9_change = new Button("Change Firework 9 Type");
        firework_9_change.setPrefWidth(200);

        firework_369.getChildren().addAll(firework_3_type, firework_3_change, firework_6_type, firework_6_change, firework_9_type, firework_9_change);


        //HBox for firework show buttons
        HBox firework_show_box = new HBox();
        firework_show_box.setAlignment(Pos.CENTER);
        firework_show_box.setSpacing(5);
        root.getChildren().add(firework_show_box);

        //Launch firework show button
        Button launch_show = new Button("Launch Firework Show");
        launch_show.setPrefWidth(200);

        //Reset firework show types button
        Button reset_firework_types = new Button("Reset Firework Show Types");
        reset_firework_types.setPrefWidth(200);

        //Slider to adjust time between firework show launches
        Slider timer_slider = new Slider(25, 300, 100);
        timer_slider.setPrefWidth(450);
        timer = (int)timer_slider.getValue();
        Label timer_text = new Label("Timer: " + timer);

        firework_show_box.getChildren().addAll(launch_show, reset_firework_types, timer_text, timer_slider);


        //Scene
        stage.setScene(scene);

        //Canvas
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Background
        gc.setFill(new Color(0.957, 0.84, 0.82, 1));
        gc.fillRect(0, 0, width, height);

        //New pointer object
        Pointer pointer = new Pointer();

        //Launch button action
        launch.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.launch();
            }
        });

        //Change firework type button action
        change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type();
            }
        });

        //Change firework 1 type (firework show) button action
        firework_1_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_1();
            }
        });

        //Change firework 2 type (firework show) button action
        firework_2_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_2();
            }
        });

        //Change firework 3 type (firework show) button action
        firework_3_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_3();
            }
        });

        //Change firework 4 type (firework show) button action
        firework_4_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_4();
            }
        });

        //Change firework 5 type (firework show) button action
        firework_5_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_5();
            }
        });

        //Change firework 6 type (firework show) button action
        firework_6_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_6();
            }
        });

        //Change firework 7 type (firework show) button action
        firework_7_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_7();
            }
        });

        //Change firework 8 type (firework show) button action
        firework_8_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_8();
            }
        });

        //Change firework 9 type (firework show) button action
        firework_9_change.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.change_type_9();
            }
        });

        //Reset firework show types button action
        reset_firework_types.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.reset_show_type();
            }
        });

        //Launch firework show
        launch_show.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                t.start = true;
            }
        });

        Timeline loop = new Timeline();
        loop.setCycleCount(Timeline.INDEFINITE);

        //Action loop begins
        KeyFrame frames = new KeyFrame(
            Duration.seconds(0.01),
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){

                    //Returns RGB slider values
                    particle_color_R = color_change_R.getValue();
                    particle_color_G = color_change_G.getValue();
                    particle_color_B = color_change_B.getValue();

                    //Returns pointer dimension values
                    angle = pointer_angle.getValue();
                    length = pointer_length.getValue();
                    launch_x = pointer.launch_x;
                    launch_y = pointer.launch_y;

                    //Updates firework type text and color sample
                    type_text.setText("Firework Type: " + Type.type);
                    RGB_color.setFill(new Color(color_change_R.getValue(), color_change_G.getValue(), color_change_B.getValue(), 1));

                    firework_1_type.setText("Firework 1 Type: " + Type.type_1);
                    firework_2_type.setText("Firework 2 Type: " + Type.type_2);
                    firework_3_type.setText("Firework 3 Type: " + Type.type_3);
                    firework_4_type.setText("Firework 4 Type: " + Type.type_4);
                    firework_5_type.setText("Firework 5 Type: " + Type.type_5);
                    firework_6_type.setText("Firework 6 Type: " + Type.type_6);
                    firework_7_type.setText("Firework 7 Type: " + Type.type_7);
                    firework_8_type.setText("Firework 8 Type: " + Type.type_8);
                    firework_9_type.setText("Firework 9 Type: " + Type.type_9);

                    //Updates timer length
                    timer_length = (int)timer_slider.getValue();
                    timer_text.setText("Timer: " + timer_length);

                    //Timer loop for firework show
                    if (t.start && timer > 0){
                        timer--;
                    }
                    if (t.start && timer == 0){
                        t.launch_show();
                        t.show_num++;
                        timer = timer_length;
                    }
                    if (!t.start){
                        t.show_num = 1;
                    }

                    //Firework objects are created
                    if (t.type_fire == 1){
                        firework_list.add(new Firework_1(pointer.launch_x, pointer.launch_y, 20, 1, pointer.dx, pointer.dy, 1));
                        t.type_fire = 0;
                    }
                    if (t.type_fire == 2){
                        firework_list.add(new Firework_2(pointer.launch_x, pointer.launch_y, 20, 1, pointer.dx, pointer.dy, 1));
                        t.type_fire = 0;
                    }
                    if (t.type_fire == 3){
                        firework_list.add(new Firework_3(pointer.launch_x, pointer.launch_y, 20, 1, 0, 0, 1, pointer.launch_x, pointer.launch_y));
                        t.type_fire = 0;
                    }

                    //How a new firework type would be added
                    //if (t.type_fire == 4){
                        //firework_list.add(new Firework_4())
                        //t.type_fire = 0;
                    //}

                    GraphicsContext gc = canvas.getGraphicsContext2D();

                    //Background filled with low opacity to simulate blur effect
                    gc.setFill(new Color(0.957, 0.84, 0.82, 0.3));
                    gc.fillRect(0, 0, width, height);

                    //Updates/deletes fireworks
                    for(int i = 0; i < firework_list.size(); i++)
                    {
                        firework_list.get(i).update(gc);
                        firework_list.get(i).paint(gc);
                        firework_list.get(i).update_fireworks(gc);

                        if (firework_list.get(i).delete && firework_list.get(i).particle_list.size() == 0 && firework_list.get(i).smoke_trail_list.size() == 0){
                            firework_list.remove(i);
                        }
                    }

                    //Updates pointer
                    pointer.paint(gc);
                    pointer.update();
                }
            }
        );

        loop.getKeyFrames().add(frames);
        loop.play();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}