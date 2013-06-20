package eh.team.snowfeed.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;

public class DatabaseAccessor extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION= 1;
	private static final String DATABASE_NAME = "SnowFeedDB";
	private static final String Table_USERS = "UsersTbl";
	private static final String Table_Login_History = "LoginHistory";
	//table columns USERS
	private static final String KEY_ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	
	//table columns LOGIN HISTORY
	private static final String HKEY_ID = "Id";
	private static final String H_USERNAME = "username";
	private static final String H_USERID = "UserId";
	private static final String H_DATE = "loginDate";
	private static final String H_REMEMBER = "remeber";
	
	public DatabaseAccessor(Context context)
	{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		String CREATE_USERS_TABLE_QUERY = "CREATE TABLE "+Table_USERS+ "(" +KEY_ID+" INTEGER PRIMARY KEY,"
	+USERNAME+" TEXT,"+PASSWORD+" TEXT,"+EMAIL+" TEXT,"+PHONE+" TEXT"+")";
		database.execSQL(CREATE_USERS_TABLE_QUERY);
		
		String CREATE_LOGIN_HISTORY_TABLE_QUERY = "CREATE TABLE "+Table_Login_History+ " ("+HKEY_ID+" INTEGER PRIMARY KEY,"
				+H_USERNAME+" TEXT,"+H_DATE+" TEXT,"+H_REMEMBER+" INTEGER,"+H_USERID+" INTEGER";
		database.execSQL(CREATE_LOGIN_HISTORY_TABLE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS "+Table_USERS);
		onCreate(database);
	}
	public void addUser(user _user)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(USERNAME, _user.getUsername());
		values.put(PASSWORD, _user.getPassword());
		values.put(EMAIL, _user.getEmail());
		values.put(PHONE, _user.getPhone());
		
		database.insert(Table_USERS, null,values);
		database.close();
	}
	
	public user getUser(int id)
	{
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.query(Table_USERS, new String[]{KEY_ID,USERNAME,PASSWORD,EMAIL,PHONE},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
		if(cursor !=null)
			cursor.moveToFirst();
		user _user = new user(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
		
		return _user;
	}
	
	public int getUser(String username, String password)
	{
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery("SELECT * FROM "+Table_USERS+" WHERE "+USERNAME+" ="+username.trim()+" AND"+PASSWORD+" ="+password.trim(), null);
		cursor.moveToFirst();
		
		int count = cursor.getCount();
		cursor.close();
		
		return count;
		
	}
	
	public List<user> getAllUsers()
	{
		List<user> usersList = new ArrayList<user>();
		String getUsersQuery = "SELECT * FROM "+Table_USERS;
		
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(getUsersQuery, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				user _user = new user();
				_user.setId(Integer.parseInt(cursor.getString(0)));
				_user.setUsername(cursor.getString(1));
				_user.setPassword(cursor.getString(2));
				_user.setEmail(cursor.getString(3));
				_user.setPhone(cursor.getString(4));
				
				usersList.add(_user);
			} while(cursor.moveToNext());
		}
		
		return usersList;
	}
	
	public int usersCount()
	{
		String countQuery = "SELECT * FROM "+Table_USERS;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		cursor.close();
		
		return cursor.getCount();
	}
	
	public int updateUser(user _user)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(USERNAME, _user.getUsername());
		values.put(PASSWORD, _user.getPassword());
		values.put(EMAIL, _user.getEmail());
		values.put(PHONE, _user.getPhone());
		
		return database.update(Table_USERS	, values,KEY_ID+" = ?", new String[]{String.valueOf(_user.getId())});
		
	}
	public void deleteUser(user _user)
	{
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete(Table_USERS, KEY_ID+" ? ", new String[]{String.valueOf(_user.getId())});
		database.close();
	}
	public void Login(user _user,boolean remember)
	{
		Time now = new Time();
		now.setToNow();
	if(getUser(_user.username, _user.password)>0)
	{
		SQLiteDatabase database= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(H_USERNAME,_user.username);
		values.put(H_USERID, _user.id);
		values.put(H_DATE, now.toString());
		values.put(H_REMEMBER, remember);
		
		 database.insert(Table_Login_History, null, values);
			 
	}	
	}
	
	
}
