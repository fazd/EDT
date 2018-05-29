/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javafx.scene.shape.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author fazd
 */
public class ArrowHead
{
    public  static void drawArrowHead(Graphics g2, int x2, int x1, int y2, int y1){
        Line shape = new Line(x1,y1,x2,y2);

        double angle = Math.atan2(        //find angle of line

                shape.getEndY()-shape.getStartY(),

                shape.getEndX()-shape.getStartX());


        int arrowHeight = 9;                 // change as seen fit

        int halfArrowWidth = 5;              // this too

        Point2D end = new Point2D.Double(shape.getEndX(), shape.getEndY());

        Point2D aroBase = new Point2D.Double(

                shape.getEndX() - arrowHeight*Math.cos(angle),

                shape.getEndY() - arrowHeight*Math.sin(angle)

                ); //determine the location of middle of

                   //the base of the arrow - basically move arrowHeight

                   //distance back towards the starting point

        Point2D end1 = new Point2D.Double(

                aroBase.getX()-halfArrowWidth*Math.cos(angle-Math.PI/2),

                aroBase.getY()-halfArrowWidth*Math.sin(angle-Math.PI/2));

        //locate one of the points, use angle-pi/2 to get the

        //angle perpendicular to the original line(which was 'angle')

        Point2D end2 = new Point2D.Double(

                aroBase.getX()+halfArrowWidth*Math.cos(angle-Math.PI/2),
 
                aroBase.getY()+halfArrowWidth*Math.sin(angle-Math.PI/2));
        Line2D line1 = (new Line2D.Double(end1,end2));

        Line2D line2 = (new Line2D.Double(end,end2));

        Line2D line3 = (new Line2D.Double(end,end1));
        
        Graphics2D g1 = (Graphics2D) g2;
        g1.draw(line3);
        g1.draw(line1);
        g1.draw(line2);
        g1.setColor(Color.black);
        int []xpoint = {(int)end1.getX(), (int)end.getX(), (int)end2.getX()};
        int []ypoint = {(int)end1.getY(), (int)end.getY(), (int)end2.getY()};
                
        g1.fillPolygon(xpoint,ypoint,3);
    }
}