package nextstep.ladder.domain.laddar;

import nextstep.ladder.domain.strategy.DirectionStrategy;

public class LadderColumn {
    private final LadderCell ladderCell;

    private LadderColumn(final LadderCell ladderCell) {
        this.ladderCell = ladderCell;
    }

    public static LadderColumn createFirst(final DirectionStrategy generator) {
        return new LadderColumn(LadderCell.createFirst(generator));
    }

    public LadderColumn createNext(final DirectionStrategy generator) {
        return new LadderColumn(ladderCell.createNext(generator));
    }

    public LadderColumn createLast() {
        return new LadderColumn(ladderCell.createLast());
    }

    public boolean isRight() {
        return ladderCell.isRight();
    }
}