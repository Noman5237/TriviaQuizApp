package android.example.triviaquiz;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_layout);
		
		ArrayList<Question> questions = MainActivity.questions;
		ListView listView = findViewById(R.id.questions);
		
		
		for (Question question : questions) {
			Log.v("question", question.toString());
		}
		
		QuestionAdapter adapter = new QuestionAdapter(this, 0, questions);
		listView.setAdapter(adapter);
		
		
		
		
	}
}