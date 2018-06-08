package cs341.algo.com;

import java.util.Arrays;

public class Algebra {
	/* Summary of Algebra nested classes:
	 * 		Number -		n-digit number, represented as a sequence of digits
	 * 
	 * Summary of algorithms on class Number:
	 * 		add 				-	Given two n-digit numbers, add them in theta(n)
	 * 		subtract			- 	Given two n-digit numbers, subtract them in theta(n)
	 * 		scalarMultiply	- 	Given a scalar and an n-digit numbers, multiply them in theta(n)
	 * 		multiply 		- 	Given two n-digit numbers, multiply them in theta(n^2)
	 * 		fastMultiply 	- 	Given two n-digit numbers, multiply them in theta(n^(log2(3))) using Karatsuba's algorithm
	 * 		shiftLeft 		- 	Given an n-digit numbers, left shift (multiply by base^n) in theta(n)
	 * 		update 			- 	Given an n-digit numbers, remove excess zeroes in theta(n)
	 * 		splitLeft		-	Given an index and an n-digit number, return the leftmost n digits
	 * 		splitRight		-	Given an index and an n-digit number, return the rightmost n digits
	 * 		equals			-	
	 */
	
	public static class Number {
		/* Stored from lowest index to highest index, eg. 123 = {3, 2, 1} */
		int[] digits;
		public int getNumDigits() { return digits.length; }
		public int[] getContents() { return digits; }
		
		public Number() {}
		public Number(int[] contents) { digits = contents.clone(); }
		
		@Override
		public String toString() { return digits.toString(); }
		
		/* Does not carry - intentional */
		public Number add(Number that) {
			Number result = new Number();
			int maxIndex = Math.max(this.getNumDigits(), that.getNumDigits());
			result.digits = new int[maxIndex];
			for (int i = 0; i < this.getNumDigits(); i++) {
				result.digits[i] = this.digits[i];
			}
			for (int i = 0; i < that.getNumDigits(); i++) {
				result.digits[i] += that.digits[i];
			}
			result = result.update();
			return result;
		}
		
		/* Does not carry - intentional */
		public Number subtract(Number that) {
			Number result = this.add(that.scalarMultiply(-1));
			return result;
		}
		
		public Number scalarMultiply(int n) {
			Number result = new Number();
			result.digits = this.digits.clone();
			for (int i = 0; i < result.getNumDigits(); i++) result.digits[i] *= n;
			return result;
		}
		
		public Number multiply(Number that) {
			Number result = new Number();
			result.digits = this.digits.clone();
			result = result.scalarMultiply(that.digits[0]);
			
			for (int i = 1; i < that.getNumDigits(); i++) {
				Number nextIndex = this.scalarMultiply(that.digits[i]);
				nextIndex = nextIndex.shiftRight(i);
				result = result.add(nextIndex);
			}
			return result.update();
			
		}
		
		public Number fastMultiply(Number that) {
			if ((this.getNumDigits() <= 2) || (this.getNumDigits() <= 0))
			if (this.getNumDigits() == 1) return that.scalarMultiply(this.digits[0]);
			if (that.getNumDigits() == 1) return this.scalarMultiply(that.digits[0]);
			
			int n = this.getNumDigits();
			int splitPoint = n / 2;
			Number thisLeft = this.splitLeft(splitPoint);
			Number thisRight = this.splitRight(splitPoint);
			Number thatLeft = that.splitLeft(splitPoint);
			Number thatRight = that.splitRight(splitPoint);
			
			Number PLL = thisLeft.fastMultiply(thatLeft);
			Number PRR = thisRight.fastMultiply(thatRight);
			Number thisLR = thisLeft.add(thisRight);
			Number thatLR = thatLeft.add(thatRight);
			Number PSUM = thisLR.fastMultiply(thatLR);
			
			Number leftMost = PLL;
			Number centre = PSUM;
			centre = centre.subtract(PLL);
			centre = centre.subtract(PRR);
			centre = centre.shiftRight(splitPoint);
			Number rightMost = PRR.shiftRight(n - (n%2));
			
			Number result = leftMost.add(centre).add(rightMost);
			return result;
			
		}
		
		public Number shiftRight(int shift) {
			Number result = new Number();
			if (this.digits != null) {
				int newMaxIndex = shift + this.getNumDigits();
				result.digits = new int[newMaxIndex];
				for (int i = 0; i < shift; i++) result.digits[i] = 0;
				for (int i = 0; i < this.getNumDigits(); i++) result.digits[i + shift] = this.digits[i];
			}
			return result;
		}
		
		public Number update() {
			if (this.digits == null) return new Number();
			if (this.digits[this.getNumDigits() - 1] != 0) return new Number(this.getContents().clone());
			
			int excessIndex = 0;
			int numDigits = this.getNumDigits();
			while (excessIndex < numDigits && this.digits[numDigits - excessIndex - 1] == 0) excessIndex++;
			
			int[] newDigits;
			if (excessIndex == numDigits) {
				newDigits = new int[] {0};
			} else {
				newDigits = new int[this.getNumDigits() - excessIndex];
				for (int i = 0; i < newDigits.length; i++) {
					newDigits[i] = this.digits[i];
				}
			}
			return new Number(newDigits);
		}
		
		public Number splitLeft(int n) {
			Number result = new Number();
			result.digits = new int[n];
			for (int i = 0; i < n; i++) result.digits[i] = this.digits[i];
			return result;
		}
		
		public Number splitRight(int n) {
			Number result = new Number();
			result.digits = new int[this.getNumDigits() - n];
			for (int i = 0; i < result.getNumDigits(); i++) result.digits[i] = this.digits[n + i];
			return result;
		}
		
		@Override
		public boolean equals(Object that) {
			if (that instanceof Number) {
				return Arrays.equals(this.update().digits, ((Number)that).update().digits);
			}
			return false;
		}
	}
}
