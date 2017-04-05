package edu.hm.cs.apfel.reflection;

/**
 * Hello world!
 *
 */
public class App 
{
    @RenderMe(with = "edu.hm.cs.apfel.reflection.ArrayRenderer")
    private int[] narray = new int[]{1,2,3};

    @RenderMe
    private boolean priveateBooleanField = false;

    @RenderMe
    private Integer privateIntegerField = 3;

    @RenderMe
    private Blah app = new Blah();

    @RenderMe
    public String publicStringField = "asdf";

    @RenderMe
    private final String[] publicFinalStringArray = {"firstElem", "SecondElem", "ThirdElem"};


    private class Blah {
        private int blahField = 3;

        @Override
        public String toString() {
            return "Blah{" +
                    "blahField=" + blahField +
                    '}';
        }
    }

    interface Sut{ };

    private Sut obj = new Sut() { private int blahField = 3; };
}
