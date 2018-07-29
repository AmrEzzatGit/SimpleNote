package com.example.panda.note;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    EditText Title,Body;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Title=(EditText)findViewById(R.id.TitleEdit);
        Body=(EditText)findViewById(R.id.BodyEdit);
        update=(Button)findViewById(R.id.update);
        DataBase db=new DataBase(EditNote.this);
        final SQLiteDatabase SQL=db.getWritableDatabase();
        Bundle get=getIntent().getExtras();
        String Tit=get.getString("Title");
        final String Bod=get.getString("Body");
        Title.setText(Tit);
        Body.setText(Bod);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTitle=Title.getText().toString();
                String editBody=Body.getText().toString();
                ContentValues v=new ContentValues();
                v.put(DataBase.COLUMN_NAME,editTitle);
                v.put(DataBase.COLUMN_CONTENT,editBody);
                SQL.update(DataBase.TABLE_NAME,v,DataBase.COLUMN_NAME+"=?",new String[]{editTitle});
                Toast.makeText(EditNote.this,"Your Note has been updated",Toast.LENGTH_LONG).show();
                Intent BackToShow=new Intent(EditNote.this,ShowNote.class);
                BackToShow.putExtra("Note",editTitle);
                BackToShow.putExtra("EditedBody",editBody);
                startActivity(BackToShow);
                finish();
            }
        });
    }
}
