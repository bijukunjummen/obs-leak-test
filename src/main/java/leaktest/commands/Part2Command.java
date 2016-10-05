package leaktest.commands;

import leaktest.domain.Part2;
import leaktest.util.DelayUtil;
import leaktest.util.GenericHystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class Part2Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2Command.class);

    public Observable<Part2> doPart2() {
        return GenericHystrixCommand.toObservable("part2", "part2", () -> {
            LOGGER.error("Started Calling Part2Command");
            DelayUtil.delay(300);
            LOGGER.error("Completed Calling Part2Command");
            return new Part2("payload2");
        }, null);
    }

    public Part2 doPart2Direct() {
        return GenericHystrixCommand.execute("part2", "part2", () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
            return new Part2("payload2");
        }, null);
    }

}
