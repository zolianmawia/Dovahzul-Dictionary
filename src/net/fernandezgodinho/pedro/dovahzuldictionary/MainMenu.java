package net.fernandezgodinho.pedro.dovahzuldictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenu extends Portrait {

	private TextView thuum;

	private InputStream is, is2, is3, is4, is5;

	private String english, dovahzul, wordClass, notes, canon;

	private Button bSearch, bDictionary, bTranslator, bAlphabet;

	private static String[] englishArray, dovahzulArray, wordClassArray,
			notesArray, canonArray;

	static DictionaryObject[] dictionary;

	private File extStore = Environment.getExternalStorageDirectory();

	private File dovahzulFile = new File(extStore.getAbsolutePath()
			+ "/Dovahzul_Dictionary/dovahzul.txt");

	private File englishFile = new File(extStore.getAbsolutePath()
			+ "/Dovahzul_Dictionary/english.txt");

	private File classFile = new File(extStore.getAbsolutePath()
			+ "/Dovahzul_Dictionary/class_word.txt");

	private File notesFile = new File(extStore.getAbsolutePath()
			+ "/Dovahzul_Dictionary/notes.txt");

	private File canonFile = new File(extStore.getAbsoluteFile()
			+ "/Dovahzul_Dictionary/canon.txt");

	public static Typeface dragonTf, palatinoTf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if (getBaseContext().getFileStreamPath("wordClass").exists()
				&& getBaseContext().getFileStreamPath("english").exists()
				&& getBaseContext().getFileStreamPath("dovahzul").exists()
				&& getBaseContext().getFileStreamPath("notes").exists()
				&& getBaseContext().getFileStreamPath("canon").exists()) {
			System.out.println("Files exist");
			try {
				FileInputStream fis;

				StringBuffer fileContent;

				int ch;
				fis = openFileInput("wordClass");
				fileContent = new StringBuffer("");
				while ((ch = fis.read()) != -1)
					fileContent.append((char) ch);
				wordClass = fileContent.toString();

				ch = -1;
				fis = openFileInput("english");
				fileContent = new StringBuffer("");
				while ((ch = fis.read()) != -1)
					fileContent.append((char) ch);
				english = fileContent.toString();

				ch = -1;
				fis = openFileInput("dovahzul");
				fileContent = new StringBuffer("");
				while ((ch = fis.read()) != -1)
					fileContent.append((char) ch);
				dovahzul = fileContent.toString();

				ch = -1;
				fis = openFileInput("notes");
				fileContent = new StringBuffer("");
				while ((ch = fis.read()) != -1)
					fileContent.append((char) ch);
				notes = fileContent.toString();

				ch = -1;
				fis = openFileInput("canon");
				fileContent = new StringBuffer("");
				while ((ch = fis.read()) != -1)
					fileContent.append((char) ch);
				canon = fileContent.toString();

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Files don't exist.");
			is = getResources().openRawResource(R.raw.english);
			english = convertStreamToString(is);

			is2 = getResources().openRawResource(R.raw.dovahzul);
			dovahzul = convertStreamToString(is2);

			is3 = getResources().openRawResource(R.raw.classword);
			wordClass = convertStreamToString(is3);

			is4 = getResources().openRawResource(R.raw.notes);
			notes = convertStreamToString(is4);

			is5 = getResources().openRawResource(R.raw.canon);
			canon = convertStreamToString(is5);

			try {
				FileOutputStream fOut;

				fOut = openFileOutput("wordClass", MODE_PRIVATE);
				fOut.write(wordClass.getBytes());
				fOut = openFileOutput("english", MODE_PRIVATE);
				fOut.write(english.getBytes());
				fOut = openFileOutput("dovahzul", MODE_PRIVATE);
				fOut.write(dovahzul.getBytes());
				fOut = openFileOutput("notes", MODE_PRIVATE);
				fOut.write(notes.getBytes());
				fOut = openFileOutput("canon", MODE_PRIVATE);
				fOut.write(canon.getBytes());

				fOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		new DownloadToInternalStorage().execute(getApplicationContext());

		wordClassArray = wordClass.split("\\;");
		englishArray = english.split("\\;");
		dovahzulArray = dovahzul.split("\\;");
		notesArray = notes.split("\\@");
		canonArray = canon.split("\\;");

		dictionary = new DictionaryObject[englishArray.length];
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = new DictionaryObject(englishArray[i],
												 dovahzulArray[i], canonArray[i], wordClassArray[i],
												 notesArray[i]);
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		String dragonFont = "fonts/dragon_alphabet.ttf";
		String palatinoFont = "fonts/palatino.ttf";

		dragonTf = Typeface.createFromAsset(getAssets(), dragonFont);
		palatinoTf = Typeface.createFromAsset(getAssets(), palatinoFont);

		thuum = (TextView) findViewById(R.id.tvmThuum);

		bSearch = (Button) findViewById(R.id.bmSearch);
		bDictionary = (Button) findViewById(R.id.bmDictionary);
		bTranslator = (Button) findViewById(R.id.bmTranslator);
		bAlphabet = (Button) findViewById(R.id.bmAlphabet);

		thuum.setTypeface(palatinoTf);
		bSearch.setTypeface(MainMenu.palatinoTf);
		bDictionary.setTypeface(MainMenu.palatinoTf);
		bTranslator.setTypeface(MainMenu.palatinoTf);
		bAlphabet.setTypeface(MainMenu.palatinoTf);

		addListeners();
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	private void addListeners() {
		bSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						SearchMain.class);
				startActivity(intent);
			}
		});

		bDictionary.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						Dictionary.class);
				startActivity(intent);
			}
		});

		bTranslator.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						Translator.class);
				startActivity(intent);
			}
		});

		bAlphabet.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getApplicationContext(),
						Alphabet.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 0, "Update Dictionary");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			if (isNetworkConnected()) {
				String englishUrl, dovahzulUrl, classUrl, notesUrl, canonUrl;
				try {
					englishUrl = ("http://thuum.org/app/english.txt");
					DownloadTxtFile(englishUrl, "english.txt");

					dovahzulUrl = ("http://thuum.org/app/dovahzul.txt");
					DownloadTxtFile(dovahzulUrl, "dovahzul.txt");

					classUrl = ("http://thuum.org/app/class.txt");
					DownloadTxtFile(classUrl, "class_word.txt");

					notesUrl = ("http://thuum.org/app/notes.txt");
					DownloadTxtFile(notesUrl, "notes.txt");

					canonUrl = ("http://thuum.org/app/canon.txt");
					DownloadTxtFile(canonUrl, "canon.txt");

					try {
						is = new FileInputStream(englishFile);
					} catch (FileNotFoundException e) {
					}
					english = convertStreamToString(is);

					try {
						is2 = new FileInputStream(dovahzulFile);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					dovahzul = convertStreamToString(is2);

					try {
						is3 = new FileInputStream(classFile);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					wordClass = convertStreamToString(is3);

					try {
						is4 = new FileInputStream(notesFile);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					notes = convertStreamToString(is4);

					try {
						is5 = new FileInputStream(canonFile);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					canon = convertStreamToString(is5);

					wordClassArray = wordClass.split("\\;");
					englishArray = english.split("\\;");
					dovahzulArray = dovahzul.split("\\;");
					notesArray = notes.split("\\@");
					canonArray = canon.split("\\;");

					dictionary = new DictionaryObject[englishArray.length];
					for (int i = 0; i < dictionary.length; i++) {
						dictionary[i] = new DictionaryObject(englishArray[i],
								dovahzulArray[i], canonArray[i],
								wordClassArray[i], notesArray[i]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(
						this,
						"You have no internet connection. Please try again later.",
						Toast.LENGTH_LONG).show();

			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

	@SuppressLint("NewApi")
	public void DownloadTxtFile(String url, String fileName) {
		if (!new File(Environment.getExternalStorageDirectory()
				+ "/Dovahzul_Dictionary").exists()) {
			new File(Environment.getExternalStorageDirectory()
					+ "/Dovahzul_Dictionary").mkdirs();
		}
		File file = new File(extStore.getAbsolutePath()
				+ "/Dovahzul_Dictionary/" + fileName);
		DownloadManager.Request request = new DownloadManager.Request(
				Uri.parse(url));
		request.setDescription("Downloading Text Files...");
		request.setTitle("Updating Dictionary");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			request.allowScanningByMediaScanner();
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		}
		request.setDestinationInExternalPublicDir("/Dovahzul_Dictionary",
				fileName);
		DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		if (file.exists()) {
			file.delete();
		} else {
		}
		manager.enqueue(request);
	}

	public static String[] getEnglishArray() {
		return englishArray;
	}

	public static String[] getDovahzulArray() {
		return dovahzulArray;
	}

	public static String[] getWordClassArray() {
		return wordClassArray;
	}

	public static String[] getNotesArray() {
		return notesArray;
	}

	public static String[] getCanonArray() {
		return canonArray;
	}

	public static DictionaryObject[] getDictionary() {
		return dictionary;
	}

	private class DownloadToInternalStorage extends
			AsyncTask<Context, String, String> {

		@Override
		protected String doInBackground(Context... arg0) {
			System.out.println("Called doInBackground");
			if (isNetworkConnected()) {
				System.out.println("Network is available");
				try {
					URL dovahzulURL = new URL(
							"http://thuum.org/download-dev-dovahzul-web.php");
					URL englishURL = new URL(
							"http://thuum.org/download-dev-english-web.php ");
					URL canonURL = new URL(
							"http://thuum.org/download-dev-canon-web.php ");
					URL wordClassURL = new URL(
							"http://thuum.org/download-dev-class-web.php ");
					URL notesURL = new URL(
							"http://thuum.org/download-dev-notes-web.php");
					FileOutputStream fOut;
					String t = "";
					String inputLine = "";
					StringBuilder result;
					BufferedReader in;

					System.out.println("Assigned URLS");

					in = new BufferedReader(new InputStreamReader(
							dovahzulURL.openStream()));
					result = new StringBuilder();
					inputLine = "";
					while ((inputLine = in.readLine()) != null) {
						result.append(inputLine);
					}
					fOut = openFileOutput("dovahzul", MODE_PRIVATE);
					fOut.write(result.toString().getBytes());
					
					
					in = new BufferedReader(new InputStreamReader(
												englishURL.openStream()));
					result = new StringBuilder();
					inputLine = "";
					while ((inputLine = in.readLine()) != null) {
						result.append(inputLine);
					}
					fOut = openFileOutput("english", MODE_PRIVATE);
					fOut.write(result.toString().getBytes());
					
					
					in = new BufferedReader(new InputStreamReader(
												notesURL.openStream()));
					result = new StringBuilder();
					inputLine = "";
					while ((inputLine = in.readLine()) != null) {
						result.append(inputLine);
					}
					fOut = openFileOutput("notes", MODE_PRIVATE);
					fOut.write(result.toString().getBytes());

					
					in = new BufferedReader(new InputStreamReader(
												canonURL.openStream()));
					result = new StringBuilder();
					inputLine = "";
					while ((inputLine = in.readLine()) != null) {
						result.append(inputLine);
					}
					fOut = openFileOutput("canon", MODE_PRIVATE);
					fOut.write(result.toString().getBytes());
					
					
					in = new BufferedReader(new InputStreamReader(
												wordClassURL.openStream()));
					result = new StringBuilder();
					inputLine = "";
					while ((inputLine = in.readLine()) != null) {
						result.append(inputLine);
					}
					fOut = openFileOutput("wordClass", MODE_PRIVATE);
					fOut.write(result.toString().getBytes());


					fOut.close();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Network isn't available");
			}
			return null;
		}
	}
}
