/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.first.mesclasses;

/**
 *
 * @author LENOVO
 */
public class Rectangle {
private int longueur; 
public int largeur;

   
public int getLongueur(){  
    return this.longueur;
    
}
public Rectangle(int longueur,int largeur){
    
 this.longueur = longueur;
 this.largeur = largeur;
    
    
}
public Rectangle (int longueur,int largeur ,int coef){
this.longueur = longueur*coef;
this.largeur = largeur*coef;

}
public void setLongueur(int longueur){
    this.longueur = longueur;
}
public float surface(){

return longueur*largeur;
}
 public int perimetre(){

return (longueur+largeur)*2;
}

    public void Longueur(int longueur) {
       this.longueur = longueur;
    }
    
}