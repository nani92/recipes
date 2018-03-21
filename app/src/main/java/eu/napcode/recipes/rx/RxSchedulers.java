package eu.napcode.recipes.rx;

import io.reactivex.Scheduler;

public interface RxSchedulers {

    Scheduler io();

    Scheduler androidMainThread();
}
