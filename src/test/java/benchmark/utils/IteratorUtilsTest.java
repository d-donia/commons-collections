package benchmark.utils;

import org.apache.commons.collections4.IteratorUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Thread)
@Fork(3)
public class IteratorUtilsTest {

    @Param({"10", "100", "1000"})
    private int size;

    private List<Integer> list;

    @Setup
    public void setup() {
        list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public List<Integer> testToList() {
        Iterator<Integer> iterator = list.iterator();
        return IteratorUtils.toList(iterator);
    }

    @Benchmark
    public void testForEach() {
        Iterator<Integer> iterator = list.iterator();
        IteratorUtils.forEach(iterator, integer -> {});
    }

    @Benchmark
    public List<Integer> testToArray() {
        Iterator<Integer> iterator = list.iterator();
        return IteratorUtils.toList(iterator);
    }

}
