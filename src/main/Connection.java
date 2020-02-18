package main;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.*;
import org.json.simple.parser.*;


public class Connection {
	
	public static String login(String url, String name, String password) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create(url))
                .setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 OPR/66.0.3515.95")
                .POST(HttpRequest.BodyPublishers.ofString("User[login]=" + name + "&User[password]=" + password))   
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        
        Integer response_cookies_count = response.headers().map().get("set-cookie").size();
        String cookie = new String(response.headers().map().get("set-cookie").get(response_cookies_count - 1));			
		return cookie;
	}
	
//	public static String getStudentPage(String url, String cookie) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                .setHeader("Cookie", cookie)
//                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 OPR/66.0.3515.95")
//                .build();
//
//        HttpResponse<String> response = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
//
//        String responseBody = response.body();
//        return responseBody;
//	}
	
	public static JSONObject getStudentLessons(String url, String student_id, Integer year, String month, String cookie) throws IOException, InterruptedException, ParseException {
        HttpClient client = HttpClient.newHttpClient();
        
        url += "?stud=" + student_id + "&dateyear=" + year + "&datemonth=" + month;
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .setHeader("Cookie", cookie)
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 OPR/66.0.3515.95")
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        Object obj = new JSONParser().parse(response.body());
        JSONObject jo = (JSONObject) obj;
        
        return jo;
	}
}
