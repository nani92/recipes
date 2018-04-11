package eu.napcode.recipes;

import eu.napcode.recipes.rx.RxSchedulers;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class MockRxSchedulers implements RxSchedulers {

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler androidMainThread() {
        return Schedulers.trampoline();
    }
}