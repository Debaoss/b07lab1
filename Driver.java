import java.io.File;
public class Driver { 
 public static void main(String [] args) { 
  Polynomial p = new Polynomial(); 
  System.out.println(p.evaluate(3)); 
  double [] c1 = {6,5}; 
  int [] d1 = {0,3};
  Polynomial p1 = new Polynomial(c1, d1); 
  double [] c2 = {-2,-9}; 
  int [] d2 = {1,4};
  Polynomial p2 = new Polynomial(c2, d2); 
  Polynomial s = p1.add(p2); 
  System.out.println("s(0.1) = " + s.evaluate(0.1)); 
  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 
  s.saveToFile("C:/Users/Username/Documents/CSCB07/Work/test.txt");
  File myFile = new File("C:/Users/Username/Documents/CSCB07/Work/test.txt");
  Polynomial red = new Polynomial(myFile);
  System.out.println("s(0.1) = " + red.evaluate(0.1)); 
  if(red.hasRoot(1)) 
	   System.out.println("1 is a root of s"); 
	  else 
	   System.out.println("1 is not a root of s"); 
  double [] c3 = {1, 1};
  int [] d3 = {0, 2};
  Polynomial p3 = new Polynomial(c3, d3);
  Polynomial t = p3.multiply(red);
  System.out.println("t(0.1) = " + t.evaluate(0.1));
  if(t.hasRoot(1))
   System.out.println("1 is a root of t");
  else
   System.out.println("1 is not a root of t");
  t.saveToFile("C:/Users/Username/Documents/CSCB07/Work/test.txt");
  }
} 