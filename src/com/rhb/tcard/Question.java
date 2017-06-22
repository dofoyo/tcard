package com.rhb.tcard;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * 
 * @author ran
 */


public class Question {
	private String id = null;
	private String imagePath = null;
	private String question = null;
	private String answer = null;
	private String key = null;
	
	public Question(String imagePath){
		JSONObject jb = new JSONObject(FileUtil.readTextFile(imagePath + ".txt"));
		this.question = jb.getString("question");
		this.answer = jb.getString("answer");
		this.key = jb.getString("key");
		this.imagePath = imagePath;
	}
	
	public boolean isRight(String text){
		String a = this.answer.trim().toLowerCase();
		String t = text.trim().toLowerCase();
		return a.equals(t);
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", imagePath=" + imagePath
				+ ", question=" + question + ", answer=" + answer + ", key="
				+ key + "]";
	}
	
	public boolean matcher(String str){
//		System.out.println(toString());

		boolean flag = true;
		if(str!=null && !str.isEmpty()){
			Pattern p=Pattern.compile(str);
			Matcher m=p.matcher(this.key);
			flag = m.find();
		}
//		System.out.println("filter: " + str);
//		System.out.println("matcher: " + flag);
		return flag;
	}
	
}
