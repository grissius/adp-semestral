package cz.fit.dpo.mvcshooter.model.object;

/**
 * Created by smolijar on 10/25/16.
 */
public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    public Vector add(Vector other) {
        this.addX(other.getX());
        this.addY(other.getY());
        return this;
    }

    public boolean isZero() {
        return this.getX() + this.getY() == 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double value) {
        x += value;
    }

    public void addY(double value) {
        y += value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;

        Vector vector = (Vector) o;

        if (x != vector.x) return false;
        return y == vector.y;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getX());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public void diminish(double divisor) {
        if (Math.abs(x) <= 1) {
            x = 0;
        }
        if (Math.abs(y) <= 1) {
            y = 0;
        }
        x /= divisor;
        y /= divisor;
    }

    public double distance(Vector other) {
        double a = getX() - other.getX();
        double b = getY() - other.getY();
        return Math.sqrt(a*a + b*b);
    }
}
