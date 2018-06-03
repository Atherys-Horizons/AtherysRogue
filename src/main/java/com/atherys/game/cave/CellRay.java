package com.atherys.game.cave;

import com.atherys.game.math.Ray;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CellRay extends Ray {

    private Cave cave;

    public CellRay(Cave cave, int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
        this.cave = cave;
    }

    public static CellRay of(Cave cave, Cell start, Cell stop) {
        return new CellRay(cave, start.getPosition().getX(), start.getPosition().getY(), stop.getPosition().getX(), stop.getPosition().getY());
    }

    public void forEach(Consumer<Cell> cellConsumer) {
        forEach((x,y) -> cellConsumer.accept(cave.getCell(x, y)));
    }

    public List<Cell> filter(Predicate<Cell> cellPredicate) {
        List<Cell> result = new ArrayList<>();
        forEach(cell -> { if ( cellPredicate.test(cell) ) result.add(cell); });
        return result;
    }

    public Optional<Cell> first(Predicate<Cell> predicate) {
        List<Cell> filter = filter(predicate);
        return Optional.ofNullable(filter.isEmpty() ? null : filter.get(0));
    }
}
