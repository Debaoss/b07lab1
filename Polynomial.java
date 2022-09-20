public class Polynomial {
	double[] coefficients;
	
	public Polynomial(){
		coefficients = new double[] {0};
	}
	
	public Polynomial(double poly[]){
		coefficients = new double[poly.length];
		for (int i = 0; i < poly.length; i++){
			coefficients[i] = poly[i];
		}
	}
	
	public Polynomial add(Polynomial second){
		int length = coefficients.length;
		if (second.coefficients.length > coefficients.length){
			length = second.coefficients.length;
		}
		double sum[] = new double[length];
		for (int i = 0; i < length; i++){
			if (i < coefficients.length){
				sum[i] = sum[i] + coefficients[i];
			}
			if (i < second.coefficients.length){
				sum[i] = sum[i] + second.coefficients[i];
			}
		}
	return new Polynomial(sum);
	}
	
	public double evaluate(double value){
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++){
			sum = sum + coefficients[i] * Math.pow(value, i);
		}
		return sum;
	}
	
	public boolean hasRoot(double value){
		if (this.evaluate(value) == 0){
			return true;
		}
		return false;
	}
}