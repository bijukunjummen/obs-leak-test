package leaktest.commands;

import leaktest.domain.Part3;
import leaktest.util.DelayUtil;
import leaktest.util.GenericHystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class Part3Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part3Command.class);

    public Observable<Part3> doPart3() {
        return GenericHystrixCommand.toObservable("part3", "part3", () -> {
            LOGGER.error("Started Calling Part3Command");
            DelayUtil.delay(200);
            LOGGER.error("Completed Calling Part3Command");
            return new Part3("payload3");
        }, null);
    }

    public Part3 doPart3Direct() {
        return GenericHystrixCommand.execute("part3", "part3", () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return new Part3("payload3");
        }, null);
    }

}
