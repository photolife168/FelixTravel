package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Schema;
import pl.surecase.eu.schema.Travel;

public class MyTravelDaoGenerator {

    private static final int DB_VERSION = 1;

    private static final String BEAN_PACKAGE = "greendao.bean";
    private static final String DAO_PACKAGE = "greendao.dao";
    private static final String OUTPUT_PATH = "app/src/main/FelixGreenDAO";


    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(DB_VERSION, BEAN_PACKAGE);
        schema.enableKeepSectionsByDefault();
        schema.setDefaultJavaPackageDao(DAO_PACKAGE);
        generateTable(schema);
        new DaoGenerator().generateAll(schema, OUTPUT_PATH);
    }

    private static void generateTable(Schema schema){
        generateTravelTable(schema);
    }

    private static void generateTravelTable(Schema schema){
        Travel.addField(schema);
    }
}
