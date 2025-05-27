package thedungeon.models;

// @author vanes
public class Enemy1 extends Enemy{

    public Enemy1(float x, float y, int width, int height, int difficulty) {
        super(x, y, width, height, 20, 5, 7, difficulty);
        AtaqueVomito vomito = new AtaqueVomito(damage, 1.2);
        ataques.add(vomito);
    }

}
