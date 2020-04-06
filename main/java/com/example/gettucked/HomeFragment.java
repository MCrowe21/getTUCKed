package com.example.gettucked;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    GridView cities;
    GridView rooms;
    public JSONArray objArray;

    public static String[] city_names;
    public static String[] descrip;
    public int[] listImages;
    public static int[] hotelImages;

    public static String[] room_1;
    public static String[] room_2;
    public static String[] room_3;

    public static String[] price_1;
    public static String[] price_2;
    public static String[] price_3;

    public static int[] room_imgs1;
    public static int[] room_imgs2;
    public static int[] room_imgs3;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("test", "homefrag oncreat method");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        try {
            Log.d("crash", "start of try block");
            String cheese = getJSONString();
            JSONObject jsonObject = new JSONObject(cheese);
            objArray = jsonObject.getJSONArray("locations");
            int size = objArray.length();
            city_names = new String[size];
            descrip = new String[size];
            listImages = new int[size];
            hotelImages = new int[size];

            room_1 = new String[size];
            room_2 = new String[size];
            room_3 = new String[size];

            price_1 = new String[size];
            price_2 = new String[size];
            price_3 = new String[size];

            room_imgs1 = new int[size];
            room_imgs2 = new int[size];
            room_imgs3 = new int[size];


            Integer t = size;
            Log.d("size", t.toString());

            for (int i = 0; i < size; i++) {
                JSONObject city = objArray.getJSONObject(i);
                city_names[i] = city.getString("city");
                descrip[i] = city.getString("description");

                String imageName = city.getString("list_image");
                int imageID = getResources().getIdentifier(imageName, "drawable", getActivity().getPackageName());
                listImages[i] = imageID;

                String desc_imageName = city.getString("hotel_image");
                int image_descID = getResources().getIdentifier(desc_imageName, "drawable", getActivity().getPackageName());
                hotelImages[i] = image_descID;

                String room = city.getString("room1_type");
                room_1[i] = room;
                room = city.getString("room2_type");
                room_2[i] = room;
                room = city.getString("room3_type");
                room_3[i] = room;

                String price = city.getString("room1_rate");
                price_1[i] = price;
                price = city.getString("room2_rate");
                price_2[i] = price;
                price = city.getString("room3_rate");
                price_3[i] = price;

                String room_imageName = city.getString("room1_img");
                int roomImage_ID = getResources().getIdentifier(room_imageName, "drawable", getActivity().getPackageName());
                room_imgs1[i] = roomImage_ID;

                room_imageName = city.getString("room2_img");
                roomImage_ID = getResources().getIdentifier(room_imageName, "drawable", getActivity().getPackageName());
                room_imgs2[i] = roomImage_ID;

                room_imageName = city.getString("room3_img");
                roomImage_ID = getResources().getIdentifier(room_imageName, "drawable", getActivity().getPackageName());
                room_imgs3[i] = roomImage_ID;


                Integer q = listImages[i];
                Integer w = hotelImages[i];

                Log.d("arrays", city_names[i]);
                Log.d("arrays", descrip[i]);
                Log.d("arrays", q.toString());
                Log.d("arrays", w.toString());


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        cities = view.findViewById(R.id.gridV_cities);
        CityAdapter city_adapt = new CityAdapter();
        cities.setAdapter(city_adapt);


        cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("here", "in onclick");
                Intent intent = new Intent(getActivity(), hotels.class);
                intent.putExtra("city", city_names[position]);
                intent.putExtra("description", descrip[position]);
                intent.putExtra("hotel_image", hotelImages[position]);
                intent.putExtra("roomIntArray1", room_imgs1);
                intent.putExtra("roomIntArray2", room_imgs2);
                intent.putExtra("roomIntArray3", room_imgs3);
                intent.putExtra("room1", room_1[position]);
                intent.putExtra("room2", room_2[position]);
                intent.putExtra("room3", room_3[position]);
                intent.putExtra("rate1", price_1[position]);
                intent.putExtra("rate2", price_2[position]);
                intent.putExtra("rate3", price_3[position]);
                int size = objArray.length();
                intent.putExtra("size", size);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

    public String getJSONString() {
        String jayson;
        try {
            InputStream is = getActivity().getAssets().open("locations.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jayson = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jayson;
    }

    private class CityAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return objArray.length();
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
            View v = getLayoutInflater().inflate(R.layout.city_rows, null);
            ImageView cityimage = v.findViewById(R.id.city_img);
            TextView cityname = v.findViewById(R.id.cityName);
            cityname.setText(city_names[position]);
            cityimage.setImageResource(listImages[position]);
            return v;
        }
    }

}
