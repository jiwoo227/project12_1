package com.example.project12_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDBHelper dbHelper;
    EditText editName, editCOunt, editNameResult, editCountResult;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.edit_name);
        editCOunt = findViewById(R.id.edit_count);
        editNameResult = findViewById(R.id.edit_name_result);
        editCountResult = findViewById(R.id.edit_count_result);
        Button btnInit = findViewById(R.id.btn_init);
        Button btnInput = findViewById(R.id.btn_input);
        Button btnSearch = findViewById(R.id.btn_search);
        Button btnUpdate = findViewById(R.id.btn_update);
        Button btnDelete = findViewById(R.id.btn_delete);

        dbHelper = new MyDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                dbHelper.onUpgrade(db, 1, 2);
                db.close();
            }
        });
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                db.execSQL("insert into gtoupTB values('"+editName.getText().toString()+"', "+editCOunt.getText().toString()+");");
                db.close();
                Toast.makeText(getApplicationContext(), "정상적으로 행이 삽입 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dbHelper.getReadableDatabase();
                Cursor cursor = db.rawQuery("select * from groupTB;", null);
                String strName = "그룹이름\r\n___________\r\n";
                String strCount = "인원\r\n___________\r\n";
                while (cursor.moveToNext()) {
                    strName += cursor.getString(0) + "\r\n";
                    strCount += cursor.getInt(1) + "\r\n";
                }
                editNameResult.setText(strName);
                editCountResult.setText(strCount);

                cursor.close();
                db.close();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                db.execSQL("insert into gtoupTB values('"+editName.getText().toString()+"', "+editCOunt.getText().toString()+");");
                db.close();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = dbHelper.getWritableDatabase();
                db.execSQL("insert into gtoupTB values('"+editName.getText().toString()+"', "+editCOunt.getText().toString()+");");
                db.close();
            }
        });
    }

    public class MyDBHelper extends SQLiteOpenHelper {

        public MyDBHelper(Context context) {
            super(context, "groupDB", null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table groupTB (name char(20) primary key, count integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists groubTB");
            onCreate(db);
        }


    }
}