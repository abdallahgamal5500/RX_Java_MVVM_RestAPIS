package ui;

import android.view.Display;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.PostClient;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pojo.Model;

public class viewModel extends ViewModel {

    public MutableLiveData<List<Model>> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();

    public void getPosts() {
        Observable<List<Model>> observable = PostClient.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<Model>> observer = new Observer<List<Model>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Model> models) {
                mutableLiveData.setValue(models);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
