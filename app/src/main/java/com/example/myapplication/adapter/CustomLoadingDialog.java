package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.example.myapplication.R;

public class CustomLoadingDialog {

    private Dialog dialog;

    public CustomLoadingDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);  // 不可取消
    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
