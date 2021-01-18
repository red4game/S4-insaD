public class circle {
    int x;
    int y;
    int radius;

    public circle(int x, int y, int r){
        this.x=x;
        this.y=y;
        this.radius=r;
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
