package com.zair.geometry;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class Theory extends AppCompatActivity {

    ListView listView;
    List<String> listData; //для listView
    DatabaseReference db;
    String  USER_KEY = "User";
    List<User> listTemp; //для showActivity
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);
        FloatingActionButton fab = findViewById(R.id.fab); //кнопка для перехода в saveData
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Theory.this, SaveData.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.list_item, listData);
        listView.setAdapter(adapter);
        db = FirebaseDatabase.getInstance().getReference(USER_KEY);
        getDataFromDB();
        setOnClickItem();
    }

    //Показываем теорию
    private void getDataFromDB()
    {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(listData.size() > 0)listData.clear();
                if(listTemp.size() > 0)listTemp.clear();
                //пробигаемся по каждой теории(в двнном случае по юзерам), которые были когда-то созданы
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    listData.add(user.nameT);
                    listTemp.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        db.addValueEventListener(vListener);
    }

    //Переходим в showActivity
    private void setOnClickItem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = listTemp.get(position);
                Intent intent = new Intent(Theory.this, ShowActivity.class);
                intent.putExtra("user_name", user.nameT);
                intent.putExtra("user_description", user.description);
                intent.putExtra("user_image", user.imageUri);
                startActivity(intent);
            }
        });
    }
}