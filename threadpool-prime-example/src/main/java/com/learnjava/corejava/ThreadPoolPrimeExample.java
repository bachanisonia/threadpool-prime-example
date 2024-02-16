package com.learnjava.corejava;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolPrimeExample {

	public static void main(String[] args) {
		
		//This does not give you specific methods of the Scheduled Executor 
		//ExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
		
		//To access methods specific to Thread pool
		//ExecutorService executorService = Executors.newFixedThreadPool(3);
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		//ExecutorService executorService = Executors.newCachedThreadPool();
		//ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		
		ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
		
		Runnable rReport = () -> {
			System.out.println("Running the report...");
			System.out.println("\n Active Threads : " + executorService.getActiveCount());
			System.out.println("\n Completed Threads : " + executorService.getCompletedTaskCount());
		};
		
		scheduledExecutor.scheduleWithFixedDelay(rReport, 1, 5, TimeUnit.SECONDS);
		
		
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("\n I can tell you the nth prime number. Enter n: ");
			
			int n = sc.nextInt();
			if (n==0) {
				try {
					executorService.awaitTermination(1, TimeUnit.SECONDS);
					scheduledExecutor.shutdown();
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("Interrupted ! Leaving...");
				}
				break;
			}
			
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					
					int number = PrimeNumberUtil.calculatePrime(n);
					System.out.println("\n Value of the " + n + "th prime number is : " +  number);
					
				}
			};	
			
			executorService.execute(r);

		}

	}
}


class PrimeNumberUtil {
	
	private static int outputNo;
	
	public static int calculatePrime(int inputNo) {
		
		int numOfPrimesFound = 0;
		int i;
		int number = 1;
		
		while (numOfPrimesFound < inputNo) {
			
			number++;
			
			for (i=2; i <= number && number%i != 0; i++) {
			}
			
			if (i == number) {
				numOfPrimesFound++;
			}
			
		}
		
		return number;
		
	}
	
}