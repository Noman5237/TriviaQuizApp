package android.example.triviaquiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Question {
	
	private String question;
	private String correctOption;
	private String[] incorrectOptions;
	private int checked = -1;
	
	public Question(JSONObject questionJSON) {
		try {
			String question = questionJSON.getString("question");
			String correctOption = questionJSON.getString("correct_answer");
			JSONArray incorrectOptionsJson = questionJSON.getJSONArray("incorrect_answers");
			String[] incorrectOptions = new String[3];
			for (int i = 0; i < 3; i++) {
				incorrectOptions[i] = incorrectOptionsJson.getString(i);
			}
			
			this.question = question.replaceAll("&quot;", "'");
			this.correctOption = correctOption;
			this.incorrectOptions = incorrectOptions;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public Question(String question, String correctOption, String[] incorrectOptions) {
		this.question = question;
		this.correctOption = correctOption;
		this.incorrectOptions = incorrectOptions;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getCorrectOption() {
		return correctOption;
	}
	
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	
	public String[] getIncorrectOptions() {
		return incorrectOptions;
	}
	
	public void setIncorrectOptions(String[] incorrectOptions) {
		this.incorrectOptions = incorrectOptions;
	}
	
	@Override
	public String toString() {
		return "Question{" +
				"question='" + question + '\'' +
				", correctOption='" + correctOption + '\'' +
				", incorrectOptions=" + Arrays.toString(incorrectOptions) +
				'}';
	}
	
	public int checkedIndex() {
		return checked;
	}
	
	public void setChecked(int checked) {
		if (this.checked == -1) {
			QuizActivity.incrementCounter();
		}
		this.checked = checked;
	}
}
