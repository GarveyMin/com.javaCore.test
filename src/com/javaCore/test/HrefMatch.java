package com.javaCore.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HrefMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// get URL string from command line or use default
			String urlString;
			if(args.length>0) urlString=args[0];
			else urlString="https://www.oracle.com/technetwork/java/index.html";
			// open reader for URL
			InputStreamReader in=new InputStreamReader(new URL(urlString).openStream());
			// read contents into string builder
			StringBuilder input=new StringBuilder();
			int ch;
			while((ch=in.read())!=-1) {
				input.append((char)ch);
			}
			// search for all occurrences of pattern
			String patternString="<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
			Pattern pattern=Pattern.compile(patternString,Pattern.CASE_INSENSITIVE);
			Matcher matcher=pattern.matcher(input);
			while(matcher.find()) {
				int start=matcher.start();
				int end=matcher.end();
				String match=input.substring(start-end);
				System.out.println(match);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (PatternSyntaxException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
