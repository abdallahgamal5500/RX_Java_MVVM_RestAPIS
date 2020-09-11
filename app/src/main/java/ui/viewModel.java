package ui;

import android.view.Display;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_rxjava.MainActivity;

import java.util.List;

import data.PostClient;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pojo.Model;

public class viewModel extends ViewModel {

    public MutableLiveData<List<Model>> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public void getPosts() {
        Observable<List<Model>> observable = getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<List<Model>> observer = getObserver();
        observable.subscribe(observer);
    }

    private Observable<List<Model>> getObservable() {
        return PostClient.getINSTANCE().getPosts();
    }

    private Observer<List<Model>> getObserver() {
        return new Observer<List<Model>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(List<Model> models) {
                mutableLiveData.setValue(models);
            }

            @Override
            public void onError(Throwable e) {
                error.setValue("Error");
            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
