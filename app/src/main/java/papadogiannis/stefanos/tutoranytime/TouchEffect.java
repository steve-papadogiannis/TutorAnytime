package papadogiannis.stefanos.tutoranytime;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchEffect implements OnTouchListener {

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			v.setBackgroundResource(R.drawable.button_bg_blue_alpha);
		}
		else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
			v.setBackgroundResource(R.drawable.button_bg_blue);
		}
		return false;
	}

}
