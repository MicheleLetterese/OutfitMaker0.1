package com.example.outfitmaker;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class GeneraOutfit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genera_outfit);
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
}
