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
    	/*
    	 * ���������� ������ � ������ ��� ����� � �������.
    	 * ��� ������� ����� �������� ����, ������� ����� �������������� ��� ���������� �������� 
    	 */
    	String cookie = Connection.login(
    			"https://ifspo.ifmo.ru", 
    			"gutovskijsergejaleksandrovich", 
    			"pbaah8kc");
    	
//    	String body = Connection.getStudentPage("https://ifspo.ifmo.ru/profile", cookie);

    	// ��������� ���� �� "s:" 
    	String[] decoded_cookie = URLDecoder.decode(cookie).toString().split("s:");
    	
    	// �� ������� <decoded_cookie.length - 5> ��������� ������ <7:"1000557";>
    	// ����� ����� ����� ':' � �������� <"1000557";>
    	String userIdDirty = decoded_cookie[decoded_cookie.length - 5].split(":")[1];
    	
    	// �������� �� ������ <"1000557";> ������ � ��� ��������� ������� 
    	// �� ������ �������� 1000557
    	String userId = userIdDirty.substring(1, userIdDirty.length() - 2);
    	
    	// �������� ��� � ���� <xxxx> � ����� � ���� <xx>
    	Integer currentYear = LocalDate.now().getYear();
    	String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    	
    	/* 
    	 * lessonsData - ������ � ������ json ������, � ������� ���� 4 ������� ��������:
    	 * [ExercisesVisits, Exercises, lessonteachers, userlessons] <- System.out.println(lessonsData.keySet());
    	 * ���� ������������ ��� ������������� ����� � �������
    	 */
    	JSONObject lessonsData;
    	lessonsData = Connection.getStudentLessons("https://ifspo.ifmo.ru/profile/getStudentLessonsVisits", 
    			userId, 
    			currentYear, 
    			currentMonth, 
    			cookie);
    	/*
    	 * ������ �������� - userlessons (������)
    	 * ��� ������ ������������ ��������� (������ �������� ���������)
    	 * �������� ������� ����� �������� ���������:
    	 * <name>, <id>, <semester>
    	 */
    	JSONArray lessons = (JSONArray) lessonsData.get("userlessons");
//    	
//    	Iterator lessonsIterator = (Iterator) lessons.iterator();
//    	while (lessonsIterator.hasNext()) {
//    		JSONObject lesson = (JSONObject) lessonsIterator.next();
//    		System.out.println("Name: " + lesson.get("name"));
//    		System.out.println("Semester: " + lesson.get("semester"));
//    		System.out.println("ID: " + lesson.get("id"));
//    	}
    	
    	/*
    	 * ������ �������� - Exercises (������ ��� �������� ���, �� ������� �� ���?)
    	 * ����� ������� �� ���� <"97": [...], "123": [...]>
    	 * �� ���� ��� �������� - ��� id ��������� (�� userlessons)
    	 * ��� �������� - �������, �������� ������� ����� �������� ���������:
    	 * <topic>, <id>, <type>, <time>, <timeday>, <day> 
    	 */
    	JSONObject visits = (JSONObject) lessonsData.get("Exercises");
    	JSONArray lessonVisits = (JSONArray) visits.get("96");
//    	
//    	Iterator lessonVisitsIterator = (Iterator) lessonVisits.iterator();
//    	while (lessonVisitsIterator.hasNext()) {
//    		JSONObject lesson = (JSONObject) lessonVisitsIterator.next();
//    		System.out.println("Topic: " + lesson.get("topic"));
//    		System.out.println("ID: " + lesson.get("id"));
//    		System.out.println("Type: " + lesson.get("type"));
//    		System.out.println("Time: " + lesson.get("time"));
//    		System.out.println("TimeDay: " + lesson.get("timeday"));
//    		System.out.println("Day: " + lesson.get("day"));
//    	}
    	
    	/*
    	 * ������ �������� - lessonteachers (������ �������)
    	 */
    	JSONObject teachers = (JSONObject) lessonsData.get("lessonteachers");
    	JSONObject teacher = (JSONObject) teachers.get("27");
//    	
//		System.out.println("Firstname: " + teacher.get("firstname"));
//		System.out.println("lastname: " + teacher.get("lastname"));
//		System.out.println("Middlename: " + teacher.get("middlename"));
//		System.out.println("ID: " + teacher.get("id"));
    	

    	/*
    	 * ��������� ��������, ���������, - ExercisesVisits
    	 * ��� ������, �� ��� ����� ������ ���������������
    	 * ��� ��� ���� �� �� ����� �������, ��� ��� �������� 
    	 */
    	JSONObject marks = (JSONObject) lessonsData.get("ExercisesVisits");
    	JSONObject lessonMarks = (JSONObject) marks.get("33");
    	JSONArray vistMarks = (JSONArray) lessonMarks.get("133087");

//    	Iterator vistMarksIterator = (Iterator) vistMarks.iterator();
//    	while (vistMarksIterator.hasNext()) {
//    		JSONObject lesson = (JSONObject) vistMarksIterator.next();
//    		System.out.println("Mark: " + lesson.get("point"));
//    		System.out.println("Delay: " + lesson.get("delay"));
//    		System.out.println("Performance: " + lesson.get("performance"));
//    		System.out.println("Mark need: " + lesson.get("mark_need"));
//    		System.out.println("ID: " + lesson.get("id"));
//    		System.out.println("Visit need: " + lesson.get("visit_need"));
//    		System.out.println("Presence: " + lesson.get("presence"));
//    	}
    }
}