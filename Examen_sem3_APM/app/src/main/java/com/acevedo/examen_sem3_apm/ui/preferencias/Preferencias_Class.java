package com.acevedo.examen_sem3_apm.ui.preferencias;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.acevedo.examen_sem3_apm.R;

public class Preferencias_Class extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias_class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean dat1 = preferences.getBoolean("mantener_sesion",true);
        String dat1S;
        String dat2 = preferences.getString("mantener_usuario","no registrado");
        if(dat1) dat1S="Si";
        else dat1S="No";
        Toast.makeText(this, "Mantener sesion "+ dat1S +"\n Usuario" + dat2, Toast.LENGTH_SHORT).show();
    }
}
