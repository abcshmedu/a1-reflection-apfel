/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */

package edu.hm.cs.apfel.reflection;

/**
 * Interface for classes which are used for the with-Attribute in RenderMe-Annotations.
 *
 * @author Maximilian Lipp, lipp@hm.edu
 * @author Florian Tobusch, tobusch@hm.edu
 * @version 01.04.2017
 */
public interface Renderface {

    /**
     * Method renders one object.
     *
     * @param o an object for which the method supports the render-functionality
     * @return String representation of the object with type and values
     */
    String render(Object o);
}
