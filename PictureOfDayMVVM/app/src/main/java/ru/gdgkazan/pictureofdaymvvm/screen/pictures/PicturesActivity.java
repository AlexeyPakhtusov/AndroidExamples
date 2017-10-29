package ru.gdgkazan.pictureofdaymvvm.screen.pictures;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;
import ru.gdgkazan.pictureofdaymvvm.R;
import ru.gdgkazan.pictureofdaymvvm.databinding.ActivityPicturesBinding;

/**
 * @author Alexey Pakhtusov
 */
public class PicturesActivity extends AppCompatActivity {

    /**
     * TODO : task
     *
     * 1) Implement activity with list of images for each day
     * 2) New images must be loaded when scrolling to end
     * 3) Implement this screen with MVVM and Data Binding
     * 4) Test your ViewModel
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        PicturesViewModel picturesViewModel = new PicturesViewModel(this, lifecycleHandler);

        ActivityPicturesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_pictures);
        binding.setModel(picturesViewModel);
        binding.executePendingBindings();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        picturesViewModel.init();
    }
}
