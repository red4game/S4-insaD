
public class circle {
    int x;
    int y;
    int radius;

    /**
     *
     * @param x
     * @param y
     * @param r
     */
    public circle(int x, int y, int r){
        this.x=x;
        this.y=y;
        this.radius=r;
    }

    /**
     *
     * @return
     */
    public int getRadius() {
        return radius;
    }

    /**
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     *
     * @param dx
     * @param dy
     */
    public void deplace(int dx,int dy){
        this.x+=dx;
        this.y+=dy;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
