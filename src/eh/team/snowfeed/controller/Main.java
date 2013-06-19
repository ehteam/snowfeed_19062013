package eh.team.snowfeed.controller;

import java.util.List;

import android.os.Bundle;
import eh.team.snowfeed.model.*;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        //database test..
        
        DatabaseAccessor databaseAccessor = new DatabaseAccessor(this);
        
        TextView txv = (TextView)findViewById(R.id.textviewMain);
        txv.setText("Inserting...");
        databaseAccessor.addUser(new user("Mido","123456","mjavax@gmail.com","022222222"));
        databaseAccessor.addUser(new user("mohammed","123456","email@com","22333211"));
        
        txv.append("\n");
        txv.append("Reading Data \n");
        
        List<user> users = databaseAccessor.getAllUsers();
        
        for(user _user:users)
        {
        	String log ="Id:"+_user.getId()+"\n username: "+_user.getUsername()+" \n password: "+_user.getPassword()+"\n Email: "+_user.getEmail()+"\n Phone: "+_user.getPhone()+"\n ----- \n";
        	txv.append(log);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
