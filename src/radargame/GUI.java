// Name         : Leopold Tejkowski
// Date Created : 05/11/2022
// Last Modified: 05/11/2022
// Description  : Class which sets up GUI, instructions, and end of game.
package radargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GUI {
    private JFrame window;
    private Display mainPanel;
    private Timer timerAnimation;
    private JPanel scorePanel;
    private JLabel score;
    private ArrayList<Point> points;
    private int startSpeed;
    private Clip clip;
    private AudioInputStream audioInput;
    
    
    
    
    
    public GUI(){
        
        score = new JLabel("Score is: ");
        startSpeed = 10;
        points = new ArrayList<>();


        window = new JFrame("Radar Game");
        window.setSize(1100, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.addKeyListener(new LetterListener());
        window.addMouseListener(new MouseListen());
        
        
        //Create the panels
        mainPanel = new Display();
        
        
        try{
            File musicPath = new File("gameMusic.wav");
            
            if(musicPath.exists()){
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                
                
                
            }
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Music Error");
        }
        
        //Create the timer for game
        timerAnimation = new Timer(40, new MovementListener());
        
        
        String instructionMessage = "ENEMIES INBOUND! \n\n"
        + "Getting hit = -2 Health | "
                + "Hitting enemy = +1 Health \n" 
                + "Click to shoot at your enemies, the blue planes...";
        String instruct = "<html> <h1>Blue Invasion</h1>\n" +
"\n" +
"Click To Shoot Your Enemies\n" + "\n" +
                "Get 20 kills to win.";
        
        JOptionPane.showMessageDialog(null, instruct, 
                "Instructions", JOptionPane.PLAIN_MESSAGE);
        
        window.add(mainPanel);
        
        init();
        window.setVisible(true);
        timerAnimation.start();
        
        
        
    }
    private void init() {
        
    }
    
    private class MovementListener implements ActionListener {
        
        /** actionPerformed(ActionEvent e) fires every time timer passes to 
         *  check the status of the game or repaint.
         * 
         * @param e - timer fires is event e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if(mainPanel.getKills() >= 20){
                JOptionPane.showMessageDialog(null, "You ROCK! You won. :D", "WINNER", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
            }
            
          if(mainPanel.score <= 0){
            
            JOptionPane.showMessageDialog(null, "You stink! You Lost >:|", "LOSER", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        } 
          else {
             mainPanel.repaint(); 
          }
            
            
        }
        
    }
    
    private class LetterListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent ke) {
            
        }
        
        
        /** Method keyPressed(keyEvent ke) fires every time space is clicked
         *  to clear the map
         * @param ke - space bar is pressed
         */
        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode()==KeyEvent.VK_SPACE){
             Display.Enemies.clear();
         }
        }
        
        
        @Override
        public void keyReleased(KeyEvent e) {
              
        }
        
    }
    private class MouseListen implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
        }
        
        
        /** Method mousePressed(MouseEvent e) fires every time mouse clicked
         *  and it adds new bullets going toward that target clicked point.
         * 
         * @param e - mouse clicked
         */
        @Override
        public void mousePressed(MouseEvent e) {
           points.add(new Point(e.getX(), e.getY()));
           
            if(Display.Bullets.size() < 1){
                Display.Bullets.add(new Bullet(525, 
                  410, 50, 50, e.getX(), e.getY(), 
                        startSpeed));
            
            }
            
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
           
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
}
