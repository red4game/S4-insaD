public class TP1 {
    public static void main(String[] args) {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(20, 20);
        System.out.println(p1==p2);
        System.out.println(p1.equals(p2));

        System.out.println(p1);
        System.out.println(p2);

        Point p3=p1; // on copie l'adresse de l'instance
        Point p4=p1.copie(); // on copie les valeurs dans une nouvelle instance

        System.out.println(p1.barycentre(1,2,p2));
        System.out.println(p1.milieu(p2));

        p2.deplace(1,1);
        System.out.println( "p2 apres deplacement de (1,1) : ("
                + p2.x() +","+ p2.y()  +")" );
    }
}
