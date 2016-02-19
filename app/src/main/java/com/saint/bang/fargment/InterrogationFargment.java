package com.saint.bang.fargment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.saint.bang.R;

/**
 * Created by zzh on 16-1-9.
 */
public class InterrogationFargment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.interrogation_layout,container,false);
        Button btn= (Button) view.findViewById(R.id.btn_dialog);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        NiftyDialogBuilder dialogBuilder=NiftyDialogBuilder.getInstance(getActivity());
        dialogBuilder.withTitle("this is my first dialog ")
                .withMessage("Demo")
                .show();
    }
}
