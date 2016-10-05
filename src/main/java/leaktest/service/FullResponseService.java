package leaktest.service;

import leaktest.commands.Part1Command;
import leaktest.commands.Part2Command;
import leaktest.commands.Part3Command;
import leaktest.domain.FullResponse;
import leaktest.domain.Part1;
import leaktest.domain.Part2;
import leaktest.domain.Part3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

@Service
public class FullResponseService {

    @Autowired
    private Part1Command part1Command;

    @Autowired
    private Part2Command part2Command;

    @Autowired
    private Part3Command part3Command;


    public FullResponse getFullResponse() {
        Observable<Part1> part1Observable = part1Command.doPart1();
        Observable<Part2> part2Observable = part2Command.doPart2();
        Observable<Part3> part3Observable = part3Command.doPart3();

        return Observable.zip(
                part1Observable,
                part2Observable,
                part3Observable,
                (part1, part2, part3) -> new FullResponse(part1, part2, part3)).toBlocking().single();
    }


}
