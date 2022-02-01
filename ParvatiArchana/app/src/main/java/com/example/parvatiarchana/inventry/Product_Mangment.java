//http://www.codeplayon.com/2018/11/android-image-upload-to-server-from-camera-and-gallery/
package com.example.parvatiarchana.inventry;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.parvatiarchana.R;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

//Hint : http://www.androidcoding.in/2020/06/08/android-multilevel-spinner/
public class Product_Mangment extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    public static final String KEY_User_Document1 = "doc1";
    ImageView IDProf, IDProf1;
    Button Upload_Btn, getimagBtn1;
    private String uploadtriggerbuttonname = "";
    private String Document_img1 = "";
    private String Registration_URL = "";
    private Uri selectedImage = null;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_mangment);

        IDProf = (ImageView) findViewById(R.id.IdProf);
        IDProf1 = (ImageView) findViewById(R.id.IdProf1);
        Upload_Btn = (Button) findViewById(R.id.UploadBtn);
        getimagBtn1 = (Button) findViewById(R.id.getimagBtn1);
        IDProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
        IDProf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage1();

            }
        });


        Upload_Btn.setOnClickListener(this);
/** Category drop down  */
        Spinner dropdown = findViewById(R.id.cat);
//create a list of items for the spinner.
        String[] items = new String[]{"Select Cat", "1", "2", "three"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        Spinner subcat = findViewById(R.id.subcat);
        subcat.setOnItemSelectedListener(this);
        Spinner subsubcat = findViewById(R.id.subsubcat);
        subsubcat.setOnItemSelectedListener(this);
/*
        dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String[] items1={};
               if(dropdown.getSelectedItem().equals("1")){
                   items1 = new String[]{"Allo", "pyaj"};

               }
               else if(dropdown.getSelectedItem().equals("2")){
                   items1 = new String[]{"Goat Meat", "Fish","Egg"};
               }
                final Spinner subcat = findViewById(R.id.subcat);


                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Product_Mangment.this, android.R.layout.simple_spinner_dropdown_item, items1);
//se
                subcat.setAdapter(adapter1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

*/
    }

    /**
     * Sai siddharth
     */
    private void selectImage1() {

    /*    Intent intent = new Intent();
        intent.setType("image/*");
        int REQUEST_CODE_LOAD_IMAGE = 2;
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //..mGetContent .launch(intent);
        startActivityForResult(intent, REQUEST_CODE_LOAD_IMAGE);

     */
        uploadtriggerbuttonname = "img1";
        selectImage();

    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    // Handle the returned Uri
                }
            });

    private void selectMultipleDoc() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setType("image/*");
        uploadtriggerbuttonname = "mul";
        startActivityForResult(intent, 2);//1 for taking image by camera

    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        // android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Product_Mangment.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(Product_Mangment.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);


                    /*;
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    */


                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);



                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println(" After load image onActivityResult is called ......................... ");
        if ("img1".equals(uploadtriggerbuttonname)) {
            System.out.println(" After load image onActivityResult is called . IMG1........................... ");
            if (true) { //(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                if (requestCode == 2) {//Select from galary
                    try {
                        Uri filePath = data.getData();
                        // bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                        //imageView.setImageBitmap(bitmap);
                        IDProf1.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), filePath));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (requestCode == 1) {//take by camera
                    if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
                    {
                        Bitmap photo = (Bitmap) data.getExtras().get("data");
                        IDProf1.setImageBitmap(photo);
                    }
                }
            }
            uploadtriggerbuttonname="";
            return;
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                    bitmap = getResizedBitmap(bitmap, 400);
                    IDProf.setImageBitmap(bitmap);
                    BitMapToString(bitmap);
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                thumbnail = getResizedBitmap(thumbnail, 400);
                Log.w("pathofimage", picturePath + "");
                IDProf.setImageBitmap(thumbnail);
                BitMapToString(thumbnail);
            }
        }
    }

    public String BitMapToString(Bitmap userImage1) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        userImage1.compress(Bitmap.CompressFormat.PNG, 60, baos);
        byte[] b = baos.toByteArray();
        Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
        return Document_img1;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void SendDetail() {
        final ProgressDialog loading = new ProgressDialog(Product_Mangment.this);
        loading.setMessage("Please Wait...");
        loading.show();
        loading.setCanceledOnTouchOutside(false);
        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Registration_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            loading.dismiss();
                            Log.d("JSON", response);

                            JSONObject eventObject = new JSONObject(response);
                            String error_status = eventObject.getString("error");
                            if (error_status.equals("true")) {
                                String error_msg = eventObject.getString("msg");
                                ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("Vendor Detail");
                                alertDialogBuilder.setCancelable(false);
                                alertDialogBuilder.setMessage(error_msg);
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                alertDialogBuilder.show();

                            } else {
                                String error_msg = eventObject.getString("msg");
                                ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                                alertDialogBuilder.setTitle("Registration");
                                alertDialogBuilder.setCancelable(false);
                                alertDialogBuilder.setMessage(error_msg);
//                                alertDialogBuilder.setIcon(R.drawable.doubletick);
                                alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                      /*  Intent intent=new Intent(Product_Mangment.this,Log_In.class);
                                        startActivity(intent);
                                        finish();

                                       */

                                    }
                                });
                                alertDialogBuilder.show();
                            }
                        } catch (Exception e) {
                            Log.d("Tag", e.getMessage());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                            alertDialogBuilder.setTitle("No connection");
                            alertDialogBuilder.setMessage(" Connection time out error please try again ");
                            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            alertDialogBuilder.show();
                        } else if (error instanceof AuthFailureError) {
                            ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                            alertDialogBuilder.setTitle("Connection Error");
                            alertDialogBuilder.setMessage(" Authentication failure connection error please try again ");
                            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            alertDialogBuilder.show();
                            //TODO
                        } else if (error instanceof ServerError) {
                            ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                            alertDialogBuilder.setTitle("Connection Error");
                            alertDialogBuilder.setMessage("Connection error please try again");
                            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            alertDialogBuilder.show();
                            //TODO
                        } else if (error instanceof NetworkError) {
                            ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                            alertDialogBuilder.setTitle("Connection Error");
                            alertDialogBuilder.setMessage("Network connection error please try again");
                            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            alertDialogBuilder.show();
                            //TODO
                        } else if (error instanceof ParseError) {
                            ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
                            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
                            alertDialogBuilder.setTitle("Error");
                            alertDialogBuilder.setMessage("Parse error");
                            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            alertDialogBuilder.show();
                        }
