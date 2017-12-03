package papadogiannis.stefanos.tutoranytime;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class UserProfile extends Custom implements SwipeRefreshLayout.OnRefreshListener {

    private ParseImageView photoImageView;

    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayout photoLayout;

    private EditText onomaEditText, epithetoEditText, tilefwnoEditText, emailEditText, odosEditText,
            arithmosEditText, dimosEditText, pistopoiisiEditText, aggelia1EditText, pistopoiisi2EditText,
            aggelia2EditText, pistopoiisi3EditText, aggelia3EditText, pistopoiisi4EditText, aggelia4EditText;

    private TextView eidikotitaLabel, eidikotita2Label, eidikotita3Label, eidikotita4Label;

    private LinearLayout secondSet, thirdSet, forthSet, eidikotita1controls, eidikotita2controls, eidikotita3controls, eidikotita4controls,
                         secondSetQuestion, thirdSetQuestion, secondSetSubtract, forthSetQuestion, thirdSetSubtract;

    private ImageView editOnoma, editEpitheto, editTilefwno, editEmail, editOdos, editArithmos, editDimos,
            editNomos, saveOnoma, saveEpitheto, saveTilefwno, saveEmail, saveNomos, editKatigoria1,
            saveOdos, saveArithmos, saveDimos, undoOnoma, undoEpitheto, undoTilefwno, undoEmail, undoOdos,
            undoArithmos, undoDimos, undoNomos, saveKatigoria1, undoKatigoria1, editEidikotita1, saveEidikotita1,
            undoEidikotita1, editPistopoiisi1, savePistopoiisi1, undoPistopoiisi1, editAggelia1, saveAggelia1, undoAggelia1,
            editKatigoria2, saveKatigoria2, undoKatigoria2, editEidikotita2, saveEidikotita2, undoEidikotita2,
            editPistopoiisi2, savePistopoiisi2, undoPistopoiisi2, editAggelia2, saveAggelia2, undoAggelia2,
            editKatigoria3, saveKatigoria3, undoKatigoria3, editEidikotita3, saveEidikotita3, undoEidikotita3,
            editPistopoiisi3, savePistopoiisi3, undoPistopoiisi3, editAggelia3, saveAggelia3, undoAggelia3,
            editKatigoria4, saveKatigoria4, undoKatigoria4, editEidikotita4, saveEidikotita4, undoEidikotita4,
            editPistopoiisi4, savePistopoiisi4, undoPistopoiisi4, editAggelia4, saveAggelia4, undoAggelia4;

    private String onoma, epitheto, tilefwno, email, odos, arithmos, dimos, pistopoiisi1, pistopoiisi2,
                   pistopoiisi3, pistopoiisi4, aggelia1, aggelia2, aggelia3, aggelia4;

    private int katigoria1num, katigoria2num, katigoria3num, katigoria4num, eidikotita1num, eidikotita2num,
                eidikotita3num, eidikotita4num, nomos;

    private String[] categories, eidikotites_kalitehnikwn, eidikotites_xenwn, nomoi;

    private ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3, arrayAdapter4;

    private Spinner katigoria1, katigoria2, katigoria3, katigoria4, eidikotita1, eidikotita2,
            eidikotita3, eidikotita4, nomosSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.orange);

        photoImageView = (ParseImageView) findViewById(R.id.photo);
        photoLayout = (LinearLayout) findViewById(R.id.photo_layout);

        onomaEditText = (EditText) findViewById(R.id.onoma_text_view);
        epithetoEditText = (EditText) findViewById(R.id.epitheto_text_view);
        tilefwnoEditText = (EditText) findViewById(R.id.tilefwno_text_view);
        emailEditText = (EditText) findViewById(R.id.email_text_view);
        odosEditText = (EditText) findViewById(R.id.odos_text_view);
        arithmosEditText = (EditText) findViewById(R.id.arithmos_text_view);
        dimosEditText = (EditText) findViewById(R.id.perioxi_text_view);

        secondSetQuestion = (LinearLayout) findViewById(R.id.second_set_question);
        thirdSetQuestion = (LinearLayout) findViewById(R.id.third_set_question);
        forthSetQuestion = (LinearLayout) findViewById(R.id.forth_set_question);
        secondSetSubtract = (LinearLayout) findViewById(R.id.second_set_subtract);
        thirdSetSubtract = (LinearLayout) findViewById(R.id.third_set_subtract);

        nomoi = getResources().getStringArray(R.array.nomoi);

        nomosSpinner = (Spinner) findViewById(R.id.nomos);

        arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1, nomoi)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(UserProfile.this);
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
        nomosSpinner.setEnabled(false);

        categories = getResources().getStringArray(R.array.categories);
        eidikotites_xenwn = getResources().getStringArray(R.array.eidikotita_xenes_glwsses);
        eidikotites_kalitehnikwn = getResources().getStringArray(R.array.eidikotita_kallitexnikwn);

        katigoria1 = (Spinner) findViewById(R.id.category);
        katigoria2 = (Spinner) findViewById(R.id.category2);
        katigoria3 = (Spinner) findViewById(R.id.category3);
        katigoria4 = (Spinner) findViewById(R.id.category4);

        eidikotitaLabel = (TextView) findViewById(R.id.eidikotita_label);
        eidikotita2Label = (TextView) findViewById(R.id.eidikotita2_label);
        eidikotita3Label = (TextView) findViewById(R.id.eidikotita3_label);
        eidikotita4Label = (TextView) findViewById(R.id.eidikotita4_label);

        eidikotita1 = (Spinner) findViewById(R.id.eidikotita);
        eidikotita2 = (Spinner) findViewById(R.id.eidikotita2);
        eidikotita3 = (Spinner) findViewById(R.id.eidikotita3);
        eidikotita4 = (Spinner) findViewById(R.id.eidikotita4);

        pistopoiisiEditText = (EditText) findViewById(R.id.pistopoiisi_text_view);
        pistopoiisi2EditText = (EditText) findViewById(R.id.pistopoiisi2_text_view);
        pistopoiisi3EditText = (EditText) findViewById(R.id.pistopoiisi3_text_view);
        pistopoiisi4EditText = (EditText) findViewById(R.id.pistopoiisi4_text_view);

        pistopoiisiEditText.setEnabled(false);
        pistopoiisi2EditText.setEnabled(false);
        pistopoiisi3EditText.setEnabled(false);
        pistopoiisi4EditText.setEnabled(false);

        aggelia1EditText= (EditText) findViewById(R.id.aggelia_text_view);
        aggelia2EditText = (EditText) findViewById(R.id.aggelia2_text_view);
        aggelia3EditText= (EditText) findViewById(R.id.aggelia3_text_view);
        aggelia4EditText = (EditText) findViewById(R.id.aggelia4_text_view);

        aggelia1EditText.setEnabled(false);
        aggelia2EditText.setEnabled(false);
        aggelia3EditText.setEnabled(false);
        aggelia4EditText.setEnabled(false);

        editOnoma = (ImageView) findViewById(R.id.editOnoma);
        editEpitheto = (ImageView) findViewById(R.id.editEpitheto);
        editTilefwno = (ImageView) findViewById(R.id.editTilefwno);
        editEmail = (ImageView) findViewById(R.id.editEmail);
        editOdos = (ImageView) findViewById(R.id.editOdos);
        editArithmos = (ImageView) findViewById(R.id.editArithmos);
        editDimos = (ImageView) findViewById(R.id.editDimos);
        editNomos = (ImageView) findViewById(R.id.editNomos);
        editKatigoria1 = (ImageView) findViewById(R.id.editKatigoria1);
        editEidikotita1 = (ImageView) findViewById(R.id.editEidikotita1);
        editPistopoiisi1 = (ImageView) findViewById(R.id.editPistopoiisi1);
        editAggelia1 = (ImageView) findViewById(R.id.editAggelia1);
        editKatigoria2 = (ImageView) findViewById(R.id.editKatigoria2);
        editEidikotita2 = (ImageView) findViewById(R.id.editEidikotita2);
        editPistopoiisi2 = (ImageView) findViewById(R.id.editPistopoiisi2);
        editAggelia2 = (ImageView) findViewById(R.id.editAggelia2);
        editKatigoria3 = (ImageView) findViewById(R.id.editKatigoria3);
        editEidikotita3 = (ImageView) findViewById(R.id.editEidikotita3);
        editPistopoiisi3 = (ImageView) findViewById(R.id.editPistopoiisi3);
        editAggelia3 = (ImageView) findViewById(R.id.editAggelia3);
        editKatigoria4 = (ImageView) findViewById(R.id.editKatigoria4);
        editEidikotita4 = (ImageView) findViewById(R.id.editEidikotita4);
        editPistopoiisi4 = (ImageView) findViewById(R.id.editPistopoiisi4);
        editAggelia4 = (ImageView) findViewById(R.id.editAggelia4);

        saveOnoma = (ImageView) findViewById(R.id.saveOnoma);
        saveEpitheto = (ImageView) findViewById(R.id.saveEpitheto);
        saveTilefwno = (ImageView) findViewById(R.id.saveTilefwno);
        saveEmail = (ImageView) findViewById(R.id.saveEmail);
        saveOdos = (ImageView) findViewById(R.id.saveOdos);
        saveArithmos = (ImageView) findViewById(R.id.saveArithmos);
        saveDimos = (ImageView) findViewById(R.id.saveDimos);
        saveNomos = (ImageView) findViewById(R.id.saveNomos);
        saveKatigoria1 = (ImageView) findViewById(R.id.saveKatigoria1);
        saveEidikotita1 = (ImageView) findViewById(R.id.saveEidikotita1);
        savePistopoiisi1 = (ImageView) findViewById(R.id.savePistopoiisi1);
        saveAggelia1 = (ImageView) findViewById(R.id.saveAggelia1);
        saveKatigoria2 = (ImageView) findViewById(R.id.saveKatigoria2);
        saveEidikotita2 = (ImageView) findViewById(R.id.saveEidikotita2);
        savePistopoiisi2 = (ImageView) findViewById(R.id.savePistopoiisi2);
        saveAggelia2 = (ImageView) findViewById(R.id.saveAggelia2);
        saveKatigoria3 = (ImageView) findViewById(R.id.saveKatigoria3);
        saveEidikotita3 = (ImageView) findViewById(R.id.saveEidikotita3);
        savePistopoiisi3 = (ImageView) findViewById(R.id.savePistopoiisi3);
        saveAggelia3 = (ImageView) findViewById(R.id.saveAggelia3);
        saveKatigoria4 = (ImageView) findViewById(R.id.saveKatigoria4);
        saveEidikotita4 = (ImageView) findViewById(R.id.saveEidikotita4);
        savePistopoiisi4 = (ImageView) findViewById(R.id.savePistopoiisi4);
        saveAggelia4 = (ImageView) findViewById(R.id.saveAggelia4);

        undoOnoma = (ImageView) findViewById(R.id.undoOnoma);
        undoEpitheto = (ImageView) findViewById(R.id.undoEpitheto);
        undoTilefwno = (ImageView) findViewById(R.id.undoTilefwno);
        undoEmail = (ImageView) findViewById(R.id.undoEmail);
        undoOdos = (ImageView) findViewById(R.id.undoOdos);
        undoArithmos = (ImageView) findViewById(R.id.undoArithmos);
        undoDimos = (ImageView) findViewById(R.id.undoDimos);
        undoNomos = (ImageView) findViewById(R.id.undoNomos);
        undoKatigoria1 = (ImageView) findViewById(R.id.undoKatigoria1);
        undoEidikotita1 = (ImageView) findViewById(R.id.undoEidikotita1);
        undoPistopoiisi1 = (ImageView) findViewById(R.id.undoPistopoiisi1);
        undoAggelia1 = (ImageView) findViewById(R.id.undoAggelia1);
        undoKatigoria2 = (ImageView) findViewById(R.id.undoKatigoria2);
        undoEidikotita2  = (ImageView) findViewById(R.id.undoEidikotita2);
        undoPistopoiisi2 = (ImageView) findViewById(R.id.undoPistopoiisi2);
        undoAggelia2 = (ImageView) findViewById(R.id.undoAggelia2);
        undoKatigoria3 = (ImageView) findViewById(R.id.undoKatigoria3);
        undoEidikotita3  = (ImageView) findViewById(R.id.undoEidikotita3);
        undoPistopoiisi3 = (ImageView) findViewById(R.id.undoPistopoiisi3);
        undoAggelia3 = (ImageView) findViewById(R.id.undoAggelia3);
        undoKatigoria4 = (ImageView) findViewById(R.id.undoKatigoria4);
        undoEidikotita4  = (ImageView) findViewById(R.id.undoEidikotita4);
        undoPistopoiisi4 = (ImageView) findViewById(R.id.undoPistopoiisi4);
        undoAggelia4 = (ImageView) findViewById(R.id.undoAggelia4);

        secondSet = (LinearLayout) findViewById(R.id.second_set);
        thirdSet = (LinearLayout) findViewById(R.id.third_set);
        forthSet = (LinearLayout) findViewById(R.id.forth_set);

        eidikotita1controls = (LinearLayout) findViewById(R.id.eidikotita1controls);
        eidikotita2controls = (LinearLayout) findViewById(R.id.eidikotita2controls);
        eidikotita3controls = (LinearLayout) findViewById(R.id.eidikotita3controls);
        eidikotita4controls = (LinearLayout) findViewById(R.id.eidikotita4controls);

        onomaEditText.setEnabled(false);
        epithetoEditText.setEnabled(false);
        tilefwnoEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        odosEditText.setEnabled(false);
        arithmosEditText.setEnabled(false);
        dimosEditText.setEnabled(false);

        katigoria1.setEnabled(false);
        eidikotita1.setEnabled(false);
        katigoria2.setEnabled(false);
        eidikotita2.setEnabled(false);
        katigoria3.setEnabled(false);
        eidikotita3.setEnabled(false);
        katigoria4.setEnabled(false);
        eidikotita4.setEnabled(false);

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom, R.id.text1 ,categories)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(UserProfile.this);
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

        arrayAdapter2 = new ArrayAdapter<String>(UserProfile.this, R.layout.spinner_custom, R.id.text1, eidikotites_kalitehnikwn)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(UserProfile.this);
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

        arrayAdapter3 = new ArrayAdapter<String>(UserProfile.this, R.layout.spinner_custom, R.id.text1, eidikotites_xenwn) {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(UserProfile.this);
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

        katigoria1.setAdapter(arrayAdapter);
        eidikotita1.setAdapter(arrayAdapter2);
        katigoria2.setAdapter(arrayAdapter);
        eidikotita2.setAdapter(arrayAdapter2);
        katigoria3.setAdapter(arrayAdapter);
        eidikotita3.setAdapter(arrayAdapter2);
        katigoria4.setAdapter(arrayAdapter);
        eidikotita4.setAdapter(arrayAdapter2);

        katigoria1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        eidikotita1.setVisibility(View.GONE);
                        eidikotitaLabel.setVisibility(View.GONE);
                        eidikotita1controls.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotitaLabel.setVisibility(View.VISIBLE);
                        eidikotita1.setVisibility(View.VISIBLE);
                        eidikotita1controls.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita1() != -1) {
                            eidikotita1.setSelection(TutorAnytimeApp.getEidikotita1());
                        }
                        break;
                    case 6:
                        eidikotitaLabel.setVisibility(View.VISIBLE);
                        eidikotita1.setVisibility(View.VISIBLE);
                        eidikotita1controls.setVisibility(View.VISIBLE);
                        eidikotita1.setAdapter(arrayAdapter3);
                        if (TutorAnytimeApp.getEidikotita1() != -1) {
                            eidikotita1.setSelection(TutorAnytimeApp.getEidikotita1());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        katigoria2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        eidikotita2.setVisibility(View.GONE);
                        eidikotita2Label.setVisibility(View.GONE);
                        eidikotita2controls.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotita2Label.setVisibility(View.VISIBLE);
                        eidikotita2.setVisibility(View.VISIBLE);
                        eidikotita2controls.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita2() != -1) {
                            eidikotita2.setSelection(TutorAnytimeApp.getEidikotita2());
                        }
                        break;
                    case 6:
                        eidikotita2Label.setVisibility(View.VISIBLE);
                        eidikotita2.setVisibility(View.VISIBLE);
                        eidikotita2.setAdapter(arrayAdapter3);
                        eidikotita2controls.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita2() != -1) {
                            eidikotita2.setSelection(TutorAnytimeApp.getEidikotita2());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        katigoria3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        eidikotita3.setVisibility(View.GONE);
                        eidikotita3Label.setVisibility(View.GONE);
                        eidikotita3controls.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotita3Label.setVisibility(View.VISIBLE);
                        eidikotita3.setVisibility(View.VISIBLE);
                        eidikotita3controls.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita3() != -1) {
                            eidikotita3.setSelection(TutorAnytimeApp.getEidikotita3());
                        }
                        break;
                    case 6:
                        eidikotita3Label.setVisibility(View.VISIBLE);
                        eidikotita3.setVisibility(View.VISIBLE);
                        eidikotita3.setAdapter(arrayAdapter3);
                        eidikotita3controls.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita3() != -1) {
                            eidikotita3.setSelection(TutorAnytimeApp.getEidikotita3());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        katigoria4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        eidikotita4.setVisibility(View.GONE);
                        eidikotita4Label.setVisibility(View.GONE);
                        eidikotita4controls.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotita4Label.setVisibility(View.VISIBLE);
                        eidikotita4.setVisibility(View.VISIBLE);
                        eidikotita4controls.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita4() != -1) {
                            eidikotita4.setSelection(TutorAnytimeApp.getEidikotita4());
                        }
                        break;
                    case 6:
                        eidikotita4Label.setVisibility(View.VISIBLE);
                        eidikotita4.setVisibility(View.VISIBLE);
                        eidikotita4controls.setVisibility(View.VISIBLE);
                        eidikotita4.setAdapter(arrayAdapter3);
                        if (TutorAnytimeApp.getEidikotita4() != -1) {
                            eidikotita4.setSelection(TutorAnytimeApp.getEidikotita4());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (!hasConnection())
            showDialog();
        else
            fetchData();
    }

    public void editClicked(View view){
        switch (view.getId()){
            case R.id.editOnoma:
                onomaEditText.setEnabled(true);
                editOnoma.setVisibility(View.GONE);
                saveOnoma.setVisibility(View.VISIBLE);
                undoOnoma.setVisibility(View.VISIBLE);
                break;
            case R.id.editEpitheto:
                epithetoEditText.setEnabled(true);
                editEpitheto.setVisibility(View.GONE);
                saveEpitheto.setVisibility(View.VISIBLE);
                undoEpitheto.setVisibility(View.VISIBLE);
                break;
            case R.id.editTilefwno:
                tilefwnoEditText.setEnabled(true);
                editTilefwno.setVisibility(View.GONE);
                saveTilefwno.setVisibility(View.VISIBLE);
                undoTilefwno.setVisibility(View.VISIBLE);
                break;
            case R.id.editEmail:
                emailEditText.setEnabled(true);
                editEmail.setVisibility(View.GONE);
                saveEmail.setVisibility(View.VISIBLE);
                undoEmail.setVisibility(View.VISIBLE);
                break;
            case R.id.editOdos:
                odosEditText.setEnabled(true);
                editOdos.setVisibility(View.GONE);
                saveOdos.setVisibility(View.VISIBLE);
                undoOdos.setVisibility(View.VISIBLE);
                break;
            case R.id.editArithmos:
                arithmosEditText.setEnabled(true);
                editArithmos.setVisibility(View.GONE);
                saveArithmos.setVisibility(View.VISIBLE);
                undoArithmos.setVisibility(View.VISIBLE);
                break;
            case R.id.editDimos:
                dimosEditText.setEnabled(true);
                editDimos.setVisibility(View.GONE);
                saveDimos.setVisibility(View.VISIBLE);
                undoDimos.setVisibility(View.VISIBLE);
                break;
            case R.id.editNomos:
                nomosSpinner.setEnabled(true);
                editNomos.setVisibility(View.GONE);
                saveNomos.setVisibility(View.VISIBLE);
                undoNomos.setVisibility(View.VISIBLE);
                break;
            case R.id.editKatigoria1:
                katigoria1.setEnabled(true);
                editKatigoria1.setVisibility(View.GONE);
                saveKatigoria1.setVisibility(View.VISIBLE);
                undoKatigoria1.setVisibility(View.VISIBLE);
                break;
            case R.id.editEidikotita1:
                eidikotita1.setEnabled(true);
                editEidikotita1.setVisibility(View.GONE);
                saveEidikotita1.setVisibility(View.VISIBLE);
                undoEidikotita1.setVisibility(View.VISIBLE);
                break;
            case R.id.editPistopoiisi1:
                pistopoiisiEditText.setEnabled(true);
                editPistopoiisi1.setVisibility(View.GONE);
                savePistopoiisi1.setVisibility(View.VISIBLE);
                undoPistopoiisi1.setVisibility(View.VISIBLE);
                break;
            case R.id.editAggelia1:
                aggelia1EditText.setEnabled(true);
                editAggelia1.setVisibility(View.GONE);
                saveAggelia1.setVisibility(View.VISIBLE);
                undoAggelia1.setVisibility(View.VISIBLE);
                break;
            case R.id.editKatigoria2:
                katigoria2.setEnabled(true);
                editKatigoria2.setVisibility(View.GONE);
                saveKatigoria2.setVisibility(View.VISIBLE);
                undoKatigoria2.setVisibility(View.VISIBLE);
                break;
            case R.id.editEidikotita2:
                eidikotita2.setEnabled(true);
                editEidikotita2.setVisibility(View.GONE);
                saveEidikotita2.setVisibility(View.VISIBLE);
                undoEidikotita2.setVisibility(View.VISIBLE);
                break;
            case R.id.editPistopoiisi2:
                pistopoiisi2EditText.setEnabled(true);
                editPistopoiisi2.setVisibility(View.GONE);
                savePistopoiisi2.setVisibility(View.VISIBLE);
                undoPistopoiisi2.setVisibility(View.VISIBLE);
                break;
            case R.id.editAggelia2:
                aggelia2EditText.setEnabled(true);
                editAggelia2.setVisibility(View.GONE);
                saveAggelia2.setVisibility(View.VISIBLE);
                undoAggelia2.setVisibility(View.VISIBLE);
                break;
            case R.id.editKatigoria3:
                katigoria3.setEnabled(true);
                editKatigoria3.setVisibility(View.GONE);
                saveKatigoria3.setVisibility(View.VISIBLE);
                undoKatigoria3.setVisibility(View.VISIBLE);
                break;
            case R.id.editEidikotita3:
                eidikotita3.setEnabled(true);
                editEidikotita3.setVisibility(View.GONE);
                saveEidikotita3.setVisibility(View.VISIBLE);
                undoEidikotita3.setVisibility(View.VISIBLE);
                break;
            case R.id.editPistopoiisi3:
                pistopoiisi3EditText.setEnabled(true);
                editPistopoiisi3.setVisibility(View.GONE);
                savePistopoiisi3.setVisibility(View.VISIBLE);
                undoPistopoiisi3.setVisibility(View.VISIBLE);
                break;
            case R.id.editAggelia3:
                aggelia3EditText.setEnabled(true);
                editAggelia3.setVisibility(View.GONE);
                saveAggelia3.setVisibility(View.VISIBLE);
                undoAggelia3.setVisibility(View.VISIBLE);
                break;
            case R.id.editKatigoria4:
                katigoria4.setEnabled(true);
                editKatigoria4.setVisibility(View.GONE);
                saveKatigoria4.setVisibility(View.VISIBLE);
                undoKatigoria4.setVisibility(View.VISIBLE);
                break;
            case R.id.editEidikotita4:
                eidikotita4.setEnabled(true);
                editEidikotita4.setVisibility(View.GONE);
                saveEidikotita4.setVisibility(View.VISIBLE);
                undoEidikotita4.setVisibility(View.VISIBLE);
                break;
            case R.id.editPistopoiisi4:
                pistopoiisi4EditText.setEnabled(true);
                editPistopoiisi4.setVisibility(View.GONE);
                savePistopoiisi4.setVisibility(View.VISIBLE);
                undoPistopoiisi4.setVisibility(View.VISIBLE);
                break;
            case R.id.editAggelia4:
                aggelia4EditText.setEnabled(true);
                editAggelia4.setVisibility(View.GONE);
                saveAggelia4.setVisibility(View.VISIBLE);
                undoAggelia4.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void saveClicked(View view){
        switch (view.getId()){
            case R.id.saveOnoma:
                onomaEditText.setEnabled(false);
                saveOnoma.setVisibility(View.GONE);
                undoOnoma.setVisibility(View.GONE);
                editOnoma.setVisibility(View.VISIBLE);
                final String tempOnoma = onomaEditText.getText().toString();
                //TODO onoma validation
                if (!tempOnoma.equals(onoma)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("onoma", tempOnoma);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Ονόματος");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    onoma = tempOnoma;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveEpitheto:
                epithetoEditText.setEnabled(false);
                saveEpitheto.setVisibility(View.GONE);
                undoEpitheto.setVisibility(View.GONE);
                editEpitheto.setVisibility(View.VISIBLE);
                final String tempEpitheto = epithetoEditText.getText().toString();
                //TODO epitheto validation
                if (!tempEpitheto.equals(epitheto)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("epitheto", tempEpitheto);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Επιθέτου");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    epitheto = tempEpitheto;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveTilefwno:
                tilefwnoEditText.setEnabled(false);
                saveTilefwno.setVisibility(View.GONE);
                undoTilefwno.setVisibility(View.GONE);
                editTilefwno.setVisibility(View.VISIBLE);
                final String tempTilefwno = tilefwnoEditText.getText().toString();
                //TODO tilefwno validation
                if (!tempTilefwno.equals(tilefwno)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("tilefwno", tempTilefwno);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Τηλεφώνου");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    tilefwno = tempTilefwno;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveEmail:
                emailEditText.setEnabled(false);
                saveEmail.setVisibility(View.GONE);
                undoEmail.setVisibility(View.GONE);
                editEmail.setVisibility(View.VISIBLE);
                final String tempEmail = emailEditText.getText().toString();
                //TODO email validation
                if (!tempEmail.equals(email)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("email", tempEmail);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Email");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    email = tempEmail;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveOdos:
                odosEditText.setEnabled(false);
                saveOdos.setVisibility(View.GONE);
                undoOdos.setVisibility(View.GONE);
                editOdos.setVisibility(View.VISIBLE);
                final String tempOdos = odosEditText.getText().toString();
                //TODO odos validation
                if (!tempOdos.equals(odos)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("odos", tempOdos);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Οδού");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    odos = tempOdos;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveArithmos:
                arithmosEditText.setEnabled(false);
                saveArithmos.setVisibility(View.GONE);
                undoArithmos.setVisibility(View.GONE);
                editArithmos.setVisibility(View.VISIBLE);
                final String tempArithmos = arithmosEditText.getText().toString();
                //TODO arithmos validation
                if (!tempArithmos.equals(arithmos)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("arithmos", tempArithmos);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Αριθμού");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    arithmos = tempArithmos;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveDimos:
                dimosEditText.setEnabled(false);
                saveDimos.setVisibility(View.GONE);
                undoDimos.setVisibility(View.GONE);
                editDimos.setVisibility(View.VISIBLE);
                final String tempDimos = dimosEditText.getText().toString();
                //TODO dimos validation
                if (!tempDimos.equals(dimos)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("dimos", tempDimos);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Δήμου");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    dimos = tempDimos;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveNomos:
                nomosSpinner.setEnabled(false);
                saveNomos.setVisibility(View.GONE);
                undoNomos.setVisibility(View.GONE);
                editNomos.setVisibility(View.VISIBLE);
                final int tempNomos = nomosSpinner.getSelectedItemPosition();
                if (tempNomos != nomos) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("nomos", tempNomos);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Νομού");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    nomos = tempNomos;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveKatigoria1:
                katigoria1.setEnabled(false);
                saveKatigoria1.setVisibility(View.GONE);
                undoKatigoria1.setVisibility(View.GONE);
                editKatigoria1.setVisibility(View.VISIBLE);
                final int tempKatigoria1 = katigoria1.getSelectedItemPosition();
                if (tempKatigoria1 != katigoria1num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("katigoria1", tempKatigoria1);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Κατηγορίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    katigoria1num = tempKatigoria1;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveEidikotita1:
                eidikotita1.setEnabled(false);
                saveEidikotita1.setVisibility(View.GONE);
                undoEidikotita1.setVisibility(View.GONE);
                editEidikotita1.setVisibility(View.VISIBLE);
                final int tempEidikotita1 = eidikotita1.getSelectedItemPosition();
                if (tempEidikotita1 != eidikotita1num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("eidikotita1", tempEidikotita1);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Ειδικότητας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    eidikotita1num = tempEidikotita1;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.savePistopoiisi1:
                pistopoiisiEditText.setEnabled(false);
                savePistopoiisi1.setVisibility(View.GONE);
                undoPistopoiisi1.setVisibility(View.GONE);
                editPistopoiisi1.setVisibility(View.VISIBLE);
                final String tempPistopoiisi = pistopoiisiEditText.getText().toString();
                //TODO pistopoiisi validation
                if (!tempPistopoiisi.equals(pistopoiisi1)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("pistopoiisi1", tempPistopoiisi);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Πιστοποίησης");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    pistopoiisi1 = tempPistopoiisi;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveAggelia1:
                aggelia1EditText.setEnabled(false);
                saveAggelia1.setVisibility(View.GONE);
                undoAggelia1.setVisibility(View.GONE);
                editAggelia1.setVisibility(View.VISIBLE);
                final String tempAggelia1 = aggelia1EditText.getText().toString();
                //TODO aggelia validation
                if (!tempAggelia1.equals(aggelia1)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("aggelia1", tempAggelia1);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Αγγελίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    aggelia1 = tempAggelia1;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveKatigoria2:
                katigoria2.setEnabled(false);
                saveKatigoria2.setVisibility(View.GONE);
                undoKatigoria2.setVisibility(View.GONE);
                editKatigoria2.setVisibility(View.VISIBLE);
                final int tempKatigoria2 = katigoria2.getSelectedItemPosition();
                if (tempKatigoria2 != katigoria2num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("katigoria2", tempKatigoria2);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Κατηγορίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    katigoria2num = tempKatigoria2;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveEidikotita2:
                eidikotita2.setEnabled(false);
                saveEidikotita2.setVisibility(View.GONE);
                undoEidikotita2.setVisibility(View.GONE);
                editEidikotita2.setVisibility(View.VISIBLE);
                final int tempEidikotita2 = eidikotita2.getSelectedItemPosition();
                if (tempEidikotita2 != eidikotita2num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("eidikotita2", tempEidikotita2);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Ειδικότητας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    eidikotita2num = tempEidikotita2;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.savePistopoiisi2:
                pistopoiisi2EditText.setEnabled(false);
                savePistopoiisi2.setVisibility(View.GONE);
                undoPistopoiisi2.setVisibility(View.GONE);
                editPistopoiisi2.setVisibility(View.VISIBLE);
                final String tempPistopoiisi2 = pistopoiisi2EditText.getText().toString();
                //TODO pistopoiisi validation
                if (!tempPistopoiisi2.equals(pistopoiisi2)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("pistopoiisi2", tempPistopoiisi2);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Πιστοποίησης");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    pistopoiisi2 = tempPistopoiisi2;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveAggelia2:
                aggelia2EditText.setEnabled(false);
                saveAggelia2.setVisibility(View.GONE);
                undoAggelia2.setVisibility(View.GONE);
                editAggelia2.setVisibility(View.VISIBLE);
                final String tempAggelia2 = aggelia2EditText.getText().toString();
                //TODO aggelia validation
                if (!tempAggelia2.equals(aggelia2)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("aggelia2", tempAggelia2);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Αγγελίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    aggelia2 = tempAggelia2;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveKatigoria3:
                katigoria3.setEnabled(false);
                saveKatigoria3.setVisibility(View.GONE);
                undoKatigoria3.setVisibility(View.GONE);
                editKatigoria3.setVisibility(View.VISIBLE);
                final int tempKatigoria3 = katigoria3.getSelectedItemPosition();
                if (tempKatigoria3 != katigoria3num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("katigoria3", tempKatigoria3);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Κατηγορίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    katigoria3num = tempKatigoria3;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveEidikotita3:
                eidikotita3.setEnabled(false);
                saveEidikotita3.setVisibility(View.GONE);
                undoEidikotita3.setVisibility(View.GONE);
                editEidikotita3.setVisibility(View.VISIBLE);
                final int tempEidikotita3 = eidikotita3.getSelectedItemPosition();
                if (tempEidikotita3 != eidikotita3num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("eidikotita3", tempEidikotita3);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Ειδικότητας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    eidikotita3num = tempEidikotita3;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.savePistopoiisi3:
                pistopoiisi3EditText.setEnabled(false);
                savePistopoiisi3.setVisibility(View.GONE);
                undoPistopoiisi3.setVisibility(View.GONE);
                editPistopoiisi3.setVisibility(View.VISIBLE);
                final String tempPistopoiisi3 = pistopoiisi3EditText.getText().toString();
                //TODO pistopoiisi validation
                if (!tempPistopoiisi3.equals(pistopoiisi3)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("pistopoiisi3", tempPistopoiisi3);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Πιστοποίησης");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    pistopoiisi3 = tempPistopoiisi3;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveAggelia3:
                aggelia3EditText.setEnabled(false);
                saveAggelia3.setVisibility(View.GONE);
                undoAggelia3.setVisibility(View.GONE);
                editAggelia3.setVisibility(View.VISIBLE);
                final String tempAggelia3 = aggelia3EditText.getText().toString();
                //TODO aggelia validation
                if (!tempAggelia3.equals(aggelia3)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("aggelia3", tempAggelia3);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Αγγελίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    aggelia3 = tempAggelia3;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveKatigoria4:
                katigoria4.setEnabled(false);
                saveKatigoria4.setVisibility(View.GONE);
                undoKatigoria4.setVisibility(View.GONE);
                editKatigoria4.setVisibility(View.VISIBLE);
                final int tempKatigoria4 = katigoria4.getSelectedItemPosition();
                if (tempKatigoria4 != katigoria4num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("katigoria4", tempKatigoria4);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Κατηγορίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    katigoria4num = tempKatigoria4;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveEidikotita4:
                eidikotita4.setEnabled(false);
                saveEidikotita4.setVisibility(View.GONE);
                undoEidikotita4.setVisibility(View.GONE);
                editEidikotita4.setVisibility(View.VISIBLE);
                final int tempEidikotita4 = eidikotita4.getSelectedItemPosition();
                if (tempEidikotita4 != eidikotita4num) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("eidikotita4", tempEidikotita4);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Ειδικότητας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    eidikotita4num = tempEidikotita4;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.savePistopoiisi4:
                pistopoiisi4EditText.setEnabled(false);
                savePistopoiisi4.setVisibility(View.GONE);
                undoPistopoiisi4.setVisibility(View.GONE);
                editPistopoiisi4.setVisibility(View.VISIBLE);
                final String tempPistopoiisi4 = pistopoiisi4EditText.getText().toString();
                //TODO pistopoiisi validation
                if (!tempPistopoiisi4.equals(pistopoiisi4)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("pistopoiisi4", tempPistopoiisi4);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Πιστοποίησης");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    pistopoiisi4 = tempPistopoiisi4;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.saveAggelia4:
                aggelia4EditText.setEnabled(false);
                saveAggelia4.setVisibility(View.GONE);
                undoAggelia4.setVisibility(View.GONE);
                editAggelia4.setVisibility(View.VISIBLE);
                final String tempAggelia4 = aggelia4EditText.getText().toString();
                //TODO aggelia validation
                if (!tempAggelia4.equals(aggelia4)) {
                    if (!hasConnection())
                        showDialog();
                    else {
                        ParseUser.getCurrentUser().put("aggelia4", tempAggelia4);
                        final ProgressDialog progressDialog = new ProgressDialog(UserProfile.this);
                        progressDialog.setMessage("Αποθήκευση Αγγελίας");
                        progressDialog.setCancelable(false);
                        ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    progressDialog.dismiss();
                                    aggelia4 = tempAggelia4;
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(UserProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
                break;
        }
    }

    public void undoClicked(View view){
        switch(view.getId()){
            case R.id.undoOnoma:
                onomaEditText.setEnabled(false);
                onomaEditText.setText(onoma);
                undoOnoma.setVisibility(View.GONE);
                saveOnoma.setVisibility(View.GONE);
                editOnoma.setVisibility(View.VISIBLE);
                break;
            case R.id.undoEpitheto:
                epithetoEditText.setEnabled(false);
                epithetoEditText.setText(epitheto);
                undoEpitheto.setVisibility(View.GONE);
                saveEpitheto.setVisibility(View.GONE);
                editEpitheto.setVisibility(View.VISIBLE);
                break;
            case R.id.undoTilefwno:
                tilefwnoEditText.setEnabled(false);
                tilefwnoEditText.setText(tilefwno);
                undoTilefwno.setVisibility(View.GONE);
                saveTilefwno.setVisibility(View.GONE);
                editTilefwno.setVisibility(View.VISIBLE);
                break;
            case R.id.undoEmail:
                emailEditText.setEnabled(false);
                emailEditText.setText(email);
                undoEmail.setVisibility(View.GONE);
                saveEmail.setVisibility(View.GONE);
                editEmail.setVisibility(View.VISIBLE);
                break;
            case R.id.undoOdos:
                odosEditText.setEnabled(false);
                odosEditText.setText(odos);
                undoOdos.setVisibility(View.GONE);
                saveOdos.setVisibility(View.GONE);
                editOdos.setVisibility(View.VISIBLE);
                break;
            case R.id.undoArithmos:
                arithmosEditText.setEnabled(false);
                arithmosEditText.setText(arithmos);
                undoArithmos.setVisibility(View.GONE);
                saveArithmos.setVisibility(View.GONE);
                editArithmos.setVisibility(View.VISIBLE);
                break;
            case R.id.undoDimos:
                dimosEditText.setEnabled(false);
                dimosEditText.setText(dimos);
                undoDimos.setVisibility(View.GONE);
                saveDimos.setVisibility(View.GONE);
                editDimos.setVisibility(View.VISIBLE);
                break;
            case R.id.undoNomos:
                nomosSpinner.setEnabled(false);
                nomosSpinner.setSelection(nomos);
                undoNomos.setVisibility(View.GONE);
                saveOdos.setVisibility(View.GONE);
                editNomos.setVisibility(View.VISIBLE);
                break;
            case R.id.undoKatigoria1:
                katigoria1.setEnabled(false);
                katigoria1.setSelection(katigoria1num);
                undoKatigoria1.setVisibility(View.GONE);
                saveKatigoria1.setVisibility(View.GONE);
                editKatigoria1.setVisibility(View.VISIBLE);
                break;
            case R.id.undoEidikotita1:
                eidikotita1.setEnabled(false);
                eidikotita1.setSelection(eidikotita1num);
                undoEidikotita1.setVisibility(View.GONE);
                saveEidikotita1.setVisibility(View.GONE);
                editEidikotita1.setVisibility(View.VISIBLE);
                break;
            case R.id.undoPistopoiisi1:
                pistopoiisiEditText.setEnabled(false);
                pistopoiisiEditText.setText(pistopoiisi1);
                undoPistopoiisi1.setVisibility(View.GONE);
                savePistopoiisi1.setVisibility(View.GONE);
                editPistopoiisi1.setVisibility(View.VISIBLE);
                break;
            case R.id.undoAggelia1:
                aggelia1EditText.setEnabled(false);
                aggelia1EditText.setText(aggelia1);
                undoAggelia1.setVisibility(View.GONE);
                saveAggelia1.setVisibility(View.GONE);
                editAggelia1.setVisibility(View.VISIBLE);
                break;
            case R.id.undoKatigoria2:
                katigoria2.setEnabled(false);
                katigoria2.setSelection(katigoria2num);
                undoKatigoria2.setVisibility(View.GONE);
                saveKatigoria2.setVisibility(View.GONE);
                editKatigoria2.setVisibility(View.VISIBLE);
                break;
            case R.id.undoEidikotita2:
                eidikotita2.setEnabled(false);
                eidikotita2.setSelection(eidikotita2num);
                undoEidikotita2.setVisibility(View.GONE);
                saveEidikotita2.setVisibility(View.GONE);
                editEidikotita2.setVisibility(View.VISIBLE);
                break;
            case R.id.undoPistopoiisi2:
                pistopoiisi2EditText.setEnabled(false);
                pistopoiisi2EditText.setText(pistopoiisi2);
                undoPistopoiisi2.setVisibility(View.GONE);
                savePistopoiisi2.setVisibility(View.GONE);
                editPistopoiisi2.setVisibility(View.VISIBLE);
                break;
            case R.id.undoAggelia2:
                aggelia2EditText.setEnabled(false);
                aggelia2EditText.setText(aggelia2);
                undoAggelia2.setVisibility(View.GONE);
                saveAggelia2.setVisibility(View.GONE);
                editAggelia2.setVisibility(View.VISIBLE);
                break;
            case R.id.undoKatigoria3:
                katigoria3.setEnabled(false);
                katigoria3.setSelection(katigoria3num);
                undoKatigoria3.setVisibility(View.GONE);
                saveKatigoria3.setVisibility(View.GONE);
                editKatigoria3.setVisibility(View.VISIBLE);
                break;
            case R.id.undoEidikotita3:
                eidikotita3.setEnabled(false);
                eidikotita3.setSelection(eidikotita3num);
                undoEidikotita3.setVisibility(View.GONE);
                saveEidikotita3.setVisibility(View.GONE);
                editEidikotita3.setVisibility(View.VISIBLE);
                break;
            case R.id.undoPistopoiisi3:
                pistopoiisi3EditText.setEnabled(false);
                pistopoiisi3EditText.setText(pistopoiisi3);
                undoPistopoiisi3.setVisibility(View.GONE);
                savePistopoiisi3.setVisibility(View.GONE);
                editPistopoiisi3.setVisibility(View.VISIBLE);
                break;
            case R.id.undoAggelia3:
                aggelia3EditText.setEnabled(false);
                aggelia3EditText.setText(aggelia3);
                undoAggelia3.setVisibility(View.GONE);
                saveAggelia3.setVisibility(View.GONE);
                editAggelia3.setVisibility(View.VISIBLE);
                break;
            case R.id.undoKatigoria4:
                katigoria4.setEnabled(false);
                katigoria4.setSelection(katigoria4num);
                undoKatigoria4.setVisibility(View.GONE);
                saveKatigoria4.setVisibility(View.GONE);
                editKatigoria4.setVisibility(View.VISIBLE);
                break;
            case R.id.undoEidikotita4:
                eidikotita4.setEnabled(false);
                eidikotita4.setSelection(eidikotita4num);
                undoEidikotita4.setVisibility(View.GONE);
                saveEidikotita4.setVisibility(View.GONE);
                editEidikotita4.setVisibility(View.VISIBLE);
                break;
            case R.id.undoPistopoiisi4:
                pistopoiisi4EditText.setEnabled(false);
                pistopoiisi4EditText.setText(pistopoiisi4);
                undoPistopoiisi4.setVisibility(View.GONE);
                savePistopoiisi4.setVisibility(View.GONE);
                editPistopoiisi4.setVisibility(View.VISIBLE);
                break;
            case R.id.undoAggelia4:
                aggelia4EditText.setEnabled(false);
                aggelia4EditText.setText(aggelia4);
                undoAggelia4.setVisibility(View.GONE);
                saveAggelia4.setVisibility(View.GONE);
                editAggelia4.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void fetchData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Φόρτωση στοιχείων χρήστη");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    objects.get(0).fetchInBackground(new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject object, ParseException e) {
                            if (object != null) {
                                onoma = (String) object.get("onoma");
                                onomaEditText.setText(onoma);
                                epitheto = (String) object.get("epitheto");
                                epithetoEditText.setText(epitheto);
                                tilefwno = (String) object.get("tilefwno");
                                tilefwnoEditText.setText(tilefwno);
                                email = (String) object.get("email");
                                emailEditText.setText(email);
                                odos = (String) object.get("odos");
                                odosEditText.setText(odos);
                                arithmos = (String) object.get("arithmos");
                                arithmosEditText.setText(arithmos);
                                dimos = (String) object.get("dimos");
                                dimosEditText.setText(dimos);
                                nomos = (int) object.get("nomos");
                                nomosSpinner.setSelection(nomos);
                                katigoria1num = (int) object.get("katigoria1");
                                katigoria1.setSelection(katigoria1num);
                                if ((boolean) object.get("eidikotita1Visible")) {
                                    eidikotitaLabel.setVisibility(View.VISIBLE);
                                    eidikotita1.setVisibility(View.VISIBLE);
                                    eidikotita1controls.setVisibility(View.VISIBLE);
                                    eidikotita1num = (int) object.get("eidikotita1");
                                    if (katigoria1num == 4)
                                        eidikotita1.setSelection(eidikotita1num);
                                    else if (katigoria1num == 6) {
                                        eidikotita1.setAdapter(arrayAdapter3);
                                        eidikotita1.setSelection(eidikotita1num);
                                    }
                                } else {
                                    eidikotitaLabel.setVisibility(View.GONE);
                                    eidikotita1controls.setVisibility(View.GONE);
                                    eidikotita1.setVisibility(View.GONE);
                                }
                                pistopoiisi1 = (String) object.get("pistopoiisi1");
                                pistopoiisiEditText.setText(pistopoiisi1);
                                aggelia1 = (String) object.get("aggelia1");
                                aggelia1EditText.setText(aggelia1);
                                if ((boolean) object.get("secondSetVisible")) {
                                    secondSet.setVisibility(View.VISIBLE);
                                    secondSetQuestion.setVisibility(View.GONE);
                                    katigoria2num = (int) object.get("katigoria2");
                                    katigoria2.setSelection(katigoria2num);
                                    if ((boolean) object.get("eidikotita2Visible")) {
                                        eidikotita2Label.setVisibility(View.VISIBLE);
                                        eidikotita2.setVisibility(View.VISIBLE);
                                        eidikotita2controls.setVisibility(View.VISIBLE);
                                        eidikotita2num = (int) object.get("eidikotita2");
                                        if (katigoria2num == 4)
                                            eidikotita2.setSelection(eidikotita2num);
                                        else if (katigoria2num == 6) {
                                            eidikotita2.setAdapter(arrayAdapter3);
                                            eidikotita2.setSelection(eidikotita2num);
                                        }
                                    } else {
                                        eidikotita2Label.setVisibility(View.GONE);
                                        eidikotita2.setVisibility(View.GONE);
                                        eidikotita2controls.setVisibility(View.GONE);
                                    }
                                    pistopoiisi2 = (String) object.get("pistopoiisi2");
                                    pistopoiisi2EditText.setText(pistopoiisi2);
                                    aggelia2 = (String) object.get("aggelia2");
                                    aggelia2EditText.setText(aggelia2);
                                } else {
                                    secondSet.setVisibility(View.GONE);
                                    secondSetQuestion.setVisibility(View.VISIBLE);
                                }
                                if ((boolean) object.get("thirdSetVisible")) {
                                    thirdSet.setVisibility(View.VISIBLE);
                                    secondSetSubtract.setVisibility(View.GONE);
                                    thirdSetQuestion.setVisibility(View.GONE);
                                    katigoria3num = (int) object.get("katigoria3");
                                    katigoria3.setSelection(katigoria3num);
                                    if ((boolean) object.get("eidikotita3Visible")) {
                                        eidikotita3Label.setVisibility(View.VISIBLE);
                                        eidikotita3.setVisibility(View.VISIBLE);
                                        eidikotita3controls.setVisibility(View.VISIBLE);
                                        eidikotita3num = (int) object.get("eidikotita3");
                                        if (katigoria3num == 4)
                                            eidikotita3.setSelection(eidikotita3num);
                                        else if (katigoria3num == 6) {
                                            eidikotita3.setAdapter(arrayAdapter3);
                                            eidikotita3.setSelection(eidikotita3num);
                                        }
                                    } else {
                                        eidikotita3Label.setVisibility(View.GONE);
                                        eidikotita3.setVisibility(View.GONE);
                                        eidikotita3controls.setVisibility(View.GONE);
                                    }
                                    pistopoiisi3 = (String) object.get("pistopoiisi3");
                                    pistopoiisi3EditText.setText(pistopoiisi3);
                                    aggelia3 = (String) object.get("aggelia3");
                                    aggelia3EditText.setText(aggelia3);
                                } else {
                                    thirdSet.setVisibility(View.GONE);
                                    thirdSetQuestion.setVisibility(View.VISIBLE);
                                    secondSetSubtract.setVisibility(View.VISIBLE);
                                }
                                if ((boolean) object.get("forthSetVisible")) {
                                    forthSet.setVisibility(View.VISIBLE);
                                    thirdSetSubtract.setVisibility(View.GONE);
                                    forthSetQuestion.setVisibility(View.GONE);
                                    katigoria4num = (int) object.get("katigoria4");
                                    katigoria4.setSelection(katigoria4num);
                                    if ((boolean) object.get("eidikotita4Visible")) {
                                        eidikotita4Label.setVisibility(View.VISIBLE);
                                        eidikotita4.setVisibility(View.VISIBLE);
                                        eidikotita4controls.setVisibility(View.VISIBLE);
                                        eidikotita4num = (int) object.get("eidikotita4");
                                        if (katigoria4num == 4)
                                            eidikotita4.setSelection(eidikotita4num);
                                        else if (katigoria4num == 6) {
                                            eidikotita4.setAdapter(arrayAdapter3);
                                            eidikotita4.setSelection(eidikotita4num);
                                        }
                                    } else {
                                        eidikotita4Label.setVisibility(View.GONE);
                                        eidikotita4.setVisibility(View.GONE);
                                        eidikotita4controls.setVisibility(View.GONE);
                                    }
                                    pistopoiisi4 = (String) object.get("pistopoiisi4");
                                    pistopoiisi4EditText.setText(pistopoiisi4);
                                    aggelia4 = (String) object.get("aggelia4");
                                    aggelia4EditText.setText(aggelia4);
                                } else {
                                    forthSet.setVisibility(View.GONE);
                                    forthSetQuestion.setVisibility(View.VISIBLE);
                                    thirdSetSubtract.setVisibility(View.VISIBLE);
                                }
                                if (object.get("userPhoto") != null) {
                                    photoLayout.setVisibility(View.VISIBLE);
                                    ((ParseFile) object.get("userPhoto")).getDataInBackground(new GetDataCallback() {
                                        @Override
                                        public void done(byte[] data, ParseException e) {
                                            if (data != null) {
                                                progressDialog.dismiss();
                                                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                                photoImageView.setImageBitmap(bitmap);
                                            }
                                        }
                                    });
                                } else
                                    progressDialog.dismiss();
                            }
                        }
                    });
                } else {
                    // Something went wrong.
                }
            }
        });
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

    public void storeData() {

    }

    public void addClicked(View view)
    {
        secondSetQuestion.setVisibility(View.GONE);
        secondSet.setVisibility(View.VISIBLE);
    }

    public void addClicked2(View view)
    {
        thirdSetQuestion.setVisibility(View.GONE);
        secondSetSubtract.setVisibility(View.GONE);
        thirdSet.setVisibility(View.VISIBLE);
    }

    public void addClicked3(View view)
    {
        forthSetQuestion.setVisibility(View.GONE);
        thirdSetSubtract.setVisibility(View.GONE);
        forthSet.setVisibility(View.VISIBLE);
    }

    public void subtractClicked3(View view)
    {
        forthSet.setVisibility(View.GONE);
        forthSetQuestion.setVisibility(View.VISIBLE);
        thirdSetSubtract.setVisibility(View.VISIBLE);
    }

    public void subtractClicked2(View view)
    {
        thirdSet.setVisibility(View.GONE);
        forthSet.setVisibility(View.GONE);
        thirdSetQuestion.setVisibility(View.VISIBLE);
        secondSetSubtract.setVisibility(View.VISIBLE);
    }

    public void subtractClicked(View view)
    {
        secondSet.setVisibility(View.GONE);
        thirdSet.setVisibility(View.GONE);
        forthSet.setVisibility(View.GONE);
        secondSetQuestion.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        if (!hasConnection()) {
            swipeRefreshLayout.setRefreshing(true);
            showDialog();
            swipeRefreshLayout.setRefreshing(false);
        }
        else {
            swipeRefreshLayout.setRefreshing(true);
            fetchData();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
