package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.factory.enemy.AbstractEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.enemy.RealisticEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.enemy.SimpleEnemyFactory;
import cz.fit.dpo.mvcshooter.model.factory.projectile.RealisticProjectileFactory;
import cz.fit.dpo.mvcshooter.model.factory.projectile.SimpleProjectileFactory;
import cz.fit.dpo.mvcshooter.model.geometry.Vector;
import cz.fit.dpo.mvcshooter.model.memento.Memento;
import cz.fit.dpo.mvcshooter.model.memento.State;
import cz.fit.dpo.mvcshooter.model.object.GameObject;
import cz.fit.dpo.mvcshooter.model.object.enemy.Enemy;
import cz.fit.dpo.mvcshooter.model.object.projectile.Projectile;
import cz.fit.dpo.mvcshooter.model.object.sling.Sling;
import cz.fit.dpo.mvcshooter.model.object.pattern.observer.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by smolijar on 10/25/16.
 */
public class Model extends Subject {
    private float gravity;
    private Vector battlefield;
    private Sling sling;
    private List<Projectile> projectiles;
    private List<Enemy> enemies;
    private AbstractEnemyFactory enemyFactory;
    private Mode mode;
    private int score = 0;
    private int cooked = 0;
    private Memento saved;

    public static enum Mode {
        REALISTIC,
        SIMPLE
    }

    public Model() {
        this.battlefield = new Vector(500,500);
        this.gravity = 1;
        this.sling = new Sling();
        this.projectiles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.mode = Mode.REALISTIC;
        applyMode();
        for (int i = 0; i < 10; ++i) {
            this.enemies.add(enemyFactory.create());
        }
    }

    public void saveGame() {
        saved = createMemento();
        System.out.println("Game saved!");
    }

    public void loadGame() {
        if(saved != null) {
            setMemento(saved);
            notifyObservers();
        }
        else {
            System.out.println("No saved game");
        }
    }

    public Vector getBattlefield() {
        return battlefield;
    }

    public void setMemento(Memento memento) {
        State state = memento.getState();
        enemies = state.getEnemies().stream().map((e) -> e.clone()).collect(Collectors.toList());
        projectiles = state.getProjectiles().stream().map((e) -> e.clone()).collect(Collectors.toList());;
        gravity = state.getGravity();
        battlefield = new Vector(state.getBattlefield());
        mode = state.getMode();
        sling = state.getSling().clone();
        score = state.getScore();
        applyMode();
    }


    public Memento createMemento() {
        State state = new State();
        state.setEnemies(enemies.stream().map((e) -> e.clone()).collect(Collectors.toList()));
        state.setProjectiles(projectiles.stream().map((e) -> e.clone()).collect(Collectors.toList()));
        state.setGravity(gravity);
        state.setBattlefield(new Vector(battlefield));
        state.setMode(mode);
        state.setSling(getSling().clone());
        state.setScore(score);
        return new Memento(state);
    }

    public void resetObjects() {
        projectiles.clear();
        enemies.clear();
        notifyObservers();
    }

    public void spawnEnemy() {
        enemies.add(enemyFactory.create());
        notifyObservers();
    }

    public void startCooking() {
        this.cooked = 1;
    }

    public void cookingRelease() {
        fire(cooked);
        cooked = 0;
    }

    public void swapMode() {
        if (mode == Mode.REALISTIC) {
            mode = Mode.SIMPLE;
        } else {
            mode = Mode.REALISTIC;
        }
        applyMode();
        notifyObservers();
    }

    private void applyMode() {
        if(mode == Mode.REALISTIC) {
            turnRealistic();
        } else {
            turnSimple();
        }
    }

    private void turnRealistic() {
        this.enemyFactory = new RealisticEnemyFactory(battlefield);
        this.getSling().setProjectileFactory(new RealisticProjectileFactory());
    }

    private void turnSimple() {
        this.enemyFactory = new SimpleEnemyFactory(battlefield);
        this.getSling().setProjectileFactory(new SimpleProjectileFactory());
    }

    public Sling getSling() {
        return sling;
    }

    public void fire(int firePower) {
        this.projectiles.addAll(getSling().fire(firePower));
    }

    public synchronized List<GameObject> getObjects() {
        List<GameObject> objects = new ArrayList<>(projectiles);
        objects.addAll(enemies);
        objects.add(getSling());
        return objects;
    }

    private boolean removeAddObjects() {
        List<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile p : projectiles) {
            if (p.outOfField(battlefield)) {
                projectilesToRemove.add(p);
            }
        }
        projectiles.removeAll(projectilesToRemove);

        List<Enemy> enemiesToRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            if (e.outOfField(battlefield)) {
                enemiesToRemove.add(e);
            }
        }

        for (Projectile p : projectiles) {
            for (Enemy e : enemies) {
                if (p.collides(e)) {
                    enemiesToRemove.add(e);
                    score++;
                }
            }
        }
        enemies.removeAll(enemiesToRemove);
        for (int i = 0; i < enemiesToRemove.size(); ++i) {
            this.enemies.add(enemyFactory.create());
        }
        return (projectilesToRemove.size() + enemiesToRemove.size()) != 0;
    }

    public void addGravity(int value) {
        gravity += (float) value / 10;
    }

    public void tick() {
        boolean changed = false;
        // move
        for (GameObject o : getObjects()) {
            changed |= o.move(battlefield, gravity);
        }

        // remove projectiles and enemies, replanish enemies
        changed |= removeAddObjects();

        if(cooked != 0) {
            cooked++;
        }

        if (changed) {
            notifyObservers();
        }
    }

    public String createHudText() {
        String msg = "";

        msg += getSling().getUserMsg();
        msg += "\n";
        msg += "Firepower: ";
        for (int i = 0; i < cooked; i++) {
            msg += "#";
        }
        msg += "\n";

        if (mode == Mode.REALISTIC) {
            msg += "Realistic mode";
        } else if (mode == Mode.SIMPLE) {
            msg += "Simple mode";
        }
        msg += ", Gravity: " + (float)Math.round(gravity * 10) / 10;
        msg += "\n";
        msg += "Score: " + score;
        msg += "\n";
        return msg;
    }

    public void swapSling() {
        getSling().swapStates();
    }
}
