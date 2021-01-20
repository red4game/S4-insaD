# TD N°1 d'informatique

## sommaire du cours 
* préambule (diapo 2)
* introduction sur java (diapo 5)
* rappels 1A (diapo 13)
* POO (diapo 15)
* objets et classes (diapo 20)
* unité de compilation (diapo 31)
* égalité d'objets (diapo 33)
* aggrégation (diapo 35)
* notion de recopie (diapo 38)
* exercice conception d'un classe Polygone (diapo 41)
* gestion mémoire (diapo 49)


[lien vers la doc de java ](https://docs.oracle.com/en/java/) 

## Liste d'exercices
- [X] exercice Moyenne/Recherche
- [X] exercice Cercle
- [ ] exercice Polygone

***
### Exercice Moyenne/Recherche (diapo 14)
```java
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
}
```
***
### Exercice Cercle (diapo 26)
```java
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

```
***
### Exercice polygone
```java
//on a un texte sur le cours à l'endroits de diapos, on pourrait recopier mais ça serait débile
```
