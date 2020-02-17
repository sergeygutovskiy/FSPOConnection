package main;

import java.io.IOException;
import main.Connection;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
    	String cookie = Connection.login(
    			"https://ifspo.ifmo.ru", 
    			"gutovskijsergejaleksandrovich", 
    			"pbaah8kc");
    	String body = Connection.getStudentPage("https://ifspo.ifmo.ru/profile", cookie);
    	
    	String buffer = "";
    	for (int i = 1; i < body.length(); i++) {
    		if (!(body.charAt(i) == ' ' && body.charAt(i - 1) == ' ')) {
    			buffer += body.charAt(i);
    		}
    	}
    	
    	System.out.println(body);
    }
}