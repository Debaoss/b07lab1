import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Polynomial {
	double[] coefficients;
	int[] degree;
	
	public Polynomial(){
		coefficients = new double[] {0};
		degree = new int[] {0};
	}
	
	public Polynomial(double poly[], int deg[]){
		coefficients = new double[poly.length];
		degree = new int[deg.length];
		for (int i = 0; i < poly.length; i++){
			coefficients[i] = poly[i];
			degree[i] = deg[i];
		}
	}

	public Polynomial(File new_file){
		try{
		coefficients = new double[] {0};
		degree = new int[] {0};
		Scanner scan = new Scanner(new_file);
		String line = scan.nextLine();
		scan.close();
		String buffer = "";
		int int_buffer = 0;
		double double_buffer = 1.0;
		boolean negative = false;
		boolean exponent = false;
		double[] swap1;
		int[] swap2;
		for (int i = 0; i < line.length(); i ++){
			if (line.substring(i, i+1).equals("+") || line.substring(i, i+1).equals("-") || i == line.length() - 1){
				if (i == line.length() - 1) {
					if(line.substring(i, i+1).equals("+") == false && line.substring(i, i+1).equals("-") == false) {
						buffer = buffer + line.substring(i, i+1);
					}
				}
				if (buffer.equals("") == false){
					if (exponent == true){
						int_buffer = Integer.parseInt(buffer);
						buffer = "";
					}
					else {
						double_buffer = Double.parseDouble(buffer);
						buffer = "";
					}
				}
				if (i != 0){
					swap1 = new double[coefficients.length + 1];
					swap2 = new int[degree.length + 1];
					for (int j = 0; j < degree.length; j ++){
						swap1[j] = coefficients[j];
						swap2[j] = degree[j];
					}
					swap1[degree.length] = double_buffer;
					swap2[degree.length] = int_buffer;
					coefficients = swap1;
					degree = swap2;
					if (negative == true){
						coefficients[coefficients.length - 1] = -coefficients[coefficients.length - 1];
					}
				}
				exponent = false;
				if (line.substring(i, i+1).equals("-")){
					negative = true;
				}
				else{
					negative = false;
				}
				double_buffer = 1.0;
				if (i == line.length() - 1){
					swap1 = new double[degree.length - 1];
					swap2 = new int[degree.length - 1];
					for (int j = 1; j < degree.length; j ++){
						swap1[j - 1] = coefficients[j];
						swap2[j - 1] = degree[j];
					}
					coefficients = swap1;
					degree = swap2;
				}
			}
			else if (line.substring(i, i + 1).equals("x")){
				int_buffer = 1;
				if (buffer.equals("") != true){
					double_buffer = Double.parseDouble(buffer);
					buffer = "";
				}
				exponent = true;
			}
			else {
				buffer = buffer + line.substring(i, i+1);
			}
		}
		}
		catch (Exception e){
			e.getStackTrace();
		}
	}

	public Polynomial trim(){
		int length = degree.length;
		int j = 0;
		double[] new_coef;
		int[] new_deg;
		for (int i = 0; i < degree.length; i ++){
			if (coefficients[i] == 0){
				length --;
			}
		}
		new_coef = new double[length];
		new_deg = new int[length];
		for (int i = 0; i < degree.length; i ++){
			if (coefficients[i] != 0){
				new_coef[j] = coefficients[i];
				new_deg[j] = degree[i];
				j ++;
			}
		}
		return new Polynomial(new_coef, new_deg);
	}
	
	public Polynomial add(Polynomial second){
		int length = 0;
		int k = 0;
		int i = 0;
		int j = 0; 
		while (k <= this.degree[this.degree.length - 1] || k <= second.degree[second.degree.length - 1]){
			if (i != this.degree.length){
				if (j != second.degree.length){
					if (this.degree[i] == k){
						length ++;
						i ++;
						if (second.degree[j] == k){
							j ++;
						}
					}
					else if (second.degree[j] == k){
						length ++;
						j ++;
					}
				}
				else {
					if (this.degree[i] == k){
						length ++;
						i ++;
					}
				}
			}
			else if (j != second.degree.length) {
				if (second.degree[j] == k) {
					length ++;
					j ++;
				}
			}
			k ++;
		}
		double sum[] = new double[length];
		int deg[] = new int[length];
		i = 0;
		j = 0;
		for (int c = 0; c < length; c++){
			if (i < this.degree.length) {
				if (j < second.degree.length) {
					if (this.degree[i] == second.degree[j]){
						sum[c] = this.coefficients[i] + second.coefficients[j];
						deg[c] = this.degree[i];
						i ++;
						j ++;
					}
					else if (this.degree[i] < second.degree[j]){
						sum[c] = this.coefficients[i];
						deg[c] = this.degree[i];
						i ++;
					}
					else {
						sum[c] = second.coefficients[j];
						deg[c] = second.degree[j];
						j ++;
					}
				}
				else {
					sum[c] = this.coefficients[i];
					deg[c] = this.degree[i];
					i ++;
				}
			}
			else if (j < second.degree.length) {
				sum[c] = second.coefficients[j];
				deg[c] = second.degree[j];
				j ++;
			}
			
		}
	Polynomial fin = new Polynomial(sum, deg);
	return fin.trim();
	}
	
	public double evaluate(double value){
		double sum = 0;
		for (int i = 0; i < coefficients.length; i++){
			sum = sum + coefficients[i] * Math.pow(value, degree[i]);
		}
		return sum;
	}
	
	public boolean hasRoot(double value){
		if (this.evaluate(value) == 0){
			return true;
		}
		return false;
	}

	public Polynomial multiply(Polynomial second){
		Polynomial product = new Polynomial();
		for (int i = 0; i < this.degree.length; i ++){
			double[] new_coef = second.coefficients;
			int[] new_deg = second.degree;
			for (int j = 0; j < second.degree.length; j ++){
				new_coef[j] = new_coef[j] * this.coefficients[i];
				new_deg[j] = new_deg[j] + this.degree[i];
			}
			product = product.add(new Polynomial(new_coef, new_deg));
		}
	return product;
	}

	public void saveToFile(String filename){
		try {
			FileWriter saver = new FileWriter(filename, false);
			String expression = "";
			for (int i = 0; i < degree.length; i ++){
				if (expression != "" && coefficients[i] > 0){
					expression = expression + "+";
				}
				expression = expression + coefficients[i];
				if (degree[i] > 0){
					expression = expression + "x";
					if (degree[i] > 1){
						expression = expression + degree[i];
					}
				}
			}
			saver.write(expression);
			saver.close();
		}
		catch (Exception e){
			e.getStackTrace();
		}
	}
}