//                        Toast.makeText(Login_Activity.this,error.toString(), Toast.LENGTH_LONG ).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_User_Document1, Document_img1);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(mRetryPolicy);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if (Document_img1.equals("") || Document_img1.equals(null)) {
            ContextThemeWrapper ctw = new ContextThemeWrapper(Product_Mangment.this, R.style.Theme_AlertDialog);
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);
            alertDialogBuilder.setTitle("Id Prof Can't Empty ");
            alertDialogBuilder.setMessage("Id Prof Can't empty please select any one document");
            alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            alertDialogBuilder.show();
            return;
        } else {
      /*...comment Siddh
            if (AppStatus.getInstance(this).isOnline()) {
                SendDetail();


                //           Toast.makeText(this,"You are online!!!!",Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this,"You are not online!!!!",Toast.LENGTH_LONG).show();
                Log.v("Home", "############################You are not online!!!!");
            }
            */

        }
    }

    //Hint :For Drop down listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        if (parent.getId() == R.id.cat) {
            Spinner dropdown = findViewById(R.id.cat);
            String[] items1 = {};
            if (dropdown.getSelectedItem().equals("1")) {
                items1 = new String[]{"Aloo", "pyaj"};

            } else if (dropdown.getSelectedItem().equals("2")) {
                items1 = new String[]{"Goat Meat", "Fish", "Egg"};
            }
            final Spinner subcat = findViewById(R.id.subcat);


            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Product_Mangment.this, android.R.layout.simple_spinner_dropdown_item, items1);
//se
            subcat.setAdapter(adapter1);


        } else if (parent.getId() == R.id.subcat) {

            Spinner dropdown = findViewById(R.id.subcat);
            String[] items1 = {};
            if (dropdown.getSelectedItem().equals("Aloo")) {
                items1 = new String[]{"lalima", "kufari"};

            } else if (dropdown.getSelectedItem().equals("pyaj")) {
                items1 = new String[]{"white", "red"};
            }
            final Spinner subsubcat = findViewById(R.id.subsubcat);


            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Product_Mangment.this, android.R.layout.simple_spinner_dropdown_item, items1);
//se
            subsubcat.setAdapter(adapter1);

        } else if (parent.getId() == R.id.subsubcat) {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}