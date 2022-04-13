import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EmptyBlock extends GameObject
{
   //Construct the EmptyBlock - take in x,y coordinates
   public EmptyBlock(int x, int y)
   {
      super(x,y);
   }
   
   //Draw the gray EmptyBlock at x,y coordinates
   public void draw(Graphics g)
   {
      g.setColor(Color.GRAY);
      g.fillRect(x+25,y,25,25);
   }
}