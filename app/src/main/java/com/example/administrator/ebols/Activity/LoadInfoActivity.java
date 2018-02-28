package com.example.administrator.ebols.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ebols.DB.DBHandler;
import com.example.administrator.ebols.DB.GetDbData;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Archive_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_AssignDriver_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_BOL_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Edit_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Invoice_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_MarkPaid_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_PickUp_Fragment;
import com.example.administrator.ebols.Fragment.ActionFragments.Action_Trash_Fragment;
import com.example.administrator.ebols.R;
import com.example.administrator.ebols.RetrofitClass.Archive;
import com.example.administrator.ebols.RetrofitClass.AssignDriver;
import com.example.administrator.ebols.RetrofitClass.BilledOrder;
import com.example.administrator.ebols.RetrofitClass.PickUpAndDelievery;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadInfoActivity extends AppCompatActivity implements Action_Archive_Fragment.OnFragmentInteractionListener,
        Action_AssignDriver_Fragment.OnFragmentInteractionListener, Action_BOL_Fragment.OnFragmentInteractionListener,
        Action_Edit_Fragment.OnFragmentInteractionListener, Action_Invoice_Fragment.OnFragmentInteractionListener,
        Action_MarkPaid_Fragment.OnFragmentInteractionListener, Action_PickUp_Fragment.OnFragmentInteractionListener,
        Action_Trash_Fragment.OnFragmentInteractionListener{
    private Spinner spinner, assign_driver;
    private DBHandler dbHandler;
    private TextView customer_name, customer_phone,driver_name, payment;
    private String list_id;
    private Button cancel_driver, confirm_driver;
    private String tab;
    private Cursor cursor;
    private GetDbData getDbData;
    private String stateString, customerNameString, customerAddressLinesString, customerAddressCityString, customerAddressZipcodeString, customerContactString, customerPhoneString
    ,customerFaxString, customerEmailString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_info);
        Intent intent = getIntent();
        list_id = intent.getStringExtra("id");
        tab = intent.getStringExtra("tab");
        dbHandler = new DBHandler(getApplicationContext());
        getDbData = new GetDbData(dbHandler, list_id);
        customer_name = (TextView)findViewById(R.id.custom_name);
        customer_phone = (TextView)findViewById(R.id.custom_phone);
        driver_name = (TextView)findViewById(R.id.driver_name);
        payment = (TextView)findViewById(R.id.loadInfo_payment);
        cursor = getDbData.getDbData(tab);
        customer_name.setText(cursor.getString(67));
        customer_phone.setText(cursor.getString(73));
        driver_name.setText(cursor.getString(13));
        payment.setText(cursor.getString(76));
        spinner = (Spinner)findViewById(R.id.loadInfo_action_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        final Dialog StateDialog = new Dialog(LoadInfoActivity.this);
                        WindowManager.LayoutParams lp_state = new WindowManager.LayoutParams();
                        lp_state.copyFrom(StateDialog.getWindow().getAttributes());
                        lp_state.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp_state.height = WindowManager.LayoutParams.MATCH_PARENT;
                        StateDialog.setContentView(R.layout.dialogstate);
                        final Spinner state = (Spinner)StateDialog.findViewById(R.id.state_spinner);
                        List<String> states = new ArrayList<String>();
                        states.add("Pick Up");
                        states.add("Delievery");
                        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, states);
                        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        state.setAdapter(spinnerArrayAdapter1);
                        final EditText customerName = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerAddressLines = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerAddressCity = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerAddressZipcode = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerContact = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerPhone = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerFax = (EditText)StateDialog.findViewById(R.id.customerName);
                        final EditText customerEmail = (EditText)StateDialog.findViewById(R.id.customerName);
                        final SignaturePad customerSig = (SignaturePad)StateDialog.findViewById(R.id.customerSig);
                        final SignaturePad driverSig = (SignaturePad)StateDialog.findViewById(R.id.driverSig);
                        final Button upload = (Button)StateDialog.findViewById(R.id.upload);
                        final File imageFile1 = new File(getApplicationContext().getFilesDir(), "test.png");
                        final File imageFile2 = new File(getApplicationContext().getFilesDir(), "test2.png");
                        upload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bitmap customerSigBitmap = customerSig.getSignatureBitmap();
                                Bitmap driverSigBitmap = driverSig.getSignatureBitmap();
                                try {
                                    OutputStream os = new FileOutputStream(imageFile1);
                                    OutputStream os2 = new FileOutputStream(imageFile2);
                                    customerSigBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                                    driverSigBitmap.compress(Bitmap.CompressFormat.PNG, 100, os2);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                PickUpAndDelievery pickUpAndDelievery = new PickUpAndDelievery(dbHandler, state.getSelectedItem().toString(),imageFile1, getApplicationContext());
                                pickUpAndDelievery.run();
                            }
                        });
                        StateDialog.show();
                        StateDialog.getWindow().setAttributes(lp_state);
                        break;
                    case 2:
                        final Dialog InvoiceDialog = new Dialog(LoadInfoActivity.this);
                        WindowManager.LayoutParams lp2 = new WindowManager.LayoutParams();
                        lp2.copyFrom(InvoiceDialog.getWindow().getAttributes());
                        lp2.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp2.height = WindowManager.LayoutParams.MATCH_PARENT;
                        InvoiceDialog.setContentView(R.layout.dialogbillorder);
                        final EditText receiverFullName = (EditText) InvoiceDialog.findViewById(R.id.receiverFullName);
                        final EditText billFax = (EditText) InvoiceDialog.findViewById(R.id.billFax);
                        final EditText billEmail = (EditText) InvoiceDialog.findViewById(R.id.billEmail);
                        final EditText billInvoiceNumber = (EditText) InvoiceDialog.findViewById(R.id.billInvoiceNumber);
                        final EditText billInvoiceNote = (EditText) InvoiceDialog.findViewById(R.id.billInvoiceNote);
                        Button billBtn = (Button)InvoiceDialog.findViewById(R.id.billBtn);
                        billBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String receiverFullNameString = receiverFullName.getText().toString();
                                String billFaxString = billFax.getText().toString();
                                String billEmailString = billEmail.getText().toString();
                                String billInvoiceNumberString = billInvoiceNumber.getText().toString();
                                String billInvoiceNoteString = billInvoiceNote.getText().toString();
                                List<String> list = new ArrayList<String>();
                                list.add(receiverFullNameString);
                                list.add(billFaxString);
                                list.add(billEmailString);
                                list.add(billInvoiceNoteString);
                                list.add(billInvoiceNumberString);
                                BilledOrder billedOrder = new BilledOrder(LoadInfoActivity.this, list, list_id, InvoiceDialog, tab);
                                billedOrder.run();
                            }
                        });
                        InvoiceDialog.show();
                        InvoiceDialog.getWindow().setAttributes(lp2);
                        break;
                    case 4:
                        final Dialog dialog = new Dialog(LoadInfoActivity.this);
                        dialog.setTitle("Assign Driver");
                        dialog.setContentView(R.layout.dialogassigndriver);
                        assign_driver = (Spinner)dialog.findViewById(R.id.driver_list);
                        Cursor cursor = dbHandler.getDriverData();
                        ArrayList<String> spinnerArray = new ArrayList<String>();
                        final ArrayList<Integer> driverIdList = new ArrayList<Integer>();
                        cursor.moveToFirst();
                        for(int i = 0; i<cursor.getCount(); i++){
                            spinnerArray.add(cursor.getString(1));
                            driverIdList.add(cursor.getInt(0));
                            if(!cursor.isLast()){
                                cursor.moveToNext();
                            }
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        assign_driver.setAdapter(spinnerArrayAdapter);
                        cancel_driver = (Button)dialog.findViewById(R.id.cancel_assign_driver);
                        confirm_driver = (Button)dialog.findViewById(R.id.confirm_driver);
                        confirm_driver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(assign_driver.getSelectedItem() != null){
                                    new AssignDriver(getApplicationContext(), driverIdList.get(assign_driver.getSelectedItemPosition()), dialog, tab).run();
                                }
                            }
                        });
                        cancel_driver.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;
                    case 6:
                        final Dialog reasonDialog = new Dialog(LoadInfoActivity.this);
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                        lp.copyFrom(reasonDialog.getWindow().getAttributes());
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                        reasonDialog.setContentView(R.layout.dialogarchive);
                        Button archiveBtn = (Button)reasonDialog.findViewById(R.id.archiveButton);
                        final EditText archiveReason = (EditText)reasonDialog.findViewById(R.id.reason);
                        archiveBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String reason = archiveReason.getText().toString();
                                Archive archive = new Archive(LoadInfoActivity.this, dbHandler, reasonDialog, list_id, reason,tab);
                                archive.run();
                            }
                        });
                        reasonDialog.show();
                        reasonDialog.getWindow().setAttributes(lp);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.png", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        int permissionCheck = ContextCompat.checkSelfPermission(LoadInfoActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
            Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newBitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap, 0, 0, null);
            OutputStream stream = new FileOutputStream(photo);
            newBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
            stream.close();
        } else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }

    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        LoadInfoActivity.this.sendBroadcast(mediaScanIntent);
    }
}
