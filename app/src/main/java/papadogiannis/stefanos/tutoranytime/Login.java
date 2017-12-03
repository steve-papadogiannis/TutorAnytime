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
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Login extends Custom {

    private EditText usernameEditText, passwordEditText;
    private TextView emptyUsernameError, emptyPasswordError, nonExistingUserError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setTouchNClick(R.id.login_button);
        setTouchNClick2(R.id.register_link_button);

        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);

        emptyUsernameError = (TextView)findViewById(R.id.empty_username_error);
        emptyPasswordError = (TextView)findViewById(R.id.empty_password_error);
        nonExistingUserError = (TextView) findViewById(R.id.non_existing_user_error);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emptyUsernameError.setVisibility(View.GONE);
                nonExistingUserError.setVisibility(View.GONE);
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
                nonExistingUserError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.login_button) {
            if (!hasConnection())
                showDialog();
            else
                login();
        }
        else
        {
            startActivity(new Intent(this, Register.class));
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

    private void login() {
        final String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        boolean validationError = false;
        if (username.length() == 0) {
            validationError = true;
            emptyUsernameError.setVisibility(View.VISIBLE);
        }
        if (password.length() == 0) {
            validationError = true;
            emptyPasswordError.setVisibility(View.VISIBLE);
        }
        if (!validationError) {
            try {
                password = SHA512.sha512(password);
            }
            catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            final ProgressDialog progressDialog = new ProgressDialog(Login.this);
            progressDialog.setMessage(getString(R.string.progress_login));
            progressDialog.show();
            ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser pu, ParseException e) {
                    progressDialog.dismiss();
                    if (e != null) {
                        if (e.getCode() == ParseException.OBJECT_NOT_FOUND)
                            nonExistingUserError.setVisibility(View.VISIBLE);
                        else
                            Log.e("tsifsa",String.valueOf(e.getCode()));
                    } else {
                        Intent intent = new Intent(Login.this, Map.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }
}
