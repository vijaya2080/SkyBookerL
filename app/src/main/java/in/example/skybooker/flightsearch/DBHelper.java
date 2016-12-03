package in.example.skybooker.flightsearch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.apache.commons.codec.DecoderException;

import in.example.skybooker.Serializer;

/**
 * Created by nbhag on 9/19/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String DATA_BASENAME = "SKYBOOKER";
    private static final int DATA_VERSION = 9;
    public static final String TABLE_AddTraveller = "TravellerGroup";
    //public static final String TABLE_RoundTrip = "RoundTripFragment";

    //public static final String TABLE_Addcontact = "AddContact";


    public static final String ADDTRAVELLERID = "travellerId";
    public static final String ADDTRAVELLEROBJ = "travellerobj";
    public static final String ADDTRAVELLERKEY = "travellerKey";
   /* public static final String ADDROUNDTRIPID = "roundtripId";
    public static final String ADDROUNDTRIPOBJ = "roundtripObj";
*/

    String traveller_Group = " CREATE TABLE " + TABLE_AddTraveller + "(  travellerId integer primary key autoincrement, travellerobj BLOB, travellerKey TEXT )";

    //  String fragment_RoundTrip = " CREATE TABLE " + TABLE_RoundTrip + "(  roundtripId integer primary key autoincrement, roundtripObj BLOB )";

    public DBHelper(Context c) {
        super(c, DATA_BASENAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(traveller_Group);

        //db.execSQL(addcontact);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists " + TABLE_AddTraveller);

        onCreate(db);
    }

    public void insert(String tableName, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName, null, values);
        Log.i("Dbhelper", "Success");
        db.close();
    }

    public TravellersObject getObjDetails(String tableName, String key) throws DecoderException {
//        /List<GroupContact> contactList = new ArrayList<GroupContact>();
        String selectQuery = "SELECT  * FROM " + tableName+" WHERE travellerKey = '" + key + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Log.i("cursorcount...."+key,c.getCount()+"");
        Serializer ou = new Serializer();
        TravellersObject p = null;
        if (c.moveToFirst()) {
            //  do {
            p = new TravellersObject();
            p = (TravellersObject) ou.deserializeObject(c.getBlob(c.getColumnIndex(ADDTRAVELLEROBJ)));
            p.setId(c.getInt(c.getColumnIndex(ADDTRAVELLERID)));
            p.setFragmentKey(c.getString(c.getColumnIndex(ADDTRAVELLERKEY)));
            //   todos.add(p);
            Log.i("pobject",p.getFrom_City()+"");
            // } while (c.moveToNext());
        }
        return p;
    }



    public void update(String tableName, ContentValues values, String key, String idLabel) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.update(tableName, values, idLabel + " = '" + key + "'", null);
        db.close();
    }
}


    /*public ArrayList<Deals> getAllNames() throws DecoderException, IOException, ClassNotFoundException {
        ObjectUtil ou = new ObjectUtil();
        ArrayList<Deals> todos = new ArrayList<Deals>();
        String selectQuery = "SELECT  * FROM " + TABLE_addDeal;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Deals p = new Deals();
                p = (Deals) ou.deserializeObject(c.getBlob(c.getColumnIndex(OBJ_KEY)));
                p.setPatientId(c.getInt(c.getColumnIndex(ID)));
                todos.add(p);
            } while (c.moveToNext());
        }
        return todos;
    }
*//*
    public GroupContact getAllDetailsById(int id) throws DecoderException{
        String selectQuery = "SELECT  * FROM " + TABLE_Addgroup+" WHERE groupnameId = '" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        Serializer ou = new Serializer();
        GroupContact td = null;
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            td = new GroupContact();
            td = (GroupContact) ou.deserializeObject(c.getBlob(c.getColumnIndex(ADDGROUPOBJ)));
        }
        return td;
    }

    public void update(String tableName, ContentValues values, int id, String idLabel) throws IOException {
        SQLiteDatabase db=this.getWritableDatabase();
        db.update(tableName, values, idLabel + " = '" + id + "'", null);
        db.close();
    }
    public void delete(String tableName) {
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(tableName, null, null);
    }
    public void remove(String tableName, int id, int patientId, String labelName){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(tableName, labelName + "=?", new String[]{String.valueOf(id)});
        Log.i(labelName+" "+id," deleted ");
    }

    public Boolean check(int id) {
        String query = "Select * from " + TABLE_Addgroup + " where id = '"+ id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.getCount()==0)
        {
            return false;
        }
        else
            return true;
    }

}

*/
