package attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cli.command.Command;
import com.jsoniter.output.JsonStream;
import dto.ExamDTO;
import dto.PostBody;
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


	private AttendanceChecker() {
		isFinished = false;
	}

	public static AttendanceChecker getInstance() {
		return instance;
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

	// -----------------------------------------------------------------------

	public void addStudentByName(String firstName, String lastName) throws ProcessError{
		if(isCurrentExamFinished()){
			throw new ProcessError("there is no selected exam");
		}else{
			this.currentExam.addStudentByName(firstName,lastName);
		}
	}

	public void addStudentById(int id) throws ProcessError{
		if(isCurrentExamFinished()){
			throw new ProcessError("there is no selected exam");
		}else{
			this.currentExam.addStudentById(id);
		}

	}

	public void fetchExams() throws IOException,ProcessError {
		HttpGet httpGet = new HttpGet("http://142.93.134.194:8088/api/attendance");
		String total = this.extractGetData(httpGet);
		DayExams examsList = JsonIterator.deserialize(total, DayExams.class);

		if(this.todayExams == null){
			this.todayExams = examsList;
			this.todayExamList = new ArrayList<ExamAttendance>();
			for (ExamDTO exam : examsList.getClasses()){
				this.todayExamList.add(exam.getExamAttendance());
			}
		}
//		else if(this.todayExams.isExpired()){
		else if (this.todayExams.getDate().equals(examsList.getDate())){
			throw new ProcessError("today exams fetched before");
		}
		else {
			if(currentExam != null ){
				throw new ProcessError("you have one unfinished exam");
			}else{
				this.todayExams = examsList;
				this.todayExamList = new ArrayList<ExamAttendance>();
				for (ExamDTO exam : examsList.getClasses()){
					this.todayExamList.add(exam.getExamAttendance());
				}
			}
		}

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

	public void finishExam() throws ProcessError{
		if(isCurrentExamFinished()){
			throw new ProcessError("there is no selected exam yet");
		}
		else if(this.currentExam.isSigned() == false){
			throw new ProcessError("teacher should sign the exam first");
		}
		else{
//			System.out.println("attendent student num : " + Integer.toString(studentChecker.size()));

			String d = JsonStream.serialize(new PostBody(currentExam.getExamId(), true, this.currentExam.getAttendanceStringList()));
			System.out.println("log: data sended = " + d);
			post("http://142.93.134.194:8088/api/attendance", d);

			this.currentExam.finishExam();
			this.currentExam = null;
		}

	}

	public void finishProcess(){
		this.isFinished = true;
	}

	public void professorSign(int id) throws ProcessError {
		if(isCurrentExamFinished()){
			throw new ProcessError("no exam selected yet");
		}
		else{
			this.currentExam.professorSign(id);
		}
	}

	public void selectExam(int id) throws ProcessError {

		if(!isCurrentExamFinished()){
			throw new ProcessError("you have one unfinished exam");
		}

		ExamAttendance foundedExam = null;
		for (ExamAttendance exam : this.todayExamList){
			if (exam.getExamId() == id){
				foundedExam = exam;
				break;
			}
		}

		if (foundedExam == null){
			throw new ProcessError("exam id not found");
		}
		else if(foundedExam.isFinished()){
			throw new ProcessError("exam is finished");
		}
		else if(foundedExam.isExpired()){
			throw new ProcessError("exam time is expired");
		}
		else{
			this.currentExam = foundedExam;
		}
	}

	private boolean isCurrentExamFinished(){
		if (this.currentExam != null){
			return this.currentExam.isFinished();
		}
		else{
			return true;
		}
	}

	private DayExams todayExams;
	private boolean isFinished;
	private ArrayList<ExamAttendance> todayExamList;
	private ExamAttendance currentExam ;

//	private enum ProcessState {notFetched, fetched, selected, signed};
//	private ProcessState processState = ProcessState.notFetched;
}
