public class TD1 {
    double moyenne(double a, double b){
        double moy = (a+b)/2;
        return moy;
    }

    boolean contient(int[] tab, int value){
        for (int i =0;i<tab.length;i++){
            if (tab[i]==value){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("bonjour les enfants");
        circle mycircle = new circle(2,2,2);
        System.out.println(mycircle);
    }
}
