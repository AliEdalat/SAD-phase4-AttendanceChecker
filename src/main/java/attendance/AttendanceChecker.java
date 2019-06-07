package attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cli.command.Command;
import com.jsoniter.output.JsonStream;

import cache.Cache;
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
		cache = new Cache();
	}

	public static AttendanceChecker getInstance() {
		return instance;
	}

	public Boolean isEmptyExamsList(){
		return todayExamList == null;
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
	
	private void post(String completeUrl, String body) throws ProcessError, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost(completeUrl);
	    httpPost.setHeader("Content-type", "application/json");
	    try {
	        StringEntity stringEntity = new StringEntity(body);
	        httpPost.getRequestLine();
	        httpPost.setEntity(stringEntity);

	        httpclient.execute(httpPost);
	    } catch (Exception e) {
	    	cache.put(body);
	        throw new ProcessError("connection error: we send requst with this body later.\n\tbody:  " + body);
	    }
	}
	
	public void run() {
		CLI cli = CLI.getInstance();
        while (!isFinished) {
            try{
            	showHelpInformation();
                String[] splittedCommands = cli.parseCommand(cli.getCommand());
                Command command = cli.createCommand(splittedCommands);
                command.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
	
	private void showHelpInformation() {
		CLI cli = CLI.getInstance();
		cli.showAllComands();
		if(processState == ProcessState.notFetched) {
			cli.showFetchCommand();
		} else if(processState == ProcessState.fetched) {
			cli.showExamsComand(todayExamList);
		} else if(processState == ProcessState.selected) {
			cli.showStudentsCommand(currentExam);
		} else if (processState == ProcessState.signed) {
			cli.showFinishExamComand();
		}
	}

	public void showStudentInfo(int id) throws ProcessError{
		if(isCurrentExamFinished()){
			throw new ProcessError("There is no selected exam!");
		}else{
			this.currentExam.showStudentInfo(id);
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
		processState = ProcessState.fetched;
	}

	public void finishExam() throws ProcessError{
		if(isCurrentExamFinished()){
			throw new ProcessError("there is no selected exam yet");
		}
		else if(this.currentExam.isSigned() == false){
			throw new ProcessError("teacher should sign the exam first");
		}
		else{
			String d = JsonStream.serialize(new PostBody(currentExam.getExamId(), true, this.currentExam.getAttendanceStringList()));
			try {
				post("http://142.93.134.194:8088/ap12i/attendance", d);
				this.currentExam.finishExam();
				processState = ProcessState.fetched;
				this.currentExam = null;
				sendCacheData();
			} catch (IOException e) {
				System.out.println("*try to send data again.");
			} catch(ProcessError pe) {
				this.currentExam.finishExam();
				processState = ProcessState.fetched;
				this.currentExam = null;
				throw new ProcessError(pe.toString());
			}
		}

	}
	
	private void sendCacheData() throws IOException {
		int index = 0;
		ArrayList<String> dataset = cache.get();
		try {
			for(String item : dataset) {
				post("http://142.93.134.194:8088/api/attendance", item);
				System.out.println(item);
				index++;
			}
		} catch (Exception e) {
			ArrayList<String> set = new ArrayList<>();
			for (int i = index; i < dataset.size(); i++) {
				set.add(dataset.get(i));
			}
			cache.put(set);
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
			processState = ProcessState.signed;
		}
	}

	public void selectExam(int id) throws ProcessError {

		if(!isCurrentExamFinished()){
			throw new ProcessError("you have an unfinished exam!");
		}

		ExamAttendance foundedExam = null;
		for (ExamAttendance exam : this.todayExamList){
			if (exam.getExamId() == id){
				foundedExam = exam;
				break;
			}
		}

		if (foundedExam == null){
			throw new ProcessError("exam id not found!");
		}
		else if(foundedExam.isFinished()){
			throw new ProcessError("exam is finished!");
		}
		else if(foundedExam.isExpired()){
			throw new ProcessError("exam time is expired!");
		}
		else{
			this.currentExam = foundedExam;
			processState = ProcessState.selected;
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

	public void acceptStudentAttendance(int id) throws ProcessError {
		if(isCurrentExamFinished()){
			throw new ProcessError("There is no selected exam!");
		}else{
			this.currentExam.addStudentById(id);
		}
	}

	private DayExams todayExams;
	private boolean isFinished;
	private ArrayList<ExamAttendance> todayExamList;
	private ExamAttendance currentExam ;
	private Cache cache;
	private enum ProcessState {notFetched, fetched, selected, signed};
	private ProcessState processState = ProcessState.notFetched;
}
