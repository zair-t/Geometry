package com.zair.geometry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class SaveData extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    ImageView imageView;
    EditText edName;
    EditText edDescription;
    StorageReference storageRef;
    Uri uploadUri;
    DatabaseReference db;
    String  USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        imageView = findViewById(R.id.imageView);
        edName = findViewById(R.id.name);
        edDescription = findViewById(R.id.description);
        storageRef = FirebaseStorage.getInstance().getReference("ImageDB");
        db = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    //Для выбора картинки
    public void onBtnPickImageClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Выбрать картинку"), GALLERY_REQUEST_CODE);
    }


    //Для изображения картинки на imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri imageData = data.getData();
            imageView.setImageURI(imageData);
        }
    }

    //Загружаем картинку в firebase storage
    private void uploadImage()
    {
        Bitmap bitMap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        final StorageReference imRef = storageRef.child(System.currentTimeMillis() + "im");
        UploadTask up = imRef.putBytes(byteArray);
        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return imRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                uploadUri = task.getResult();
                saveUser();
            }
        });
    }

    //Добавляем данные в базу данных
    private void saveUser()
    {
        String id = db.push().getKey();
        String name = edName.getText().toString();
        String description = edDescription.getText().toString();
        User newUser = new User(id, name, description, uploadUri.toString());
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)){
            assert id != null;
            db.child(id).setValue(newUser);
            Toast.makeText(this, "Сохранено", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this, "Вы забыли вписать название или описание", Toast.LENGTH_LONG).show();
        }
    }

    public void onSaveClick(View view) {
        uploadImage();
    }
}