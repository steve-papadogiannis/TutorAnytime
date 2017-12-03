package papadogiannis.stefanos.tutoranytime;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends Custom {

    private EditText usernameEditText, passwordEditText, passwordAgainEditText;
    private TextView emptyUsernameError, emptyPasswordError, emptyPasswordAgainError,
                     differentPasswordError, usedUsernameError, smallUsernameError,
                     smallPasswordError, formatUsernameError, formatUsernameHint,
                     bigUsernameError, bigPasswordError, formatPasswordError,
                     formatPasswordHint;
    private String regex;
    private Pattern pattern;
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setTouchNClick(R.id.register_button);
        setTouchNClick2(R.id.login_link_button);

        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);

        emptyUsernameError = (TextView)findViewById(R.id.empty_username_error);
        emptyPasswordError = (TextView)findViewById(R.id.empty_password_error);
        emptyPasswordAgainError = (TextView)findViewById(R.id.empty_password_again_error);
        differentPasswordError = (TextView)findViewById(R.id.different_password_again_error);
        usedUsernameError = (TextView)findViewById(R.id.used_username_error);
        smallUsernameError = (TextView) findViewById(R.id.small_username_error);
        smallPasswordError = (TextView) findViewById(R.id.small_password_error);
        bigUsernameError = (TextView) findViewById(R.id.big_username_error);
        formatUsernameError = (TextView) findViewById(R.id.format_username_error);
        formatUsernameHint = (TextView) findViewById(R.id.format_username_hint);
        bigPasswordError = (TextView) findViewById(R.id.big_password_error);
        formatPasswordError = (TextView) findViewById(R.id.format_password_error);
        formatPasswordHint = (TextView) findViewById(R.id.format_password_hint);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usedUsernameError.setVisibility(View.GONE);
                emptyUsernameError.setVisibility(View.GONE);
                smallUsernameError.setVisibility(View.GONE);
                bigUsernameError.setVisibility(View.GONE);
                formatUsernameError.setVisibility(View.GONE);
                formatUsernameHint.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyPasswordError.setVisibility(View.GONE);
                differentPasswordError.setVisibility(View.GONE);
                smallPasswordError.setVisibility(View.GONE);
                bigPasswordError.setVisibility(View.GONE);
                formatPasswordError.setVisibility(View.GONE);
                formatPasswordHint.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        passwordAgainEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyPasswordAgainError.setVisibility(View.GONE);
                differentPasswordError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        if (TutorAnytimeApp.getUsername() != null)
            usernameEditText.setText(TutorAnytimeApp.getUsername());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            if (!hasConnection())
                showDialog();
            else
                register();
        }
        else {
            startActivity(new Intent(this, Login.class));
            finish();
        }
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

    private void register() {
        final String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordAgain = passwordAgainEditText.getText().toString();
        boolean validationError = false;
        if (username.length() == 0) {
            validationError = true;
            emptyUsernameError.setVisibility(View.VISIBLE);
        }
        else if (username.length() < 8) {
            validationError = true;
            smallUsernameError.setVisibility(View.VISIBLE);
        }
        else if (username.length() > 20) {
            validationError = true;
            bigUsernameError.setVisibility(View.VISIBLE);
        }
        else if (!isValidUsername(username)) {
            validationError = true;
            formatUsernameError.setVisibility(View.VISIBLE);
            formatUsernameHint.setVisibility(View.VISIBLE);
        }
        if (password.length() == 0) {
            validationError = true;
            emptyPasswordError.setVisibility(View.VISIBLE);
        }
        else if (password.length() < 8) {
            validationError = true;
            smallPasswordError.setVisibility(View.VISIBLE);
        }
        else if (password.length() < 8) {
            validationError = true;
            bigPasswordError.setVisibility(View.VISIBLE);
        }
        else if (!isValidPassword(password)) {
            validationError = true;
            formatPasswordError.setVisibility(View.VISIBLE);
            formatPasswordHint.setVisibility(View.VISIBLE);
        }
        if (passwordAgain.length() == 0) {
            validationError = true;
            emptyPasswordAgainError.setVisibility(View.VISIBLE);
        }
        else if (!password.equals(passwordAgain)) {
            validationError = true;
            differentPasswordError.setVisibility(View.VISIBLE);
        }
        if (!validationError) {
            try {
                password = SHA512.sha512(password);
            }
            catch (NoSuchAlgorithmException e) {
                Log.e("", "Ο sha512 δεν μπόρεσε να εκτελεσθεί");
            }
            catch (UnsupportedEncodingException e) {
                Log.e("", "Δεν υποστηρίζεται το encoding του κωδικού");
            }
            final String password2 = password;
            final ProgressDialog progressDialog = new ProgressDialog(Register.this);
            progressDialog.setMessage(getString(R.string.progress_register));
            progressDialog.show();
            ParseUser.getQuery().whereEqualTo("username", username).findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<ParseUser> li, ParseException e) {
                    progressDialog.dismiss();
                    if (li != null) {
                        if (li.size() == 0) {
                            TutorAnytimeApp.setUsername(username);
                            TutorAnytimeApp.setPassword(password2);
                            startActivity(new Intent(Register.this, PersonalData.class));
                        }
                        else {
                            usedUsernameError.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
    }

    public boolean isValidUsername(String username) {
        String ePattern = "^[a-z0-9_-]{8,20}$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(username);
        return m.matches();
    }

    public boolean isValidPassword(String password) {
        return isInRange(password) && containsLowerCaseLetter(password) &&
                containsUpperCaseLetter(password) && containsNumber(password)
                && containsPermittedSymbol(password) && !containsBannedSymbol(password)
                && !containsSpaceSymbol(password);
    }

    public boolean isInRange(String password){
        regex = "[\\u0021-\\u007e]+";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean containsLowerCaseLetter(String password){
        regex = "[a-z]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean containsUpperCaseLetter(String password){
        regex = "[A-Z]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean containsNumber(String password){
        regex = "[0-9]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean containsPermittedSymbol(String password){
        regex = "[`~!@#^*()_+={}:|,/?]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean containsBannedSymbol(String password){
        regex = "[$%&<>;\\u005b\\u005c\\u005d\\u0022\\u002d\\u002e\\u0027]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean containsSpaceSymbol(String password){
        regex = "[\\s]";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(password);
        return matcher.find();
    }
}
