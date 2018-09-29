package com.javaCore.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*
 * This task enumerates all files in a directory and its subdirectories.
 */
class FileEnumerationTask implements Runnable{
	
	public static File DUMMY=new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;
	/**
	 * Constructs a FileEnumerationTask.
	 * @param queue the blocking queue to which the enumerated files are added
	 * @param startingDirectory the directory in which to start the enumeration
	 */
	public FileEnumerationTask(BlockingQueue<File> queue,File startingDirectory) {
		// TODO Auto-generated constructor stub
		this.queue=queue;
		this.startingDirectory=startingDirectory;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			enumrate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	/**
	 * Recursively enumerates all files in a given directory and its subdirectories
	 * @param directory the directory in which to start
	 * @throws InterruptedException
	 */
	public void enumrate(File directory) throws InterruptedException{
		File[] files=directory.listFiles();
		for(File file:files) {
			if(file.isDirectory())
				enumrate(file);
			else queue.put(file);
		}
	}
}
/*
 * This task searches files for a given keyword.
 */
class SearchTask implements Runnable{
	private BlockingQueue<File> queue;
	private String keyword;
	/**
	 * Constructs a SearchTask.
	 * @param queue the queue from which to take files
	 * @param keyword the keyword to look for
	 */
	public SearchTask(BlockingQueue<File> queue,String keyword) {
		// TODO Auto-generated constructor stub
		this.queue=queue;
		this.keyword=keyword;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			boolean done=false;
			while(!done) {
				File file=queue.take();
				if(file==FileEnumerationTask.DUMMY) {
					queue.put(file);
					done=true;
				}else {
					search(file);
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	/**
	 * Searches a file for a given keyword and prints all matching lines.
	 * @param file the file to search
	 * @throws IOException
	 */
	public void search(File file) throws IOException{
		Scanner  in=new Scanner(new FileInputStream(file));
		int lineNumber=0;
		while(in.hasNextLine()) {
			lineNumber++;
			String line=in.nextLine();
			if(line.contains(keyword)) {
				System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
			}
		}
		in.close();
	}
}
public class BlockingQueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		System.out.print("Enter base directory(e.g. /usr/local/jdk1.8.0/src): ");
		String directort=in.nextLine();
		System.out.println("Enter keyword (e.g. volatile): ");
		String keyword=in.nextLine();
		
		final int FILE_QUEUE_SIZE=10;
		final int SERACH_THREADS=100;
		BlockingQueue<File> queue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		FileEnumerationTask enumerator=new FileEnumerationTask(queue, new File(directort));
		new Thread(enumerator).start();
		for(int i=1;i<=SERACH_THREADS;i++)
			new Thread(new SearchTask(queue, keyword)).start();
	}

}
