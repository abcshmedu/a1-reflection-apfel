/**
 * Organisation: Hochschule Muenchen, Fakultaet 07 Informatik und Mathematik
 * Purpose: lab software-architecture, IF4B, SS2017
 * Purpose: solution of assignment 1
 */
package edu.hm.cs.apfel.reflection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Maximilian Lipp, lipp@hm.edu
 * @author Florian Tobusch, tobusch@hm.edu
 * @version 01.04.2017
 */
@RunWith(Parameterized.class)
public class RendererTest {

        @Parameterized.Parameters(name = "{0}") //name = "{index}: renderer({0})={1}"
        public static Iterable<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { "ObjectWithPrivateInt", new Renderer( new Object() {@RenderMe private int privateIntField = 2;} ), "Instance of null:\nprivateIntField(Type int): 2\n" },
                    { "ObjectWithPrivateString", new Renderer( new Object() {@RenderMe private String publicStringField = "this is a test-String";} ), "Instance of null:\npublicStringField(Type String): this is a test-String\n" },
                    { "ObjectWithPrivateStringWithoutAnnotation", new Renderer( new Object() {private String publicStringField = "this is a test-String";} ), "Instance of null:\n" },
                    { "ObjectWithPrivateBoolean", new Renderer( new Object() {@RenderMe private boolean privateBooleanField = false;} ), "Instance of null:\nprivateBooleanField(Type boolean): false\n" },
                    { "ObjectWithArrayRenderer", new Renderer( new Object() {@RenderMe(with = "edu.hm.cs.apfel.reflection.ArrayRenderer") private int[] privateIntArray = new int[]{1,2,3};} ), "Instance of null:\nprivateIntArray(Type int[]) [1, 2, 3, ]\n" },
                    { "ObjectWithDate", new Renderer( new Object() {@RenderMe private Date privateDateField = new Date(123456789);} ), "Instance of null:\nprivateDateField(Type Date): Fri Jan 02 11:17:36 CET 1970\n" }
            });
        }

        private Renderer input;
        private String expected;

        public RendererTest(String nameOfTest, Renderer input, String expected) {
            this.input = input;
            this.expected = expected;
        }

        @org.junit.Test
        public void test() throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
            assertEquals(expected, input.render());
        }
}