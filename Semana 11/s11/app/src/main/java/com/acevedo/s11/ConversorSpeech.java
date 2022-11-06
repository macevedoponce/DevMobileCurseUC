package com.acevedo.s11;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class ConversorSpeech {
    TextToSpeech mtts = null;
    boolean isLoaded = false;

    public void init(Context context){
        try{
            mtts = new TextToSpeech(context,onInitListener);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private TextToSpeech.OnInitListener onInitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            Locale lenguaje = new Locale("es","ES");
            if(status==TextToSpeech.SUCCESS){
                int result = mtts.setLanguage(lenguaje);
                isLoaded = true;
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Log.i("TT", "El lenguaje elegido no esta soportado");
                }
            }
        }
    };
    public void apagar(){mtts.shutdown();}
    public void agregarCola(String texto, float velPitch, float velRate){
        if(isLoaded){
            mtts.setPitch(velPitch);
            mtts.setSpeechRate(velRate);
            mtts.speak(texto, TextToSpeech.QUEUE_ADD, null);
        }else{
            Log.i("error","Text to Speech no inicializado");
        }
    }
    public void iniciarCola(String texto, float velPitch, float velRate){
        if(isLoaded){
            mtts.setPitch(velPitch);
            mtts.setSpeechRate(velRate);
            mtts.speak(texto, TextToSpeech.QUEUE_FLUSH, null);
        }else{
            Log.i("error","Text to Speech no inicializado");
        }
    }
}
