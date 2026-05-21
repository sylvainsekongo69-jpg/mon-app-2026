
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sylvain;

import my.first.mesclasses.Rectangle;

/**
 *
 * @author LENOVO
 */
public class Sylvain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rectangle sks;
        sks = new Rectangle(2,4,3);
        Rectangle sks2 = new Rectangle(2,4);
        
        sks.largeur = 2;
        sks.setLongueur(4);
        System.out.println("largeur:"+sks.largeur);
        System.out.println("Longueur:"+sks.getLongueur());
        System.out.println("surface:"+sks.surface());
        System.out.println("perimetre:"+sks.perimetre());
    }
    
}


