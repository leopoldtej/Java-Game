// Name         : Leopold Tejkowski
// Date Created : 05/11/2022
// Last Modified: 05/11/2022
// Description  : The class which defines out bullets for my radar game
package radargame;

import java.awt.Color;
import java.awt.Rectangle;


public class Bullet extends Rectangle{
    private Color backgroundColor;
    private int[][] grid;
    private int dx, dy;
    private double speed;
    private int targetX;
    private int targetY;
    private double distance;
    private double time;
    
    
    
    public Bullet(int x, int y, int w, int h, int targetX, int targetY, double speed){
        // implement super
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.targetX = targetX;
        this.targetY = targetY;
        this.dx = targetX  - x;
        this.dy = targetY - y;
        this.distance = Math.sqrt((dx) * (dx) +
            (dy) * (dy));
        this.speed = speed;
        backgroundColor = Color.GREEN;
    }
    
    // Setters
    
    /** Method setBackgroundColor(Color c) sets a new color on bullet.
     * 
     * @param c Color - new color to be set.
     */
    public void setbackgroundColor(Color c){
        backgroundColor = c;
    }
    
    /** Method setSpeed(double speed) sets a new speed of the bullets moving
     * 
     * @param speed double - new value for speed of bullet.
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }
    
    /** Method setX(int x) sets a new x point on the bullet
     * 
     * @param x int - new x point on the bullet
     */
    public void setX(int x){
        this.x = x;
    }
    
    /** Method setY(int y) sets a new y point on the bullet
     * 
     * @param y int - new y point on the bullet
     */
    public void setY(int y){
        this.y = y;
    }
    
    
    
    // Getters
    
    /** Method getdX() to get the change in x
     * 
     * @return dx int - the change in x values
     */
    public int getdX(){
        return dx;
    }
    /** Method getdY() to get the change in x
     * 
     * @return dy int - the change in x values
     */
    public int getdY(){
        return dy;
    }
    
    /** Method getBackgroundColor() returns the color of the bullet
     * 
     * @return backgroundColor Color - the color of that bullet.
     */
    public Color getbackgroundColor(){
        return backgroundColor;
    }
    
    /** Method getTargetX() to get the clicked X point
     * 
     * @return targetX int - x point of click
     */
    public int getTargetX(){
        return targetX;
        
    }
    
    /** Method getTargetY() to get the clicked Y point
     * 
     * @return targetY int - y point of click
     */
    public int getTargetY(){
        return targetY;
    }
    
    /** Method returnX() to get the x point of the bullet
     * 
     * @return x int - x point of bullet
     */
    public int returnX(){
        return x;
    }
    
    /** Method returnY() to get the y point of the bullet
     * 
     * @return y int - y point of the bullet
     */
    public int returnY(){
        return y;
    }
    
    /** Method getSpeed() returns the speed 
     * 
     * @return speed int - the speed of the bullet.
     */
    public double getSpeed(){
        return speed;
    }
    
}
