package gad17.blatt04;

import java.io.IOException;
import java.io.Serializable;

/**
 * Dieses Interface beschreibt alle gemeinsamen Methoden eines
 * Requests.
 */
public interface IRequest extends Serializable {
    /**
     * Diese Methode entspricht der accept()-Methode des Visitor-Patterns.
     */
    void accept(RequestVisitor v) throws IOException;

    /**
     * Diese Methode ermittelt des Schlüssel des Requests.
     *
     * @return der Schlüssel
     */
    String getKey();
}
