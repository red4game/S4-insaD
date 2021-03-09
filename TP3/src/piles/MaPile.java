

package piles;

public class MaPile implements Pile {
    private static final int TAILLE_MAX = 10;
    private int taille = 10;
    private int sommet = 0;
    private Object[] tab;

    public MaPile() {
        this.tab = new Object[this.taille];
    }

    public void viderPile() {
        this.sommet = 0;
    }

    public void empiler(Object var1) {
        if (this.sommet<=TAILLE_MAX & var1!=null) {
            this.tab[this.sommet] = var1;
            this.sommet++;
        } else {
            System.out.println("vous avez dépassé la taille maximale ou avez essayé d'empiler un null");
        }
    }

    public Object depiler() {
        if (!(pileVide()))/*this.sommet>0 marche aussi, je préfère perso*/ {
            this.sommet--;
            return this.tab[this.sommet];
        } else {
            System.out.println("la taille minimale est atteinte, la pile est vide");
            return null;
        }
    }

    public Object sommetPile() {
        return this.tab[this.sommet - 1];
    }

    public boolean pileVide() {
        return this.sommet == 0;
    }

    public String toString() {
        String var1 = "-----\n";
        if (this.pileVide()) {
            var1 = var1 + "pile vide";
        } else {
            var1 = var1 + this.tab[this.sommet - 1] + "   <- sommet";

            for(int var2 = this.sommet - 2; var2 >= 0; --var2) {
                var1 = var1 + "\n" + this.tab[var2];
            }

            var1 = var1 + "\n-----\n";
        }

        return var1;
    }

    public static void main(String[] var0) {
        MaPile var1 = new MaPile();
        var1.empiler(1);
        System.out.println(var1);        var1.empiler(2);
        System.out.println(var1);
        var1.empiler(3);
        System.out.println(var1);
    }
}


