/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheDungeon.models;

import baseThedungeon.models.Sprite;
import thedungeon.models.AtaqueHoja;
import thedungeon.models.Enemy;

/**
 *
 * @author Nico
 */
public class Enemy1 extends Enemy{

    public Enemy1(float x, float y, int width, int height, int difficulty) {
        super(x, y, width, height, 22, 8, 4, difficulty);
        AtaqueHoja hoja;
        hoja = new AtaqueHoja(getDamage() , 1.3);
        ataques.add(hoja);
    }

}
