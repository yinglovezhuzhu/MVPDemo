/*
 * Copyright (C) 2016. The Android Open Source Project.
 *
 *          yinglovezhuzhu@gmail.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.xiaoying.mvpdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xiaoying.mvpdemo.bean.UserBean;

/**
 * Created by yunying on 2016/8/11.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 3;

    private static final String TB_USER = "User";

    private static DBHelper mInstance = null;

    public static DBHelper getInstance(Context context) {
        synchronized (DBHelper.class) {
            if(null == mInstance) {
                mInstance = new DBHelper(context.getApplicationContext());
            }
            return mInstance;
        }
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_USER + "(_id TEXT PRIMARY KEY, name TEXT, age INTEGER, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }


    public UserBean loadUser(String id) {
        SQLiteDatabase db = getReadableDatabase();
        if(db.isOpen()) {
            Cursor cursor = db.query(TB_USER, null, "_id=?", new String[] {id, }, null, null, null);
            if(null == cursor) {
                db.close();
                return null;
            }
            UserBean user = null;
            if(cursor.moveToFirst()) {
                user = new UserBean();
                user.setId(cursor.getString(cursor.getColumnIndex("_id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            }
            cursor.close();
            db.close();
            return user;
        }
        return null;
    }

    public boolean saveUser(UserBean user) {
        SQLiteDatabase db = getWritableDatabase();
        if(db.isOpen()) {
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                values.put("_id", user.getId());
                values.put("name", user.getName());
                values.put("age", user.getAge());
                values.put("email", user.getEmail());
                long id = db.insert(TB_USER, "", values);
                db.setTransactionSuccessful();
                return id != -1;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

}
