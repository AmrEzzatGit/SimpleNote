package com.example.panda.note;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet4Address;

public class ShowNote extends AppCompatActivity {
    TextView Title,Body;
    Button share,edit,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        Title=(TextView)findViewById(R.id.TitleView);
        Body=(TextView)findViewById(R.id.BodyView);
        share=(Button)findViewById(R.id.share);
        edit=(Button)findViewById(R.id.Edit);
        delete=(Button)findViewById(R.id.Delete);
        Bundle get=getIntent().getExtras();
         final String get_Title=get.getString("Note");
         final String get_Body=get.getString("EditedBody");
         Body.setText(get_Body);
         Title.setText(get_Title);
        DataBase db=new DataBase(ShowNote.this);
        final SQLiteDatabase SQL=db.getReadableDatabase();
        final Cursor c=SQL.query(DataBase.DATABASE_NAME,new String[]{DataBase.COLUMN_CONTENT},DataBase.COLUMN_NAME+"=?",new String[]{get_Title},null,null,null);

        if (c.moveToFirst())
        {
            String Grap=c.getString(c.getColumnIndex(DataBase.COLUMN_CONTENT));
            Body.setText(Grap);
        }
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s=Body.getText().toString();
                Intent send=new Intent();
                send.setAction(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_TEXT,s);
                send.setType("text/plain");
                startActivity(send);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent GoToEdit=new Intent(ShowNote.this,EditNote.class);
                String Tit=Title.getText().toString();
                String Bod=Body.getText().toString();
                GoToEdit.putExtra("Title",Tit);
                GoToEdit.putExtra("Body",Bod);
                startActivity(GoToEdit);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQL.delete(DataBase.TABLE_NAME,DataBase.COLUMN_NAME+"=?",new  String[]{get_Title});
                Intent BackToMain=new Intent(ShowNote.this,MainNote.class);
                finish();
            }
        });






    }
}
