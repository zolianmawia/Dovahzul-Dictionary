package net.fernandezgodinho.pedro.dovahzuldictionary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Alphabet extends Activity {
	TextView keyPointsTitle, keyTextOne, keyTextTwo, keyTextThree, keyTextFour,
			alphabetTitle;
	String[][] alph = {
			{ "A", "1", "4", "B", "D", "E", "2", "9", "F", "G", "H", "I", "3",
					"7", "J", "K", "L", "M", "N", "O", "8", "P", "Q", "R", "S",
					"T", "U", "5", "6", "V", "W", "X", "Y", "Z" },
			{ "A", "AA", "AH", "B", "D", "E", "EI", "EY", "F", "G", "H", "I",
					"II", "IR", "J", "K", "L", "M", "N", "O", "OO", "P", "Q",
					"R", "S", "T", "U", "UU", "UR", "V", "W", "X", "Y", "Z" },
			{ "/æ/", "/ɑ:/", "/ɑ:/", "/b/", "/d/", "/ɛ/, /ə/, /eɪ/", "/aɪ/",
					"/eɪ/", "/f/", "/g/", "/h/", "/i:/", "/i:/", "/ɪəʳ/",
					"/dʒ/", "/k/", "/l/", "/m/", "/n/", "/oʊ/", "/u:/", "/p/",
					"/xw/", "/r/", "/s/", "/t/", "/u:/", "/u:/", "/ʊəʳ/",
					"/v/", "/w/", "/ks/", "/j/", "/z/" } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alphabet);

		keyPointsTitle = (TextView) findViewById(R.id.aKeyPointsTitle);
		keyTextOne = (TextView) findViewById(R.id.aKeyTextOne);
		keyTextTwo = (TextView) findViewById(R.id.aKeyTextTwo);
		keyTextThree = (TextView) findViewById(R.id.aKeyTextThree);
		keyTextFour = (TextView) findViewById(R.id.aKeyTextFour);
		alphabetTitle = (TextView) findViewById(R.id.aAlphabetTitle);

		keyPointsTitle.setTypeface(MainMenu.palatinoTf);
		keyTextOne.setTypeface(MainMenu.palatinoTf);
		keyTextTwo.setTypeface(MainMenu.palatinoTf);
		keyTextThree.setTypeface(MainMenu.palatinoTf);
		keyTextFour.setTypeface(MainMenu.palatinoTf);
		alphabetTitle.setTypeface(MainMenu.palatinoTf);

		Toast.makeText(getApplicationContext(), alph[2][2], Toast.LENGTH_LONG).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

}
