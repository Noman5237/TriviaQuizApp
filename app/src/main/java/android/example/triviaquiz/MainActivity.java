package android.example.triviaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	
	private Button dynamicQuizButton;
	private Button mathQuizButton;
	public static final ArrayList<Question> questions = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Initializing Buttons
		dynamicQuizButton = findViewById(R.id.dynamicQuiz);
		mathQuizButton = findViewById(R.id.mathQuiz);
		
		//
		final Intent switchToQuiz = new Intent(getApplicationContext(), QuizActivity.class);
		
		dynamicQuizButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				questions.clear();
				startActivity(switchToQuiz);
				
			}
		});
		
		mathQuizButton.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				questions.clear();
				String staticData = "{questions = [\n" +
						"  {\n" +
						"    \"question\": \"What is the form of ideal quadratic equation?\",\n" +
						"    \"correct_answer\": \"\\\\(ax^2 + bx + c = 0\\\\)\",\n" +
						"    \"incorrect_answers\": [\n" +
						"      \"\\\\(ax^2 + bx + c = 5\\\\)\",\n" +
						"      \"\\\\(ax^2 + bx + c = 1\\\\)\",\n" +
						"      \"\\\\(ax^2 + bx + c = 1\\\\)\"\n" +
						"    ]\n" +
						"  },\n" +
						"  {\n" +
						"    \"question\": \"What is the reciprocal of \\\\(\\\\frac{4+y^2}{4-y^2}\\\\)?\",\n" +
						"    \"correct_answer\": \"\\\\(\\\\frac{4+y^2}{4-y^2}\\\\)\",\n" +
						"    \"incorrect_answers\": [\"\\\\(\\\\frac{4+x^3}{4-y^2}\\\\)\",\n" +
						"      \"\\\\(\\\\frac{4+y^2}{4+y^2}\\\\)\",\n" +
						"      \"\\\\(\\\\frac{4+x^4}{4+y^2}\\\\)\",\n" +
						"    ]\n" +
						"  }\n" +
						"]}";
				try {
					JSONObject mathQuestionList = new JSONObject(staticData);
					JSONArray questionJSONData = mathQuestionList.getJSONArray("questions");
					for (int i = 0; i < questionJSONData.length(); i++) {
						questions.add(new Question((JSONObject) questionJSONData.get(i)));
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				startActivity(switchToQuiz);
			}
		});
		
	}
}