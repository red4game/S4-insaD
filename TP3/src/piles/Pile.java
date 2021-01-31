package piles;

public interface Pile {

    /**
     * Enleve l'element du sommet de pile et le fournit en resultat.
     * @return L'element en sommet de pile.
     */
    Object depiler();

    /**
     * Met l'objet en sommet de pile. C'est la reference qui est mise dans la structure, l'objet n'est pas copié.
     * @param item - L'element a empiler.
     */
    void empiler(Object item);

    /**
     * Indique si la pile est vide.
     * @return true Si la pile est vide.
     */
    boolean pileVide();

    /**
     * Fournit l'element du sommet de pile, sans l'enlever. La structure de donnee n'est pas modifiee.
     * @return L'element du sommet de pile.
     */
    Object sommetPile();


    /**
     * Enleve tous les objets de la pile. Toutes les references sur les objets faites par la structure de donnee sont supprimees.
     */
    void viderPile();

}
