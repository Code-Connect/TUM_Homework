package gad17.blatt05;

public class IntHashableFactory implements HashableFactory<Integer> {

    @Override
    public DoubleHashable<Integer> create(int size) {
        return new DoubleHashInt(size);
    }

}
