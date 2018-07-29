package com.example.panda.note;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainNote extends AppCompatActivity {
    ListView list;
    Button creatBtn;
    ArrayList <String> Titles=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_note);
        list=(ListView)findViewById(R.id.showList);
        creatBtn=(Button)findViewById(R.id.CreatNote);
        DataBase db=new DataBase(MainNote.this);
        SQLiteDatabase SQL=db.getReadableDatabase();
        Cursor c=SQL.query(DataBase.DATABASE_NAME,new String[]{DataBase.COLUMN_NAME},null,null,null,null,null);

        if(c.moveToFirst())
        {
            do
            {
                String name=c.getString(c.getColumnIndex(DataBase.COLUMN_NAME));
                Titles.add(name);
            }
            while (c.moveToNext());
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainNote.this,R.layout.list_view_tamplate,R.id.Tamplet,Titles);

            list.setAdapter(adapter);
        }

        creatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent GoToWrite=new Intent(MainNote.this,WriteNote.class);
                startActivity(GoToWrite);

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String Note_To_Show=Titles.get(i);
                Intent show=new Intent(MainNote.this,ShowNote.class);
                show.putExtra("Note",Note_To_Show);
                startActivity(show);
                finish();

            }
        });

    }

}
