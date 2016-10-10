package com.example.cristianbaita.MusicMap.animators;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.cristianbaita.MusicMap.R;

/**
 * Created by Cretu Calinn on 10/9/2016.
 */

public class Animators {

    public void fadeInScaleInAnimation(View view, Context context)
    {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.startAnimation(anim);
    }
    public void fadeOutScaleOutAnimation(View view, Context context)
    {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        view.startAnimation(anim);
    }
}
