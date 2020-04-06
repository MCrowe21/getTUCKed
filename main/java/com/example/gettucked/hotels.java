package com.example.gettucked;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class hotels extends AppCompatActivity {

    TextView name;
    TextView desc;
    ImageView image;
    ImageView roomIMG1, roomIMG2, roomIMG3;
    TextView room1_name, room2_name, room3_name, room1_rate, room2_rate, room3_rate;
    public int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotels_layout);

        image = findViewById(R.id.image_fullsize);
        name = findViewById(R.id.textV_name);
        desc = findViewById(R.id.textV_descript);

        roomIMG1 = findViewById(R.id.room1_img);
        roomIMG2 = findViewById(R.id.room2_img);
        roomIMG3 = findViewById(R.id.room3_img);

        room1_name = findViewById(R.id.textVRoom1_name);
        room2_name = findViewById(R.id.textVRoom2_name);
        room3_name = findViewById(R.id.textVRoom3_name);

        room1_rate = findViewById(R.id.textVroom1_rate);
        room2_rate = findViewById(R.id.textVroom2_rate);
        room3_rate = findViewById(R.id.textVroom3_rate);

        Intent intent = getIntent();

        size = intent.getIntExtra("size", 0);
        name.setText(intent.getStringExtra("city"));
        desc.setText(intent.getStringExtra("description"));
        image.setImageResource(intent.getIntExtra("hotel_image", 0));

        room1_name.setText(intent.getStringExtra("room1"));
        room1_name.setText(intent.getStringExtra("room2"));
        room1_name.setText(intent.getStringExtra("room3"));

        room1_rate.setText(intent.getStringExtra("rate1"));
        room1_rate.setText(intent.getStringExtra("rate2"));
        room1_rate.setText(intent.getStringExtra("rate3"));

        Bundle intArray_fromIntent = getIntent().getExtras();
        int[] room1_IDs = new int[0];
        if (intArray_fromIntent != null)
            room1_IDs = intArray_fromIntent.getIntArray("roomIntArray1");
        int[] room2_IDs = new int[0];
        if (intArray_fromIntent != null)
            room2_IDs = intArray_fromIntent.getIntArray("roomIntArray2");
        int[] room3_IDs = new int[0];
        if (intArray_fromIntent != null) {
            room3_IDs = intArray_fromIntent.getIntArray("roomIntArray3");
            Log.d("if", "does int array!=null");
        }
        // Was originally trying to do this section dynamic like the city_row.xml,
        // I left rooms_gridview.xml in the package to show my set up. can update for future improvements
        // hope this actually works lol - Mike
        int idx = setindex(name.getText().toString());
        Integer l = idx;
        Log.d("idx", l.toString());
        if (room1_IDs != null) roomIMG1.setImageResource(room1_IDs[idx]);
        if (room2_IDs != null) roomIMG2.setImageResource(room2_IDs[idx]);
        if (room3_IDs != null) roomIMG3.setImageResource(room3_IDs[idx]);
//potential fix for better dynamic viewing - Mike
   /*     rooms=findViewById(R.id.gridVrooms);
        RoomAdapter room_adapt = new RoomAdapter();
        rooms.setAdapter(room_adapt);
        */
    }

    public void room_click(View v) {
        Intent i = new Intent(this, date_select.class);
        this.startActivity(i);
    }

    private int setindex(String name) {
        int i = -1;
        switch (name) {
            case "Kamloops":
                i = 0;
                break;
            case "Kelowna":
                i = 1;
                break;
            case "Vancouver":
                i = 2;
                break;
            case "Revelstoke":
                i = 3;
                break;
            default:
                return i;
        }
        return i;
    }//potential fix for better dynamic viewing - Mike

 /*   public class RoomAdapter  extends BaseAdapter {


        @Override
        public int getCount() {
            return size;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.rooms_grideview, null);
            ImageView room1 = v.findViewById(R.id.room_img);
            TextView type1 = v.findViewById(R.id.roomType);
            TextView price1 = v.findViewById(R.id.textVRoomPrice);
            type1.setText(room_1[position]);
            room1.setImageResource(room_imgs1[position]);
            price1.setText(price_1[position]);

            ImageView room2 = v.findViewById(R.id.room_img);
            TextView type2 = v.findViewById(R.id.roomType);
            TextView price2 = v.findViewById(R.id.textVRoomPrice);
            type2.setText(room_2[position]);
            room2.setImageResource(room_imgs2[position]);
            price2.setText(price_2[position]);

            ImageView room3 = v.findViewById(R.id.room_img);
            TextView type3 = v.findViewById(R.id.roomType);
            TextView price3 = v.findViewById(R.id.textVRoomPrice);
            type3.setText(room_3[position]);
            room3.setImageResource(room_imgs3[position]);
            price3.setText(price_3[position]);
            return v;
        }
    }
    */
}
