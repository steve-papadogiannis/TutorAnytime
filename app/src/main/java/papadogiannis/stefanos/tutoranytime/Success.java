package papadogiannis.stefanos.tutoranytime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Success extends Custom {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);
        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);
    }

    public void onClick(View v)
    {
        if (v.getId() == R.id.next)
        {
            Intent intent = new Intent(this, Map.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
