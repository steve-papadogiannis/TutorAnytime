package papadogiannis.stefanos.tutoranytime;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JobInfo extends Custom
{

    private Spinner spinner, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8;
    private ArrayAdapter arrayAdapter, arrayAdapter2, arrayAdapter3;
    private String[] eidikotites_kalitehnikwn, eidikotites_xenwn, categories;
    private TextView eidikotitaTextView, eidikotita2TextView, eidikotita3TextView, eidikotita4TextView;
    private EditText pistopoiisiEditText, aggeliaEditText, pistopoiisi2EditText, aggelia2EditText,
            pistopoiisi3EditText, aggelia3EditText, pistopoiisi4EditText, aggelia4EditText;
    private LinearLayout secondSet, thirdSet, forthSet, secondSetQuestion, thirdSetQuestion, forthSetQuestion,
        secondSetSubtract, thirdSetSubtract;

    private TextView emptyPistopoiisiError, emptyPistopoiisi2Error, emptyPistopoiisi3Error, emptyPistopoiisi4Error;

    private TextView emptyAggeliaError, emptyAggelia2Error, emptyAggelia3Error, emptyAggelia4Error;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_info);

        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);

        secondSet = (LinearLayout)findViewById(R.id.second_set);
        thirdSet = (LinearLayout)findViewById(R.id.third_set);
        forthSet = (LinearLayout) findViewById(R.id.forth_set);

        secondSetQuestion = (LinearLayout)findViewById(R.id.second_set_question);
        thirdSetQuestion = (LinearLayout)findViewById(R.id.third_set_question);
        forthSetQuestion = (LinearLayout)findViewById(R.id.forth_set_question);

        secondSetSubtract = (LinearLayout)findViewById(R.id.second_set_subtract);
        thirdSetSubtract = (LinearLayout)findViewById(R.id.third_set_subtract);

        emptyPistopoiisiError = (TextView) findViewById(R.id.empty_pistopoiisi_error);
        emptyPistopoiisi2Error = (TextView) findViewById(R.id.empty_pistopoiisi2_error);
        emptyPistopoiisi3Error = (TextView) findViewById(R.id.empty_pistopoiisi3_error);
        emptyPistopoiisi4Error = (TextView) findViewById(R.id.empty_pistopoiisi4_error);

        emptyAggeliaError = (TextView) findViewById(R.id.empty_aggelia_error);
        emptyAggelia2Error = (TextView) findViewById(R.id.empty_aggelia2_error);
        emptyAggelia3Error = (TextView) findViewById(R.id.empty_aggelia3_error);
        emptyAggelia4Error = (TextView) findViewById(R.id.empty_aggelia4_error);

        spinner = (Spinner) findViewById(R.id.category);
        spinner2 = (Spinner) findViewById(R.id.eidikotita);
        spinner3 = (Spinner) findViewById(R.id.category2);
        spinner4 = (Spinner) findViewById(R.id.eidikotita2);
        spinner5 = (Spinner) findViewById(R.id.category3);
        spinner6 = (Spinner) findViewById(R.id.eidikotita3);
        spinner7 = (Spinner) findViewById(R.id.category4);
        spinner8 = (Spinner) findViewById(R.id.eidikotita4);

        categories = getResources().getStringArray(R.array.categories);
        eidikotites_xenwn = getResources().getStringArray(R.array.eidikotita_xenes_glwsses);
        eidikotites_kalitehnikwn = getResources().getStringArray(R.array.eidikotita_kallitexnikwn);

        eidikotitaTextView = (TextView)findViewById(R.id.eidikotita_textview);
        eidikotita2TextView = (TextView)findViewById(R.id.eidikotita2_textview);
        eidikotita3TextView = (TextView)findViewById(R.id.eidikotita3_textview);
        eidikotita4TextView = (TextView)findViewById(R.id.eidikotita4_textview);

        pistopoiisiEditText = (EditText) findViewById(R.id.pistopoiisi);
        pistopoiisi2EditText = (EditText) findViewById(R.id.pistopoiisi2);
        pistopoiisi3EditText = (EditText) findViewById(R.id.pistopoiisi3);
        pistopoiisi4EditText = (EditText) findViewById(R.id.pistopoiisi4);

        aggeliaEditText = (EditText) findViewById(R.id.aggelia);
        aggelia2EditText = (EditText) findViewById(R.id.aggelia2);
        aggelia3EditText = (EditText) findViewById(R.id.aggelia3);
        aggelia4EditText = (EditText) findViewById(R.id.aggelia4);

        pistopoiisiEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyPistopoiisiError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pistopoiisi2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyPistopoiisi2Error.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pistopoiisi3EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyPistopoiisi3Error.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pistopoiisi4EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyPistopoiisi4Error.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        aggeliaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyAggeliaError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        aggelia2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyAggelia2Error.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        aggelia3EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyAggelia3Error.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        aggelia4EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyAggelia4Error.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom, R.id.text1 ,categories)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(JobInfo.this);
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

        arrayAdapter2 = new ArrayAdapter<String>(JobInfo.this, R.layout.spinner_custom, R.id.text1, eidikotites_kalitehnikwn)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(JobInfo.this);
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

        arrayAdapter3 = new ArrayAdapter<String>(JobInfo.this, R.layout.spinner_custom, R.id.text1, eidikotites_xenwn) {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(JobInfo.this);
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

        if (TutorAnytimeApp.getPistopoiisi1() != null)
            pistopoiisiEditText.setText(TutorAnytimeApp.getPistopoiisi1());

        if (TutorAnytimeApp.getAggelia1() != null)
            aggeliaEditText.setText(TutorAnytimeApp.getAggelia1());

        if (TutorAnytimeApp.getCategory1() != -1) {
            spinner.setSelection(TutorAnytimeApp.getCategory1());
        }

        if (TutorAnytimeApp.getEidikotitaVisible()) {
            spinner2.setVisibility(View.VISIBLE);
            spinner2.setSelection(TutorAnytimeApp.getEidikotita1());
        }
        else
            spinner2.setVisibility(View.GONE);

        if (TutorAnytimeApp.getSecondSetVisible()){
            secondSet.setVisibility(View.VISIBLE);
            secondSetQuestion.setVisibility(View.GONE);
            spinner3.setSelection(TutorAnytimeApp.getCategory2());
            if (TutorAnytimeApp.getEidikotita2Visible()){
                spinner4.setVisibility(View.VISIBLE);
                spinner4.setSelection(TutorAnytimeApp.getEidikotita2());
            }
            else
                spinner4.setVisibility(View.GONE);
            pistopoiisi2EditText.setText(TutorAnytimeApp.getPistopoiisi2());
            aggelia2EditText.setText(TutorAnytimeApp.getAggelia2());
        }
        else
            secondSet.setVisibility(View.GONE);

        if (TutorAnytimeApp.getThirdSetVisible()) {
            thirdSet.setVisibility(View.VISIBLE);
            thirdSetQuestion.setVisibility(View.GONE);
            secondSetSubtract.setVisibility(View.GONE);
            spinner5.setSelection(TutorAnytimeApp.getCategory3());
            if (TutorAnytimeApp.getEidikotita3Visible()) {
                spinner6.setVisibility(View.VISIBLE);
                spinner6.setSelection(TutorAnytimeApp.getEidikotita3());
            }
            else
                spinner6.setVisibility(View.GONE);
            pistopoiisi3EditText.setText(TutorAnytimeApp.getPistopoiisi3());
            aggelia3EditText.setText(TutorAnytimeApp.getAggelia3());
        }
        else
            thirdSet.setVisibility(View.GONE);

        if (TutorAnytimeApp.getForthSetVisible()) {
            forthSet.setVisibility(View.VISIBLE);
            forthSetQuestion.setVisibility(View.GONE);
            thirdSetSubtract.setVisibility(View.GONE);
            spinner7.setSelection(TutorAnytimeApp.getCategory3());
            if (TutorAnytimeApp.getEidikotita4Visible()) {
                spinner8.setVisibility(View.VISIBLE);
                spinner8.setSelection(TutorAnytimeApp.getEidikotita4());
            }
            else
                spinner8.setVisibility(View.GONE);
            pistopoiisi4EditText.setText(TutorAnytimeApp.getPistopoiisi4());
            aggelia4EditText.setText(TutorAnytimeApp.getAggelia4());
        }
        else
            forthSet.setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:case 1:case 2:case 3:case 5:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:
                        spinner2.setVisibility(View.GONE);
                        eidikotitaTextView.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotitaTextView.setVisibility(View.VISIBLE);
                        spinner2.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita1() != -1) {
                            spinner2.setSelection(TutorAnytimeApp.getEidikotita1());
                        }
                        break;
                    case 6:
                        eidikotitaTextView.setVisibility(View.VISIBLE);
                        spinner2.setVisibility(View.VISIBLE);
                        spinner2.setAdapter(arrayAdapter3);
                        if (TutorAnytimeApp.getEidikotita1() != -1) {
                            spinner2.setSelection(TutorAnytimeApp.getEidikotita1());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:case 1:case 2:case 3:case 5:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:
                        spinner4.setVisibility(View.GONE);
                        eidikotita2TextView.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotita2TextView.setVisibility(View.VISIBLE);
                        spinner4.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita2() != -1) {
                            spinner4.setSelection(TutorAnytimeApp.getEidikotita2());
                        }
                        break;
                    case 6:
                        eidikotita2TextView.setVisibility(View.VISIBLE);
                        spinner4.setVisibility(View.VISIBLE);
                        spinner4.setAdapter(arrayAdapter3);
                        if (TutorAnytimeApp.getEidikotita2() != -1) {
                            spinner4.setSelection(TutorAnytimeApp.getEidikotita2());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:case 1:case 2:case 3:case 5:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:
                        spinner6.setVisibility(View.GONE);
                        eidikotita3TextView.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotita3TextView.setVisibility(View.VISIBLE);
                        spinner6.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita3() != -1) {
                            spinner6.setSelection(TutorAnytimeApp.getEidikotita3());
                        }
                        break;
                    case 6:
                        eidikotita3TextView.setVisibility(View.VISIBLE);
                        spinner6.setVisibility(View.VISIBLE);
                        spinner6.setAdapter(arrayAdapter3);
                        if (TutorAnytimeApp.getEidikotita3() != -1) {
                            spinner6.setSelection(TutorAnytimeApp.getEidikotita3());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:case 1:case 2:case 3:case 5:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:
                        spinner8.setVisibility(View.GONE);
                        eidikotita4TextView.setVisibility(View.GONE);
                        break;
                    case 4:
                        eidikotita4TextView.setVisibility(View.VISIBLE);
                        spinner8.setVisibility(View.VISIBLE);
                        if (TutorAnytimeApp.getEidikotita4() != -1) {
                            spinner8.setSelection(TutorAnytimeApp.getEidikotita4());
                        }
                        break;
                    case 6:
                        eidikotita4TextView.setVisibility(View.VISIBLE);
                        spinner8.setVisibility(View.VISIBLE);
                        spinner8.setAdapter(arrayAdapter3);
                        if (TutorAnytimeApp.getEidikotita4() != -1) {
                            spinner8.setSelection(TutorAnytimeApp.getEidikotita4());
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.next)
            checkJobInfo();
        else
            finish();
    }

    public void checkJobInfo()
    {
        boolean validationError = false;

        int category2 = -1, category3 = -1, category4 = -1, eidikotita2 = -1, eidikotita3 = -1, eidikotita4 = -1;

        String pistopoiisi2 = null, pistopoiisi3 = null, pistopoiisi4 = null,
               aggelia2 = null, aggelia3 = null, aggelia4 = null;

        int category1 = spinner.getSelectedItemPosition();
        int eidikotita1 = spinner2.getSelectedItemPosition();
        String pistopoiisi1 = pistopoiisiEditText.getText().toString().trim();
        if (pistopoiisi1.length() == 0)
        {
            validationError = true;
            emptyPistopoiisiError.setVisibility(View.VISIBLE);
        }
        String aggelia1 = aggeliaEditText.getText().toString().trim();
        if (aggelia1.length() == 0)
        {
            validationError = true;
            emptyAggeliaError.setVisibility(View.VISIBLE);
        }

        if (secondSet.getVisibility() == View.VISIBLE) {
            category2 = spinner3.getSelectedItemPosition();
            eidikotita2 = spinner4.getSelectedItemPosition();
            pistopoiisi2 = pistopoiisi2EditText.getText().toString().trim();
            if (pistopoiisi2.length() == 0) {
                validationError = true;
                emptyPistopoiisi2Error.setVisibility(View.VISIBLE);
            }
            aggelia2 = aggelia2EditText.getText().toString().trim();
            if (aggelia2.length() == 0) {
                validationError = true;
                emptyAggelia2Error.setVisibility(View.VISIBLE);
            }
        }

        if (thirdSet.getVisibility() == View.VISIBLE) {
            category3 = spinner5.getSelectedItemPosition();
            eidikotita3 = spinner6.getSelectedItemPosition();
            pistopoiisi3 = pistopoiisi3EditText.getText().toString().trim();
            if (pistopoiisi3.length() == 0) {
                validationError = true;
                emptyPistopoiisi3Error.setVisibility(View.VISIBLE);
            }
            aggelia3 = aggelia3EditText.getText().toString().trim();
            if (aggelia3.length() == 0) {
                validationError = true;
                emptyAggelia3Error.setVisibility(View.VISIBLE);
            }
        }

        if (forthSet.getVisibility() == View.VISIBLE) {
            category4 = spinner7.getSelectedItemPosition();
            eidikotita4 = spinner8.getSelectedItemPosition();
            pistopoiisi4 = pistopoiisi4EditText.getText().toString().trim();
            if (pistopoiisi4.length() == 0) {
                validationError = true;
                emptyPistopoiisi4Error.setVisibility(View.VISIBLE);
            }
            aggelia4 = aggelia4EditText.getText().toString().trim();
            if (aggelia4.length() == 0) {
                validationError = true;
                emptyAggelia4Error.setVisibility(View.VISIBLE);
            }
        }
        if (!validationError)
        {
            TutorAnytimeApp.setCategory1(category1);
            if (eidikotitaTextView.getVisibility() == View.VISIBLE) {
                TutorAnytimeApp.setEidikotitaVisible(true);
                TutorAnytimeApp.setEidikotita1(eidikotita1);
            }
            else
                TutorAnytimeApp.setEidikotitaVisible(false);
            TutorAnytimeApp.setPistopoiisi1(pistopoiisi1);
            TutorAnytimeApp.setAggelia1(aggelia1);

            if (secondSet.getVisibility() == View.VISIBLE) {
                TutorAnytimeApp.setCategory2(category2);
                TutorAnytimeApp.setSecondSetVisible(true);
                if (eidikotita2TextView.getVisibility() == View.VISIBLE){
                    TutorAnytimeApp.setEidikotita2Visible(true);
                    TutorAnytimeApp.setEidikotita2(eidikotita2);
                }
                else
                    TutorAnytimeApp.setEidikotita2Visible(false);
                TutorAnytimeApp.setPistopoiisi2(pistopoiisi2);
                TutorAnytimeApp.setAggelia2(aggelia2);
            }
            else
                TutorAnytimeApp.setSecondSetVisible(false);

            if (thirdSet.getVisibility() == View.VISIBLE) {
                TutorAnytimeApp.setCategory3(category3);
                TutorAnytimeApp.setThirdSetVisible(true);
                if (eidikotita3TextView.getVisibility() == View.VISIBLE){
                    TutorAnytimeApp.setEidikotita3Visible(true);
                    TutorAnytimeApp.setEidikotita3(eidikotita3);
                }
                else
                    TutorAnytimeApp.setEidikotita3Visible(false);
                TutorAnytimeApp.setPistopoiisi3(pistopoiisi3);
                TutorAnytimeApp.setAggelia3(aggelia3);
            }
            else
                TutorAnytimeApp.setThirdSetVisible(false);


            if (forthSet.getVisibility() == View.VISIBLE) {
                TutorAnytimeApp.setCategory4(category4);
                TutorAnytimeApp.setForthSetVisible(true);
                if (eidikotita4TextView.getVisibility() == View.VISIBLE){
                    TutorAnytimeApp.setEidikotita4Visible(true);
                    TutorAnytimeApp.setEidikotita4(eidikotita4);
                }
                else
                    TutorAnytimeApp.setEidikotita4Visible(false);
                TutorAnytimeApp.setPistopoiisi4(pistopoiisi4);
                TutorAnytimeApp.setAggelia4(aggelia4);
            }
            else
                TutorAnytimeApp.setForthSetVisible(false);

            startActivity(new Intent(this, Photo.class));
        }
    }

    public void addClicked(View view)
    {
        secondSetQuestion.setVisibility(View.GONE);
        secondSet.setVisibility(View.VISIBLE);

        if (TutorAnytimeApp.getPistopoiisi2() != null)
            pistopoiisi2EditText.setText(TutorAnytimeApp.getPistopoiisi2());

        if (TutorAnytimeApp.getAggelia2() != null)
            aggelia2EditText.setText(TutorAnytimeApp.getAggelia2());

        if (TutorAnytimeApp.getCategory2() != -1) {
            spinner3.setSelection(TutorAnytimeApp.getCategory2());
        }

    }

    public void addClicked2(View view)
    {
        thirdSetQuestion.setVisibility(View.GONE);
        secondSetSubtract.setVisibility(View.GONE);
        thirdSet.setVisibility(View.VISIBLE);

        if (TutorAnytimeApp.getPistopoiisi3() != null)
            pistopoiisi3EditText.setText(TutorAnytimeApp.getPistopoiisi3());

        if (TutorAnytimeApp.getAggelia3() != null)
            aggelia3EditText.setText(TutorAnytimeApp.getAggelia3());


        if (TutorAnytimeApp.getCategory3() != -1) {
            spinner5.setSelection(TutorAnytimeApp.getCategory3());
        }

    }

    public void addClicked3(View view)
    {
        forthSetQuestion.setVisibility(View.GONE);
        thirdSetSubtract.setVisibility(View.GONE);
        forthSet.setVisibility(View.VISIBLE);

        if (TutorAnytimeApp.getPistopoiisi4() != null)
            pistopoiisi4EditText.setText(TutorAnytimeApp.getPistopoiisi4());

        if (TutorAnytimeApp.getAggelia4() != null)
            aggelia4EditText.setText(TutorAnytimeApp.getAggelia4());

        if (TutorAnytimeApp.getCategory4() != -1) {
            spinner7.setSelection(TutorAnytimeApp.getCategory4());
        }
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
}
