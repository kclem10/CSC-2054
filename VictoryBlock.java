import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VictoryBlock extends GameObject
{
   //Construct the VictoryBlock - take in x,y coordinates
   public VictoryBlock(int x, int y)
   {
      super(x,y);
   }
   
   //Draw the red VictoryBlock at x,y coordinates
   public void draw(Graphics g)
   {
      g.setColor(Color.RED);
      g.fillRect(x+25,y,25,25);
   }
}