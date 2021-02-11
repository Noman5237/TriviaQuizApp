package android.example.triviaquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import io.github.kexanie.library.MathView;

public class QuestionAdapter extends ArrayAdapter<Question> {
	
	public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> questions) {
		super(context, 0, questions);
	}
	
	
	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		LinearLayout toBeRendered = (LinearLayout) convertView;
		if (toBeRendered == null) {
			toBeRendered = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.question_item_layout, parent, false);
		}
		
		Question question = getItem(position);
		
		MathView questionText = toBeRendered.findViewById(R.id.question);
		questionText.setText(question.getQuestion());
		MathView o1, o2, o3, o4;
		
		
		o1 = toBeRendered.findViewById(R.id.op1);
		o1.setText(question.getCorrectOption());
		
		o2 = toBeRendered.findViewById(R.id.op2);
		o2.setText(question.getIncorrectOptions()[0]);
		
		o3 = toBeRendered.findViewById(R.id.op3);
		o3.setText(question.getIncorrectOptions()[1]);
		
		o4 = toBeRendered.findViewById(R.id.op4);
		o4.setText(question.getIncorrectOptions()[2]);
		
		return toBeRendered;
	}
}
