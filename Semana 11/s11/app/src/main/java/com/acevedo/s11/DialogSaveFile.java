package com.acevedo.s11;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DialogSaveFile {

    public interface EndDialog{
        void resultDialog(String edtNameFile);
    }
    private EndDialog interfaz;

    public DialogSaveFile(Context context, EndDialog activity){
        interfaz = activity;
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_save);

        final EditText edtNameFile = (EditText) dialog.findViewById(R.id.edtNameFile);
        ImageButton saveFile = (ImageButton) dialog.findViewById(R.id.btnSaveFile);

        edtNameFile.setInputType(InputType.TYPE_CLASS_TEXT);

        saveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaz.resultDialog(edtNameFile.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
