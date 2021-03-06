package com.example.online;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView3 extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter3 contactAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout3); //pehle to iss activity ka layout attach kar dia ;simple hai
        listView=(ListView)findViewById(R.id.listview36);  //ab list view namak widget ko pakad liya iss variable mei
                                                        //ab is widget par adapter lagate hai. vo adapter rakhta hai apne paas modified MKC

        contactAdapter=new ContactAdapter3(this,R.layout.row_layout3);//yaha par adapter paida kiya hai ayr usko hashing function se attach kar dia hai
        listView.setAdapter(contactAdapter);//yaha par list view k saath adapter ko attach kar dia
        json_string=getIntent().getExtras().getString("json_data");//ye apna data xD ab aa raa hai MC.  ab bas data ko adapter mei paas karna hai jo hashing function mei
                                                                   // paas kar dega aur jaadu bina kisi logic k patient !!
                                                                    // nai nai logic toh hai ContactAdapter.java mei hai

        //listview with custom adapter and custom layout
            try
            {
                jsonObject = new JSONObject(json_string);
                jsonArray=jsonObject.getJSONArray("server_response");
                int count=0;
//                String name,email,mobile;
                String adhar_id,date,hospital_name,hospital_id,doctor_name,diseases,special_remarks,medication;
                while(count<jsonArray.length())
                {

                    JSONObject JO=jsonArray.getJSONObject(count);
                    adhar_id=JO.getString("adhar_id");
                    date=JO.getString("date");
                    hospital_name=JO.getString("hospital_name");
                    hospital_id=JO.getString("hospital_id");
                    doctor_name=JO.getString("doctor_name");
                    diseases=JO.getString("diseases");
                    special_remarks=JO.getString("special_remarks");
                    medication=JO.getString("medication");


                    Contacts3 contacts=new Contacts3(adhar_id,date,hospital_name,hospital_id,doctor_name,diseases,special_remarks,medication);

                    contactAdapter.add(contacts);// ye ,ye kia paas adapter mei jo ab hashing function mei paas karega logic k thru
                    count++;
                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
    }
}
