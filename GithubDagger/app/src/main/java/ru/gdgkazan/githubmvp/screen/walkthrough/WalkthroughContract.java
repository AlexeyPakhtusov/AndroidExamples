package ru.gdgkazan.githubmvp.screen.walkthrough;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

import ru.gdgkazan.githubmvp.content.Benefit;

/**
 * @author Alexey Pakhtusov
 */
public interface WalkthroughContract {

    interface View {

        void showBenefits(@NonNull List<Benefit> benefits);

        void showActionButtonText(@StringRes int buttonTextId);

        void scrollToNextBenefit();

        void startAuth();

    }

    interface Presenter {

        void init(@NonNull View view);

        void onActionButtonClick();

        void onPageChanged(int selectedPage, boolean fromUser);

    }

}
