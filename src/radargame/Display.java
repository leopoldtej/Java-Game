// Name         : Leopold Tejkowski
// Date Created : 05/11/2022
// Last Modified: 05/11/2022
// Description  : Class which deals with displaying and moving objects in game.
package radargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



    public class Display extends JPanel{
        // ArrayLists of Enemies and Bullets
        public static ArrayList<Enemy> Enemies = new ArrayList<>();
        public static ArrayList<Bullet> Bullets = new ArrayList<>();
        
        
        public int score;
        private Rectangle hitBox;
        private Point centerPoint;
        
        public int deathCount;
        private int gameMode;
        
        
        final private Sweeper sweeper;
        final private int npoints = 3;
        private int radius = 1000;
        private int angle = 30;
        
        
        private Bullet bullet;
        
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        
        
        
        final private ImageIcon pic;
        private ImageIcon enemyImg;
        final private ImageIcon bulletImg;
        final private ImageIcon explosionImg;
        
        final private Random rand;
    
    public Display() {
        super(); // calls the superclass constructor
        setBackground(Color.DARK_GRAY);
        gameMode = 1;
        
        


        hitBox = new Rectangle(530,421,40,40);
        centerPoint = new Point(550, 435);
        
        sweeper = new Sweeper(550, 433, radius, angle);
        score = 100;
        deathCount = 0;
        
        
        // Images of each background, bullet, and explosion
        pic = new ImageIcon("radarimg.png");
        bulletImg = new ImageIcon("bullet.png");
        explosionImg = new ImageIcon("exp4.png");
        
        rand = new Random();
        
    }
     
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        
        // Background image
        g2.drawImage(pic.getImage(), 0, 0, getWidth(), getHeight(), null);
        
        
        
        // Draw Health and Kill Metrics
        g2.setColor(Color.WHITE);
        g2.drawString("METRICS", 10, 15);
        g2.setColor(Color.GREEN);
        g2.drawString("Health: " + score + "%", 10, 35);
        g2.setColor(Color.red);
        g2.drawString("Kills: " + deathCount, 10, 55);
        
        
        
        
        
        // Sweeper - Draw & Rotate
        AffineTransform old = g2.getTransform();
        AffineTransform after = new AffineTransform();
        
        
        sweeper.setAngle(sweeper.getTheta() + sweeper.getdTheta());
        after.rotate(sweeper.getTheta(), centerPoint.x, centerPoint.y);
 
        Shape ro = after.createTransformedShape(sweeper);
        g2.setColor(sweeper.getBackgroundColor());
        g2.fill(ro);
        g2.setTransform(old);
        
        
        // This makes sure the enemy's spawn point is not close to the center
        int newEnemyX = rand.nextInt(1200);
        int newEnemyY = rand.nextInt(1000);
        if(newEnemyX >= 400 && newEnemyX < 650)
            newEnemyX += 275;
        if(newEnemyY >= 370 && newEnemyY < 570)
            newEnemyY += 225;
                
        
        

        // Create the enemies and make sure only a # on screen at a time.
        if(Enemies.size() < 4){
            Enemies.add(new Enemy(newEnemyX, newEnemyY, 
                35, 35, hitBox.x, hitBox.y, 100));
            
        } // creates enemies that are smaller once we get to level 2
        if(deathCount >= 10){
            if(Enemies.size() < 20){
              Enemies.add(new Enemy(newEnemyX, newEnemyY, 
                15, 15, hitBox.x, hitBox.y, 50));
              
            }
        }
        

        // Check if existing enemies are  at center, and remove if they are, 
        // remove  that enemy and take damage.
        
        for(int i = 0; i < Enemies.size(); i++){
            Enemies.get(i).setX(Enemies.get(i).returnX() + (Enemies.get(i).getdX()/(int)Enemies.get(i).getSpeed()));
            
            Enemies.get(i).setY(Enemies.get(i).returnY() + (Enemies.get(i).getdY()/(int)Enemies.get(i).getSpeed()));
            if(Enemies.get(i).intersects(hitBox)){
                Enemies.remove(i);
                score -= 3;
            } else if(Enemies.get(i).intersectsLine(0, 0, width, 0)
                    || Enemies.get(i).intersectsLine(0, 0, 0, height) 
                    || Enemies.get(i).intersectsLine(0, height, width, height) 
                    || Enemies.get(i).intersectsLine(width, 0, width, height))
                Enemies.remove(i);
        }
        
       
        
        for(Enemy i: Enemies){
            if(ro.intersects(i)){
                i.setTrans(255);
                
                
                g2.setColor(new Color(0, 20, 255, i.getTrans()));
                g2.fill(i);
            } else {
                i.setTrans(i.getTrans() - 25);
                if(i.getTrans() <= 0)
                    i.setTrans(0);
                
                g2.setColor(new Color(0, 20, 255, i.getTrans()));
                g2.fill(i);
            }

        }// end of enemies for loop



        for(int k = 0; k < Bullets.size();k++){
                g2.drawImage(bulletImg.getImage(), Bullets.get(k).returnX(), Bullets.get(k).returnY(),75, 75, null);
                if(!Bullets.get(k).isEmpty()){
                Bullets.get(k).setX(Bullets.get(k).returnX() + (Bullets.get(k).getdX()/(int)Bullets.get(k).getSpeed()));
                Bullets.get(k).setY(Bullets.get(k).returnY() + (Bullets.get(k).getdY()/(int)Bullets.get(k).getSpeed()));
            }
                if(Bullets.get(k).intersects(Bullets.get(k).getTargetX()-25,
                                Bullets.get(k).getTargetY()-25, 50, 50))
                Bullets.remove(k);
                
                
            
                
                
        }  

        for(int i = 0; i<Enemies.size(); i++){
            for(int j = 0; j < Bullets.size(); j++){
                if(!Enemies.isEmpty() && !Bullets.isEmpty()){
                    if(Enemies.get(i).intersects(Bullets.get(j))){
                        g2.drawImage(explosionImg.getImage(), Bullets.get(j).returnX(), Bullets.get(j).returnY(),75, 75, null);
                        Enemies.remove(i);
                        Bullets.remove(j);
                    if(score >= 100){
                        score = 100;
                        deathCount++;
                    }  else {
                        score++;
                        deathCount++;
                        }
                
                    }   
                }
           
            }
        
        
        
        }
         
    }// End of paintComponent() ****************************************
    
    
    /** Method returns the score/health, and it starts at 100% and 
     * goes down if hit and goes up if you kill an enemy.
     * 
     * @return score int - returns the health
     */
    public int getScore(){
        return score;
    }
    
    /** Method returns the amount of kills you have
     * 
     * @return deathCount int - the number of times a bullet hit an enemy.
     */
    public int getKills(){
        return deathCount;
    }
    
}
    

