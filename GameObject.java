import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameObject
{
   //Create protected x,y variables to be used in other objects
   protected int x, y;
   
   //Construct the GameObject - take in j,i coordinates and pass them into x,y
   public GameObject(int j, int i)
   {
      x = j * 25;
      y = i * 25;
   }
   
   //Draw GameObject with provided variables
   public void draw(Graphics g)
   {
      g.fillRect(x+25,y,25,25);
   }
}