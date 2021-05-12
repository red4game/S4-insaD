import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Polynome {
    HashMap<Integer,Double> poly;

    public Polynome(){
        poly = new HashMap<Integer,Double>();
    }

    public double get(int i){
        return poly.getOrDefault(i, 0.0);
    }

    public void set(int i, double val){
        if (val!=0){
            poly.put(i,val);
        } else {
            poly.remove(i);
        }

    }

    public void inc(int i, double val) {
        this.poly.put(i, this.get(i) + val);
    }

    public int deg(){
        if (poly.size() ==0){
            return -1;
        }
        return Collections.max(poly.keySet());
    }

    public Polynome add(Polynome mypoly){
        Polynome res = new Polynome();
        for (int key: poly.keySet()){
            res.set(key,poly.get(key));
        }

        for (int key : mypoly.poly.keySet()){
            res.inc(key,mypoly.get(key));
        }

        return res;
    }

    public Polynome mul(Polynome mypoly){
        Polynome res = new Polynome();
        for (int i=0;i<=this.deg();i++){
            for (int j=0;j<=mypoly.deg();j++){
                res.inc(i+j,(this.get(i)*mypoly.get(j)));
            }
        }
        return res;
    }

    public Polynome extract (int begin,int length){
        Polynome res = new Polynome();
        for (int key : poly.keySet()){
            if (key>=begin&&key<begin+length){
                res.inc(key-begin,poly.get(key));
            }
        }
        return res;
    }
    public Polynome mulKnuth(Polynome ponom) {
        Polynome ret = new Polynome();

        // Take both polynoms and split them at half the maximum degree
        int n = Math.max(this.deg(), ponom.deg());
        int k = n/2; // This might flood it? Idk

        Polynome P0 = new Polynome();
        Polynome P1 = new Polynome();
        Polynome Q0 = new Polynome();
        Polynome Q1 = new Polynome();

        // P0 is every monom from us up to k excluded
        // P1 is every monom from us starting at k included
        P0 = this.extract(0,k);
        P1 = this.extract(k,this.deg()+1);
        Q0 = ponom.extract(0,k);
        Q1 = ponom.extract(k,ponom.deg()+1);
        /*
        for (int key : this.poly.keySet()) {
            if (key < k)
                P0.inc(key, this.poly.get(key));
            else
                P1.inc(key-k, this.poly.get(key));
        }

        // Same with Q and the other ponom
        for (int key : ponom.poly.keySet()) {
            if (key < k)
                Q0.inc(key, ponom.poly.get(key));
            else
                Q1.inc(key-k, ponom.poly.get(key));
        }
         */
        Polynome moins_un = new Polynome();
        moins_un.set(0, -1.0);

        Polynome POQO = P0.mul(Q0);
        Polynome PIQI = P1.mul(Q1);
        Polynome POPIQOQI = (P0.add(P1)).mul(Q0.add(Q1));

        // Manual addition of res += POQO
        // There won't be any coefficient overlaps
        for (int key : POQO.poly.keySet()) {
            ret.set(key, POQO.poly.get(key));
        }

        // Manual addition of res += PIQI
        // Again, no overlap of coefficients
        for (int key : PIQI.poly.keySet()) {
            ret.set(key + 2 * k, PIQI.poly.get(key));
        }


        // Multiply POQO and PIQI by -1 to sum them to POPIQOQI
        POQO = POQO.mul(moins_un);
        PIQI = PIQI.mul(moins_un);
        // Lead dev : No! You can't just override existing object references like that!
        // Me: Ahah garbage collector go cromch

        POPIQOQI = POPIQOQI.add(POQO);
        POPIQOQI = POPIQOQI.add(PIQI);

        for (int key : POPIQOQI.poly.keySet()) {
            ret.set(key + k, POPIQOQI.poly.get(key));
        }

        return ret;
    }

    public String toString() {
        // Can't be bothered to sort it.
        if (this.poly.size() == 0)
            return "0";

        String ret = ""; // There's at least one monom
        for (int key : this.poly.keySet()){
            double value = this.poly.get(key);
            if (value != 0.0)
                ret += value + "X^{" + key + "}" + "+";
        }
        return ret.substring(0, ret.length()-1);
    }

    public static void main(String[] args) {
        Polynome k = new Polynome();
        Polynome u = new Polynome();
        k.set(1, 4.0);
        k.set(6, 1802.0);
        u.set(0, 2.2);
        u.set(9, 32.7);
        System.out.println(k.mulKnuth(u));
    }

}
