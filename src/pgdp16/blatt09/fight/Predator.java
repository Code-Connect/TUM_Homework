package pgdp16.blatt09.fight;

/**
 * Klasse der Raubtiere.
 */
public class Predator extends Animal {

    int withoutFood;//TODO Please Rename; by CC

    /**
     * Dem Konstruktor wird das Geschlecht des Tiers uebergeben.
     */
    public Predator(boolean female) {
        //TODO
        super(false);
    }

    public void eat() {//TODO Please Rename; by CC
        throw new RuntimeException("Method eat() shall be overridden! by children!");
    }

}
