package com.example.geoquiz.pojo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.geoquiz.controller.CheatActivity;
import com.example.geoquiz.controller.MainActivity;

public class CheatActivityContract extends ActivityResultContract<Bundle, Bundle>{

    @NonNull
    @Override
    public Intent createIntent(@NonNull Context context, Bundle bundle) {
        Intent nuevoActivity = new Intent(context, CheatActivity.class);
        nuevoActivity.putExtras(bundle);
        return nuevoActivity;
    }

    @Override
    public Bundle parseResult(int i, @Nullable Intent intent) {
        if(i == Activity.RESULT_OK && intent != null){
            return intent.getExtras();
        }else{
            return null;
        }
    }
}