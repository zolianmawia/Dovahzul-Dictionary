package net.fernandezgodinho.pedro.dovahzuldictionary;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Alphabet extends Portrait {
	private TextView keyPointsTitle, keyTextOne, keyTextTwo, keyTextThree,
			keyTextFour, alphabetTitle;

	private String[] alphDov = { "A", "1", "4", "B", "D", "E", "2", "9", "F",
			"G", "H", "I", "3", "7", "J", "K", "L", "M", "N", "O", "8", "P",
			"Q", "R", "S", "T", "U", "5", "6", "V", "W", "X", "Y", "Z" };
	private String[] alphEng = { "A", "AA", "AH", "B", "D", "E", "EI", "EY",
			"F", "G", "H", "I", "II", "IR", "J", "K", "L", "M", "N", "O", "OO",
			"P", "Q", "R", "S", "T", "U", "UU", "UR", "V", "W", "X", "Y", "Z" };
	private String[] alphEx = {
			"cat, black, bad",
			"father, arm",
			"father, arm, may sometimes be pronounced with German ch at the end",
			"bread, able",
			"door, sword",
			"Normally pronounced as /ɛ/ in net, enter. When at the end of a word, pronounced as /ə/ in cinema, comma. When at the end of a word and preceded by ah as in dovahhe, pronounced as /eɪ/ in may, fey. When at the end of a word and followed by h as in geh, retains /ɛ/ pronunciation.",
			"why, hi, find",
			"hey, may, able",
			"frost, after",
			"gold, forget",
			"hello, ahoy",
			"see, clean, rarely but sometimes win",
			"always see, clean",
			"hear, leer, irritate",
			"just, agent, if after a consonant then yes",
			"kite, attack",
			"lore, bell",
			"mother, immerse",
			"no, end, kin",
			"foe, know, sole, oat, sometimes but rarely /ɒ/ in on",
			"moon, typhoon",
			"map, apart",
			"As /k/ but further back in the throat, as in loch. Pronounced with a following /w/ as in quote.",
			"run, arise, usually a rolled r", "soft, sense", "tale, fate",
			"rule, fool, very rarely sun", "rule, fool", "lure, moor",
			"valley, event", "world, awake", "axe, fox, relax",
			"yet, yesterday", "zoo, maze" };

	private ListView alphabetRoot;
	private Alphabet thisVar = this;

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
		alphabetRoot = (ListView) findViewById(R.id.aList);

		keyPointsTitle.setTypeface(MainMenu.palatinoTf, Typeface.BOLD);
		keyTextOne.setTypeface(MainMenu.palatinoTf);
		keyTextTwo.setTypeface(MainMenu.palatinoTf);
		keyTextThree.setTypeface(MainMenu.palatinoTf);
		keyTextFour.setTypeface(MainMenu.palatinoTf);
		alphabetTitle.setTypeface(MainMenu.palatinoTf, Typeface.BOLD);

		alphabetRoot.setAdapter(new ListAdapter(thisVar, alphDov, alphEng,
				alphEx));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	private static class ListAdapter extends ArrayAdapter<String> {
		public ListAdapter(Alphabet screen, String[] list1, String[] list2,
				String[] list3) {
			super(screen, R.layout.row, list1);
			this.list1 = list1;
			this.list2 = list2;
			this.list3 = list3;
			this.activity = screen;
		}

		public View getView(int position, View row, ViewGroup parent) {
			row = activity.getLayoutInflater().inflate(R.layout.alphabet_row,
					parent, false);

			TextView view1 = (TextView) row.findViewById(R.id.arItem1);
			view1.setText(list1[position]);
			TextView view2 = (TextView) row.findViewById(R.id.arItem2);
			view2.setText(list2[position]);
			TextView view3 = (TextView) row.findViewById(R.id.arItem3);
			view3.setText(list3[position]);

			view1.setTypeface(MainMenu.dragonTf);
			view2.setTypeface(MainMenu.palatinoTf);
			view3.setTypeface(MainMenu.palatinoTf);

			return row;
		}

		private Alphabet activity;
		private String[] list1, list2, list3;
	}

}
