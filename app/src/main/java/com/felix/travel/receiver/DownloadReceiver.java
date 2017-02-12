package com.felix.travel.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.felix.travel.fragment.TravelAreaFragment;

/**
 * Created by felixlin on 2017/2/12.
 */
public class DownloadReceiver extends BroadcastReceiver {

    private Context mContext;

    public DownloadReceiver(Context context){
        this.mContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(TextUtils.equals(TravelAreaFragment.MY_MESSAGE, intent.getAction())){
            new AlertDialog.Builder(mContext)
                    .setMessage("資料讀取完畢!")
                    .setPositiveButton("確定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
    }
}
