package android.example.triviaquiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

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
		
		final Intent switchToQuiz = new Intent(getApplicationContext(), QuizActivity.class);
		
		dynamicQuizButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				questions.clear();
				
				AsyncTask getQuestions = new AsyncTask<String, Void, JSONObject>() {
					@Override
					protected JSONObject doInBackground(String... intents) {
						
						HttpURLConnection connection;
						JSONObject jsonObject = null;
						try {
							URL url = new URL("https://opentdb.com/api.php?amount=10&type=multiple");
							connection = (HttpURLConnection) url.openConnection();
							connection.setRequestMethod("GET");
							connection.connect();
							int responseCode = connection.getResponseCode();
							if (responseCode != 200) {
								throw new IOException("Response Code is not 200");
							}
							
							StringBuilder json = new StringBuilder();
							Scanner scanner = new Scanner(url.openStream());
							while (scanner.hasNextLine()) {
								json.append(scanner.nextLine());
							}
							scanner.close();
							jsonObject = new JSONObject(json.toString());
						} catch (IOException | JSONException e) {
							Log.v("dynQ", e + "");
							return null;
						} finally {
						}
						
						return jsonObject;
					}
					
					@Override
					protected void onPostExecute(JSONObject jsonObject) {
						super.onPostExecute(jsonObject);
						try {
							JSONArray array = jsonObject.getJSONArray("results");
							for (int i = 0; i < array.length(); i++) {
								JSONObject question = (JSONObject) array.get(i);
								questions.add(new Question(question));
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						startActivity(switchToQuiz);
					}
				}.execute();
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
						"    \"incorrect_answers\": [\n" +
						"\"\\\\(\\\\frac{4+x^3}{4-y^2}\\\\)\",\n" +
						"      \"\\\\(\\\\frac{4+y^2}{4+y^2}\\\\)\",\n" +
						"      \"\\\\(\\\\frac{4+x^4}{4+y^2}\\\\)\",\n" +
						"    ]\n" +
						"  }\n" +
						"]}";
				try {
					JSONObject mathQuestionList = new JSONObject(staticData);
					JSONArray questionJSONData = mathQuestionList.getJSONArray("questions");
					for (int i = 0; i < questionJSONData.length(); i++) {
						questions.add(new Question((JSONObject) questionJSONData.get(i), true));
					}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				startActivity(switchToQuiz);
			}
		});
		
	}
}