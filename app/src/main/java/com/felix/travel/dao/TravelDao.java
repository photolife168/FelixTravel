package com.felix.travel.dao;

import de.greenrobot.dao.internal.DaoConfig;
import greendao.dao.DaoSession;
import greendao.dao.TravelGreenDao;

/**
 * Created by felixlin on 2017/3/5.
 */

public class TravelDao extends TravelGreenDao {

    public TravelDao(DaoConfig config) {
        super(config);
    }

    public TravelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }
}
