package ru.gdgkazan.githubmvp.screen.repositories;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.gdgkazan.githubmvp.R;
import ru.gdgkazan.githubmvp.content.Repository;
import ru.gdgkazan.githubmvp.repository.GithubRepository;

/**
 * @author Alexey Pakhtusov
 */
public class RepositoriesPresenter implements RepositoriesContract.Presenter {

    private final GithubRepository mRepository;

    private RepositoriesContract.View mView;

    @Inject
    public RepositoriesPresenter(@NonNull GithubRepository repository) {
        mRepository = repository;
    }

    @Override
    public void init(@NonNull RepositoriesContract.View view) {
        mView = view;
        mRepository.repositories()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .compose(mView.getLifecycleHandler().load(R.id.repositories_request))
                .subscribe(mView::showRepositories, throwable -> mView.showError());
    }

    @Override
    public void onItemClick(@NonNull Repository repository) {
        mView.showCommits(repository);
    }
}
