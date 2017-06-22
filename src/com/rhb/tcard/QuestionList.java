package com.rhb.tcard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.json.JSONObject;


public class QuestionList {

	private int pointer = 0;
	private String filter = "";
	private String[] filepaths = null;
	private String selectedFileName = null;
	private Set<String> keys = null;
	
	private List<Question> questions = null;
	
	
	public QuestionList(String[] filepaths, String selectedFileName, String filterRegx){
		this.filepaths = filepaths;
		this.selectedFileName = selectedFileName;
		setFilter(filterRegx);
		initKeys();

	}
	
	private void initKeys(){
		keys = new HashSet<String>();
		for(String filepath : filepaths){
			if(filepath.endsWith(".txt")){
				JSONObject jb = new JSONObject(FileUtil.readTextFile(filepath));
				String[] ss = jb.getString("key").split(",");
				for(String s : ss){
					keys.add(s);
				}
			}
		}		
	}
	
	private void initQuestions(){
		questions = new ArrayList<Question>();
		int i=0;
		for(String filepath : filepaths){
			if(!filepath.endsWith(".txt")){
				Question q = new Question(filepath);
				if(q.matcher(this.filter)){
					questions.add(q);
					if(filepath.endsWith(selectedFileName)){
						pointer = i;
					}
					i++;
				}
			}
		}
		if(this.pointer>questions.size()){
			this.pointer = 0;
		}
		
		
	}
	
	public Question current(){
		if(isEmpty()){
			return null;
		}else{
			return questions.get(pointer);
		}
	}
	
	public Question random(){
		if(isEmpty()){
			return null;
		}else{
			pointer = new Random().nextInt(questions.size());
			return questions.get(pointer);
		}
	}
	
	public Question next(){
		if(hasNext()){
			pointer ++;	
		}
		return questions.get(pointer);
	}
	
	public Question previous(){
		if(hasPrevious()){
			pointer --;			
		}
		return questions.get(pointer);
	}
	
	public boolean isEmpty(){
		return questions.size()==0;
	}
	
	public boolean hasPrevious(){
		boolean flag = false;
		if(pointer > 0 ){
			flag = true;
		}
		return flag;
	}
	
	public boolean hasNext(){
		boolean flag = false;
		if(pointer < (questions.size()-1)){
			flag = true;
		}
		return flag;
	}
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filterRegx) {
		this.filter = "";
		if(filterRegx!=null){
			String[] ss = filterRegx.split(",");
			for(int i=0; i<ss.length; i++){
				if(i>0){
					this.filter += "|";
				}
				this.filter += ss[i];
			}
		}
//		System.out.println("QuestionList.setFilter(): " + filterRegx);
//		System.out.println("QuestionList.filter: " + this.filter);
		initQuestions();
	}

	public Set<String> getKeys() {
		return keys;
	}
	
}
