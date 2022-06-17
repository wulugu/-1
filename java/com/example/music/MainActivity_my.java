package com.example.music;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * zhaohan creat 2019/3/6
 */
public class MainActivity_my extends AppCompatActivity {
    private ImageView imageView1,imageView2;
    private TextView textView1,textView2;
    //权限请求
    private RxPermissions rxPermissions;
    //是否拥有权限
    private boolean hasPermissions = false;
    //底部弹窗
    private BottomSheetDialog bottomSheetDialog;
    //弹窗视图
    private View bottomView;
    //存储拍完照后的图片
    private File outputImagePath;
    //启动相机标识
    public static final int TAKE_PHOTO = 1;
    //启动相册标识
    public static final int SELECT_PHOTO = 2;
    //图片控件
    private ShapeableImageView ivHead;
    //Base64
    private String base64Pic;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;
    private TextView tvVersionCode,tvVersionName;

    //Glide请求图片选项配置
    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy( DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_my);
        imageView1 = (ImageView) findViewById(R.id.located_imageView);
        textView1 = (TextView) findViewById(R.id.located_textview);
        ivHead = findViewById(R.id.iv_head);
        textView2 = (TextView)findViewById(R.id.change_id);
        imageView2 = (ImageView)findViewById(R.id.user_massege_change);
//        imageView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity_my.this,user_massege_change.class);//创建Intent对象，启动check
//                //将数据存入Intent对象
//                startActivity(intent);
//            }
//        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_my.this,activity_change_id.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);

            }
        });
        Intent intent=getIntent();
        String i =intent.getStringExtra("id" );
        textView2.setText(i);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_my.this,list_show.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_my.this,list_show.class);//创建Intent对象，启动check
                //将数据存入Intent对象
                startActivity(intent);
            }
        });


        checkVersion();
        //取出缓存
        String imageUrl = SPUtils.getString("imageUrl",null,this);
        if(imageUrl != null){
            Glide.with(this).load(imageUrl).apply(requestOptions).into(ivHead);
        }
    }
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 检查版本
     */
    @SuppressLint("CheckResult")
    private void checkVersion() {
        //Android6.0及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果你是在Fragment中，则把this换成getActivity()
            rxPermissions = new RxPermissions(this);
            //权限请求
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {//申请成功
                            showMsg("已获取权限");
                            hasPermissions = true;
                        } else {//申请失败
                            showMsg("权限未开启");
                            hasPermissions = false;
                        }
                    });
        } else {
            //Android6.0以下
            showMsg("无需请求动态权限");
        }
    }
    /**
     * 更换头像
     *
     * @param view
     */
    public void changeAvatar(View view) {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomView = getLayoutInflater().inflate(R.layout.dialog_bottom, null);
        bottomSheetDialog.setContentView(bottomView);
        bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
        TextView tvTakePictures = bottomView.findViewById(R.id.tv_take_pictures);
        TextView tvOpenAlbum = bottomView.findViewById(R.id.tv_open_album);
        TextView tvCancel = bottomView.findViewById(R.id.tv_cancel);

        //拍照
        tvTakePictures.setOnClickListener(v -> {
            showMsg("拍照");
            takePhoto();
            bottomSheetDialog.cancel();
        });
        //打开相册
        tvOpenAlbum.setOnClickListener(v -> {
            showMsg("打开相册");
            openAlbum();
            bottomSheetDialog.cancel();
        });
        //取消
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.cancel();
        });
        bottomSheetDialog.show();
    }
    /**
     * 拍照
     */
    private void takePhoto() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        outputImagePath = new File(getExternalCacheDir(),
                filename + ".jpg");
        Intent takePhotoIntent = CameraUtils.getTakePhotoIntent(this, outputImagePath);
        // 开启一个带有返回值的Activity，请求码为TAKE_PHOTO
        startActivityForResult(takePhotoIntent, TAKE_PHOTO);
    }
    /**
     * 打开相册
     */
    private void openAlbum() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        startActivityForResult(CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
    }
    /**
     * 返回到Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //拍照后返回
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //显示图片
                    displayImage(outputImagePath.getAbsolutePath());
                }
                break;
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = CameraUtils.getImageOnKitKatPath(data, this);
                    } else {
                        imagePath = CameraUtils.getImageBeforeKitKatPath(data, this);
                    }
                    //显示图片
                    displayImage(imagePath);
                }
                break;
            default:
                break;
        }
    }
    /**
     * 通过图片路径显示图片
     */
    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {
            SPUtils.putString("imageUrl",imagePath,this);
            //显示图片
            Glide.with(this).load(imagePath).apply(requestOptions).into(ivHead);

            //压缩图片
            orc_bitmap = CameraUtils.compression(BitmapFactory.decodeFile(imagePath));
            //转Base64
            base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        } else {
            showMsg("图片获取失败");
        }
    }

}

