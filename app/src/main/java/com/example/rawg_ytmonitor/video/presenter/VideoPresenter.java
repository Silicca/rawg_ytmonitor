package com.example.rawg_ytmonitor.video.presenter;

import com.example.rawg_ytmonitor.data.apimodel.ApiVideoResponse;
import com.example.rawg_ytmonitor.data.apimodel.Game;
import com.example.rawg_ytmonitor.data.apimodel.Video;
import com.example.rawg_ytmonitor.data.repository.IGameDisplayRepository;
import com.example.rawg_ytmonitor.video.view.IVideoView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class VideoPresenter implements IVideoPresenter {

    private IVideoView view;
    private IGameDisplayRepository repository;
    private CompositeDisposable compositeDisposable;

    public VideoPresenter(IVideoView view, IGameDisplayRepository repository) {
        this.view = view;
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadVideos() {
        // retrieve all favorite games from db
        compositeDisposable.add(repository.getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Game>>() {
                    @Override
                    public void onNext(List<Game> games) {
                        final List<Video> videoList = new ArrayList<>();
                        for (Game game: games) {
                            // for each gameId found we request their videos from the API
                            compositeDisposable.add(repository.getVideos(game.getGameId())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeWith(new DisposableSingleObserver<ApiVideoResponse>() {
                                        @Override
                                        public void onSuccess(ApiVideoResponse apiVideoResponse) {
                                            // add 5 videos to a global list
                                            // limit to 5 because API return 50 videos
                                            for (int i = 0; i< 5 || i == apiVideoResponse.getCount(); i++ ) {
                                                videoList.add(apiVideoResponse.getVideoList().get(i));
                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            e.printStackTrace();
                                        }
                                    }));
                        }
                        // send the result to the view to display
                        view.displayVideos(videoList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                }));
    }
}
