package bstar128.example.hansung.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
