// Name         : Leopold Tejkowski
// Date Created : 05/11/2022
// Last Modified: 05/11/2022
// Description  : Class that creates the enemies that are coming toward us.
package radargame;

import java.awt.Color;
import java.awt.Rectangle;



public class Enemy extends Rectangle{
    private Color backgroundColor;
    private int[][] grid;
    private double distance, speed;
    private int dx, dy;
    private int targetX, targetY;
    private int transparency;
    
    
    
    public Enemy(int x, int y, int w, int h, int targetX, int targetY, int speed){
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
        this.transparency = 0;
        backgroundColor = new Color(0, 20, 255);
        
        
        
    }
    
    // Setters
    
    /** Method setbackgroundColor(Color c) sets a new color on enemy
     * 
     * @param c color - new color of enemy
     */
    public void setbackgroundColor(Color c){
        backgroundColor = c;
    }
    
    /** Method setX(int x) sets new x point on enemy
     * 
     * @param x int - new x point
     */
    public void setX(int x){
        this.x = x;
    }
    
    /** Method setY(int y) sets new y point on enemy
     * 
     * @param y int - new y point
     */
    public void setY(int y){
        this.y = y;
    }
    
    /** Method setSpeed(double speed) sets new speed on enemy
     * 
     * @param speed double - new speed on enemy
     */
    public void setSpeed(double speed){
        this.speed = speed;
    }
    
    /** Method setTrans(int trans) sets new transparency of enemy
     * 
     * @param trans int- value between 0 and 255 for transparency
     */
    public void setTrans(int trans){
        this.transparency = trans;
        
    }
    
    
    // Getters
    
    /** Method getbackgroundColor() returns color on enemy
     * 
     * @return backgroundColor Color - color of enemy
     */
    public Color getbackgroundColor(){
        return backgroundColor;
    }
    
    /** Method getTrans() gets the transparency of the enemy
     * 
     * @return transparency int - value between 0 and 255 for transparency
     */
    public int getTrans(){
        return transparency;
    }
    
    /** Method getSpeed() returns the speed of the enemy
     * 
     * @return speed - int value of speed of enemy
     */
    public double getSpeed(){
        return speed;
    }
    
    /** Method getTargetX() returns the target X point of the enemy
     * 
     * @return targetX int - the value of the target X point of the enemy
     */
    public int getTargetX(){
        return targetX;
        
    }
    
    /** Method getTargetY() returns the target Y point of the enemy
     * 
     * @return targetY int - the value of the target Y point of the enemy
     */
    public int getTargetY(){
        return targetY;
    }
    
    /** Method returnX() returns the x point of the enemy
     * 
     * @return x int - the value of the x
     */
    public int returnX(){
        return x;
    }
    
    /** Method returnY() returns the y point of the enemy
     * 
     * @return y int - the value of the y
     */
    public int returnY(){
        return y;
    }
    
    /** Method getdX() returns the change in x of the enemy
     * 
     * @return dx int - the value of change in x of the enemy
     */
    public int getdX(){
        return dx;
    }
    
    /** Method getdY() returns the change in y of the enemy
     * 
     * @return dy int - the value of change in y of the enemy
     */
    public int getdY(){
        return dy;
    }
}
