package com.example.outfitmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menu1){
            setContentView(R.layout.activity_main);
            return true;
        } else if(id == R.id.menu2){
            setContentView(R.layout.profilo);
            return true;
        } else if(id == R.id.menu3){
            setContentView(R.layout.assistenza);
            return true;
        } else if(id == R.id.menu4){
            setContentView(R.layout.log_in);
        }
        return super.onOptionsItemSelected(item);
    }

    public void armadioClicked(View v){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Armadio.class);
        startActivity(i);
    }

    public void generaClicked(View v){
        Intent i = new Intent();
        i.setClass(getApplicationContext(), GeneraOutfit.class);
        startActivity(i);
    }

    public void creaClicked(View v){
        Intent i = new Intent(getApplicationContext(), CreaOutfit.class);
        startActivity(i);
    }
}