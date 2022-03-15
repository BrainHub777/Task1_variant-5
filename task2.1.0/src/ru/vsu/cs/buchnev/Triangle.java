package ru.vsu.cs.buchnev;


import java.awt.*;
import java.awt.geom.Path2D;

public class Triangle {
    public static Paint p1;
    public static Paint p2;
    public static Paint p3;
    public Triangle(Paint p1, Paint p2, Paint p3) throws TriangleExeption{
        if (((p1.getX() == p2.getX() && p2.getX() == p3.getX()) || (p1.getY() == p2.getY() && p2.getY() == p3.getY()))) {
            throw new TriangleExeption("Ошибка!!! Введите корректные значения для точек!!!");
        }
        else {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }

    }
    public double getA(){
        return Math.sqrt(Math.pow(Math.abs(p1.getX() - p2.getX()),2) + Math.pow(Math.abs(p1.getY() - p2.getY()),2));
    }
    public double getB(){
        return Math.sqrt(Math.pow(Math.abs(p2.getX() - p3.getX()),2) + Math.pow(Math.abs(p2.getY() - p3.getY()),2));
    }
    public double getC(){
        return Math.sqrt(Math.pow(Math.abs(p3.getX() - p1.getX()),2) + Math.pow(Math.abs(p3.getY() - p1.getY()),2));
    }

    public double square(){
        double a = this.getA();
        double b = this.getB();
        double c = this.getC();
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double square(double a, double b, double c){
        double p = (a+b+c)/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }
    public double getSide(Paint paint1,Paint paint2){
        return Math.sqrt(Math.pow(Math.abs(paint1.getX() - paint2.getX()),2) + Math.pow(Math.abs(paint1.getY() - paint2.getY()),2));
    }
    public double perimetr(){
        return this.getA() + this.getB() + this.getC();
    } //периметр
    public boolean paintInTriangle(Paint paint){
       double square1 = square(getSide(paint,p1),getSide(paint,p2),getA());
       double square2 = square(getSide(paint,p2),getSide(paint,p3),getB());
       double square3 = square(getSide(paint,p1),getSide(paint,p3),getC());
       System.out.println(square1 + square2 + square3);
        System.out.println(this.square());
       if (Math.abs((square1 + square2 + square3) - (this.square()))<0.00001){
           return true;
       }
       return false;
    }





    public void printTriangle(Graphics2D g2d,int windth, int height,Triangle triangle,boolean flag,Paint paint){
        int sizeI = 25;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = g2d.getColor();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,windth,height);
        g2d.setColor(Color.BLACK);
        Path2D.Double path = new Path2D.Double();
        path.moveTo(paint.getX()*sizeI,paint.getY()*sizeI);
        g2d.setColor(Color.GREEN);
        g2d.fillOval((int)paint.getX()*sizeI-5/2,(int)paint.getY()*sizeI-5/2,10,10);
        g2d.setColor(Color.YELLOW);
        path.moveTo(triangle.p1.getX()*sizeI,triangle.p1.getY()*sizeI);
        g2d.setColor(Color.RED);
        g2d.fillOval((int)triangle.p1.getX()*sizeI-5/2,(int)triangle.p1.getY()*sizeI-5/2,5,5);
        g2d.setColor(Color.BLACK);
        path.lineTo(triangle.p2.getX()*sizeI,triangle.p2.getY()*sizeI);
        g2d.setColor(Color.RED);
        g2d.fillOval((int)triangle.p2.getX()*sizeI-5/2,(int)triangle.p2.getY()*sizeI-5/2,5,5);
        g2d.setColor(Color.BLACK);
        path.lineTo(triangle.p3.getX()*sizeI,triangle.p3.getY()*sizeI);
        g2d.setColor(Color.RED);
        g2d.fillOval((int)triangle.p3.getX()*sizeI-5/2,(int)triangle.p3.getY()*sizeI-5/2,5,5);
        g2d.setColor(Color.BLACK);
        path.lineTo(triangle.p1.getX()*sizeI,triangle.p1.getY()*sizeI);
        path.closePath();
        g2d.draw(path);
        if(flag){
            g2d.setColor(Color.YELLOW);
            path.moveTo(paint.getX()*sizeI,paint.getY()*sizeI);
            path.lineTo(triangle.p1.getX()*sizeI,triangle.p1.getY()*sizeI);
            path.moveTo(paint.getX()*sizeI,paint.getY()*sizeI);
            path.lineTo(triangle.p2.getX()*sizeI,triangle.p2.getY()*sizeI);
            path.moveTo(paint.getX()*sizeI,paint.getY()*sizeI);
            path.lineTo(triangle.p3.getX()*sizeI,triangle.p3.getY()*sizeI);
            path.closePath();
            g2d.draw(path);
        }
    }
    public static class TriangleExeption extends Exception {
        public TriangleExeption(String message) {
            super(message);
        }
    }
}
