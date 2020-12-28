package com.example.ezpark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "ezPark_Database.db";

    private static final String TABLE_USER = "User";
    private static final String COLUMN_USER_ID = "ID";
    private static final String COLUMN_USER_USERNAME = "Username";
    private static final String COLUMN_USER_PASSWORD = "Password";

    private static final String TABLE_PARKING = "Parking";
    private static final String COLUMN_PARKING_ID = "ID";
    private static final String COLUMN_PARKING_NAME = "Name";
    private static final String COLUMN_PARKING_NUMSPACES = "NumSpaces";
    private static final String COLUMN_PARKING_CITY = "City";
    private static final String COLUMN_PARKING_LONGITUDE = "Longitude";
    private static final String COLUMN_PARKING_LATITUDE = "Latitude";

    private static final String TABLE_RESERVATION = "Reservation";
    private static final String COLUMN_RESERVATION_ID = "ID";
    private static final String COLUMN_RESERVATION_USERNAME = "Username";
    private static final String COLUMN_RESERVATION_CITY = "City";
    private static final String COLUMN_RESERVATION_TIME = "Time";
    private static final String COLUMN_RESERVATION_DATE = "Date";
    private static final String COLUMN_RESERVATION_PARKING = "Parking";
    private static final String COLUMN_RESERVATION_QR = "QR";

    private String CREATE_RESERVATION_TABLE = "CREATE TABLE " + TABLE_RESERVATION + "(" + COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_RESERVATION_USERNAME + " TEXT," +
            COLUMN_RESERVATION_CITY + " TEXT," + COLUMN_RESERVATION_TIME + " TEXT," + COLUMN_RESERVATION_DATE + " TEXT," + COLUMN_RESERVATION_PARKING + " TEXT," + COLUMN_RESERVATION_QR + " BLOB)";
    private String DROP_RESERVATION_TABLE = "DROP TABLE IF EXISTS " + TABLE_RESERVATION;

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_USERNAME + " TEXT," +
            COLUMN_USER_PASSWORD + " TEXT" + ")";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    private String CREATE_PARKING_TABLE = "CREATE TABLE " + TABLE_PARKING + "(" + COLUMN_PARKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PARKING_NAME +
            " TEXT," + COLUMN_PARKING_NUMSPACES + " INTEGER," + COLUMN_PARKING_CITY + " TEXT," + COLUMN_PARKING_LONGITUDE + " REAL," + COLUMN_PARKING_LATITUDE + " REAL" + ")";
    private String DROP_PARKING_TABLE = "DROP TABLE IF EXISTS " + TABLE_PARKING;
    private String INSERT_PARKINGS_SKOPJE = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Pedagoshka', 50, 'Skopje', 42.00070188988473, 21.404679633586777), " +
            "('Parking Treska', 100, 'Skopje', 41.99479100763139, 21.412879503191263), " +
            "('Parking Nova Makedonija', 100, 'Skopje', 41.99361643057116, 21.42246666210994), " +
            "('Parking Centar', 80, 'Skopje', 41.992580764969254, 21.436338248007168), " +
            "('Parking Aleksandar Palace', 60, 'Skopje', 42.01164663527725, 21.406172345805075), " +
            "('Parking Boris Trajkovski', 200, 'Skopje', 42.01005177214438, 21.4054679723193), " +
            "('Parking Aqua Park', 70, 'Skopje', 42.01058339894158, 21.404257204346454), " +
            "('Parking FEIT', 50, 'Skopje', 42.00539447683364, 21.408866785535977)";
    private String INSERT_PARKINGS_OHRID = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Dolni Saraj', 40, 'Ohrid', 41.112010611017936, 20.79355699338196), " +
            "('Parking Pristanishte', 60, 'Ohrid', 41.112580978229396, 20.799405033763172), " +
            "('Parking Upper Gate', 50, 'Ohrid', 41.11524190802744, 20.794921726994783), " +
            "('Parking Gradska Bolnica', 70, 'Ohrid', 41.11595745886436, 20.81584107109395), " +
            "('Parking Chinar', 30, 'Ohrid', 41.11624269224542, 20.801098624330912)";
    private String INSERT_PARKINGS_KUMANOVO = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Sports Hall', 100, 'Kumanovo', 42.13229084095623, 21.72663402067114), " +
            "('Parking Ramstore', 60, 'Kumanovo', 42.130414765670636, 21.720792770176182), " +
            "('Parking Sokolana', 80, 'Kumanovo', 42.12886409176451, 21.718363717171417)";
    private String INSERT_PARKINGS_PRILEP = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Karpalak', 70, 'Prilep', 41.34728297447863, 21.556245111864357), " +
            "('Parking Nova', 40, 'Prilep', 41.34806684318439, 21.560922233416026), " +
            "('Parking Kocho Racin', 50, 'Prilep', 41.343497981848614, 21.554589931216942)";
    private String INSERT_PARKINGS_BITOLA = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Eliksir', 100, 'Bitola', 41.026925040397494, 21.333031583708685), " +
            "('Parking Bado', 60, 'Bitola', 41.02458807996646, 21.32143721713142), " +
            "('Parking Clinic Bitola', 50, 'Bitola', 41.02371784639283, 21.318336532592834), " +
            "('Parking Tinex', 30, 'Bitola', 41.02290529201813, 21.315935508005595)";
    private String INSERT_PARKINGS_STRUMICA = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Pandev', 100, 'Strumica', 41.44531340910432, 22.634175167075725), " +
            "('Parking Tinex', 50, 'Strumica', 41.438308022664046, 22.64160522355162), " +
            "('Parking Theater Strumica', 60, 'Strumica', 41.4388701801081, 22.636474510870833)";
    private String INSERT_PARKINGS_VELES = "INSERT INTO " + TABLE_PARKING + "(" + COLUMN_PARKING_NAME + ", " + COLUMN_PARKING_NUMSPACES + ", " + COLUMN_PARKING_CITY +
            ", " + COLUMN_PARKING_LATITUDE + ", " + COLUMN_PARKING_LONGITUDE + ") VALUES('Parking Elektrometal', 30, 'Veles', 41.715558993023414, 21.765739118139603), " +
            "('Parking Blazhe Koneski', 60, 'Veles', 41.716643493502836, 21.775090427987795), " +
            "('Parking Bazen Veles', 50, 'Veles', 41.719903570488455, 21.77303487269923)";



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PARKING_TABLE);
        db.execSQL(CREATE_RESERVATION_TABLE);
        db.execSQL(INSERT_PARKINGS_BITOLA);
        db.execSQL(INSERT_PARKINGS_KUMANOVO);
        db.execSQL(INSERT_PARKINGS_SKOPJE);
        db.execSQL(INSERT_PARKINGS_PRILEP);
        db.execSQL(INSERT_PARKINGS_OHRID);
        db.execSQL(INSERT_PARKINGS_STRUMICA);
        db.execSQL(INSERT_PARKINGS_VELES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PARKING_TABLE);
        db.execSQL(DROP_RESERVATION_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_USERNAME, user.getUsername());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addReservation(Reservation reservation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RESERVATION_CITY, reservation.getCity());
        values.put(COLUMN_RESERVATION_USERNAME, reservation.getUsername());
        values.put(COLUMN_RESERVATION_TIME, reservation.getTime());
        values.put(COLUMN_RESERVATION_DATE, reservation.getDate());
        values.put(COLUMN_RESERVATION_PARKING, reservation.getParking());
        Bitmap bitmap = reservation.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        byte[] pic = stream.toByteArray();
        values.put(COLUMN_RESERVATION_QR,pic);

        db.insert(TABLE_RESERVATION,null, values);
        db.close();
    }

    public int checkReservations(String city, String time, String date, String parking){
        String[] columns = {
                COLUMN_RESERVATION_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_RESERVATION_CITY + " = ?" + " AND " + COLUMN_RESERVATION_TIME + " = ?" + " AND " + COLUMN_RESERVATION_DATE + " = ?" + " AND " + COLUMN_RESERVATION_PARKING + " = ?";
        String[] selectionArgs = {city,time,date,parking};

        Cursor cursor = db.query(TABLE_RESERVATION,columns,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount;
    }


    public boolean checkUser(String username){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USER,columns,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String username, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_USERNAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(TABLE_USER,columns,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount > 0){
            return true;
        }

        return false;
    }

    public ArrayList<Parking> getParkings(String city) {

        String[] columns = {
                COLUMN_PARKING_ID,
                COLUMN_PARKING_NAME,
                COLUMN_PARKING_CITY,
                COLUMN_PARKING_NUMSPACES,
                COLUMN_PARKING_LATITUDE,
                COLUMN_PARKING_LONGITUDE
        };

        String sortOrder =
                COLUMN_PARKING_NAME + " ASC";
        ArrayList<Parking> ParkingList = new ArrayList<Parking>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_PARKING_CITY + " = ?";
        String[] selectionArgs = {city};


        Cursor cursor = db.query(TABLE_PARKING, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                Parking parking = new Parking();
                parking.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_ID))));
                parking.setNumSpaces(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_NUMSPACES))));
                parking.setCity(city);
                parking.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_NAME)));
                parking.setLatitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_LATITUDE))));
                parking.setLongitude(Double.parseDouble(cursor.getString(cursor.getColumnIndex(COLUMN_PARKING_LONGITUDE))));
                ParkingList.add(parking);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ParkingList;
    }

    public ArrayList<Reservation> getReservations(String username) {

        String[] columns = {
                COLUMN_RESERVATION_ID,
                COLUMN_RESERVATION_USERNAME,
                COLUMN_RESERVATION_PARKING,
                COLUMN_RESERVATION_CITY,
                COLUMN_RESERVATION_DATE,
                COLUMN_RESERVATION_TIME,
                COLUMN_RESERVATION_QR
        };

        String sortOrder =
                COLUMN_RESERVATION_ID + " ASC";
        ArrayList<Reservation> ReservationList = new ArrayList<Reservation>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_RESERVATION_USERNAME + " = ?";
        String[] selectionArgs = {username};


        Cursor cursor = db.query(TABLE_RESERVATION, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        if (cursor.moveToFirst()) {
            do {
                Reservation reservation = new Reservation();
                reservation.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_ID))));
                reservation.setParking(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_PARKING)));
                reservation.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_CITY)));
                reservation.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_DATE)));
                reservation.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_TIME)));
                reservation.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_USERNAME)));
                Bitmap pic = BitmapFactory.decodeByteArray(cursor.getBlob(cursor.getColumnIndex(COLUMN_RESERVATION_QR)),0,cursor.getBlob(cursor.getColumnIndex(COLUMN_RESERVATION_QR)).length);
                reservation.setBitmap(pic);
                ReservationList.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ReservationList;
    }

    public boolean checkUsernameReservations(String username){
        String[] columns = {
                COLUMN_RESERVATION_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_RESERVATION_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_RESERVATION,columns,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount < 3){
            return true;
        }

        return false;
    }

    public void deleteReservation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_RESERVATION, COLUMN_RESERVATION_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }



}
