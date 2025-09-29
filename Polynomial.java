import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;


public class Polynomial
{

	// i)
	double[] coeff;
	int[] exp;
	
	
	// ii)
	public Polynomial()
	{

		this.coeff = new double[]{0.0};
		this.exp = new int[]{0};

	}
	
	
	//  iii)
	public Polynomial(double coeff_inputs [], int exp_inputs[])
	{
		
		this.coeff = new double[coeff_inputs.length];
		this.exp = new int[exp_inputs.length];
		
		for(int i = 0; i < coeff_inputs.length; i++)
		{
			this.coeff[i] = coeff_inputs[i];
		}
		
		for(int i = 0; i < exp_inputs.length; i++)
		{
			this.exp[i] = exp_inputs[i];
		}
		
	}
	
	// iv)
	public Polynomial add(Polynomial other)
	{
		// i'm only taking max between coeffs b/c they match the 
		// length of their respective exp arrays
		
		ArrayList<Double> Rcoeff = new ArrayList<>();
		ArrayList<Integer> Rexp = new ArrayList<>();
		
		int i = 0;
		int j = 0;
		
		while(i < this.coeff.length && j < other.coeff.length)
		{
			if (this.exp[i] == other.exp[j])
			{
				Rcoeff.add(this.coeff[i] + other.coeff[j]);
				Rexp.add(this.exp[i]);
				i++;
				j++;
			}
			else if (this.exp[i] < other.exp[j])
			{
				Rcoeff.add(this.coeff[i]);
				Rexp.add(this.exp[i]);
				i++;
			}
			else
			{
				Rcoeff.add(other.coeff[j]);
				Rexp.add(other.exp[j]);
				j++;
			}
		}
		
		while(i < this.coeff.length)
		{
			Rcoeff.add(this.coeff[i]);
			Rexp.add(this.exp[i]);
			i++;
		}
		while(j < other.coeff.length)
		{
			Rcoeff.add(other.coeff[j]);
			Rexp.add(other.exp[j]);
			j++;
		}
		
		// coverting from arraylist to list below
		
		double[] finalCoeffs = new double[Rcoeff.size()];
		int[] finalExps = new int[Rexp.size()];

		for (int k = 0; k < Rcoeff.size(); k++) 
		{
			finalCoeffs[k] = Rcoeff.get(k);
			finalExps[k] = Rexp.get(k);
		}

		return new Polynomial(finalCoeffs, finalExps);
		
	}
	
	// v)
	
	public double evaluate(double x)
	{
		int n = this.coeff.length; // get length of polynomial within class
			
		double final_ans = 0.0;
		
		for(int i = 0; i < n; i++)
		{
			final_ans = final_ans + (this.coeff[i] * Math.pow(x, this.exp[i]));
		}
		
		return final_ans; 
	}
	
	// vi)
	
	public boolean hasRoot(double h)
	{
		
		double check_this = evaluate(h);
		return Math.abs(check_this) <= 1e-9; // tolerance check
		
		/*
		
		double check_this = evaluate(h);
		
		if(check_this == 0.0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		*/
	
		
		// or ig you could do return evaluate(h) == 0.0; instead of the if else block above
		
	}
	
	public Polynomial multiply(Polynomial other)
	{
		HashMap<Integer, Double> resultTerms = new HashMap<>();
		
		for(int i = 0; i < this.coeff.length ; i++)
		{
			for(int j = 0; j < other.coeff.length; j++)
			{
				int newExp = this.exp[i] + other.exp[j];
				double newCoeff = this.coeff[i] * other.coeff[j];

				if (resultTerms.containsKey(newExp)) 
				{
				resultTerms.put(newExp, resultTerms.get(newExp) + newCoeff);
				} 
				else 
				{
					resultTerms.put(newExp, newCoeff);
				}
			}
		}
		
		ArrayList<Integer> exponents = new ArrayList<>(resultTerms.keySet());

		Collections.sort(exponents);

		double[] finalCoeff = new double[exponents.size()];
		int[] finalExp = new int[exponents.size()];

		for (int i = 0; i < exponents.size(); i++)
		{
			int exp = exponents.get(i);
			finalExp[i] = exp;
			finalCoeff[i] = resultTerms.get(exp);
		}

		return new Polynomial(finalCoeff, finalExp);
		
	}


	public void printPolynomial() 
	{
		
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < coeff.length; i++) 
		{
			double c = coeff[i];
			int e = exp[i];

			if (i > 0 && c >= 0) 
			{
				result.append(" + ");
			} 
			else if (c < 0) 
			{
				result.append(" - ");
				c = -c; // show positive number after minus sign
			}

			if (e == 0) 
			{
				result.append(c);
			} 
			else if (e == 1) 
			{
				result.append(c).append("x");
			} 
			else 
			{
				result.append(c).append("x^").append(e);
			}
		}

		System.out.println(result.toString());
	}
	
	
	public Polynomial(File file) throws FileNotFoundException 
	{
		Scanner scan = new Scanner(file);

		String line = scan.nextLine();

		if (!line.startsWith("+") && !line.startsWith("-")) 
		{
			line = "+" + line;
		}

   
		String[] terms = line.split("(?=[+-])");

   
		ArrayList<Double> coeffList = new ArrayList<>();
		ArrayList<Integer> expList = new ArrayList<>();

		for (String term : terms) 
		{
			if (term.contains("x")) 
			{
				String[] parts = term.split("x");

				double coeff = Double.parseDouble(parts[0]);

				int exp = Integer.parseInt(parts[1]);

				coeffList.add(coeff);
				expList.add(exp);
			} 
			else 
			{
				double coeff = Double.parseDouble(term);
				int exp = 0;

				coeffList.add(coeff);
				expList.add(exp);
			}
		}

		this.coeff = new double[coeffList.size()];
		this.exp = new int[expList.size()];

		for (int i = 0; i < coeffList.size(); i++) 
		{
			this.coeff[i] = coeffList.get(i);
			this.exp[i] = expList.get(i);
		}
	}

	public void saveToFile(String filename) throws IOException 
	{
		PrintWriter out = new PrintWriter(filename);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < coeff.length; i++) 
		{
			double c = coeff[i];
			int e = exp[i];

			if (c >= 0 && i > 0) 
			{
				sb.append("+");
			}

			if (e == 0) 
			{
				sb.append(c);
			} else 
			{
				sb.append(c).append("x").append(e);
			}
		}

		out.println(sb.toString());

		out.close();
	}

	
	
}