/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */

package edu.hm.cs.apfel.reflection;

import java.lang.reflect.Field;

/**
 * @author Maximilian Lipp, lipp@hm.edu
 * @author Florian Tobusch, tobusch@hm.edu
 * @version 01.04.2017
 */
public class ArrayRenderer {
    private final Object obj;

    /**
     * Constructor.
     * @param obj Object uses @RenderMe-Annotations at Arrays
     */
    public ArrayRenderer(Object obj) {
        this.obj = obj;
    }

    /**
     * for all arrays, which are annotated with @RenderMe.
     * name (Typ) Wert\n
     *
     * @throws IllegalAccessException accesses field values
     * @return String contains all fields (public and private) with their values.
     */
    public String render() throws IllegalAccessException {
        String result = "";
        Class< ? > typeObject = obj.getClass();

        for (Field field: typeObject.getDeclaredFields()) {
            if ((field.getAnnotation(RenderMe.class) != null) && (field.getAnnotation(RenderMe.class).with() == "ArrayRenderer")) {
                field.setAccessible(true);
                result += field.getName() + " (Type " + field.getType() + "): " + field.get(obj).toString() + "\n";
                // TODO: 04.04.17 Umwandlung von Typenbezeichnungen bspw. in "String" statt "class java.lang.String"
            }
        }




        return result;
    }
}
