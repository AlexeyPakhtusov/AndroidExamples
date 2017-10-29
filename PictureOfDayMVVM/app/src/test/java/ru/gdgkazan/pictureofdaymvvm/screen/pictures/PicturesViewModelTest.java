package ru.gdgkazan.pictureofdaymvvm.screen.pictures;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.pictureofdaymvvm.BR;
import ru.gdgkazan.pictureofdaymvvm.R;
import ru.gdgkazan.pictureofdaymvvm.content.DayPicture;
import ru.gdgkazan.pictureofdaymvvm.repository.NasaRepository;
import ru.gdgkazan.pictureofdaymvvm.repository.RepositoryProvider;
import ru.gdgkazan.pictureofdaymvvm.test.MockLifecycleHandler;
import ru.gdgkazan.pictureofdaymvvm.utils.Dates;
import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

/**
 * @author Alexey Pakhtusov
 */
@RunWith(JUnit4.class)
public class PicturesViewModelTest {

    //TODO : create tests for pictures screen

    private PicturesViewModel mPicturesViewModel;

    @Before
    public void setUp() throws Exception {
        Resources resources = Mockito.mock(Resources.class);
        Mockito.when(resources.getInteger(R.integer.bulk_load)).thenReturn(4);

        Context context = Mockito.mock(Context.class);
        Mockito.when(context.getResources()).thenReturn(resources);

        LifecycleHandler lifecycleHandler = new MockLifecycleHandler();

        mPicturesViewModel = Mockito.spy(new PicturesViewModel(context, lifecycleHandler));
    }

    @Test
    public void testPicturesLoadSuccess() throws Exception {
        NasaRepository repository = Mockito.mock(NasaRepository.class);
        Observable<DayPicture> observable = Observable.just(new DayPicture());
        Mockito.when(repository.datePicture(anyString())).thenReturn(observable);
        RepositoryProvider.setNasaRepository(repository);

        mPicturesViewModel.init();
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).notifyPropertyChanged(BR.pictures);
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).setLoading(true);
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).setError(false);
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).setLoading(false);
    }

    @Test
    public void testPicturesLoadFailed() throws Exception {
        NasaRepository repository = Mockito.mock(NasaRepository.class);
        Observable<DayPicture> observable = Observable.error(new Throwable());
        Mockito.when(repository.datePicture(anyString())).thenReturn(observable);
        RepositoryProvider.setNasaRepository(repository);

        mPicturesViewModel.init();
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).handleError(any(Throwable.class));
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).setLoading(true);
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).setError(false);
        Mockito.verify(mPicturesViewModel, Mockito.times(1)).setError(true);
    }

    @Test
    public void testPicturesDisplayedCorrect() throws Exception {
        NasaRepository repository = Mockito.mock(NasaRepository.class);
        DayPicture dayPicture = new DayPicture();
        dayPicture.setTitle("Title");
        dayPicture.setExplanation("Explanation");
        dayPicture.setUrl("Url");
        Observable<DayPicture> observable = Observable.just(dayPicture);
        Mockito.when(repository.datePicture(anyString())).thenReturn(observable);
        RepositoryProvider.setNasaRepository(repository);

        mPicturesViewModel.init();
        List<DayPicture> pictures = mPicturesViewModel.getPictures();
        for (int i = 0; i < 4; ++i) {
            DayPicture day = pictures.get(i);
            assertEquals("Title", day.getTitle());
            assertEquals("Explanation", day.getExplanation());
            assertEquals("Url", day.getUrl());
        }
    }
}
