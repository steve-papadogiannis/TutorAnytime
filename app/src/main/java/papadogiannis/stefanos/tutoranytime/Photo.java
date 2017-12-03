package papadogiannis.stefanos.tutoranytime;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class Photo extends Custom {

    private static final int SELECT_PICTURE = 1;
    private String imagePath;
    int TAKE_PHOTO_CODE = 0;
    public static int count=0;
    private File newfile;
    private Bitmap myBitmap;
    private ImageView photoImageView;
    private LinearLayout setSubtract, setAdd;
    private TextView photoAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);

        photoImageView = (ImageView) findViewById(R.id.photoImageView);
        setSubtract = (LinearLayout) findViewById(R.id.set_subtract);
        setAdd = (LinearLayout) findViewById(R.id.set_add);

        photoAdded = (TextView) findViewById(R.id.photo_added);

        if (TutorAnytimeApp.getPhotoPath() != null) {
            myBitmap = BitmapFactory.decodeFile(TutorAnytimeApp.getPhotoPath());
            setAdd.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            photoImageView.setImageBitmap(myBitmap);
            photoAdded.setVisibility(View.VISIBLE);
            setSubtract.setVisibility(View.VISIBLE);
        }
    }

    public void photoRemove(View view) {
        photoImageView.setVisibility(View.GONE);
        setAdd.setVisibility(View.VISIBLE);
        photoAdded.setVisibility(View.GONE);
        setSubtract.setVisibility(View.GONE);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.next) {
            TutorAnytimeApp.setPhotoPath(imagePath);
            startActivity(new Intent(this, Revision.class));
        }
        else
            finish();
    }

    public void photoAddClicked(View view) {
        TextView textView = (TextView) findViewById(R.id.photo_added);
        textView.setVisibility(View.GONE);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.selection);
        dialog.setCancelable(false);
        dialog.show();

        Button photoCaption = (Button) dialog.findViewById(R.id.photo_caption_button);
        photoCaption.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                File root = Environment.getExternalStorageDirectory();

                File dir = new File (root.getAbsolutePath() + "/TutorAnyTime/"); //it is my root directory

                File image = new File (dir.getAbsolutePath()+ "/Pictures/"); // it is my sub folder directory .. it can vary..

                try {
                    if(!dir.exists()) {
                        dir.mkdirs();
                    }
                    if(!image.exists()) {
                        image.mkdirs();
                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                count++;
                String file = image.getAbsolutePath()+"/Picture"+count+".jpg";
                newfile = new File(file);
                try {
                    newfile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }

        });

        Button gallerySelection = (Button) dialog.findViewById(R.id.gallery_selection_button);
        gallerySelection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Επιλέξτε Πηγή"), SELECT_PICTURE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                imagePath = getPath(selectedImageUri);
                myBitmap = BitmapFactory.decodeFile(imagePath);
                photoImageView.setImageBitmap(myBitmap);
                photoImageView.setVisibility(View.VISIBLE);
                setAdd.setVisibility(View.GONE);
                photoAdded.setVisibility(View.VISIBLE);
                setSubtract.setVisibility(View.VISIBLE);
            }
            if (requestCode == TAKE_PHOTO_CODE) {
                if (newfile.exists()) {
                    Log.d("CameraDemo", "Pic saved");
                    imagePath = newfile.getAbsolutePath();
                    myBitmap = BitmapFactory.decodeFile(imagePath);
                    photoImageView.setImageBitmap(myBitmap);
                    photoImageView.setVisibility(View.VISIBLE);
                    setAdd.setVisibility(View.GONE);
                    photoAdded.setVisibility(View.VISIBLE);
                    setSubtract.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }


}
