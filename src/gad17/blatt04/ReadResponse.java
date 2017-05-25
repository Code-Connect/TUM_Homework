package gad17.blatt04;

import java.io.IOException;

public class ReadResponse implements IResponse {
    private static final long serialVersionUID = 1L;

    private SerializableOptional<Integer> value;

    /**
     * Diese Methode gibt den gelesenen Wert zurück. Gibt
     * es zum gesuchten Schlüssel keinen Wert, so wird
     * {@link SerializableOptional}.empty() zurückgeliefert.
     *
     * @return der gelesene Wert
     */
    public SerializableOptional<Integer> getValue() {
        return value;
    }

    public ReadResponse(int value) {
        this.value = SerializableOptional.of(value);
    }

    public ReadResponse() {
        this.value = SerializableOptional.empty();
    }

    @Override
    public String toString() {
        return "Read() = " + value;
    }

    @Override
    public void accept(ResponseVisitor v) throws IOException {
        v.visit(this);
    }
}
