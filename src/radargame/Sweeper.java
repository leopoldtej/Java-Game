// Name         : Leopold Tejkowski
// Date Created : 05/11/2022
// Last Modified: 05/11/2022
// Description  : Sweeper that is the radar in the radar game
package radargame;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;


public class Sweeper extends Polygon {
    private int radius;
    private Color backgroundColor;
    private double theta;
    private int side;
    private int x;
    private int y;
    private double dTheta;
    
    public Sweeper(int x, int y, int radius, int theta){
        //implement super
        dTheta = .1;
        backgroundColor = new Color(200, 255, 200, 150);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.theta = theta;
        
        
        side = (int) ((x + radius)*(Math.abs(Math.tan(Math.toRadians(theta)))));
        
        npoints = 3;
        xpoints = new int[npoints];
        ypoints = new int[npoints];
        
        
        xpoints[0] = x;
        ypoints[0] = y;
        
        xpoints[1] = x + radius;
        ypoints[1] = y;
        
        xpoints[2] = x + radius;
        ypoints[2] = y + side;
    }
    
    // Getters
    
    /** Method getTheta() returns the angle of the sweeper
     * 
     * @return double theta - angle of the sweeper
     */
    public double getTheta(){
        return theta;
    }
    
    /** Method getBackgroundColor() returns color of sweeper
     * 
     * @return Color - background color of sweeper
     */
    public Color getBackgroundColor(){
        return backgroundColor;
    }
    
    /** Method getdTheta() returns the change in angle
     * 
     * @return double - dTheta - change in angle
     */
    public double getdTheta(){
        return dTheta;
    }
    
    
    // Setters
    
    /** Method setAngle(double angle) sets a new angle.
     * 
     * @param angle double - the new angle to be set.
     */
    public void setAngle(double angle){
        this.theta = angle;
    }
    
    /** Method setdTheta(double d) sets a new change in angle.
     * 
     * @param d double - the new change in angle to be set.
     */
    public void setdTheta(double d){
        this.dTheta = d;
    }
    
    /** Method setBackgroundColor(Color c) sets a new color on sweeper
     * 
     * @param c Color - new color to be set.
     */
    public void setBackgroundColor(Color c){
        backgroundColor = c;        
    }
    
    
    /** Method to set new XPoint
     * 
     * @param x int - the original x point
     * @param newPoint int - the new point to override x
     */
    public void setXPoint(int x, int newPoint){
        
        xpoints[x] = newPoint;
    }
    
    /** Method to set new YPoint
     * 
     * @param y int - the original y point
     * @param newPoint int - the new point to override y
     */
    public void setYPoint(int y, int newPoint){
        
        ypoints[y] = newPoint;
    }
    
}
