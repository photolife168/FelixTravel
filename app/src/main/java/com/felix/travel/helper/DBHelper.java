package com.felix.travel.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * Created by felixlin on 2017/2/2.
 */
public class DBHelper {

    private Context mContext;

    public DBHelper(Context context){
        this.mContext = context;
    }

    public DaoSession getDaoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext,"felix-db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


}
