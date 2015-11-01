/*
 * Author: Jules de Courtenay
 * Description: Creates a polygon object which can be manipulated with ease
 * Version, Date: V 1.0, 29/09/15
 */

package programming.task.pkg2;

/**
 *
 * @author Jules
 */
import java.util.ArrayList;
import java.awt.geom.*;

public class IrregularPolygon {
    private ArrayList <Point2D.Double> myPolygon;
    
    public IrregularPolygon(){
        myPolygon = new ArrayList<>();
    }
    
    public void add(Point2D.Double aPoint){
        //adds a new point to the polygon
        myPolygon.add(aPoint);
    }
    
    public double getPerimeter(){
        //finds the sum of the distance between each point in the polygon ArrayList
        double perSum = myPolygon.get(myPolygon.size()-1).distance(myPolygon.get(0));
        
        for (int i = 0; i < myPolygon.size() - 1; i++){
           perSum += myPolygon.get(i).distance(myPolygon.get(i+1));
        }
        return perSum;
    }
    
    public double getArea(){
        //instantiates the area variable and gives it the quantity of x(sub)n * y(sub)0 - y(sub)n * x(sub)0
        double area = ((myPolygon.get(myPolygon.size() -1).getX() * myPolygon.get(0).getY()) - (myPolygon.get(myPolygon.size() - 1).getY() * myPolygon.get(0).getX()));
        
        for(int i = 0; i < myPolygon.size() - 1; i++){
            area += ((myPolygon.get(i).getX() * myPolygon.get(i+1).getY()) - (myPolygon.get(i).getY() * myPolygon.get(i+1).getX()));
        }
        return Math.abs(.5 * area);
    }
    
    public Point2D getPoint(int index){
        return myPolygon.get(index);
    }

    public int getNumVerticies(){
        //the equivalent to using the instanceOfObject.size() method but since objects don't have that method:
        return myPolygon.size();
    }
}
