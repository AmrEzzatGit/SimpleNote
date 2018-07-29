package com.example.panda.note;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteNote extends AppCompatActivity {
    EditText Title,Body;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        Title=(EditText)findViewById(R.id.Title);
        Body=(EditText)findViewById(R.id.Body);
        Save=(Button)findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataBase db=new DataBase(WriteNote.this);
                SQLiteDatabase SQL=db.getWritableDatabase();
                String Note_Title=Title.getText().toString();
                String Note_Body=Body.getText().toString();
                ContentValues v=new ContentValues();
                v.put(DataBase.COLUMN_NAME,Note_Title);
                v.put(DataBase.COLUMN_CONTENT,Note_Body);
                SQL.insert(DataBase.TABLE_NAME,null,v);
                Toast.makeText(WriteNote.this,"New Note added to your Notes",Toast.LENGTH_LONG).show();
                Intent BackToMain=new Intent(WriteNote.this,MainNote.class);
                startActivity(BackToMain);
                finish();


            }
        });
    }
}
