package cs341.algo.com;

public class Fibonacci {
	/* Define the Fibonacci sequence as 0, 1, 1, 2, 3, 5, 8, 15 ...
	 * Fib(0) = 0
	 * Fib(1) = 1
	 * Fib(n, n > 1) = Fib(n - 1) + Fib(n - 2)
	 */
	
	/* Exponential recursive */
	public static int Fib1(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		return Fib1(n - 1) + Fib1(n - 2);
	}
	
	/* Linear-time linear-space iterative */
	public static int Fib2(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;
		
		int[] fibonacciValues = new int[n + 1];
		fibonacciValues[0] = 0;
		fibonacciValues[1] = 1;
		for (int i = 2; i <= n; i++) {
			fibonacciValues[i] = fibonacciValues[i - 1] + fibonacciValues[i - 2];
		}
		return fibonacciValues[n];
	}
	
	/* Linear-time constant-space iterative */
	public static int Fib3(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;

		int term = 1;
		int currentFib = 1;
		int prior1Fib = 0;
		int prior2Fib = 0;
		
		while (term < n) {
			prior2Fib = prior1Fib;
			prior1Fib = currentFib;
			currentFib = prior2Fib + prior1Fib;
			term++;
		}
		return currentFib;
	}
	
	/* Constant */
	public static int Fib4(int n) {
		double goldenRatio = (1 + Math.sqrt(5)) / 2;
		double result = (Math.pow(goldenRatio, n) / Math.sqrt(5)) + 0.5;
		return (int)result;
	}
}
