package com.amigos.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
public Button save,get;
EditText name,age;
List<Person> personList;
    PersonDatabase personDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=findViewById(R.id.ok2);
        get=findViewById(R.id.get);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        RoomDatabase.Callback myCallback=new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        personDB= Room.databaseBuilder(getApplicationContext(),PersonDatabase.class,"PersonDB")
                 .addCallback(myCallback).build();
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String NAME=name.getText().toString();
        String age=name.getText().toString();
        Person p=new Person(NAME,age);
        addPersonInBackground(p);
    }
});
get.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        getaddPersonInBackground();
        
    }
});

    }
    public void addPersonInBackground(Person person){
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.getMainLooper());
       executorService.execute(new Runnable() {
           @Override
           public void run() {
               personDB.getPersonDAO().addPerson(person);
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(MainActivity.this, "addToDatabase", Toast.LENGTH_SHORT).show();
                   }
               });
       };


    });
    }
    public void getaddPersonInBackground(){
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        Handler handler=new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                personList=personDB.getPersonDAO().getAllPerson();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb=new StringBuilder();
                        for(Person p :personList)
                        {
                            sb.append(p.getName()+":"+p.getAge());
                            sb.append("\n");
                        }
                        String data=sb.toString();
                        Toast.makeText(MainActivity.this, ""+ data, Toast.LENGTH_SHORT).show();                    }
                });
            };


        });
    }
}