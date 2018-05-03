package com.startup.naveen.trackcustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    public ArrayList<String> cusname=new ArrayList<String>();
    public ArrayList<String> order=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Main2Activity obj=new Main2Activity();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.food);
        Firebase.setAndroidContext(Main2Activity.this);
        final String hoteln=getIntent().getExtras().getString("hotel");
        Firebase fm1=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln+"/cusname");
        fm1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists()) {

                    //final HashMap<String,String> order=new HashMap<String, String>();
                    String val = dataSnapshot.getValue(String.class);
                    //Toast.makeText(Main2Activity.this,val,Toast.LENGTH_LONG).show();
                    final String names[]=val.split(",");
                    /*for( int j=0;j<names.length;j++)
                    {
                        final String hname=names[j];
                        Firebase fm1=new Firebase("https://foodtrack-1afcd.firebaseio.com/hotels/"+hoteln+"/customers/"+names[j]+"/orders");
                        fm1.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String orders = dataSnapshot.getValue(String.class);
                                obj.cusname.add(hname);
                                obj.order.add(orders);
                                Toast.makeText(Main2Activity.this,orders,Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                   // String name[]= (String[]) cusname.toArray();
                    //String item[]=(String[])order.toArray();
                    String name[]=new String[obj.cusname.size()];
                    String item[]=new String[obj.order.size()];
                    for(int g=0;g<obj.cusname.size();g++)
                    {
                        name[g]=obj.cusname.get(g);
                        item[g]=obj.order.get(g);
                    }
                   Toast.makeText(Main2Activity.this,obj.cusname.size(),Toast.LENGTH_LONG).show();*/
                    ListView lv=(ListView)findViewById(R.id.l1);
                    lv.setAdapter(new CustomAdap(Main2Activity.this,names));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent i1=new Intent(Main2Activity.this,Main3Activity.class);
                            i1.putExtra("name",names[i]);
                            i1.putExtra("hotel",getIntent().getExtras().getString("hotel"));
                            startActivity(i1);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
