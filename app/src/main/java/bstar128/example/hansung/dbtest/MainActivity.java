package bstar128.example.hansung.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText editName,editCount,editResultName,editResultCount;
    Button butInit, butInsert,butSelect;
    MyDBHelper mydb;
    SQLiteDatabase sqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName=(EditText)findViewById(R.id.edit_group_name);
        editCount=(EditText)findViewById(R.id.edit_group_count);
        editResultName=(EditText)findViewById(R.id.result_name);
        editResultCount=(EditText)findViewById(R.id.edit_group_count);
        butInit=(Button)findViewById(R.id.but_insert);
        butInsert=(Button)findViewById(R.id.but_insert);
        butSelect=(Button)findViewById(R.id.but_select);

        //DB 생성
        mydb=new MyDBHelper(this);
        //기존의 테이블이 존재하면 삭제하고 테이블을 새로 생성
        butInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sqldb= mydb.getWritableDatabase();
                mydb.onUpgrade(sqldb,1,2);
                sqldb.close();
            }
        });
    }
    class MyDBHelper extends SQLiteOpenHelper{
    //IdolDB라는 이름의 데이터베이스가 생성됨

        public MyDBHelper(Context context) {
            super(context, "IdolDB", null, 1);
        }
    //IdolDB하는 이름의 테이블 생성
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql="create table IdolTable(idolname text not null primary key, Idolcount integer)";
            db.execSQL(sql);
        }
//이미 IdolDB이 존재하면 기존 테이블 삭제, 새로 테이블 만들때 호출
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql="drop table ifexist idolTable";
            db.execSQL(sql);
            onCreate(db);
        }
    }

}
