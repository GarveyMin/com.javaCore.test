package com.javaCore.test;

import java.awt.GraphicsEnvironment;

public class ListFonts {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		for(String fontName:fontNames) {
			System.out.println(fontName);
		}
	}

}
