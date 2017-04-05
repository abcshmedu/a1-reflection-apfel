/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */
package edu.hm.cs.apfel.reflection;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

/**
 * @author Maximilian Lipp, lipp@hm.edu
 * @author Florian Tobusch, tobusch@hm.edu
 * @version 01.04.2017
 */
public class RendererTestForException {

    @Test(expected=ClassNotFoundException.class)
    public void testClassNotFoundException() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Renderer r = new Renderer(new Object() {@RenderMe(with = "ArrayRenderer") private int[] privateIntArray = new int[]{1,2,3};});
        r.render();
    }

}