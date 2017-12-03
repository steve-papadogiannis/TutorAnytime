package papadogiannis.stefanos.tutoranytime;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.ArraySet;

import com.google.android.gms.maps.model.LatLng;
import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class TutorAnytimeApp extends Application {

    // Key for saving the search distance preference
    private static final String KEY_SEARCH_DISTANCE = "searchDistance";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_TELEPHONE = "telephone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_DIMOS = "dimos";
    private static final String KEY_ADDRESS_NUMBER = "address_number";
    private static final String KEY_NOMOS = "nomos";
    private static final String KEY_CATEGORY1 = "category1";
    private static final String KEY_CATEGORY2 = "category2";
    private static final String KEY_CATEGORY3 = "category3";
    private static final String KEY_CATEGORY4 = "category4";
    private static final String KEY_EIDIKOTITA1 = "eidikotita1";
    private static final String KEY_EIDIKOTITA2 = "eidikotita2";
    private static final String KEY_EIDIKOTITA3 = "eidikotita3";
    private static final String KEY_EIDIKOTITA4 = "eidikotita4";
    private static final String KEY_PISTOPOIISI1 = "pistopoiisi1";
    private static final String KEY_PISTOPOIISI2 = "pistopoiisi2";
    private static final String KEY_PISTOPOIISI3 = "pistopoiisi3";
    private static final String KEY_PISTOPOIISI4 = "pistopoiisi4";
    private static final String KEY_AGGELIA1 = "aggelia1";
    private static final String KEY_AGGELIA2 = "aggelia2";
    private static final String KEY_AGGELIA3 = "aggelia3";
    private static final String KEY_AGGELIA4 = "aggelia4";
    private static final String KEY_EIDIKOTITA_VISIBLE = "eidikotita_visible";
    private static final String KEY_EIDIKOTITA2_VISIBLE = "eidikotita2_visible";
    private static final String KEY_EIDIKOTITA3_VISIBLE = "eidikotita3_visible";
    private static final String KEY_EIDIKOTITA4_VISIBLE = "eidikotita4_visible";
    private static final String KEY_SECOND_SET_VISIBLE = "second_set_visible";
    private static final String KEY_THIRD_SET_VISIBLE = "third_set_visible";
    private static final String KEY_FORTH_SET_VISIBLE = "forth_set_visible";
    private static final String KEY_PHOTO_PATH = "photo_path";

    private static final float DEFAULT_SEARCH_DISTANCE = 150.0f;

    private static SharedPreferences preferences;

    public static void setPhotoPath(String photoPath)
    {
        preferences.edit().putString(KEY_PHOTO_PATH, photoPath).apply();
    }

    public static String getPhotoPath(){
        return preferences.getString(KEY_PHOTO_PATH, null);
    }

    public static void clearRegisterPreferences()
    {
        preferences.edit().clear().apply();
    }

    public static void setName(String name) {
        preferences.edit().putString(KEY_NAME, name).apply();
    }

    public static void setCategory1(int category) {
        preferences.edit().putInt(KEY_CATEGORY1, category).apply();
    }

    public static void setCategory2(int category) {
        preferences.edit().putInt(KEY_CATEGORY2, category).apply();
    }

    public static void setCategory3(int category) {
        preferences.edit().putInt(KEY_CATEGORY3, category).apply();
    }

    public static void setCategory4(int category) {
        preferences.edit().putInt(KEY_CATEGORY4, category).apply();
    }

    public static void setEidikotita1(int eidikotita) {
        preferences.edit().putInt(KEY_EIDIKOTITA1, eidikotita).apply();
    }

    public static void setEidikotita2(int eidikotita) {
        preferences.edit().putInt(KEY_EIDIKOTITA2, eidikotita).apply();
    }

    public static void setEidikotita3(int eidikotita) {
        preferences.edit().putInt(KEY_EIDIKOTITA3, eidikotita).apply();
    }

    public static void setEidikotita4(int eidikotita) {
        preferences.edit().putInt(KEY_EIDIKOTITA4, eidikotita).apply();
    }

    public static void setPistopoiisi1(String pistopoiisi) {
        preferences.edit().putString(KEY_PISTOPOIISI1, pistopoiisi).apply();
    }

    public static void setPistopoiisi2(String pistopoiisi) {
        preferences.edit().putString(KEY_PISTOPOIISI2, pistopoiisi).apply();
    }

    public static void setPistopoiisi3(String pistopoiisi) {
        preferences.edit().putString(KEY_PISTOPOIISI3, pistopoiisi).apply();
    }

    public static void setPistopoiisi4(String pistopoiisi) {
        preferences.edit().putString(KEY_PISTOPOIISI4, pistopoiisi).apply();
    }

    public static void setAggelia1(String aggelia) {
        preferences.edit().putString(KEY_AGGELIA1, aggelia).apply();
    }

    public static void setAggelia2(String aggelia) {
        preferences.edit().putString(KEY_AGGELIA2, aggelia).apply();
    }

    public static void setAggelia3(String aggelia) {
        preferences.edit().putString(KEY_AGGELIA3, aggelia).apply();
    }

    public static void setAggelia4(String aggelia) {
        preferences.edit().putString(KEY_AGGELIA4, aggelia).apply();
    }

    public static void setSurname(String surname) {
        preferences.edit().putString(KEY_SURNAME, surname).apply();
    }

    public static void setTelephone(String telephone) {
        preferences.edit().putString(KEY_TELEPHONE, telephone).apply();
    }

    public static void setEmail(String email) {
        preferences.edit().putString(KEY_EMAIL, email).apply();
    }

    public static String getName() {
        return preferences.getString(KEY_NAME, null);
    }

    public static int getCategory1() {
        return preferences.getInt(KEY_CATEGORY1, -1);
    }

    public static int getCategory2() {
        return preferences.getInt(KEY_CATEGORY2, -1);
    }
    public static int getCategory3() {
        return preferences.getInt(KEY_CATEGORY3, -1);
    }
    public static int getCategory4() {
        return preferences.getInt(KEY_CATEGORY4, -1);
    }

    public static int getEidikotita1() {
        return preferences.getInt(KEY_EIDIKOTITA1, -1);
    }

    public static int getEidikotita2() {
        return preferences.getInt(KEY_EIDIKOTITA2, -1);
    }

    public static int getEidikotita3() {
        return preferences.getInt(KEY_EIDIKOTITA3, -1);
    }

    public static String getPistopoiisi1() {
        return preferences.getString(KEY_PISTOPOIISI1, null);
    }

    public static String getPistopoiisi2() {
        return preferences.getString(KEY_PISTOPOIISI2, null);
    }

    public static String getPistopoiisi3() {
        return preferences.getString(KEY_PISTOPOIISI3, null);
    }

    public static String getPistopoiisi4() {
        return preferences.getString(KEY_PISTOPOIISI4, null);
    }

    public static String getAggelia1() {
        return preferences.getString(KEY_AGGELIA1, null);
    }

    public static String getAggelia2() {
        return preferences.getString(KEY_AGGELIA2, null);
    }

    public static String getAggelia3() {
        return preferences.getString(KEY_AGGELIA3, null);
    }

    public static String getAggelia4() {
        return preferences.getString(KEY_AGGELIA4, null);
    }

    public static int getEidikotita4() {
        return preferences.getInt(KEY_EIDIKOTITA4, -1);
    }

    public static String getSurname() {
        return preferences.getString(KEY_SURNAME, null);
    }

    public static String getTelephone() {
        return preferences.getString(KEY_TELEPHONE, null);
    }

    public static String getEmail() {
        return preferences.getString(KEY_EMAIL, null);
    }

    public static void setEidikotitaVisible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_EIDIKOTITA_VISIBLE, visible).apply();
    }

    public static void setEidikotita2Visible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_EIDIKOTITA2_VISIBLE, visible).apply();
    }

    public static void setEidikotita3Visible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_EIDIKOTITA3_VISIBLE, visible).apply();
    }

    public static void setEidikotita4Visible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_EIDIKOTITA4_VISIBLE, visible).apply();
    }

    public static boolean getEidikotitaVisible()
    {
        return preferences.getBoolean(KEY_EIDIKOTITA_VISIBLE, false);
    }

    public static boolean getEidikotita2Visible()
    {
        return preferences.getBoolean(KEY_EIDIKOTITA2_VISIBLE, false);
    }

    public static boolean getEidikotita3Visible()
    {
        return preferences.getBoolean(KEY_EIDIKOTITA3_VISIBLE, false);
    }

    public static boolean getEidikotita4Visible()
    {
        return preferences.getBoolean(KEY_EIDIKOTITA4_VISIBLE, false);
    }

    public static void setSecondSetVisible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_SECOND_SET_VISIBLE, visible).apply();
    }

    public static boolean getSecondSetVisible()
    {
        return preferences.getBoolean(KEY_SECOND_SET_VISIBLE, false);
    }

    public static boolean getThirdSetVisible()
    {
        return preferences.getBoolean(KEY_THIRD_SET_VISIBLE, false);
    }

    public static boolean getForthSetVisible()
    {
        return preferences.getBoolean(KEY_FORTH_SET_VISIBLE, false);
    }

    public static void setThirdSetVisible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_THIRD_SET_VISIBLE, visible).apply();
    }

    public static void setForthSetVisible(boolean visible)
    {
        preferences.edit().putBoolean(KEY_FORTH_SET_VISIBLE, visible).apply();
    }

    public static void setPosition(LatLng position)
    {
        preferences.edit().putFloat(KEY_LATITUDE, (float) position.latitude).apply();
        preferences.edit().putFloat(KEY_LONGITUDE, (float) position.longitude).apply();
    }

    public static LatLng getPosition()
    {
        return new LatLng(preferences.getFloat(KEY_LATITUDE, 0.0f), preferences.getFloat(KEY_LONGITUDE, 0.0f));
    }

    public static void setAddress(String address)
    {
        preferences.edit().putString(KEY_ADDRESS, address).apply();
    }

    public static String getAddress()
    {
        return preferences.getString(KEY_ADDRESS, null);
    }

    public static String getDimos()
    {
        return preferences.getString(KEY_DIMOS, null);
    }

    public static void setDimos(String dimos)
    {
        preferences.edit().putString(KEY_DIMOS, dimos).apply();
    }

    public static void setAddressNumber(String addressNumber) {
        preferences.edit().putString(KEY_ADDRESS_NUMBER, addressNumber).apply();
    }

    public static String getAddressNumber()
    {
        return preferences.getString(KEY_ADDRESS_NUMBER, null);
    }

    public static void setNomos(int nomos)
    {
        preferences.edit().putInt(KEY_NOMOS, nomos).apply();
    }

    public static int getNomos()
    {
        return preferences.getInt(KEY_NOMOS, -1);
    }

    @Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "", "");
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.saveInBackground();
        preferences = getSharedPreferences("papadogiannis.stefanos.tutoranytime", Context.MODE_PRIVATE);
	}

    public static float getSearchDistance() {
        return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
    }

    public static void setSearchDistance( float value ) {
        preferences.edit().putFloat( KEY_SEARCH_DISTANCE, value ).apply();
    }

    public static void setUsername(String username)
    {
        preferences.edit().putString(KEY_USERNAME, username).apply();
    }

    public static String getUsername()
    {
        return preferences.getString(KEY_USERNAME, null);
    }

    public static void setPassword(String password)
    {
        preferences.edit().putString(KEY_PASSWORD, password).apply();
    }

    public static String getPassword()
    {
        return preferences.getString(KEY_PASSWORD, null);
    }
}
