package ru.gdgkazan.githubmvp.screen.walkthrough;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Benefit;
import ru.gdgkazan.githubmvp.repository.KeyValueStorage;

/**
 * @author Artur Vasilov
 */
public class WalkthroughPresenter implements WalkthroughContract.Presenter {

    private static final int PAGES_COUNT = 3;

    private final KeyValueStorage mKeyValueStorage;

    private WalkthroughContract.View mView;

    private int mCurrentItem = 0;

    @Inject
    public WalkthroughPresenter(@NonNull KeyValueStorage keyValueStorage) {
        mKeyValueStorage = keyValueStorage;
    }

    @Override
    public void init(@NonNull WalkthroughContract.View view) {
        mView = view;
        if (mKeyValueStorage.isWalkthroughPassed()) {
            mView.startAuth();
        } else {
            mView.showBenefits(getBenefits());
            mView.showActionButtonText(R.string.next_uppercase);
        }
    }

    @Override
    public void onActionButtonClick() {
        if (isLastBenefit()) {
            mKeyValueStorage.saveWalkthroughPassed();
            mView.startAuth();
        } else {
            mCurrentItem++;
            showBenefitText();
            mView.scrollToNextBenefit();
        }
    }

    @Override
    public void onPageChanged(int selectedPage, boolean fromUser) {
        if (fromUser) {
            mCurrentItem = selectedPage;
            showBenefitText();
        }
    }

    @NonNull
    private List<Benefit> getBenefits() {
        return new ArrayList<Benefit>() {
            {
                add(Benefit.WORK_TOGETHER);
                add(Benefit.CODE_HISTORY);
                add(Benefit.PUBLISH_SOURCE);
            }
        };
    }

    private boolean isLastBenefit() {
        return mCurrentItem == PAGES_COUNT - 1;
    }

    private void showBenefitText() {
        @StringRes int buttonTextId = isLastBenefit() ? R.string.finish_uppercase : R.string.next_uppercase;
        mView.showActionButtonText(buttonTextId);
    }
}
