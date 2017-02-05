package com.felix.travel.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * Created by felixlin on 2017/2/2.
 */
public class DBUtils {

    private Context mContext;

    public DBUtils(Context context){
        this.mContext = context;
    }

    public DaoSession getDaoSession(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext,"felix-db",null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


}
