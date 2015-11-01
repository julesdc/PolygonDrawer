/*
 * Author: Jules de Courtenay
 * Description: An application which creates a new polygon and adds coordinates to it then outputs the coordinates and its perimeter and area.
 * Version, Date: V 1.0, 29/09/15
 */

package programming.task.pkg2;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Main extends JFrame {
    
    public IrregularPolygon poly = new IrregularPolygon();
    private JPanel pnl;
    private JPanel paintedPNL;
    private JLabel lbl;
    private JTextField txt1;
    private Font f;
    private ImageIcon ico;
    public static void main(String[] args) {
        Main m = new Main();
        double x = 0,y = 0;
        int coordCount = 1;
        String input;
        boolean flag = true;
        
        
        JOptionPane.showMessageDialog(null,"Please enter coordinates of x < 350 and y < 200.");
        do{
            input = JOptionPane.showInputDialog(null, "Enter an X coordinate for point " + coordCount + ":");
            
            //checking that input != null was not working for cancel being clicked so I had to go for a try/catch
            try{
                
            // checks for input      AND                 if its a decimal       OR     its a whole number
                if (!input.equals("")    &&     (input.matches("\\d+\\.\\d") || input.matches("[0-9]+"))){
                    //generated a value for an x coordinate
                    x = Double.parseDouble(input);
                    
                    //time for y
                    input = JOptionPane.showInputDialog(null, "Enter a Y coordinate for point " + coordCount + ":");
                    if (!input.isEmpty()    &&    (input.matches("\\d+\\.\\d") || input.matches("[0-9]+"))){ 
                        //generated a value for a y coordinate
                        y = Double.parseDouble(input);
                        //add a new point2d point to the polygon of coordinates (x,y)
                        m.poly.add(new Point2D.Double(x,y));
                        //increments the count which tells the user which coordinate they're on
                        coordCount++;
                    }else{
                        JOptionPane.showMessageDialog(null, "Entry must be a decimal or whole number! (2)", "Input error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Entry must be a decimal or whole number! (1)", "Input error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(NullPointerException | ArrayIndexOutOfBoundsException e){
                //if cancel is clicked
                flag = false;                
            }
        }while(flag);
                
        /*
         * output statements
         */
        if (m.poly.getNumVerticies() > 2){
            //prints to the console each coordinate of the polygon
            for (int i = 0; i <= m.poly.getNumVerticies() - 1; i++){
                System.out.println(m.poly.getPoint(i).toString());
            }
            //gets the perimeter and area and outputs them through message boxes
            JOptionPane.showMessageDialog(null, "The perimeter of this polygon is: " + m.poly.getPerimeter());
            JOptionPane.showMessageDialog(null, "The area of this polygon is: " + m.poly.getArea());
            
            m.createGUI();  
            m.setVisible(true);
            
            
        }else{
            //tells the user the program is over
            JOptionPane.showMessageDialog(null, "You did not enter enough values to generate a polygon.", "Input Error" ,JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "Application Terminated.", "Exiting", JOptionPane.INFORMATION_MESSAGE);
        }
        
        

    }    
   
    private void createGUI(){
        //methods attached to the frame class to change the properties of the main frame
        setSize(400,300);
        setTitle("Draw Polygon");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //woohoo! a application icon!
        ico = new ImageIcon("polygon_icon.jpg");
        setIconImage(ico.getImage());
        
        //a container object for the content pane of the JFrame
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(Color.DARK_GRAY);
        
        f = new Font("Calibri", Font.BOLD, 15);
        
        //functions as a title label
        lbl = new JLabel("Here is the polygon you depicted:");
        lbl.setFont(f);
        lbl.setForeground(Color.WHITE);
        
        //titled panel which houses another panel
        pnl = new JPanel();
        pnl.setBorder(BorderFactory.createTitledBorder(null,"Your Polygon",0,0, f, Color.RED));
        pnl.setLayout(new BoxLayout(pnl,BoxLayout.Y_AXIS));
        pnl.setBackground(Color.DARK_GRAY);
        
        //an object of the inner PaintedPNL class which is used solely for painting the polygon
        paintedPNL = new PaintedPNL();
        pnl.add(paintedPNL);
        
        //adds the label then the panel (and its housed panel) to the content pane of the frame
        c.add(lbl);
        c.add(pnl);      
    }
    
    //a inner class which extends jpanel so that its paint method does not apply to its parent panel
    public class PaintedPNL extends JPanel{

        @Override
        public void paint (Graphics g){
            Graphics2D g2D = (Graphics2D) g;
            //makes a new GeneralPath drawing object to draw the polygon
            GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, poly.getNumVerticies());
            
            double xCoords[] = new double[poly.getNumVerticies()];
            double yCoords[] = new double[poly.getNumVerticies()];
            //populates both arrays above with each corresponding x and y value of the polygon
            for (int i = 0; i < poly.getNumVerticies(); i++){
                xCoords[i] = poly.getPoint(i).getX();
                yCoords[i] = poly.getPoint(i).getY();
            }

            //the polygon starts at the first coordinate,
            polygon.moveTo(xCoords[0], yCoords[0]);
            //then lines are drawn to each coordinate following that one
            for (int i = 1; i < poly.getNumVerticies(); i++){
                polygon.lineTo(xCoords[i],yCoords[i]);
            }
            //finishes the last line
            polygon.closePath();

            //draws the polygon
            g2D.setColor(Color.RED);
            g2D.draw(polygon);
        }
    } 
}
