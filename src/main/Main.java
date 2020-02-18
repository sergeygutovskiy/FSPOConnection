package main;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.*;
import org.json.simple.parser.*;

import main.Connection;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException{
    	String cookie = Connection.login(
    			"https://ifspo.ifmo.ru", 
    			"gutovskijsergejaleksandrovich", 
    			"pbaah8kc");
    	
//    	String body = Connection.getStudentPage("https://ifspo.ifmo.ru/profile", cookie);

    	String[] decoded_cookie = URLDecoder.decode(cookie).toString().split("s:");
    	String userIdDirty = decoded_cookie[decoded_cookie.length - 5].split(":")[1];
    	String userId = userIdDirty.substring(1, userIdDirty.length() - 2);
//    	System.out.println(user_id);
    	
    	Integer currentYear = LocalDate.now().getYear();
    	String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    	
    	JSONObject lessonsData;
    	lessonsData = Connection.getStudentLessons("https://ifspo.ifmo.ru/profile/getStudentLessonsVisits", 
    			userId, 
    			currentYear, 
    			currentMonth, 
    			cookie);

    	JSONArray lessons = (JSONArray) lessonsData.get("userlessons");
    	
    	Iterator lessonsIterator = (Iterator) lessons.iterator();
    	while (lessonsIterator.hasNext()) {
    		JSONObject lesson = (JSONObject) lessonsIterator.next();
    		System.out.println("Name: " + lesson.get("name"));
    		System.out.println("Semester: " + lesson.get("semester"));
    		System.out.println("ID: " + lesson.get("id"));
    	}
    }
}