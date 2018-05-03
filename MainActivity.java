package com.startup.naveen.trackcustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
public class MainActivity extends AppCompatActivity {

    private Firebase ref;
    public EditText e1, e2,e3,e4;
    public Button b1, b2;
    public int coun=0;
    public int nof;
    static public int nof1;
    public void getnof()
    {
        Firebase.setAndroidContext(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.food);
        final MainActivity mm=new MainActivity();
        e1 = (EditText) findViewById(R.id.e1);
        e4 = (EditText) findViewById(R.id.e4);
        e3 = (EditText) findViewById(R.id.e3);
        e2 = (EditText) findViewById(R.id.e2);
        b2 = (Button) findViewById(R.id.b2);
        b1 = (Button) findViewById(R.id.b1);
        //Toast.makeText(getApplicationContext(),"nof1="+String.valueOf(mm.nof),Toast.LENGTH_LONG).show();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"nof1="+String.valueOf(mm.nof),Toast.LENGTH_LONG).show();
                final String hotel=e1.getText().toString().trim();
                //Toast.makeText(getApplicationContext(),"welcome "+user,Toast.LENGTH_LONG).show();
                Firebase fm=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hotel);
                Firebase fg=fm.child("password");
                fg.setValue(e2.getText().toString());
                Toast.makeText(getApplicationContext(),hotel+" successfully registered",Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
            }
      /*  ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                e1.setText(val);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/
        });

        b2=(Button)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String uname=e3.getText().toString().trim();
                final String pass=e4.getText().toString().trim();
                Firebase fm=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+uname+"/password");
                fm.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        String pass1=dataSnapshot.getValue(String.class);
                        if(pass.equals(pass1))
                        {
                            //Toast.makeText(getApplicationContext(),pass+" "+pass1,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(MainActivity.this,Main2Activity.class);
                            i.putExtra("hotel",uname);
                            startActivity(i);
                            //Intent i=new Intent(login.this,MapsActivity.class);
                            //i.putExtra("name",uname);
                            //startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"dont have an account ??",Toast.LENGTH_LONG).show();

                        }
                        // Firebase fm1=new Firebase("https://androidtrainee-7e562.firebaseio.com/user/usernames/"+e1+"/password");
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError)
                    {

                    }
                });
                //Intent i=new Intent(MainActivity.thisass);
                //startActivity(i);
            }
        });
    }
}