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
    private static String FELIX_TRAVEL_DB_NAME = "felix-db";

    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;


    public static DaoSession getDaoSession(Context context){
        if(mDaoSession == null){
            initDB(context);
        }
        return mDaoSession;
    }

    private static void clearSession(){
        if(mDaoSession != null){
            mDaoSession.clear();
        }
    }

    private static void initDB(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, FELIX_TRAVEL_DB_NAME, null);
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

}
