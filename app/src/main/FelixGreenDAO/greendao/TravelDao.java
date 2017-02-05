package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Travel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TRAVEL.
*/
public class TravelDao extends AbstractDao<Travel, Long> {

    public static final String TABLENAME = "TRAVEL";

    /**
     * Properties of entity Travel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Area_name = new Property(1, String.class, "area_name", false, "AREA_NAME");
        public final static Property Area_station = new Property(2, String.class, "area_station", false, "AREA_STATION");
        public final static Property Area_pic = new Property(3, String.class, "area_pic", false, "AREA_PIC");
        public final static Property Area_desc = new Property(4, String.class, "area_desc", false, "AREA_DESC");
    };


    public TravelDao(DaoConfig config) {
        super(config);
    }
    
    public TravelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TRAVEL' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'AREA_NAME' TEXT," + // 1: area_name
                "'AREA_STATION' TEXT," + // 2: area_station
                "'AREA_PIC' TEXT," + // 3: area_pic
                "'AREA_DESC' TEXT);"); // 4: area_desc
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TRAVEL'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Travel entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String area_name = entity.getArea_name();
        if (area_name != null) {
            stmt.bindString(2, area_name);
        }
 
        String area_station = entity.getArea_station();
        if (area_station != null) {
            stmt.bindString(3, area_station);
        }
 
        String area_pic = entity.getArea_pic();
        if (area_pic != null) {
            stmt.bindString(4, area_pic);
        }
 
        String area_desc = entity.getArea_desc();
        if (area_desc != null) {
            stmt.bindString(5, area_desc);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Travel readEntity(Cursor cursor, int offset) {
        Travel entity = new Travel( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // area_name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // area_station
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // area_pic
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // area_desc
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Travel entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setArea_name(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setArea_station(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setArea_pic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setArea_desc(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Travel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Travel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}