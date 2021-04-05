package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "quizdb";
    private static final String DB_TABLE = "quiztable3";


    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "optA";
    private static final String KEY_OPTB = "optB";
    private static final String KEY_OPTC = "optC";
    private static final String KEY_EXP = "exp";

    private SQLiteDatabase dbase ;
    private int rowCount = 0;

    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.dbase = getWritableDatabase();
        String sqlQuery = "CREATE TABLE IF NOT EXISTS "+
                DB_TABLE+"("+KEY_ID  + " INTEGER PRIMARY KEY," + KEY_QUES +" TEXT," + KEY_ANSWER+" TEXT," + KEY_OPTA +" TEXT," + KEY_OPTB+" TEXT," + KEY_OPTC +" TEXT," +KEY_EXP+" TEXT"+")";
        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);
        dbase.execSQL(sqlQuery);
        dbase = this.getWritableDatabase();
        addQuestions();
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public void addQuestions() {
        Log.d("question","added");
        Question q1 = new Question("Which player scored the fastest hat-trick in the Premier League?", "Sadio Mane", "Zlatan Ibrahimovic", "Romelu Lukaku", "Sadio Mane","a");
        this.addQuestionToDB(q1);
        Question q2 = new Question("Which player, with 653 games, has made the most Premier League appearances?", "Gareth Bale", "Garenth Barry", "Ryan Giggs", "Gareth Barry","a");
        this.addQuestionToDB(q2);
        Question q3 = new Question("The record for most Premier League red cards (8) is held by?", "Patric Viera", "Diego Costa", "Rojo", "Patric Viera","a");
        this.addQuestionToDB(q3);
        Question q4 = new Question("With 260 goals, who is the Premier League's all-time top scorer?", "Alan Shearer", "Sergio Aguero", "Wayne Rooney", "Alan Shearer","a");
        this.addQuestionToDB(q4);
        Question q5 = new Question("When was the inaugural Premier League season?", "1992-93", "1991-92", "1994-95", "1992-93","a");
        this.addQuestionToDB(q5);
        Question q6 = new Question("Which team won the first Premier League title?", "Blackburn", "Leeds United", "Manchester United", "Manchester United","a");
        this.addQuestionToDB(q6);
        Question q7 = new Question("With 202 clean sheets, which goalkeeper has the best record in the Premier League?", "Petr Cech", "DeGea", "Joe Hart", "Petr Cech","a");
        this.addQuestionToDB(q7);
        Question q8 = new Question("How many clubs competed in the inaugural Premier League season?", "24", "22", "20", "22","a");
        this.addQuestionToDB(q8);
        Question q9 = new Question("The fastest goal scored in Premier League history came in 7.69 seconds. Who scored it?", "Sadio Mane", "Shane Long", "James Ward Prowse", "Shane Long","a");
        this.addQuestionToDB(q9);
        Question q10 = new Question("Which country has won the most World Cups?", "Brazil", "Argentina", "Germany", "Brazil","a");
        this.addQuestionToDB(q10);
    }

    //code to add new question
    public void addQuestionToDB(Question q){

        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER,q.getAnswer());
        values.put(KEY_OPTA,q.getOptA());
        values.put(KEY_OPTB,q.getOptB());
        values.put(KEY_OPTC,q.getOptC());
        values.put(KEY_EXP,q.getExplanation());
        //insert row
        dbase.insert(DB_TABLE, null, values);
    }
    //get all question in listview
    public List <Question> getAllQuestions(){
        List <Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setOptC(cursor.getString(5));
                q.setExplanation(cursor.getString(5));

                //add question in list
                questionList.add(q);

                //loop all rows
            }while (cursor.moveToNext());
        }
        return questionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }
}