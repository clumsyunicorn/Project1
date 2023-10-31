import java.util.ArrayList;
import java.lang.Math;
public class StatsLibrary {
	
	//Mean  Method
	public static double findMean(ArrayList<Double> userInputNumbers){
		double result = 0;
		double sum = 0;

		for(double singleElement : userInputNumbers){
				sum = sum + singleElement;
		}
			result = sum / userInputNumbers.size();
			return result;
	}
	
	//Median
	public static double findMedian(ArrayList<Double> userInputNumbers) {
		double result = 0;
		double size = userInputNumbers.size();
		boolean ordered;
		double x = 0;
		for(int i = 0; i < size - 1; i++) {
			ordered = false;
			for(int j = 0; j < size - i - 1; j++) {
				
				if(userInputNumbers.get(j) > userInputNumbers.get(j+1)) {
					x = userInputNumbers.get(j);
					userInputNumbers.set(j, userInputNumbers.get(j+1));
					userInputNumbers.set(j+1, x);
					ordered = true;
				}
			}
			
			if(!ordered) {
				break;
			}
		}
			
			
		//check order
		System.out.println(userInputNumbers);
		
		if (size %2 == 0) {
			//even
			double m1 = (double)userInputNumbers.get((int) ((size/2.0)-1.0));
			double m2 = (double)userInputNumbers.get((int) ((size/2.0)));
			result = (m1 + m2)/2;
			
			//result = (userInputNumbers.get((int)(Math.round(userInputNumbers.get((int) ((size/2.0)-1.0))))) + userInputNumbers.get((int)Math.floor(userInputNumbers.get((int) ((size/2.0) / 2)))));
		} else {
			//odd
			result = userInputNumbers.get((int) (size/2));
		}
	return result;
	}
	
	//I used bubble sort as inspiration https://www.geeksforgeeks.org/bubble-sort/
	public static double findMode(ArrayList<Double> userInputNumbers) {
		double result = 0;
		double max = 0;
		double temp = 0;
		int j = 0;
		int size = userInputNumbers.size();
			
		for (double element: userInputNumbers) {
			
			for(int i = 0; i < size; i++) {
				if(element == userInputNumbers.get(i)) {
					temp++;
				}
			}
			if (temp > max) {
				max = temp;
				result = element;
			}
			temp = 0;
		}
	return result;
	}
	
	//Inspired by https://www.geeksforgeeks.org/java-program-to-calculate-standard-deviation/
	public static double findSTD(ArrayList<Double> userInputNumbers) {
		
		//Mean
		double std = 0;
		int total = userInputNumbers.size();
		double mean = findMean(userInputNumbers);
		
		//Difference of the squares
//		double[] sqrDif = new double[total];
        for (int i = 0; i < total; i++) {
            std = std + Math.pow(userInputNumbers.get(i) - mean, 2);
        }
        
        //Average the differences
		std = Math.sqrt(std/total);
		return std;
	}
	
	//union
	public ArrayList<String> findUnion(ArrayList<String> A, ArrayList<String> B) {
		ArrayList<String> union = new ArrayList<>();
		boolean repeat = false;
		
		//iterate through lists and compare elements
		for (String element: A) {
			union.add(element);
		}
		
		for (String element: B) {
			for (int i = 0; i < union.size(); i++) {
				if (union.get(i) == element) {
					repeat = true;
					break;
				}
			}
			
			//check if element exists already
			if (!repeat) {
				union.add(element);
			}
		}		
		
		//check if exclusive
		if (union.isEmpty()){
			
			union.add("A and B are Mutually Exclusive");
		}
				
		return union;
	}
	
	//intersection
	public ArrayList<String> findInter(ArrayList<String> A, ArrayList<String> B) {
		
		ArrayList<String> inter = new ArrayList<>();
		
		//add all elements that are in both A AND B
		for (String elementA: A) {
			for (String elementB: B) {
				if (elementA == elementB) {
					inter.add(elementA);
				}
			}
		}
		
		return inter;
	}
	
	//complement
	public ArrayList<String> findComp(ArrayList<String> list, ArrayList<String> A) {
		
		//remove all elements that are in A
		for (String element: A) {
			for (int i = 0; i < list.size(); i++) {
				if(element == list.get(i)) {
					list.remove(i);
				}			
			}
		}
		
		return list;
	}	
	
	//factorial
	public static Integer factorial(int n) {
		int result = 1;
		
		//0! = 1
		if (n == 0){
			return result;
		}
		
		for (int i = 1; i <= n; i++) {
			result *= i; 
		}
		
		return result;
	}
	
	//Conditional Probability
	public static double conditional(double A, double B) {
		//Calculate joint probability
		double result = (A*B)/B;
		
		return result;
	}
	
	public static double bayes(double A, double B) {
		
		double result = ((conditional(B,A))*(A))/B;
		
		return result;
	}
	
	//Determine Independence
	public static boolean independence(double A, double B) {
		
		if (conditional(A, B) == A) {
			return true;
		} else if (conditional(B, A) == B) {
			return true;
		} 
		
		return false;
	}
	
	//Combinations -- order doesn't matter
	public static double combination(int n, int y) {
		
		double result = (factorial(n))/((factorial(y))*(factorial(n-y)));
		
		return result;	
	}
	
	//Permutations -- order matters 
	public static double permutation(int n, int y) {
		
		double result = factorial(n)/factorial((n-y));
		
		return result;	
	}
	
	//Binomial Distributions
	public static double binomialDistribution(int n, int y, double p){
		
		double result = (combination(n, y)) * (Math.pow(p, y)) * (Math.pow((1-p), (n-y)));
		
		return result;
		
	}
	
	//Geometric Distributions
	public static double geometricDistribution(int y, double p) {
		
		double result = (Math.pow((1-p), (y-1))) * p;
		
		return result;
	}
	
	//Negative Binomial Distributions
	public static double negativeBinomialDistribution(int y, int r, double p) {
		
		double result = (combination((y-1), (r-1)))*(Math.pow(p, r))*(Math.pow((1-p), (y-r)));
		
		return result;
	}
	
	//Hyper Geometric Distribution
	public static double hyperGeometricDistribution(int n, int y, int r, int N) {
		
		double result = ((combination(r, y)) * (combination((N - r), (n - y))))/ combination(N, n);
		
		return result;
	}
	
	//Poisson Probability Distribution
	public static double poisson (int y, int m) {
		
		double result = ((Math.pow(m, y)) * (Math.pow(2.71828, m)))/ factorial(y);
		
		return result;
	}
	
	//Chevychev
	public static double chevychev() {
		
		return 0;
	}
}
