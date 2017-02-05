package pl.surecase.eu.schema;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by felixlin on 2017/2/5.
 */
public class Travel {

    public static final String TABLE_NAME = "travel";
    public static final String CLASS_NAME = "Travel";
    public static final String DAO_NAME = "TravelGreenDao";

    public static final String FIELD_AREA_NAME = "area_name";
    public static final String FIELD_AREA_STATION = "area_station";
    public static final String FIELD_AREA_PIC = "area_pic";
    public static final String FIELD_AREA_DESC = "area_desc";

    private static Entity travel;

    public static Entity getTravel(){
        return travel;
    }

    public static void addField(Schema schema){
        travel = schema.addEntity(CLASS_NAME);
        travel.setClassNameDao(DAO_NAME);
        travel.setTableName(TABLE_NAME);
        travel.addIdProperty().unique().autoincrement();
        travel.addStringProperty("area_name");
        travel.addStringProperty("area_station");
        travel.addStringProperty("area_pic");
        travel.addStringProperty("area_desc");
    }
}
