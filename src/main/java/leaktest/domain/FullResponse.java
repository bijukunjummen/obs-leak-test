package leaktest.domain;

public class FullResponse {

    private final Part1 part1;

    private final Part2 part2;

    private final Part3 part3;

    public FullResponse(Part1 part1, Part2 part2, Part3 part3) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
    }

    public Part1 getPart1() {
        return part1;
    }

    public Part2 getPart2() {
        return part2;
    }

    public Part3 getPart3() {
        return part3;
    }
}
