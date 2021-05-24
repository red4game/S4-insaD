import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Comparator;
import java.util.Locale;
import java.util.TreeMap;

public class Polynom_ElPainAuChocolat {
	public static final DecimalFormat FORMATTER = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
	private TreeMap<Integer, Double> coefficients;
	
	public Polynom_ElPainAuChocolat() {
		coefficients = new TreeMap<Integer, Double>(new Comparator<Integer> () {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i1-i2;
			}
		});
		coefficients.put(0, 0.0);
	}
	
	
	public double get(int i) {
		Double result = coefficients.get(i);
		if(result != null) {
			return result;
		}
		return 0;
	}
	
	public void set(int i, double val) {
		if((i > 0) && (val == 0)) {
			coefficients.remove(i);
		} else {
			coefficients.put(i, val);
		}
	}
	
	
	public int deg() {
		if((coefficients.lastKey() == 0) && (coefficients.get(0) == 0)) {
			return Integer.MIN_VALUE;
		}
		return coefficients.lastKey();
	}
	
	
	public Polynom_ElPainAuChocolat add(Polynom_ElPainAuChocolat p) {
		Polynom_ElPainAuChocolat result = new Polynom_ElPainAuChocolat();
		for(int i = 0; i <= Math.max(deg(), p.deg()); i++) {
			result.set(i, get(i) + p.get(i));
		}
		return result;
	}
	
	
	public Polynom_ElPainAuChocolat substract(Polynom_ElPainAuChocolat p) {
		Polynom_ElPainAuChocolat result = new Polynom_ElPainAuChocolat();
		for(int i = 0; i <= Math.max(deg(), p.deg()); i++) {
			result.set(i, get(i) - p.get(i));
		}
		return result;
	}
	
	
	public Polynom_ElPainAuChocolat mul(Polynom_ElPainAuChocolat p) {
		Polynom_ElPainAuChocolat result = new Polynom_ElPainAuChocolat();
		for(int i = 0; i <= deg() ; i++) {
			for(int j = 0; j <= deg(); j++) {
				result.set(i+j, result.get(i+j) + get(i) * p.get(j));
			}
		}
		return result;
	}
	
	
