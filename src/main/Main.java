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
    	 * Отправляем запрос с формой для входа в аккаунт.
    	 * При удачном входе получаем куки, которая будет использоваться для дальнейших запросов 
    	 */
    	String cookie = Connection.login(
    			"https://ifspo.ifmo.ru", 
    			"gutovskijsergejaleksandrovich", 
    			"pbaah8kc");
    	
//    	String body = Connection.getStudentPage("https://ifspo.ifmo.ru/profile", cookie);

    	// Разделяем куки по "s:" 
    	String[] decoded_cookie = URLDecoder.decode(cookie).toString().split("s:");
    	
    	// По индексу <decoded_cookie.length - 5> находится строка <7:"1000557";>
    	// Берем часть после ':' и получаем <"1000557";>
    	String userIdDirty = decoded_cookie[decoded_cookie.length - 5].split(":")[1];
    	
    	// Обрезаем из строки <"1000557";> первый и два последних символа 
    	// На выходе получаем 1000557
    	String userId = userIdDirty.substring(1, userIdDirty.length() - 2);
    	
    	// получаем год в виде <xxxx> и месяц в виде <xx>
    	Integer currentYear = LocalDate.now().getYear();
    	String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    	
    	/* 
    	 * lessonsData - строка с первым json файлом, в котором есть 4 главных свойства:
    	 * [ExercisesVisits, Exercises, lessonteachers, userlessons] <- System.out.println(lessonsData.keySet());
    	 * Куки отправляется для подтверждения входа в аккаунт
    	 */
    	JSONObject lessonsData;
    	lessonsData = Connection.getStudentLessons("https://ifspo.ifmo.ru/profile/getStudentLessonsVisits", 
    			userId, 
    			currentYear, 
    			currentMonth, 
    			cookie);
    	/*
    	 * Первое свойтсво - userlessons (массив)
    	 * Это список существующих дисциплин (просто названия предметов)
    	 * Элементы массива имеют следущую структуру:
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
    	 * Второе свойтсво - Exercises (видимо это описание пар, на которых ты был?)
    	 * Имеет свойста по типу <"97": [...], "123": [...]>
    	 * То есть его свойтсва - это id предметов (из userlessons)
    	 * Эти свойства - массивы, элементы массива имеют следущую структуру:
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
    	 * Третье свойство - lessonteachers (просто учителя)
    	 */
    	JSONObject teachers = (JSONObject) lessonsData.get("lessonteachers");
    	JSONObject teacher = (JSONObject) teachers.get("27");
//    	
//		System.out.println("Firstname: " + teacher.get("firstname"));
//		System.out.println("lastname: " + teacher.get("lastname"));
//		System.out.println("Middlename: " + teacher.get("middlename"));
//		System.out.println("ID: " + teacher.get("id"));
    	

    	/*
    	 * Четвертое свойство, последнее, - ExercisesVisits
    	 * Это оценки, но они очень сложно структурированы
    	 * Так что пока не до конца понимаю, как они работают 
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