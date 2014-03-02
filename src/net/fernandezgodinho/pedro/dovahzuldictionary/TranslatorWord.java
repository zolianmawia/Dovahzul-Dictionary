package net.fernandezgodinho.pedro.dovahzuldictionary;

import net.fernandezgodinho.pedro.helpers.ArrayHelpers;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class TranslatorWord extends TextView {
	ArrayHelpers ah = new ArrayHelpers();
	String[] words = new String[0];

	public TranslatorWord(Context context) {
		super(context);
		setTypeface(MainMenu.palatinoTf);
		setTextColor(Color.WHITE);

		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	public void setWord(String s, boolean ed) {
		if(ed){
			
			if (!s.equals("")) {
				boolean cap = false;
				if (Character.isUpperCase(s.toCharArray()[0])) {
					cap = true;
				}
				String p = "";
				if (s.endsWith(".")) {
					p = ".";
					s = s.replaceAll("\\.", "");
				} else if (s.endsWith(",")) {
					p = ",";
					s = s.replaceAll("\\,", "");
				} else if (s.endsWith("!")) {
					p = ",";
					s = s.replaceAll("\\!", "");
				} else if (s.endsWith(":")) {
					p = ":";
					s = s.replaceAll("\\:", "");
				} else if (s.endsWith("?")) {
					p = "?";
					s = s.replaceAll("\\?", "");
				}
				
				
				int[] i = ah.searchWithExactMatchEnglish(MainMenu.getDictionary(),
						s);
				if (i.length != 0) {
					words = ah.arrayFromArraySearchDovahzul(
							MainMenu.getDictionary(), i);
					s = words[0].trim().split(",")[0].trim().split("/")[0].trim();
				}
				
				
				if (cap) {
					char[] tmp = s.toCharArray();
					Character.toUpperCase(tmp[0]);
					s = "";
					for (int in = 0; in < tmp.length; in++) {
						s = s + tmp[in];
					}
				} else {
					s = s.toLowerCase();
				}
				setText(s + p + " ");
			} else {
				setText("");
			}
			
		} else {
			if (!s.equals("")) {
				boolean cap = false;
				if (Character.isUpperCase(s.toCharArray()[0])) {
					cap = true;
				}
				String p = "";
				if (s.endsWith(".")) {
					p = ".";
					s = s.replaceAll("\\.", "");
				} else if (s.endsWith(",")) {
					p = ",";
					s = s.replaceAll("\\,", "");
				} else if (s.endsWith("!")) {
					p = ",";
					s = s.replaceAll("\\!", "");
				} else if (s.endsWith(":")) {
					p = ":";
					s = s.replaceAll("\\:", "");
				} else if (s.endsWith("?")) {
					p = "?";
					s = s.replaceAll("\\?", "");
				}
				int[] i = ah.searchWithExactMatchDovahzul(MainMenu.getDictionary(),
						s);
				if (i.length != 0) {
					words = ah.arrayFromArraySearchEnglish(
							MainMenu.getDictionary(), i);
					s = words[0].trim().split(",")[0].trim().split("/")[0].trim();
				}
				if (cap) {
					char[] tmp = s.toCharArray();
					Character.toUpperCase(tmp[0]);
					s = "";
					for (int in = 0; in < tmp.length; in++) {
						s = s + tmp[in];
					}
				} else {
					s = s.toLowerCase();
				}
				setText(s + p + " ");
			} else {
				setText("");
			}
		}
	}
}
