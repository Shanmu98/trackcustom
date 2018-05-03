package com.startup.naveen.trackcustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    public TextView e1,e2;
    public Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final String name=getIntent().getExtras().getString("name");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.food);
        final String hoteln=getIntent().getExtras().getString("hotel");
        e1=(TextView) findViewById(R.id.e1);
        e2=(TextView) findViewById(R.id.eee);
        final LinearLayout l1=(LinearLayout)findViewById(R.id.ll);
        b1=(Button)findViewById(R.id.b1);
        b2=(Button)findViewById(R.id.bb);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Firebase fm2=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln+"/cusname");
                fm2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        if(dataSnapshot.exists())
                        {
                            String val = dataSnapshot.getValue(String.class);
                            String h[]=val.split(",");
                            String up="";
                            for(int y=0;y<h.length;y++)
                            {
                                if(!(h[y].equals(name)))
                                {
                                    up=up+h[y]+",";
                                }
                            }
                            //val=val.replaceFirst(name,"");
                            //final String names[]=val.split(",");
                            Firebase fm3=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln);
                            fm3.child("cusname");
                            fm3.setValue(val);
                            Firebase fm4=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln+"/customers/");
                            fm4.child(name).removeValue();
                            Intent i=new Intent(Main3Activity.this,Main2Activity.class);
                            i.putExtra("hotel",hoteln);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main3Activity.this,MapsActivity.class);
                i.putExtra("name",name);
                i.putExtra("hotel",hoteln);
                startActivity(i);
            }
        });
        e1.setText(name);
        Firebase fm1=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln+"/customers/"+name+"/orders");
        fm1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    String orders = dataSnapshot.getValue(String.class);
                    String quant[] = orders.split(",");

                    EditText ed;
                    for (int u = 0; u < quant.length; u++) {
                        ed = new EditText(Main3Activity.this);
                        String zz[] = quant[u].split(";");
                        String ans = zz[0] + " : " + zz[1];
                        ed.setText(ans);
                        ed.setInputType(InputType.TYPE_NULL);
                        ed.setClickable(false);
                        ed.setLayoutParams(new LinearLayout.LayoutParams(1000, 200));
                        l1.addView(ed);
                    }
                    //ed.setHint("enter no of quantity or addson");
                    // Toast.makeText(Main3Activity.this,orders, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Firebase fm2=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln+"/customers/"+name+"/nofs");
        fm2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String orders = dataSnapshot.getValue(String.class);
                e2.setText(orders);
                //ed.setHint("enter no of quantity or addson");
                // Toast.makeText(Main3Activity.this,orders, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}

