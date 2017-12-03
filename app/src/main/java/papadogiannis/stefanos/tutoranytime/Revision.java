package papadogiannis.stefanos.tutoranytime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.ProgressCallback;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class Revision extends Custom {

    private TextView onomaTextView, epithetoTextView, tilefwnoTextView, emailTextView, odosTextView, arithmosTextView,
            perioxiTextView, eidikotita1Label, pistopoiisiTextView,
            aggeliaTextView, eidikotita2Label, pistopoiisi2TextView, aggelia2TextView,
            pistopoiisi3TextView, aggelia3TextView, eidikotita3Label,
             pistopoiisi4TextView, aggelia4TextView, eidikotita4Label;

    private ImageView photo;

    private Spinner spinner, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, nomosSpinner;

    private LinearLayout secondSet, thirdSet, forthSet, photoLayout;

    private ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3, arrayAdapter4;

    private String[] categories, eidikotites_kalitehnikwn, eidikotites_xenwn, nomoi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revision);

        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);

        onomaTextView = (TextView) findViewById(R.id.onoma_text_view);
        epithetoTextView = (TextView) findViewById(R.id.epitheto_text_view);
        tilefwnoTextView = (TextView) findViewById(R.id.tilefwno_text_view);
        emailTextView = (TextView) findViewById(R.id.email_text_view);
        odosTextView = (TextView) findViewById(R.id.odos_text_view);
        arithmosTextView = (TextView) findViewById(R.id.arithmos_text_view);
        perioxiTextView = (TextView) findViewById(R.id.perioxi_text_view);
        eidikotita1Label = (TextView) findViewById(R.id.eidikotita_label);
        pistopoiisiTextView = (TextView) findViewById(R.id.pistopoiisi_text_view);
        aggeliaTextView  = (TextView) findViewById(R.id.aggelia_text_view);

        secondSet = (LinearLayout) findViewById(R.id.second_set);
        thirdSet = (LinearLayout) findViewById(R.id.third_set);
        forthSet = (LinearLayout) findViewById(R.id.forth_set);

        photoLayout = (LinearLayout) findViewById(R.id.photo_layout);

        categories = getResources().getStringArray(R.array.categories);
        eidikotites_xenwn = getResources().getStringArray(R.array.eidikotita_xenes_glwsses);
        eidikotites_kalitehnikwn = getResources().getStringArray(R.array.eidikotita_kallitexnikwn);
        nomoi = getResources().getStringArray(R.array.nomoi);

        nomosSpinner = (Spinner) findViewById(R.id.nomos);
        nomosSpinner.setEnabled(false);

        arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1, nomoi)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(Revision.this);
                }

                TextView item = (TextView) convertView;
                item.setText(nomoi[position]);
                item.setTextSize(18);
                item.setGravity(Gravity.CENTER);
                item.setPadding(10,10,10,10);
                final TextView finalItem = item;
                item.post(new Runnable() {
                    @Override
                    public void run() {
                        finalItem.setSingleLine(false);
                    }
                });
                return item;
            }
        };

        nomosSpinner.setAdapter(arrayAdapter4);

        spinner = (Spinner) findViewById(R.id.category);
        spinner2 = (Spinner) findViewById(R.id.eidikotita);
        spinner3 = (Spinner) findViewById(R.id.category2);
        spinner4 = (Spinner) findViewById(R.id.eidikotita2);
        spinner5 = (Spinner) findViewById(R.id.category3);
        spinner6 = (Spinner) findViewById(R.id.eidikotita3);
        spinner7 = (Spinner) findViewById(R.id.category4);
        spinner8 = (Spinner) findViewById(R.id.eidikotita4);

        spinner.setEnabled(false);
        spinner2.setEnabled(false);
        spinner3.setEnabled(false);
        spinner4.setEnabled(false);
        spinner5.setEnabled(false);
        spinner6.setEnabled(false);
        spinner7.setEnabled(false);
        spinner8.setEnabled(false);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom, R.id.text1 ,categories)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(Revision.this);
                }

                TextView item = (TextView) convertView;
                item.setText(categories[position]);
                item.setTextSize(18);
                item.setGravity(Gravity.CENTER);
                item.setPadding(10, 10, 10, 10);
                final TextView finalItem = item;
                item.post(new Runnable() {
                    @Override
                    public void run() {
                        finalItem.setSingleLine(false);
                    }
                });
                return item;
            }
        };

        arrayAdapter2 = new ArrayAdapter<String>(Revision.this, R.layout.spinner_custom, R.id.text1, eidikotites_kalitehnikwn)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(Revision.this);
                }

                TextView item = (TextView) convertView;
                item.setText(eidikotites_kalitehnikwn[position]);
                item.setTextSize(18);
                item.setPadding(10, 10, 10, 10);
                item.setGravity(Gravity.CENTER);
                final TextView finalItem = item;
                item.post(new Runnable() {
                    @Override
                    public void run() {
                        finalItem.setSingleLine(false);
                    }
                });
                return item;
            }
        };

        arrayAdapter3 = new ArrayAdapter<String>(Revision.this, R.layout.spinner_custom, R.id.text1, eidikotites_xenwn) {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(Revision.this);
                }

                TextView item = (TextView) convertView;
                item.setText(eidikotites_xenwn[position]);
                item.setTextSize(18);
                item.setPadding(10, 10, 10, 10);
                item.setGravity(Gravity.CENTER);
                final TextView finalItem = item;
                item.post(new Runnable() {
                    @Override
                    public void run() {
                        finalItem.setSingleLine(false);
                    }
                });
                return item;
            }
        };

        spinner.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter2);
        spinner3.setAdapter(arrayAdapter);
        spinner4.setAdapter(arrayAdapter2);
        spinner5.setAdapter(arrayAdapter);
        spinner6.setAdapter(arrayAdapter2);
        spinner7.setAdapter(arrayAdapter);
        spinner8.setAdapter(arrayAdapter2);

        eidikotita2Label = (TextView) findViewById(R.id.eidikotita2_label);
        pistopoiisi2TextView = (TextView) findViewById(R.id.pistopoiisi2_text_view);
        aggelia2TextView = (TextView) findViewById(R.id.aggelia2_text_view);

        eidikotita3Label = (TextView) findViewById(R.id.eidikotita3_label);
        pistopoiisi3TextView = (TextView) findViewById(R.id.pistopoiisi3_text_view);
        aggelia3TextView = (TextView) findViewById(R.id.aggelia3_text_view);

        eidikotita4Label = (TextView) findViewById(R.id.eidikotita4_label);
        pistopoiisi4TextView = (TextView) findViewById(R.id.pistopoiisi4_text_view);
        aggelia4TextView = (TextView) findViewById(R.id.aggelia4_text_view);

        photo = (ImageView) findViewById(R.id.photo);

        if (TutorAnytimeApp.getName() != null)
            onomaTextView.setText(TutorAnytimeApp.getName());
        if (TutorAnytimeApp.getSurname() != null)
            epithetoTextView.setText(TutorAnytimeApp.getSurname());
        if (TutorAnytimeApp.getTelephone() != null)
            tilefwnoTextView.setText(TutorAnytimeApp.getTelephone());
        if (TutorAnytimeApp.getEmail() != null)
            emailTextView.setText(TutorAnytimeApp.getEmail());
        if (TutorAnytimeApp.getAddress() != null)
            odosTextView.setText(TutorAnytimeApp.getAddress());
        if (TutorAnytimeApp.getAddressNumber() != null)
            arithmosTextView.setText(TutorAnytimeApp.getAddressNumber());
        if (TutorAnytimeApp.getDimos() != null)
            perioxiTextView.setText(TutorAnytimeApp.getDimos());
        if (TutorAnytimeApp.getNomos() != -1)
            nomosSpinner.setSelection(TutorAnytimeApp.getNomos());

        if (TutorAnytimeApp.getCategory1() != -1)
            spinner.setSelection(TutorAnytimeApp.getCategory1());
        if (TutorAnytimeApp.getEidikotitaVisible()){
            eidikotita1Label.setVisibility(View.VISIBLE);
            spinner2.setVisibility(View.VISIBLE);
            if (TutorAnytimeApp.getCategory1() == 4)
                spinner2.setSelection(TutorAnytimeApp.getEidikotita1());
            else if (TutorAnytimeApp.getCategory1() == 6) {
                spinner2.setAdapter(arrayAdapter3);
                spinner2.setSelection(TutorAnytimeApp.getEidikotita1());
            }
        }
        else
        {
            eidikotita1Label.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
        }
        if (TutorAnytimeApp.getPistopoiisi1() != null)
            pistopoiisiTextView.setText(TutorAnytimeApp.getPistopoiisi1());
        if (TutorAnytimeApp.getAggelia1() != null)
            aggeliaTextView.setText(TutorAnytimeApp.getAggelia1());

        if (TutorAnytimeApp.getSecondSetVisible())
        {
            secondSet.setVisibility(View.VISIBLE);
            spinner3.setSelection(TutorAnytimeApp.getCategory2());
            if (TutorAnytimeApp.getEidikotita2Visible()){
                eidikotita2Label.setVisibility(View.VISIBLE);
                spinner4.setVisibility(View.VISIBLE);
                if (TutorAnytimeApp.getCategory2() == 4)
                    spinner5.setSelection(TutorAnytimeApp.getEidikotita2());
                else if (TutorAnytimeApp.getCategory2() == 6) {
                    spinner4.setAdapter(arrayAdapter3);
                    spinner4.setSelection(TutorAnytimeApp.getEidikotita2());
                }
            }
            else
            {
                eidikotita2Label.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
            }
            pistopoiisi2TextView.setText(TutorAnytimeApp.getPistopoiisi2());
            aggelia2TextView.setText(TutorAnytimeApp.getAggelia2());
        }
        else
        {
            secondSet.setVisibility(View.GONE);
        }

        if (TutorAnytimeApp.getThirdSetVisible())
        {
            thirdSet.setVisibility(View.VISIBLE);
            spinner5.setSelection(TutorAnytimeApp.getCategory3());
            if (TutorAnytimeApp.getEidikotita3Visible()){
                eidikotita3Label.setVisibility(View.VISIBLE);
                spinner6.setVisibility(View.VISIBLE);
                if (TutorAnytimeApp.getCategory3() == 4)
                    spinner6.setSelection(TutorAnytimeApp.getEidikotita3());
                else if (TutorAnytimeApp.getCategory3() == 6){
                    spinner6.setAdapter(arrayAdapter3);
                    spinner6.setSelection(TutorAnytimeApp.getEidikotita3());
                }
            }
            else
            {
                eidikotita3Label.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
            }
            pistopoiisi3TextView.setText(TutorAnytimeApp.getPistopoiisi3());
            aggelia3TextView.setText(TutorAnytimeApp.getAggelia3());
        }
        else
        {
            thirdSet.setVisibility(View.GONE);
        }

        if (TutorAnytimeApp.getForthSetVisible())
        {
            forthSet.setVisibility(View.VISIBLE);
            spinner7.setSelection(TutorAnytimeApp.getCategory4());
            if (TutorAnytimeApp.getEidikotita4Visible()){
                eidikotita4Label.setVisibility(View.VISIBLE);
                spinner8.setVisibility(View.VISIBLE);
                if (TutorAnytimeApp.getCategory4() == 4)
                    spinner8.setSelection(TutorAnytimeApp.getEidikotita4());
                else if (TutorAnytimeApp.getCategory4() == 6) {
                    spinner8.setAdapter(arrayAdapter3);
                    spinner8.setSelection(TutorAnytimeApp.getEidikotita4());
                }
            }
            else
            {
                eidikotita4Label.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
            }
            pistopoiisi4TextView.setText(TutorAnytimeApp.getPistopoiisi4());
            aggelia4TextView.setText(TutorAnytimeApp.getAggelia4());
        }
        else
        {
            forthSet.setVisibility(View.GONE);
        }

        if (TutorAnytimeApp.getPhotoPath() != null){
            photoLayout.setVisibility(View.VISIBLE);
            Bitmap myBitmap = BitmapFactory.decodeFile(TutorAnytimeApp.getPhotoPath());
            photo.setImageBitmap(myBitmap);
        }
        else
            photoLayout.setVisibility(View.GONE);
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.next)
            if (!hasConnection())
                showDialog();
            else
                storeData();
        else
            finish();
    }

    public void storeData() {
        final ProgressDialog progressDialog = new ProgressDialog(Revision.this);
        progressDialog.setMessage("Επιβεβαίωση στοιχείων");
        progressDialog.show();

        ParseUser.getQuery().whereEqualTo("username", TutorAnytimeApp.getUsername())
                 .findInBackground(new FindCallback<ParseUser>() {
                     @Override
                     public void done(List<ParseUser> li, ParseException e) {
                         progressDialog.dismiss();
                         final ProgressDialog progressDialog1 = new ProgressDialog(Revision.this);
                         progressDialog1.setMessage("Αποθήκευση στοιχείων");
                         progressDialog1.show();
                         final ParseUser parseUser = new ParseUser();
                         parseUser.setUsername(TutorAnytimeApp.getUsername());
                         parseUser.setPassword(TutorAnytimeApp.getPassword());
                         parseUser.setEmail(TutorAnytimeApp.getEmail());
                         parseUser.put("tilefwno", TutorAnytimeApp.getTelephone());
                         parseUser.put("onoma", TutorAnytimeApp.getName());
                         parseUser.put("epitheto", TutorAnytimeApp.getSurname());
                         parseUser.put("odos", TutorAnytimeApp.getAddress());
                         parseUser.put("arithmos", TutorAnytimeApp.getAddressNumber());
                         parseUser.put("dimos", TutorAnytimeApp.getDimos());
                         parseUser.put("nomos", TutorAnytimeApp.getNomos());
                         parseUser.put("katigoria1", TutorAnytimeApp.getCategory1());
                         if (TutorAnytimeApp.getEidikotitaVisible()) {
                             parseUser.put("eidikotita1Visible", true);
                             parseUser.put("eidikotita1", TutorAnytimeApp.getEidikotita1());
                         }
                         else
                            parseUser.put("eidikotita1Visible", false);
                         parseUser.put("pistopoiisi1", TutorAnytimeApp.getPistopoiisi1());
                         parseUser.put("aggelia1", TutorAnytimeApp.getAggelia1());
                         if (TutorAnytimeApp.getSecondSetVisible()) {
                             parseUser.put("secondSetVisible", true);
                             parseUser.put("katigoria2", TutorAnytimeApp.getCategory2());
                             if (TutorAnytimeApp.getEidikotita2Visible()) {
                                 parseUser.put("eidikotita2Visible", true);
                                 parseUser.put("eidikotita2", TutorAnytimeApp.getEidikotita2());
                             }
                             else
                                parseUser.put("eidikotita2Visible", false);
                             parseUser.put("pistopoiisi2", TutorAnytimeApp.getPistopoiisi2());
                             parseUser.put("aggelia2", TutorAnytimeApp.getAggelia2());
                         }
                         else
                            parseUser.put("secondSetVisible", false);
                         if (TutorAnytimeApp.getThirdSetVisible()) {
                             parseUser.put("thirdSetVisible", true);
                             parseUser.put("katigoria3", TutorAnytimeApp.getCategory3());
                             if (TutorAnytimeApp.getEidikotita3Visible()) {
                                 parseUser.put("eidikotita3Visible", true);
                                 parseUser.put("eidikotita3", TutorAnytimeApp.getEidikotita3());
                             }
                             else
                                parseUser.put("eidikotita3Visible", false);
                             parseUser.put("pistopoiisi3", TutorAnytimeApp.getPistopoiisi3());
                             parseUser.put("aggelia3", TutorAnytimeApp.getAggelia3());
                         }
                         else
                            parseUser.put("thirdSetVisible", false);
                         if (TutorAnytimeApp.getForthSetVisible()) {
                             parseUser.put("forthSetVisible", true);
                             parseUser.put("katigoria4", TutorAnytimeApp.getCategory4());
                             if (TutorAnytimeApp.getEidikotita4Visible()) {
                                 parseUser.put("eidikotita4Visible", true);
                                 parseUser.put("eidikotita4", TutorAnytimeApp.getEidikotita4());
                             }
                             else
                                parseUser.put("eidikotita4Visible", false);
                             parseUser.put("pistopoiisi4", TutorAnytimeApp.getPistopoiisi4());
                             parseUser.put("aggelia4", TutorAnytimeApp.getAggelia4());
                         }
                         else
                            parseUser.put("forthSetVisible", false);
                         parseUser.put("location", new ParseGeoPoint(TutorAnytimeApp.getPosition().latitude,
                                 TutorAnytimeApp.getPosition().longitude));
                         parseUser.signUpInBackground(new SignUpCallback() {
                             @Override
                             public void done(ParseException e) {
                                 progressDialog1.dismiss();
                                 if (e != null) {
                                     Toast.makeText(Revision.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                 } else {
                                     savePhoto();
                                 }
                             }
                         });
                     }
                 });
    }

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null;
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.no_internet);
        dialog.setCancelable(false);
        dialog.show();

        Button okButton = (Button) dialog.findViewById(R.id.ok_button);
        okButton.setOnTouchListener(new View.OnTouchListener() {
            int motionEventFinished = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setBackgroundResource(R.drawable.button_bg_blue_alpha);
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    view.setBackgroundResource(R.drawable.button_bg_blue);
                    motionEventFinished++;
                }
                if (motionEventFinished == 1)
                    dialog.dismiss();
                return false;
            }
        });
    }

    public void savePhoto()
    {
        if (TutorAnytimeApp.getPhotoPath() != null){
            Bitmap myBitmap = BitmapFactory.decodeFile(TutorAnytimeApp.getPhotoPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            // get byte array here
            byte[] bytearray = stream.toByteArray();
            final ParseFile file = new ParseFile("Picture1.jpg", bytearray);
            final ProgressDialog progressDialog1 = new ProgressDialog(Revision.this);
            progressDialog1.setMessage("Αποθήκευση εικόνας");
            progressDialog1.show();
            file.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e != null)
                        Toast.makeText(Revision.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    else {
                        ParseUser.getCurrentUser().put("userPhoto", file);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e==null) {
                                    progressDialog1.dismiss();
                                    TutorAnytimeApp.clearRegisterPreferences();
                                    startActivity(new Intent(Revision.this, Success.class));
                                    finish();
                                }
                            }
                        });
                    }
                }
            });
        }
        else
        {
            TutorAnytimeApp.clearRegisterPreferences();
            startActivity(new Intent(Revision.this, Success.class));
            finish();
        }
    }
}