package leaktest.commands;

import leaktest.domain.Part1;
import leaktest.util.DelayUtil;
import leaktest.util.GenericHystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class Part1Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1Command.class);

    public Observable<Part1> doPart1() {
        return GenericHystrixCommand.toObservable("part1", "part1", () -> {
            LOGGER.error("Started Calling Part1Command");
            DelayUtil.delay(600);
            LOGGER.error("Completed Calling Part1Command");
            return new Part1("payload1");
        }, null);
    }

    public Part1 doPart1Direct() {
        return GenericHystrixCommand.execute("part1", "part1", () -> new Part1("payload1"), null);
    }

}
