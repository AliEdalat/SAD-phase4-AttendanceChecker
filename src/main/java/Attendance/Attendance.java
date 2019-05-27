package Attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.jsoniter.JsonIterator;

import cli.*;
import cli.command.*;
import dto.DayExams;
import exam.Exam;
import exam.ExamAttendance;

public class Attendance {
	
	private static final Attendance instance = new Attendance(); 
	private ArrayList<Exam> todayExams;
	private ArrayList<ExamAttendance> todayExamAttendances;
	private boolean isFinished;
	
	private Attendance() {
		isFinished = false;
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
	
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	public static Attendance getInstance() {
		return instance;
	}
	
	public void fetchExams() throws IOException {
		HttpGet httpGet = new HttpGet("http://142.93.134.194:8088/api/attendance");
        String total = this.extractGetData(httpGet);
        DayExams examsList = JsonIterator.deserialize(total, DayExams.class);
        this.todayExams = examsList.getExams();
	}
	
	public void run() {
		CLI cli = CLI.getInstance();
        while (!isFinished) {
            try{
                String command = cli.getCommand();
                String[] splittedCommands = cli.parseCommand(command);
                Command commandObject = cli.createCommand(splittedCommands);
                commandObject.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
}
