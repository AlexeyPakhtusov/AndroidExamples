package ru.gdgkazan.pictureofdaymvvm.screen.day;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;

import ru.gdgkazan.pictureofdaymvvm.R;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import ru.gdgkazan.pictureofdaymvvm.databinding.ActivityDayPictureBinding;

/**
 * @author Alexey Pakhtusov
 */
public class DayPictureActivity extends AppCompatActivity {

    private static final String IMAGE = "image";
    private static final String DAY_PICTURE = "day_picture";

    public static void navigate(@NonNull AppCompatActivity appCompatActivity,
                                @NonNull View view, @NonNull DayPicture dayPicture) {
        Intent intent = new Intent(appCompatActivity, DayPictureActivity.class);
        intent.putExtra(DAY_PICTURE, dayPicture);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(appCompatActivity, view, IMAGE);
        ActivityCompat.startActivity(appCompatActivity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareTransition();
        setContentView(R.layout.activity_day_picture);

        DayPicture dayPicture = getIntent().getParcelableExtra(DAY_PICTURE);
        DayViewModel dayViewModel = new DayViewModel(dayPicture);

        ActivityDayPictureBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_day_picture);
        binding.setModel(dayViewModel);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar), IMAGE);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    private void prepareTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }
}
