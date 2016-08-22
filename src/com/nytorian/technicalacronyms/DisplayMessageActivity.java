package com.nytorian.technicalacronyms;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayMessageActivity extends ActionBarActivity {

	String acronym, definition, link, output;
	
	@SuppressLint("DefaultLocale")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		
		//Get message from the intent
		Intent intent = getIntent();
		String msg = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		
		 MySQLiteHelper myDb;
		 myDb = new MySQLiteHelper(this);
		  
		 try 
		 {
			 this.deleteDatabase("DataBase");
			 myDb.createDataBase();
		 } 
		 
		 catch (IOException ioe) 
		 {
			 throw new Error("Unable to create database");
		 }
		  
		 try 
		 {
		  
			 Cursor results = myDb.getResults();
			 
			 results.moveToFirst();
			  
			 while (!results.isAfterLast()) 
			 {
			  
				 if(results.getString(1).equals(msg) || results.getString(1).toLowerCase().equals(msg))
				 {
					 acronym    = results.getString(1);
					 definition = results.getString(2);
					 link       = results.getString(3);
				 }
				 
				 results.moveToNext();
			 }
		 }
		 
		 catch(SQLException sqle)
		 {
			 throw sqle;
		 }
		 
		 if(acronym == null || definition == null)
		 {
			 acronym    = "";
			 definition = "";
			 link       = "";
		 }
		 
		//set the output
		 TextView resultTextView = (TextView)findViewById(R.id.txtView_result);//find the TextView
		 resultTextView.setFocusable(false);//disable editing
		 
		 output = evaluateOutput(acronym, definition);
		 
		 resultTextView.setText(Html.fromHtml(output), TextView.BufferType.SPANNABLE);
		 resultTextView.setMovementMethod(LinkMovementMethod.getInstance());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			//Open About Activity
			Intent intent = new Intent(this, About.class);
        	startActivity(intent);
        	
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private String evaluateOutput(String acronym, String definition)
	{
		String output;
		
		if(!(acronym.equals("") && definition.equals("")))
		 {
			 output = "<br />" + "  <b><font color=#FE2E2E>Acronym: </font>" + acronym + "</b><br />" + 
						"<br /><br />" +  "  <b><font color=#0080FF>Definition:  </font></b>" + definition + 
						"<br /><br />" + link + "</a><br /><br />";
		 }
		 
		 else
		 {
			 //display invalid input message
			 output = getString(R.string.invalid_input);
			 output = "<font color='red'></br></br>"+ output + "</font>"; 
		 }
		
		return output;
	}
}
