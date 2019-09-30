package com.phong.baitaprenluyen10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnLayHinh;
    ImageView imgHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLayHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyLayHinh();
            }
        });
    }

    private void XuLyLayHinh() {
        //Thông qua ImplicitIntent để lấy hình trong điện thoại:
        Intent intent = new Intent();
        intent.setType("image/*");//Lấy toàn bộ hình
        intent.setAction(Intent.ACTION_GET_CONTENT);//Lấy nội dung ra
        //Lấy hình trong phần cứng đt thì thông qua intent.createChoose
        startActivityForResult(intent.createChooser(intent,"Chọn hình"),113);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 113 && resultCode == RESULT_OK)//resultCode là kết quả khi user bấm chọn hình ok thì ta lấy đc giá trị thông qua getData
        {
            try {
                //Lấy hình ảnh:
                Uri imgUri = data.getData();
                //Đưa Uri này về Bitmap đề hiển thị:
                Bitmap hinh = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                //Show hình:
                imgHinh.setImageBitmap(hinh);
            }
            catch (Exception ex)
            {
                Log.e("Lỗi",ex.toString());
            }
        }
    }

    private void addControls() {
        btnLayHinh = (Button) findViewById(R.id.btnLayHinh);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
    }
}
