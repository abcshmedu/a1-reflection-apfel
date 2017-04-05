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
public class Renderer {

    private final Object obj;

    /**
     * Constructor.
     * @param obj Object uses @RenderMe-Annotations
     */
    public Renderer(Object obj) {
        this.obj = obj;
    }

    /**
     * for all fields, which are annotated with @RenderMe.
     * name (Typ) Wert\n
     *
     * @return String contains all fields (public and private) with their values.
     */
    public String render() throws IllegalAccessException {
        String result = "";
        Class<?> typeObject = obj.getClass();

        result += "Instance of " + typeObject.getCanonicalName() + ":\n";

        for (Field field: typeObject.getDeclaredFields()) {
            if (field.getAnnotation(RenderMe.class) != null) {
                field.setAccessible(true);
                if(field.getType().isArray()){
                    result += field.getName() + "(Type " + field.getType() + "): [";
                    System.out.println(field.get(obj));
                    for(int i = 0; i < Array.getLength(field.get(obj)); i++) {
                        result += Array.get(field.get(obj),i) + ", ";
                    }
                    result += "]\n";

                } else {
                    result += field.getName() + "(Type " + field.getType() + "): "  + field.get(obj)  +"\n";
                }

            }
        }

        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        App a = new App();
        Renderer r = new Renderer(a);
        System.out.println(r.render());
    }

}

/*
    Example:

    "Instance of edu.hm.SomeClass:\n" +
    "foo (Type int): 5\n
    array (Type int[]) [1, 2, 3, ]\n
    date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"

 */