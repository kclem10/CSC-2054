import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Block extends GameObject
{
   //Construct the Block - take in x,y coordinates
   public Block(int x, int y)
   {
      super(x,y);
   }
   
   //Draw the blue Block at x,y coordinates
   public void draw(Graphics g)
   {
      g.setColor(Color.BLUE);
      g.fillRect(x+25,y,25,25);
   }
}