//	for(int i = 0; i <= k-1; i++) {
//		a1.set(i, get(i));
//		b1.set(i, p.get(i));
//	}
//	for(int i = k; i <= n; i++) {
//		a2.set(i-k, get(i));
//		b2.set(i-k, p.get(i));
//	}
	public Polynom_ElPainAuChocolat extract(int begin, int end) {
		Polynom_ElPainAuChocolat result = new Polynom_ElPainAuChocolat();
		for(int i = begin; i <= end; i++) {
			result.set(i-begin, get(i));
		}
		return result;
	}
	
	
	private void powerShift(Polynom_ElPainAuChocolat p, int shift) {
		for(int i = p.deg(); i >= 0; i--) {
			p.set(i + shift, p.get(i));
			p.set(i, 0);
		}
	}
	
	
	/*
	 * P = (a1 + a2*x^k)
	 * Q = (b1 + b2*x^k)
	 * P*Q = (a1 + a2*x^k)*(b1 + b2*x^k) = a1b1 + (a2b1 + a1b2)*x^k + a2b2*x^2k
	 * P*Q = a1b1 + ((a1 + a2)*(b1 + b2) - a1b1 + a2b2)*x^k + a2b2*x^2k
	 */
	public Polynom_ElPainAuChocolat mulK(Polynom_ElPainAuChocolat p) {
//		System.out.println("P(x) = " + this);
//		System.out.println("Q(x) = " + p);
		int n = Math.max(deg(), p.deg());
		if(n < 0) {
			return new Polynom_ElPainAuChocolat();
		}
		
		int k = n/2;
//		System.out.println("k = " + k);
		if(k == 0) {
			Polynom_ElPainAuChocolat result =  new Polynom_ElPainAuChocolat();
			result.set(0, get(0) * p.get(0));
			result.set(1, get(0) * p.get(1) + get(1) * p.get(0));
			result.set(2, get(1) * p.get(1));
			return result;
		}
		
		Polynom_ElPainAuChocolat a1 = extract(0, k-1);
		Polynom_ElPainAuChocolat a2 = extract(k, n);
		Polynom_ElPainAuChocolat b1 = p.extract(0, k-1);
		Polynom_ElPainAuChocolat b2 = p.extract(k, n);
//		System.out.println("a1(x) = " + a1);
//		System.out.println("a2(x) = " + a2);
//		System.out.println("b1(x) = " + b1);
//		System.out.println("b2(x) = " + b2);
//		System.out.println();
		
		Polynom_ElPainAuChocolat a1b1 = a1.mulK(b1);
		Polynom_ElPainAuChocolat a2b2 = a2.mulK(b2);
		Polynom_ElPainAuChocolat a1plusa2 = a1.add(a2);
		Polynom_ElPainAuChocolat b1plusb2 = b1.add(b2);
		Polynom_ElPainAuChocolat middleTerm = a1plusa2.mulK(b1plusb2).substract(a1b1).substract(a2b2);
		powerShift(middleTerm, k);
		powerShift(a2b2, 2*k);
		return a1b1.add(middleTerm).add(a2b2);
	}
	
	
	
	public String toString() {
		if(deg() >= 0) {
			String result = "";
			int i = 0;
			for(; i <= deg() && result.equals(""); i++) {
				if(get(i) != 0) {
					if(i == 0) {
						result += FORMATTER.format(get(i));
					} else {
						if(coefficients.get(i) == 1) {
							result += "x^" + i;
						} else if(get(i) == -1) {
							result += "-x^" + i;
						} else {
							result += FORMATTER.format(get(i)) + "x^" + i;
						}
					}
				}
			}
			for(; i <= deg(); i++) {
				if(get(i) > 0) {
					if(get(i) == 1) {
						result += " + x^" + i;
					} else {
						result += " + " + FORMATTER.format(get(i)) + "x^" + i;
					}
				} else if (get(i) < 0) {
					if(get(i) == -1) {
						result += " - x^" + i;
					} else {
						result += " - " + FORMATTER.format(-1 * get(i)) + "x^" + i;
					}
				}
			}
			return result;
		}
		return "0";
	}
	
	
	
	public static void main(String[] args) {
		Polynom_ElPainAuChocolat[] p = new Polynom_ElPainAuChocolat[7];
		p[0] = new Polynom_ElPainAuChocolat();
		p[1] = new Polynom_ElPainAuChocolat();
		p[1].set(0, 1);
		p[2] = new Polynom_ElPainAuChocolat();
		p[2].set(10, 1);
		p[3] = new Polynom_ElPainAuChocolat();
		p[3].set(2, 2.45);
		p[3].set(5, -9.6);
		p[3].set(6, 4);
		p[4] = new Polynom_ElPainAuChocolat();
		p[4].set(1, -1);
		p[4].set(2, -2.45);
		p[4].set(3, 40);
		p[4].set(3, 0);
		p[5] = new Polynom_ElPainAuChocolat();
		p[5].set(0, -1);
		p[5].set(5, -1);
		p[5].set(1, 3);
		p[5].set(2, -5);
		p[5].set(5, 0);
		p[6] = new Polynom_ElPainAuChocolat();
		p[6].set(0, -1);
		p[6].set(8, 7);
		p[6].set(0, 0);
		p[6].set(8, 0);
		
		for(int i = 0; i < p.length; i++) {
			System.out.println("P" + (i+1) + " = " + p[i] + " (deg(P" + (i+1) + ") = " + p[i].deg() + ")");
		}
		System.out.println("");
		
		System.out.println("P2 + P3 = " + p[1].add(p[2]));
		System.out.println("P4 + P5 = " + p[3].add(p[4]));
		System.out.println("");
		
		System.out.println("P3 * P4 = " + p[2].mul(p[3]));
		System.out.println("P5 * P6 = " + p[4].mul(p[5]));
		System.out.println("");
		
		System.out.println("P3 * P4 = " + p[2].mulK(p[3]));
		System.out.println("P5 * P6 = " + p[4].mulK(p[5]));
		System.out.println("");
//		System.out.println("⁰¹²³⁴⁵⁶⁷⁸⁹");
	}
	
}
