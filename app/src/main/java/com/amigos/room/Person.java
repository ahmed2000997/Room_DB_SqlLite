package com.amigos.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName ="Person" )
public class Person
{
    @ColumnInfo(name = "ID_PERSON")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "ID_NAME")

    public String name;
    @ColumnInfo(name = "ID_AGE")

    public String age;
@Ignore
    public Person() {

    }
    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
