public class circle {
    int x;
    int y;
    int radius;

    public circle(int x, int y, int r){
        this.x=x;
        this.y=y;
        this.radius=r;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void deplace(int dx,int dy){
        this.x+=dx;
        this.y+=dy;
    }

    @Override
    public String toString() {
        return "circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
