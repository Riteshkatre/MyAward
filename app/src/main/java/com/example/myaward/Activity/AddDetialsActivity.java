package com.example.myaward.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.myaward.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddDetialsActivity extends AppCompatActivity {
    ImageView file, photo, cameraClick;
    CircleImageView topperImage;
    TextView selectedFileTextView;
    CardView back;
    TextInputEditText txt_percentage, txt_address, txt_name, txt_mobile;
    AppCompatSpinner dropDown;
    int selectedPos = 0;
    Button add;
    ActivityResultLauncher<Intent> cameraLauncher;
    ActivityResultLauncher<Intent> galleryLauncher;
    int REQUEST_GALLERY_PERMISSION = 102;
    int REQUEST_CAMERA_PERMISSION = 101;
    File CurentPhotoFile;
    String currentPhotoPath = "";
    private ActivityResultLauncher<String> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detials);
        txt_percentage = findViewById(R.id.txt_percentage);
        selectedFileTextView = findViewById(R.id.selected_pdf_text_view);
        txt_address = findViewById(R.id.txt_address);
        txt_name = findViewById(R.id.txt_name);
        txt_mobile = findViewById(R.id.txt_mobile);
        dropDown = findViewById(R.id.dropDown);
        file = findViewById(R.id.file);
        photo = findViewById(R.id.photo);
        back = findViewById(R.id.back);
        add = findViewById(R.id.add);
        topperImage = findViewById(R.id.topperImage);
        cameraClick = findViewById(R.id.cameraClick);

        String[] st = new String[9];
        st[0] = "Select Your Relation";
        st[1] = "Son";
        st[2] = "Daughter";
        st[3] = "Brother";
        st[4] = "Sister";
        st[5] = "Father";
        st[6] = "Mother";
        st[7] = "Me";
        st[8] = "Other";
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, st);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(aa);

        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPos = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                photo.setImageURI(Uri.parse(currentPhotoPath));
                photo.setVisibility(View.VISIBLE);

            } else {
                Toast.makeText(this, "Can't Complete The Action", Toast.LENGTH_SHORT).show();
            }
        });

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Uri selectedImageUri = result.getData().getData();
                // Do something with the selected image URI
                photo.setImageURI(selectedImageUri);
                currentPhotoPath = getPathFromUri(selectedImageUri);
                CurentPhotoFile = new File(currentPhotoPath);
                photo.setVisibility(View.VISIBLE);


            } else {
                Toast.makeText(this, "Can't Complete The Action", Toast.LENGTH_SHORT).show();
            }
        });

        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                if (uri != null) {
                    if (isImageFile(uri)) {
                        photo.setImageURI(uri);
                        selectedFileTextView.setText("");
                    } else if (isPdfFile(uri)) {
                        String pdfName = getFileName(uri);
                        selectedFileTextView.setText(pdfName);
                        photo.setImageURI(uri);

                    } else {
                        Toast.makeText(AddDetialsActivity.this, "Unsupported file type", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddDetialsActivity.this, "Can't Complete The Action", Toast.LENGTH_SHORT).show();

                }
            }
        });

        file.setOnClickListener(v -> {
            try {
                currentPhotoPath = "";
                showImagePickerDialog();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        back.setOnClickListener(v -> {
            finish();
        });
        add.setOnClickListener(v -> {
            if (Validation()) {
                Toast.makeText(AddDetialsActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }
        });

        cameraClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    currentPhotoPath = "";
                    showImageDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean isImageFile(Uri uri) {
        String type = getContentResolver().getType(uri);
        return type != null && type.startsWith("image/");
    }

    private boolean isPdfFile(Uri uri) {
        String type = getContentResolver().getType(uri);
        return type != null && type.equals("application/pdf");
    }

    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (columnIndex != -1) {
                        result = cursor.getString(columnIndex);
                    }
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }

    private String getPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if (cursor == null) return null;

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();

        return path;
    }

    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery", "Files/Pdf."}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (checkCameraPermission()) {
                            openCamera();
                        }
                        break;
                    case 1:
                        if (checkGalleryPermission()) {
                            openGallery();
                        }
                        break;
                    case 2:
                        filePickerLauncher.launch("*/*");
                        break;
                }
            }
        });
        builder.show();
    }

    private boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            return false;
        }
        return true;
    }

    private boolean checkGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY_PERMISSION);
            return false;
        }
        return true;
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }

    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.myaward.provider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                cameraLauncher.launch(takePictureIntent);
            }
        }
    }

    private File createImageFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        CurentPhotoFile = image;
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private boolean Validation() {
        boolean isValid = true;

        if (txt_name.getText().toString().trim().isEmpty()) {
            txt_name.setError("Please enter a name");
            txt_name.requestFocus();
            isValid = false;
        }

        String mobileNumber = txt_mobile.getText().toString().trim();
        if (mobileNumber.isEmpty()) {
            txt_mobile.setError("Please enter a mobile number");
            txt_mobile.requestFocus();
            isValid = false;
        } else if (mobileNumber.length() != 10) {
            txt_mobile.setError("Invalid mobile number. Must be 10 digits.");
            txt_mobile.requestFocus();
            isValid = false;
        }

        if (txt_percentage.getText().toString().trim().isEmpty()) {
            txt_percentage.setError("Please enter a percentage");
            txt_percentage.requestFocus();
            isValid = false;
        }

        if (txt_address.getText().toString().trim().isEmpty()) {
            txt_address.setError("Please enter an address");
            txt_address.requestFocus();
            isValid = false;
        }

        if (selectedPos == 0) {
            Toast.makeText(this, "Please select a relation", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (selectedFileTextView.getText().toString().trim().isEmpty() && currentPhotoPath.isEmpty()) {
            Toast.makeText(this, "Please select a certificate file or capture a photo", Toast.LENGTH_SHORT).show();
            isValid = false;
        }


        return isValid;
    }


    private void showImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Image Source");
        builder.setItems(new CharSequence[]{"Camera", "Gallery"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (checkCameraPermission()) {
                            openCamera();
                        }
                        break;
                    case 1:
                        if (checkGalleryPermission()) {
                            openGallery();
                        }
                        break;
                }
            }
        });
        builder.show();
    }
}
