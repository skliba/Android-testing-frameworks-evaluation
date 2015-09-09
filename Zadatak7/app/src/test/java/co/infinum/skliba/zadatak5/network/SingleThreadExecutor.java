package co.infinum.skliba.BoatIt.network;

import java.util.concurrent.Executor;

/**
 * Created by noxqs on 29.07.15..
 */
public class SingleThreadExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
