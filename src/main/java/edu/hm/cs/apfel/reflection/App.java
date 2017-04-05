package edu.hm.cs.apfel.reflection;

import java.lang.reflect.Constructor;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main(String... args) throws ReflectiveOperationException {
        Class<?> type = String.class;

        Constructor<?> ctor = type.getConstructor(char[].class);
        Object s = ctor.newInstance(new char[] {'a'});
        System.out.println((String)s);
        System.out.println();

    }
    @RenderMe(with = "blah")
    private int test = 4;

    @RenderMe
    private int[] narray = new int[]{1,2,3};

    @RenderMe
    public String test2 = "asdf";
}

