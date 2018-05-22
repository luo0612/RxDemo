package luo.com.rxdemo.rxjava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/22.
 */

public class RxJavaTest {
    public void ambArray() {
        Observable.ambArray(Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS)
                , Observable.just(5, 6)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.print(integer + ",");//5,6,
            }
        });
    }

    public void amb() {
        List<Observable<Integer>> list = new ArrayList<>();
        Observable<Integer> observable = Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS);
        Observable<Integer> observable1 = Observable.just(5, 6);
        Observable<Integer> observable2 = Observable.just(7, 8);

        list.add(observable);
        list.add(observable1);
        list.add(observable2);

        Observable.amb(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.print(integer + ",");//5,6,
            }
        });

    }
}
