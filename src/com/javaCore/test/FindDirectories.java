package com.javaCore.test;

import java.io.File;

public class FindDirectories {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// if no arguments provided, start at the parent directory
		if (args.length == 0)
			args = new String[] { ".." };
		try {
			File pathName = new File(args[0]);
			String[] fileNames = pathName.list();
			for (int i = 0; i < fileNames.length; i++) {
				// enumerate all files in the directory
				File f = new File(pathName.getPath(), fileNames[i]);
				// if the file is again a directory, call the main method recursively
				if (f.isDirectory()) {
					System.out.println(f.getCanonicalPath());
					main(new String[] { f.getPath() });
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
