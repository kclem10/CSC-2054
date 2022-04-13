import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ProjectPanel extends JPanel
{
   //Variables used throughout the panel
   int pX, pY, x, y, n = 1, ticks = 0;
   int rows = 0, column = 0;
   ArrayList<ArrayList<GameObject>> objects = new ArrayList<ArrayList<GameObject>>();
   Player user;
   boolean jump;
   double j = 7.0;
   
   //Panel Constructor
   public ProjectPanel()
   {
      //Create level arrayList to read in level map - set background BLACK
      ArrayList<ArrayList<Integer>> level = new ArrayList<ArrayList<Integer>>();
      setBackground(Color.BLACK);
      
      //Make sure file name exists; Make sure to catch any errors
      try
      {
         //Create scanner to read from file name - read in X,Y coordinates
         Scanner read = new Scanner(new File("Project_LevelMap.txt"));
         x = read.nextInt();
         y = read.nextInt();
         
         //Read in # of rows & columns
         rows = read.nextInt();
         column = read.nextInt();
         
         //Fill out interior of level arrayList - Fill out interior of objects arrayList
         for(int i=0; i<rows; i++)
         {
            ArrayList<Integer> innerList = new ArrayList<Integer>();
            ArrayList<GameObject> filler = new ArrayList<GameObject>();
            level.add(innerList);
            objects.add(filler);
            
            for(int j=0; j<column; j++)
            {
               level.get(i).add(read.nextInt());
               if(level.get(i).get(j) == 0)
               {
                  EmptyBlock eb = new EmptyBlock(j,i);
                  objects.get(i).add(eb);
               }
               else if(level.get(i).get(j) == 1)
               {
                  Block b1 = new Block(j,i);
                  objects.get(i).add(b1);
               }
               else if(level.get(i).get(j) == 2)
               {
                  VictoryBlock vb = new VictoryBlock(j,i);
                  objects.get(i).add(vb);
               }
            }
         }
      }
      catch(FileNotFoundException fnfe)
      {
         System.out.println("File not Found");
      }
            
      //Create timer and KeyListener
      addKeyListener(new KeyEventDemo());
      setFocusable(true);
      Timer t = new Timer(10, new TimerListener());
      t.start();
      
      //Initialize user of type Player
      user = new Player(x, y);
      
      //Make pX,pY == actual coordinates
      pX = x * 25;
      pY = y * 25;
      
      //Provide objects arrayList to player class
      user.gameMap(objects);
   }
   
   public void paintComponent(Graphics g)
   {      
      super.paintComponent(g);
      
      //Draw specifics of objects arrayList
      for(int i=0; i<rows; i++)
      {
         for(int j=0; j<column; j++)
         {
            objects.get(i).get(j).draw(g);
         }
      }
      //Draw user
      user.draw(g);
   }
   
   boolean up, left, right;
   
   public class KeyEventDemo implements KeyListener 
   {
      public void keyTyped(KeyEvent e) {}
      public void keyReleased(KeyEvent e) 
      {
         //If: W,A,D release make boolean false
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            up = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = false;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = false;
         }
      }
      public void keyPressed(KeyEvent e) 
      {
         //If: W,A,D pressed make boolean false
         if(e.getKeyCode() == KeyEvent.VK_W)
         {
            up = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_A)
         {
            left = true;
         }
         if(e.getKeyCode() == KeyEvent.VK_D)
         {
            right = true;
         }
         repaint();
      }
   }
      
   public class TimerListener implements ActionListener
   {      
      public void actionPerformed(ActionEvent e)
      {
         //If victoryBlock is reached, produce message
         if(victory())
         {
            JOptionPane.showMessageDialog( null, "You made it!");
            System.exit(1);
         }
         
         //Create gravity mechanics
         if(!user.isOnGround())
         {
            //For every 20 ticks, increase N by 1
            ticks++;
            if(ticks >= 20)
            {
               ticks = 0;
               //Cap N at 7
               if(n <= 7)
               {
                  n++;
               }
            }
            //Move pY down by N1
            pY += (n*1);
            //Move user paramaters
            for(int i=0; i<n; i++)
            {
               if(!user.isOnGround())
               {
                  user.move(pY,"y");
               }
            }
         }
         //If user is on the ground, reset variables to their base conditions
         if(user.isOnGround())
         {
            jump = false;
            ticks = 0;
            n = 1;
            j = 7;
         }
         
         //Create jump mechanics - Make sure jump can only occur while user isOnGround
         if(up && user.isOnGround())
         {
            jump = true;
         }
         //As long as user isOnGround, move user up
         if(jump)
         {
            //Move user up 1 J amount of times per tick
            for(int i=0; i<j; i++)
            {
               user.move(pY--,"y");
               //If user hits the roof set J to 0
               if(!user.collides(3))
               {
                  j = 0;
               }
            }
            //As long as J is greater than 0, reduce J by 0.1
            if(j > 0)
            {
               j -= 0.1;
            }
         }
         
         //As long as user doesn't collide with sides, move to left and right
         if(left && user.collides(1))
         {
            user.move(pX--,"x");
         }
         if(right && user.collides(2))
         {
            user.move(pX++,"x");
         }
         repaint();
      }
   }
   
   //Check if user has entered the VictoryBlock
   public boolean victory()
   {
      if(objects.get(pY/25).get(pX/25) instanceof VictoryBlock)
      {
         return true;
      }
      else if(objects.get(pY/25).get((pX+24)/25) instanceof VictoryBlock)
      {
         return true;
      }
      else if(objects.get((pY+24)/25).get(pX/25) instanceof VictoryBlock)
      {
         return true;
      }
      else if(objects.get((pY+24)/25).get((pX+24)/25) instanceof VictoryBlock)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}