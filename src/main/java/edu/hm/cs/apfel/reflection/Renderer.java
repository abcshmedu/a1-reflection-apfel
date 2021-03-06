/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */

package edu.hm.cs.apfel.reflection;

import java.lang.reflect.*;

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
     * For all fields, which are annotated with @RenderMe.
     * For all methods with return value and without parameter.
     *
     * @return String contains all fields and methods (public and private) with their values.
     * @throws Exception diverse exceptions
     */
    public String render() throws Exception {
        String result = "";
        Class< ? > typeObject = obj.getClass();

        result += "Instance of " + typeObject.getCanonicalName() + ":\n";

        for (Field field: typeObject.getDeclaredFields()) {

            if (field.getAnnotation(RenderMe.class) != null) {
                field.setAccessible(true);


                String renderPath = field.getAnnotation(RenderMe.class).with();

                result += field.getName();

                if (!renderPath.equals("")) {
                    Class< ? > typ = Class.forName(renderPath);
                    Renderface renderer = (Renderface) typ.newInstance();

                    result += renderer.render(field.get(obj));

                } else { // Default Rendering:
                    result += "(Type " + field.getType().getSimpleName() + "): "  + field.get(obj);
                }

                result += "\n";
            }
        }
        for (Method method: typeObject.getDeclaredMethods()) {
            if (method.getAnnotation(RenderMe.class) != null) {                 // Mehtode Annotiert mit RenderMe?
                method.setAccessible(true);

                if (method.getParameterTypes().length == 0) {                   // Methode hat keine Parameter?
                    result += method.getName() + " ";

                    if (method.getReturnType().isArray()) {                     // Methode gibt Array zurück?
                        String renderPath = method.getAnnotation(RenderMe.class).with();

                        if (!renderPath.equals("")) {                           // für diesen Array ist ein ArrayRenderer angegeben ?
                            Class< ? > typ = Class.forName(renderPath);
                            Renderface renderer = (Renderface) typ.newInstance();

                            result += renderer.render(method.invoke(typeObject.newInstance()));
                        } else {                                                // Kein ArrayRenderer angeben -> Exeption
                            throw new Exception("Argument with is empty! @RenderMe annotated Functions with Arrays as a ReturnType have to have a valid Annotation argument (with)!");
                        }
                    } else {                                                    // Return Type  ist kein Array -> benutze DefaultRendering
                        result += "(Type " + method.getReturnType().getSimpleName() + "): " + method.invoke(typeObject.newInstance());
                    }
                } else {                                                        // Methode hat Parameter -> Exeption
                    throw new Exception("with @RenderMe Annotations, Functions are not allowed to have Arguments - and can't be rendered!");
                }
                result += "\n";
            }
        }

        return result;
    }
}

/*
    Example:

    "Instance of edu.hm.SomeClass:\n" +
    "foo (Type int): 5\n
    array (Type int[]) [1, 2, 3, ]\n
    date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"

 */