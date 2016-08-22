package com.nytorian.technicalacronyms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

	//Application constants
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    /*Called when user presses Send button */
    public void sendMessage(View view){
    	//do something in response to button
    	
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	
    	EditText editText = (EditText)findViewById(R.id.edit_message);
    	String msg = editText.getText().toString();
    	
    	intent.putExtra(EXTRA_MESSAGE, msg);
    	
    	startActivity(intent);
    	
    }
    
}
