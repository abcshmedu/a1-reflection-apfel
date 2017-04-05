/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */

package edu.hm.cs.apfel.reflection;

import java.lang.reflect.Constructor;
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
     * @throws IllegalAccessException accesses field values
     * @return String contains all fields (public and private) with their values.
     */
    public String render() throws IllegalAccessException {
        String result = "";
        Class< ? > typeObject = obj.getClass();

        result += "Instance of " + typeObject.getCanonicalName() + ":\n";

        for (Field field: typeObject.getDeclaredFields()) {
            //TODO prüfen, ob Annotation eigene Renderer-Klasse enthält

            String withValue = field.getAnnotation(RenderMe.class).with();
            boolean foundClass = true;

            try {
                Class< ? > alternativeRendererClass = Class.forName(withValue);
                if (withValue != "" && foundClass) {
                    Constructor< ? > constructor = alternativeRendererClass.getConstructor();
                }
            } catch (ClassNotFoundException e) {
                //no class found, go ahead with standard process
            } catch (NoSuchMethodException e) {
                //no class found, go ahead with standard process
            }



            if (field.getAnnotation(RenderMe.class) != null) {

                System.out.println(field.getAnnotation(RenderMe.class).with());
                Class< ? > t = "ArrayRenderer".getClass();
                System.out.println("class:" + t);

                field.setAccessible(true);
                result += field.getName() + " (Type " + field.getType() + "): " + field.get(obj).toString() + "\n";
                // TODO: 04.04.17 Umwandlung von Typenbezeichnungen bspw. in "String" statt "class java.lang.String"
            }
        }




        return result;
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        /*App a = new App();
        Renderer r = new Renderer(a);
        System.out.println(r.render());*/

        Class< ? > t = Class.forName("edu.hm.cs.apfel.reflection.ArrayRenderer");
        System.out.println("class:" + t);
    }

}

/*
    Example:

    "Instance of edu.hm.SomeClass:\n" +
    "foo (Type int): 5\n
    array (Type int[]) [1, 2, 3, ]\n
    date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"

 */