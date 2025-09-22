public class Polynomial
{

	// i)
	double[] coeff;
	
	
	// ii)
	public Polynomial()
	{

		this.coeff = new double[]{0.0};

	}
	
	
	//  iii)
	public Polynomial(double inputs [])
	{
		
		
		this.coeff = new double[inputs.length];
		
		for(int i = 0; i < inputs.length; i++)
		{
			this.coeff[i] = inputs[i];
		}
		
	}
	
	// iv)
	public Polynomial add(Polynomial other)
	{
		int x = Math.max(this.coeff.length, other.coeff.length);
		
		double[] sum = new double[x];
		
		for(int i = 0; i < x; i++)
		{
			double a = 0.0;
			double b = 0.0;
			
			if(i < this.coeff.length)
			{
				a = this.coeff[i];
			}
			
			if(i < other.coeff.length)
			{
				b = other.coeff[i];
			}
			
			sum[i] = a + b;
		}
		
		return new Polynomial(sum);
		
		
		/*
		int x = this.coeff.length; // length of array within class
		
		double[] sum = new double[x]; // declaring new array of same size which will have combined coeffecients 
		
		for(int i=0; i < x; i++)
		{
			sum[i] = this.coeff[i] + other.coeff[i];
		}
		
		return new Polynomial(sum);
		*/
	}
	
	// v)
	
	public double evaluate(double x)
	{
		int n = this.coeff.length; // get length of polynomial within class
			
		double final_ans = 0.0;
		
		for(int i = 0; i < n; i++)
		{
			final_ans = final_ans + (this.coeff[i] * Math.pow(x, i));
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

}