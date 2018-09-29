package com.javaCore.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

class MatchCounter1 implements Callable<Integer>{
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
	/**
	 * Constructs a MatchCounter1.
	 * @param directroy the directory in which to start the search
	 * @param keyword the keyword to look for
	 * @param pool the thread pool for submitting subtasks
	 */
	public MatchCounter1(File directroy,String keyword,ExecutorService pool) {
		// TODO Auto-generated constructor stub
		this.directory=directroy;
		this.keyword=keyword;
		this.pool=pool;
	}
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count=0;
		try {
			File[] files=directory.listFiles();
			ArrayList<Future<Integer>> results=new ArrayList<Future<Integer>>();
			for(File file:files) {
				if(file.isDirectory()) {
					MatchCounter1 counter=new MatchCounter1(directory, keyword, pool);
					Future<Integer> result=pool.submit(counter);
					results.add(result);
				}else {
					if(search(file)) count++;
				}
			}
			for(Future<Integer> result:results) {
				try {
					count+=result.get();
				} catch (ExecutionException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		return count;
	}
	/**
	 * Searches a file for a given keyword.
	 * @param file the file to search
	 * @return true if the keyword is contained in the file
	 */
	public boolean search(File file) {
		try {
			Scanner in=new Scanner(new FileInputStream(file));
			boolean found=false;
			while(!found&&in.hasNextLine()) {
				String line=in.nextLine();
				if(line.contains(keyword)) found=true;
			}
			in.close();
			return found;
		} catch (IOException e) {
			// TODO: handle exception
			return false;
		}
	}
	
}
public class ThreadPoolTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		System.out.print("Enter base directory (e.g. /usr/local/jdk8.0/src): ");
		String directory=in.nextLine();
		System.out.print("Enter keyword (e.g. volatile): ");
		String keyword=in.nextLine();
		
		ExecutorService pool=Executors.newCachedThreadPool();
		MatchCounter1 counter=new MatchCounter1(new File(directory), keyword, pool);
		Future<Integer> result=pool.submit(counter);
		try {
			System.out.println(result.get()+" matching files.");
		} catch (ExecutionException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO: handle exception
		}
		pool.shutdown();
		int largestPoolSize=((ThreadPoolExecutor)pool).getLargestPoolSize();
		System.out.println("largest pool size="+largestPoolSize);
	}

}
