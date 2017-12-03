package papadogiannis.stefanos.tutoranytime;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class TouchEffect2 implements View.OnTouchListener {

    private Context context;

    public TouchEffect2(Context context) {
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //noinspection deprecation
            ((Button) v).setTextColor(context.getResources().getColor(R.color.orange));
        }
        else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            //noinspection deprecation
            ((Button) v).setTextColor(context.getResources().getColor(R.color.blue));
        }
        return false;
    }

}
