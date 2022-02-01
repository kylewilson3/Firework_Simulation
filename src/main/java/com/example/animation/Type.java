/**
 * Firework type Class
 * @author: Kyle Wilson
 * This class holds all the values of firework types. It also contains
 * logic for changing firework type and calling to launch fireworks.
 */

package com.example.animation;

//Type class begins
public class Type {

    //Amount of firework types - change when adding firework types
    public int firework_type_amount = 3;

    //Timer variables
    public boolean start = false;
    public int show_num = 1;

    //Type variables
    public int type_fire = 0;
    public static int type = 1;
    public static int type_1 = 1;
    public static int type_2 = 1;
    public static int type_3 = 1;
    public static int type_4 = 1;
    public static int type_5 = 1;
    public static int type_6 = 1;
    public static int type_7 = 1;
    public static int type_8 = 1;
    public static int type_9 = 1;


    /**
     * Type method
     * Required but contains nothing
     */
    public Type(){}

    /**
     * Launch method
     * Tells main class to launch certain fireworks
     */
    public void launch(){
        type_fire = type;
    }

    /**
     * Change firework type method
     * Tells main class to change standard firework type
     */
    public void change_type(){
        type += 1;

        if (type == firework_type_amount + 1){
            type = 1;
        }
    }

    /**
     * Launch show method
     * Tells main class to fire firework show with certain firework types
     */
    public void launch_show(){
        if (show_num == 1){
            type_fire = type_1;
        }
        if (show_num == 2){
            type_fire = type_2;
        }
        if (show_num == 3){
            type_fire = type_3;
        }
        if (show_num == 4){
            type_fire = type_4;
        }
        if (show_num == 5){
            type_fire = type_5;
        }
        if (show_num == 6){
            type_fire = type_6;
        }
        if (show_num == 7){
            type_fire = type_7;
        }
        if (show_num == 8){
            type_fire = type_8;
        }
        if (show_num == 9){
            type_fire = type_9;
        }

        //Ends firework show when all 9 fireworks have been launched
        if (show_num == 10){
            start = false;
        }
    }

    /**
     * Reset firework type method
     * Tells main class to reset all firework show types
     */
    public void reset_show_type(){
        type_1 = 1;
        type_2 = 1;
        type_3 = 1;
        type_4 = 1;
        type_5 = 1;
        type_6 = 1;
        type_7 = 1;
        type_8 = 1;
        type_9 = 1;
    }

    /**
     * Change firework 1 type (firework show) method
     * Tells main class to change firework 1 type
     */
    public void change_type_1(){
        type_1 += 1;

        if (type_1 == firework_type_amount + 1){
            type_1 = 1;
        }
    }
    /**
     * Change firework 2 type (firework show) method
     * Tells main class to change firework 2 type
     */
    public void change_type_2(){
        type_2 += 1;

        if (type_2 == firework_type_amount + 1){
            type_2 = 1;
        }
    }
    /**
     * Change firework 3 type (firework show) method
     * Tells main class to change firework 3 type
     */
    public void change_type_3(){
        type_3 += 1;

        if (type_3 == firework_type_amount + 1){
            type_3 = 1;
        }
    }
    /**
     * Change firework 4 type (firework show) method
     * Tells main class to change firework 4 type
     */
    public void change_type_4(){
        type_4 += 1;

        if (type_4 == firework_type_amount + 1){
            type_4 = 1;
        }
    }
    /**
     * Change firework 5 type (firework show) method
     * Tells main class to change firework 5 type
     */
    public void change_type_5(){
        type_5 += 1;

        if (type_5 == firework_type_amount + 1){
            type_5 = 1;
        }
    }
    /**
     * Change firework 6 type (firework show) method
     * Tells main class to change firework 6 type
     */
    public void change_type_6(){
        type_6 += 1;

        if (type_6 == firework_type_amount + 1){
            type_6 = 1;
        }
    }
    /**
     * Change firework 7 type (firework show) method
     * Tells main class to change firework 7 type
     */
    public void change_type_7(){
        type_7 += 1;

        if (type_7 == firework_type_amount + 1){
            type_7 = 1;
        }
    }
    /**
     * Change firework 8 type (firework show) method
     * Tells main class to change firework 8 type
     */
    public void change_type_8(){
        type_8 += 1;

        if (type_8 == firework_type_amount + 1){
            type_8 = 1;
        }
    }
    /**
     * Change firework 9 type (firework show) method
     * Tells main class to change firework 9 type
     */
    public void change_type_9(){
        type_9 += 1;

        if (type_9 == firework_type_amount + 1){
            type_9 = 1;
        }
    }
}
