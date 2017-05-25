package gad17.blatt04;

import java.io.IOException;
import java.io.Serializable;

/**
 * Dieses Interface beschreibt alle gemeinsamen Methoden einer
 * Response.
 */
public interface IResponse extends Serializable {
    /**
     * Diese Methode entspricht der accept()-Methode des Visitor-Patterns.
     */
    void accept(ResponseVisitor v) throws IOException;
}
