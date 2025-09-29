import java.io.File;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) {
		
		/* below is testing up until i made multiply()
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		
		double [] c1 = {3,5};
		int[] e1 = {1,2};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {2,4};
		int[] e2 = {1, 3};
		Polynomial p2 = new Polynomial(c2, e2);
		
		Polynomial s = p1.add(p2);
		
		double value = p1.evaluate(2.0);  // Should compute 3*2 + 5*4 = 6 + 20 = 26
		System.out.println(value);
		
		boolean isRoot = p1.hasRoot(0);
		System.out.println(isRoot);
		
				// (3x^1 + 2) * (4x^2 + 1)
		double[] c1 = {2, 3}; int[] e1 = {0, 1};  // 2 + 3x
		double[] c2 = {1, 4}; int[] e2 = {0, 2};  // 1 + 4x^2
		*/
		
		double[] c1 = {2, 3};
        int[] e1 = {0, 1};
        Polynomial p1 = new Polynomial(c1, e1);

        // Q(x) = 1 + 4x^2
        double[] c2 = {1, 4};
        int[] e2 = {0, 2};
        Polynomial p2 = new Polynomial(c2, e2);

        // Multiply them
        Polynomial result = p1.multiply(p2);

        // Print all three
        System.out.print("P(x) = ");
        p1.printPolynomial();

        System.out.print("Q(x) = ");
        p2.printPolynomial();

        System.out.print("P(x) * Q(x) = ");
        result.printPolynomial();
		
		System.out.println("----------------");
		
		try 
		{
			File file = new File("testing.txt");
			Polynomial pFromFile = new Polynomial(file);
			pFromFile.printPolynomial();
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found!");
		}
		
		System.out.println("----------------");

		try 
		{
			p1.saveToFile("output.txt");
			System.out.println("Polynomial saved to file!");
		} 
		catch (IOException e) 
		{
			System.out.println("Error writing to file: " + e.getMessage());
		}

		



		/*
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		*/
	}
}