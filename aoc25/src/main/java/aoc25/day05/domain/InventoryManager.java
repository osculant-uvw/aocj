package aoc25.day05.domain;

import aoc25.common.OrderedPair;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class InventoryManager {

    private final Set<Long> ids;
    private final TreeSet<OrderedPair> idRanges;

    public InventoryManager(Set<Long> ids, Set<OrderedPair> idRanges) {
        this.ids = ids;
        this.idRanges = mergeRanges(idRanges);
    }

    public Set<Long> idsInRanges() {
        return ids.stream()
                .filter(id -> idRanges.stream().anyMatch(range -> range.contains(id)))
                .collect(Collectors.toCollection(HashSet::new));
    }

    public Set<OrderedPair> idRanges() {
        return idRanges;
    }

    TreeSet<OrderedPair> mergeRanges(Set<OrderedPair> ranges) {
        TreeSet<OrderedPair> sorted = new TreeSet<>(ranges);
        TreeSet<OrderedPair> merged = new TreeSet<>();

        if (sorted.isEmpty()) {
            return merged;
        }

        Iterator<OrderedPair> it = sorted.iterator();
        OrderedPair current = it.next();

        while (it.hasNext()) {
            OrderedPair next = it.next();

            if (current.overlaps(next)) {
                current = current.merge(next);
            } else {
                merged.add(current);
                current = next;
            }
        }

        merged.add(current);
        return merged;
    }

}
