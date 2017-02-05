package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyTravelDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "greendao");
        Entity box = schema.addEntity("Travel");
        box.addIdProperty();
        box.addStringProperty("area_name");
        box.addStringProperty("area_station");
        box.addStringProperty("area_pic");
        box.addStringProperty("area_desc");
        new DaoGenerator().generateAll(schema, "app/src/main/FelixGreenDAO");
    }
}
