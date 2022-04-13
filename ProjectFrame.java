import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ProjectFrame extends JFrame
{
   public ProjectFrame()
   {
      super("Spelunky");
      Container contents = getContentPane();
      contents.add(new ProjectPanel());
      
      setSize(850,625);
      setVisible(true);
   }
   
   public static void main(String[] args)
   {
      ProjectFrame theFrame = new ProjectFrame();
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}