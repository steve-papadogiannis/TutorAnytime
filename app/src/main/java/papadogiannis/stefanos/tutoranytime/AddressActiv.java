package papadogiannis.stefanos.tutoranytime;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class AddressActiv extends Custom
{

    private EditText addressEditText, dimosEditText, arithmosEditText;
    private Spinner nomosSpinner;
    private String[] nomoi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);

        addressEditText = (EditText) findViewById(R.id.address);
        addressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextView textView = (TextView) findViewById(R.id.empty_address_error);
                textView.setVisibility(View.GONE);
                TextView textView2 = (TextView) findViewById(R.id.format_address_error);
                textView2.setVisibility(View.GONE);
                TextView textView3 = (TextView) findViewById(R.id.big_address_error);
                textView3.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        arithmosEditText = (EditText) findViewById(R.id.arithmos);
        arithmosEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                TextView textView = (TextView) findViewById(R.id.empty_arithmos_error);
                textView.setVisibility(View.GONE);
                TextView textView2 = (TextView) findViewById(R.id.format_arithmos_error);
                textView2.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        dimosEditText = (EditText) findViewById(R.id.perioxi);
        dimosEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextView textView = (TextView) findViewById(R.id.empty_dimos_error);
                textView.setVisibility(View.GONE);
                TextView textView2 = (TextView) findViewById(R.id.format_dimos_error);
                textView2.setVisibility(View.GONE);
                TextView textView3 = (TextView) findViewById(R.id.big_dimos_error);
                textView3.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        nomoi = getResources().getStringArray(R.array.nomoi);

        nomosSpinner = (Spinner) findViewById(R.id.nomos);

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1, nomoi)
        {
            @Override
            public View getDropDownView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(AddressActiv.this);
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

        nomosSpinner.setAdapter(arrayAdapter);

        if (TutorAnytimeApp.getNomos() != -1) {
            nomosSpinner.setSelection(TutorAnytimeApp.getNomos());
        }

        if (TutorAnytimeApp.getAddress() != null)
            addressEditText.setText(TutorAnytimeApp.getAddress());
        if (TutorAnytimeApp.getAddressNumber() != null)
            arithmosEditText.setText(TutorAnytimeApp.getAddressNumber());
        if (TutorAnytimeApp.getDimos() != null)
            dimosEditText.setText(TutorAnytimeApp.getDimos());
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.next)
            checkAddressData();
        else
            finish();
    }

    public void checkAddressData()
    {
        String address = addressEditText.getText().toString().trim();
        String arithmos = arithmosEditText.getText().toString().trim();
        String dimos = dimosEditText.getText().toString().trim();
        int nomos = nomosSpinner.getSelectedItemPosition();
        // Validate the register data
        boolean validationError = false;
        if (address.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_address_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (!isValidAddress(address))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.format_address_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (address.length() > 35)
        {
            validationError = true;
            TextView textView = (TextView) findViewById(R.id.big_address_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (arithmos.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_arithmos_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (!isValidArithmos(arithmos))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.format_arithmos_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (dimos.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_dimos_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (!isValidAddress(dimos))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.format_dimos_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (dimos.length() > 35)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.big_dimos_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (validationError)
        {
            return;
        }
        else
        {
            TutorAnytimeApp.setAddress(address);
            TutorAnytimeApp.setAddressNumber(arithmos);
            TutorAnytimeApp.setDimos(dimos);
            TutorAnytimeApp.setNomos(nomos);
            startActivity(new Intent(this, AddressActiv2.class));
        }
    }

    public boolean isValidArithmos(String number)
    {
        String ePattern = "[0-9]{0,3}([\\u0020][-][\\u0020][0-9]{1,3})?";
        java.util.regex.Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(number);
        return m.matches();
    }

    public boolean isValidAddress(String address)
    {
        String ePattern = "([α-ωΑ-ΩάέήίόύώϊϋΆΈΉΊΌΎΏΪΫ\\u0390\\u03B0]*[\\u0020]?){0,2}[α-ωΑ-ΩάέήίόύώϊϋΆΈΉΊΌΎΏΪΫ\\u0390\\u03B0]*";
        java.util.regex.Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(address);
        return m.matches();
    }

}
