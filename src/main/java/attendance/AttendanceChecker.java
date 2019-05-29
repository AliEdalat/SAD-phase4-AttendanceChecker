package attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cli.command.Command;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.jsoniter.JsonIterator;

import dto.DayExams;
import cli.*;

public class AttendanceChecker {
	
	private static final AttendanceChecker instance = new AttendanceChecker();
	private ArrayList<ExamAttendance> ExamAttendances;
	private boolean isFinished;
	
	private AttendanceChecker() {
		isFinished = false;
	}

	public static AttendanceChecker getInstance() {
		return instance;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	private String extractGetData(HttpGet httpGet) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(httpGet);
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line;
		StringBuilder total = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			total.append(line);
		}
		return total.toString();
	}

	public void fetchExams() throws IOException {
		HttpGet httpGet = new HttpGet("http://142.93.134.194:8088/api/attendance");
		String total = this.extractGetData(httpGet);
		DayExams examsList = JsonIterator.deserialize(total, DayExams.class);
//		this.todayExams = examsList.getExams();
//        ArrayList<String> list =  new ArrayList<>();
//        try {
//			list.add(todayExams.get(0).findStudenId("عماد", "جبار"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        String d = JsonStream.serialize(new PostBody(todayExams.get(0).getExamId(), true, list));
//        post("http://142.93.134.194:8088/api/attendance", d);
	}
	
	private void post(String completeUrl, String body) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(completeUrl);
	    httpPost.setHeader("Content-type", "application/json");
	    try {
	        StringEntity stringEntity = new StringEntity(body);
	        httpPost.getRequestLine();
	        httpPost.setEntity(stringEntity);

	        httpclient.execute(httpPost);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void run() {
		CLI cli = CLI.getInstance();
        while (!isFinished) {
            try{
                String[] splittedCommands = cli.parseCommand(cli.getCommand());
                Command command = cli.createCommand(splittedCommands);
                command.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
