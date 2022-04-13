import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Player extends GameObject
{
   //Create variables to be used throughout the object
   int pX, pY;
   ArrayList<ArrayList<GameObject>> objects = new ArrayList<ArrayList<GameObject>>();
   
   //Construct the Player - take in x,y coordinates and pass them into pX,pY
   public Player(int x, int y)
   {
      super(x,y);
      pX = x * 25;
      pY = y * 25;
   }
   
   //Access gameMap arrayList from panel to be used in methods
   public void gameMap(ArrayList<ArrayList<GameObject>> map)
   {
      objects = map;
   }
   
   //Check if Player will collide with wall depending on move - take in int for direction
   public boolean collides(int num)
   {      
      int tX = pX;
      int tY = pY;
      
      //Check if collides with left
      if(num == 1)
      {
         if(objects.get(tY/25).get((tX-1)/25) instanceof Block)
         {
            return false;
         }
         else if(objects.get((tY+24)/25).get((tX-1)/25) instanceof Block)
         {
            return false;
         }
         else
         {
            return true;
         }
      }
      //Check if collides with right
      if(num == 2)
      {
         if(objects.get(tY/25).get((tX+25)/25) instanceof Block)
         {
            return false;
         }
         else if(objects.get((tY+24)/25).get((tX+25)/25) instanceof Block)
         {
            return false;
         }
         else
         {
            return true;
         }
      }
      //Check if collides with the roof
      if(num == 3)
      {
         if(objects.get((tY-1)/25).get(tX/25) instanceof Block)
         {
            return false;
         }
         else if(objects.get((tY-1)/25).get((tX+24)/25) instanceof Block)
         {
            return false;
         }
         else
         {
            return true;
         }
      }
      else
      {
         return true;
      }
   }
   
   //Draw Player using pX variables
   public void draw(Graphics g)
   {
      g.setColor(Color.GREEN);
      g.fillRect(pX+25,pY,25,25);      
   }
   
   //Move Player depending on input variables - whatever letter is entered, alter that direction
   public void move(int next, String letter)
   {
      if(letter.equals("x"))
      {
         if(next < pX)
         {
            pX--;
         }
         if(next > pX)
         {
            pX++;
         }
      }
      else if(letter.equals("y"))
      {
         if(next < pY)
         {
            pY--;
         }
         if(next > pY)
         {
            pY++;
         }
      }
   }
   
   //Check to see if player isOnGround - if instanceof Block is beneath Player, return true
   public boolean isOnGround()
   {
      int tX = pX;
      int tY = pY;
      
      if(objects.get((tY+25)/25).get(tX/25) instanceof Block)
      {
         return true;
      }
      else if(objects.get((tY+25)/25).get((tX+24)/25) instanceof Block)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
}