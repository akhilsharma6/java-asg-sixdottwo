/* Assignment: 6.2 : Implement a class that checks whether a given number 
   is a prime using both the Thread class and Runnable interface.
   This assignment will help to master the following concepts:
	> Thread Class/Runnable interface and its Methods
	> Logic Building
*/

class PrimeChecker implements Runnable {
	
	boolean isPrime;	// Flag declared for prime number. If true, number is Prime or else not a prime.

	public void run() {	// Run method required for threads.
		synchronized(this){	// Synchronized required to keep a current thread take over process otherwise flag value will be overriden.
			this.isPrime = true;
			try{Thread.currentThread().sleep(100);} catch (Exception e){}	// To put some delay, added sleep of 100ms.
			for (int j = 2; j < Integer.parseInt(Thread.currentThread().getName()); j++){
				if ((Integer.parseInt(Thread.currentThread().getName()) % j) == 0){
					this.isPrime = false;
				}
			}		
			if ((!this.isPrime) | Integer.parseInt(Thread.currentThread().getName()) == 1 ){
				System.out.println("Number " + Thread.currentThread().getName() + " not a prime!");
			} else {
				System.out.println("Number " + Thread.currentThread().getName() + " ==============================> PRIME!");
			}
		}
	}

	public static void main(String[] args) {

		System.out.print("Enter the max counter: ");		// Enter number of counter i.e. threads required. 
		int num = Integer.parseInt(System.console().readLine());	// Receive number through console input.
		
		PrimeChecker pc = new PrimeChecker();	// Object pc of class PrimeChecker

		for (int i = 1; i <= num; i++){		// Loop as per num value
			Thread t = new Thread(pc);	// New threads created as per num value.
			t.setName("" + i);		// Setting name to thread. Since it only takes String so passing double quotes and then current counter.
			t.start();			// Start method to run thread.
		}
	}
}