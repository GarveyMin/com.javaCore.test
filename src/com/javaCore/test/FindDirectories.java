package com.javaCore.test;

import java.io.File;

public class FindDirectories {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0)
			args = new String[] { ".." };
		try {
			File pathName = new File(args[0]);
			String[] fileNames = pathName.list();
			for (int i = 0; i < fileNames.length; i++) {
				File f = new File(pathName.getPath(), fileNames[i]);
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
