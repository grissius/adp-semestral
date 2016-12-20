package cz.fit.dpo.mvcshooter.model.object.enemy;

import cz.fit.dpo.mvcshooter.pattern.visitor.Visitor;

/**
 * Created by smolijar on 10/25/16.
 */
public class RealisticEnemy extends Enemy {
    public RealisticEnemy(int w, int h) {
        super(w, h);
    }

    protected RealisticEnemy() {}

    @Override
    public void accept(Visitor visitor) {
        visitor.visitRealisticEnemy(this);
    }

    @Override
    public synchronized boolean move(int w, int h, float gravity) {
        this.direction.addX(Math.random() * 0.2 - 0.1);
        this.direction.addY(Math.random() * 0.2 - 0.1);
        return super.move(w, h, gravity);
    }

    @Override
    public Enemy clone() {
        RealisticEnemy enemy = new RealisticEnemy();
        enemy.setAs(this);
        return enemy;
    }
}
