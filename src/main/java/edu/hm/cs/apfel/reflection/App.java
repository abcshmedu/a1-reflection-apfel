package edu.hm.cs.apfel.reflection;

/**
 * Hello world!
 *
 */
public class App 
{
    @RenderMe(with = "blah")
    private int test = 4;

    @RenderMe
    public String test2 = "asdf";
}
