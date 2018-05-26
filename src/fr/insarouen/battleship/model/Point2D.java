/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insarouen.battleship.model;

/**
 *
 *	Describes a point in a two dimensions grid.
 *
 * @author Matthieu
 */
public class Point2D {
    private int x, y;
    
    /**
	  * Constructs a point
	  */

    public Point2D(){
        this.x = 0;
        this.y = 0;
    }
    
    /**
	  * Constructs a point on specific given coordinates.
	  */

    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    
}
