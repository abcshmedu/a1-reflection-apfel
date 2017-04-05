/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */

package edu.hm.cs.apfel.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * @author Maximilian Lipp, lipp@hm.edu
 * @author Florian Tobusch, tobusch@hm.edu
 * @version 01.04.2017
 */
public class ArrayRenderer implements Renderface {

    /**
     * for all arrays, which are annotated with @RenderMe.
     * name (Typ) Wert\n
     * @return String contains all fields (public and private) with their values.
     */

    public String render(Object o) {

        String result ="(Type "+  o.getClass().getSimpleName() + ") [";

        System.out.println(o.getClass().getSimpleName());

        for(int i= 0; i < Array.getLength(o);i++) {
            Object value = Array.get(o,i);
            result += value + ", ";
        }
        result += "]";

        return result;
    }
}
