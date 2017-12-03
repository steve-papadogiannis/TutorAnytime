package papadogiannis.stefanos.tutoranytime;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalData extends Custom
{

    private EditText nameEditText, surnameEditText, telephoneEditText, emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);
        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextView textView = (TextView)findViewById(R.id.empty_name_error);
                textView.setVisibility(View.GONE);
                TextView textView1 = (TextView)findViewById(R.id.small_name_error);
                textView1.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        surnameEditText = (EditText) findViewById(R.id.surname_edit_text);
        surnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextView textView = (TextView)findViewById(R.id.empty_surname_error);
                textView.setVisibility(View.GONE);
                TextView textView1 = (TextView)findViewById(R.id.small_surname_error);
                textView1.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        telephoneEditText = (EditText) findViewById(R.id.telephone_edit_text);
        telephoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextView textView = (TextView)findViewById(R.id.empty_telephone_error);
                textView.setVisibility(View.GONE);
                TextView textView1 = (TextView)findViewById(R.id.small_telephone_error);
                textView1.setVisibility(View.GONE);
                TextView textView2 = (TextView)findViewById(R.id.not_digit_telephone_error);
                textView2.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TextView textView = (TextView)findViewById(R.id.empty_email_error);
                textView.setVisibility(View.GONE);
                TextView textView1 = (TextView)findViewById(R.id.format_email_error);
                textView1.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if (TutorAnytimeApp.getName() != null)
            nameEditText.setText(TutorAnytimeApp.getName());
        if (TutorAnytimeApp.getSurname() != null)
            surnameEditText.setText(TutorAnytimeApp.getSurname());
        if (TutorAnytimeApp.getTelephone() != null)
            telephoneEditText.setText(TutorAnytimeApp.getTelephone());
        if (TutorAnytimeApp.getEmail() != null)
            emailEditText.setText(TutorAnytimeApp.getEmail());
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.next)
            checkPersonalData();
        else
            finish();
    }

    public void checkPersonalData()
    {
        String name = nameEditText.getText().toString().trim();
        String surname = surnameEditText.getText().toString().trim();
        String telephone = telephoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        // Validate the register data
        boolean validationError = false;
        if (name.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_name_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (name.length() < 3)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.small_name_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (!isValidName(name))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.format_name_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (surname.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_surname_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (surname.length() < 3)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.small_surname_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (!isValidName(surname))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.format_surname_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (telephone.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_telephone_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (telephone.length() < 10)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.small_telephone_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (!isValidTelephone(telephone))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.not_digit_telephone_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (email.length() == 0)
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.empty_email_error);
            textView.setVisibility(View.VISIBLE);
        }
        else if (!isValidEmailAddress(email))
        {
            validationError = true;
            TextView textView = (TextView)findViewById(R.id.format_email_error);
            textView.setVisibility(View.VISIBLE);
        }
        if (validationError)
        {
            return;
        }
        else
        {
            TutorAnytimeApp.setName(name);
            TutorAnytimeApp.setSurname(surname);
            TutorAnytimeApp.setTelephone(telephone);
            TutorAnytimeApp.setEmail(email);
            startActivity(new Intent(PersonalData.this, AddressActiv.class));
        }
    }

    public boolean isValidEmailAddress(String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidName(String address)
    {
        String ePattern = "((([α-ωΑ-ΩάέήίόύώϊϋΆΈΉΊΌΎΏΪΫ\\u0390\\u03B0]){3,25})|(([a-zA-Z]){3,25}))" +
                "([\\u0020]((([-][\\u0020])?)(([α-ωΑ-ΩάέήίόύώϊϋΆΈΉΊΌΎΏΪΫ\\u0390\\u03B0])|([a-zA-Z])){3,25})){0,2}";
        java.util.regex.Pattern p = Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(address);
        return m.matches();
    }

    public boolean isValidTelephone(String telephone)
    {
        String pattern = "(2|69)[0-9]*";
        Pattern pattern1 = Pattern.compile(pattern);
        Matcher matcher = pattern1.matcher(telephone);
        return matcher.matches();
    }

}
