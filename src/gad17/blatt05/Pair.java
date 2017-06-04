package gad17.blatt05;

public class Pair<_1, _2> {
    public final _1 _1;
    public final _2 _2;

    public Pair(_1 _1, _2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public String toString() {
        return "(" + _1.toString() + "," + _2.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Pair && ((Pair) o)._1.equals(_1) && ((Pair) o)._2.equals(_2);
    }
}
