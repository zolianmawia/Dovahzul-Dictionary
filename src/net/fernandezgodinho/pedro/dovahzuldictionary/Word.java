package net.fernandezgodinho.pedro.dovahzuldictionary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.TextView;

public class Word extends Portrait {
	private TextView tvDovahzulAlpha, tvEnglish, tvDovahzul, tvCanon, tvNotes;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word);

		String fontPath = "fonts/dragon_alphabet.ttf";

		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

		tvDovahzulAlpha = (TextView) findViewById(R.id.tvDovahzulAlpha);
		tvEnglish = (TextView) findViewById(R.id.tvEnglish);
		tvDovahzul = (TextView) findViewById(R.id.tvDovahzul);
		tvCanon = (TextView) findViewById(R.id.tvCanon);
		tvNotes = (TextView) findViewById(R.id.tvNotes);

		Intent intent = getIntent();

		index = intent.getIntExtra("INDEX", 0);

		tvEnglish.setTypeface(MainMenu.palatinoTf);
		tvDovahzul.setTypeface(MainMenu.palatinoTf);
		tvCanon.setTypeface(MainMenu.palatinoTf);
		tvNotes.setTypeface(MainMenu.palatinoTf);
		tvDovahzulAlpha.setTypeface(tf);

		tvDovahzulAlpha.setText(toDovahzulAlpha(MainMenu.getDictionary()[index]
				.getDovahzul()));

		tvDovahzul.setText(MainMenu.getDictionary()[index].getDovahzul());

		tvCanon.setText("\n" + MainMenu.getDictionary()[index].getCanon()
				+ "\n");

		tvEnglish.setText("(" + MainMenu.getDictionary()[index].getWordClass()
				+ ") " + MainMenu.getDictionary()[index].getEnglish() + "\n");

		tvNotes.setText(MainMenu.getDictionary()[index].getNotes());

		if (tvNotes.getText().equals("") || tvNotes.getText().equals(" ")) {
			tvNotes.setText("No notes on this word.");
		}

		if (tvDovahzulAlpha.length() > 10) {
			tvDovahzulAlpha.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
		}
	}

	@SuppressLint("DefaultLocale")
	public String toDovahzulAlpha(String toConvert) {
		String converted = toConvert.toUpperCase().replaceAll("AA", "1")
				.replaceAll("EI", "2").replaceAll("II", "3")
				.replaceAll("AH", "4").replaceAll("UU", "5")
				.replaceAll("U'U", "5").replaceAll("UR", "6")
				.replaceAll("IR", "7").replaceAll("OO", "8")
				.replaceAll("EY", "9");

		return converted;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
