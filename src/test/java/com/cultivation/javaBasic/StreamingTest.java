package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(Collectors.toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }


    @Test
    void should_not_be_same_object_after_filter() {
        boolean isClosed = false;

        IntStream originalStream = IntStream.range(0,10);
        originalStream.filter(number -> number > 5).findFirst();

        originalStream.onClose(()->{
            close(isClosed);
        });

        assertFalse(isClosed);
    }

    private void close (boolean isClosed) {
        isClosed = true;
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.generate(() -> "Echo");
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @Test
    void should_run_0_time_after_skip() {
        int[] count = {0};
        Stream<Integer> infiniteInteger = Stream.generate( () -> {
            count[0] ++;
            return 0;});

        Stream skippedStream = infiniteInteger.skip(10000);
        assertEquals(0, count[0]);

        skippedStream.findFirst();
        assertEquals(10001, count[0]);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> ++i);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter(word -> word.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map(String::toUpperCase);
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                new AnimeCharacter[] {
                    new AnimeCharacter("Naruto"),
                    new AnimeCharacter("Kisuke"),
                    new AnimeCharacter("Tomoya")
                },
                actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {
        Stream<Stream<Character>> characters = Stream.of("Xy", "TR", "PO").map(StreamingTest::letters);

        Stream<Character> laterCharacters = characters.flatMap(stringStream -> stringStream);

        Character[] expected = {'X', 'y', 'T', 'R', 'P', 'O'};

        assertArrayEquals(expected, laterCharacters.toArray());
    }

    @Test
    void should_flatten_stream_of_streams_with_method_reference() {
        Stream<Stream<Character>> characters = Stream.of("Yu", "Xa").map(StreamingTest::letters);

        Stream<Character> laterCharacters = characters.flatMap(string -> string);

        Character[] expected = {'Y', 'u', 'X', 'a'};
        Character[] actucal = laterCharacters.toArray(Character[]::new);

        assertArrayEquals(expected, actucal);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_run_0_times_after_limit() {
        int[] count = {0};
        Stream<Integer> infiniteStream = Stream.iterate(0,(i) -> {
            count[0] ++;
            return i+1;
        });

        Stream<Integer> finiteStream = infiniteStream.limit(10);

        int iterateRunZeroTimeAfterLimit = 0;
        assertEquals(iterateRunZeroTimeAfterLimit, count[0]);

        finiteStream.toArray(Integer[]::new);

        int iterateRunNineTimeAfterLimit = 9;
        assertEquals(iterateRunNineTimeAfterLimit, count[0]);

    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream,worldStream);
        // --end-->
        {
            assertArrayEquals(
                letters("HelloWorld").toArray(Character[]::new),
                concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_not_sorted() {
        Stream<Character> notSortedStreamA = Stream.of('C', 'Y', 'A');
        Stream<Character> notSortedStreamB = Stream.of('X', 'B', 'D');

        Stream<Character> allNotSortedStream = Stream.concat(notSortedStreamA, notSortedStreamB);
        assertArrayEquals(new Character[]{'C', 'Y', 'A', 'X', 'B', 'D'}, allNotSortedStream.toArray(Character[]::new));
    }

    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
            .iterate(0, i -> i + 1)
            .limit(20)
            .filter(v -> v % 2 == 0)
            .peek(v -> holder.setValue(holder.getValue() + v));

        hookStream.forEach(i -> {});

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_be_immutable_for_stream() {
        List<Integer> oneList = new ArrayList<>();
        List<Integer> twoList = new ArrayList<>();

        twoList.add(1);

        Stream<List> original = Stream.of(oneList, twoList);

        original
                .peek(list -> {
                    list.add(999);
                    System.out.print(1+"\n");
                })
                .peek(list -> {
                    System.out.print(2+"\n");
                })
                .forEach(i -> {});

        assertIterableEquals(Collections.singletonList(999), oneList);
        assertIterableEquals(Arrays.asList(1, 999), twoList);
    }

    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = NoSuchElementException.class;
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> {
            empty.orElseThrow(IllegalStateException::new);
        };
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to integerIterator if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = (optionalS) -> {
            optionalS.ifPresent(optionalS1 -> result.add(optionalS1.toUpperCase()));
        };
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `integerIterator` list if optional is present. Then return the boolean
        // TODO: mapping integerIterator of `integerIterator.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = (stringOptional) ->
            stringOptional.map(string -> result.add(string.toUpperCase()));
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 4)
            .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 1)
            .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = yieldOptionalStream -> {
            Optional<YieldOptional> firstElement = yieldOptionalStream.findFirst();
            return firstElement.flatMap(YieldOptional::get);
        };

        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        int[] count = {0, 0, 0};
        Stream<String> stringStream = Stream.of("a", "b", "c", "x", "y", "z");

        List<String> strings = stringStream.parallel().collect(
                () -> {
                    count[0]++;
                    return new ArrayList<>();
                },
                (stringList, itemString) -> {
                    count[1]++;
                    stringList.add(itemString);
                },
                (stringList, otherStringList) -> {
                    count[2]++;
                    stringList.addAll(otherStringList);
                });

        assertIterableEquals(Arrays.asList("a", "b", "c", "x", "y", "z"), strings);

        int expectedRuntimeForNew = strings.size();
        int expectedRuntimeForAdd = strings.size();
        int expectedRuntimeForAddAll = expectedRuntimeForNew - 1;

        assertEquals(expectedRuntimeForNew, count[0]);
        assertEquals(expectedRuntimeForAdd, count[1]);
        assertEquals(expectedRuntimeForAddAll, count[2]);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033),
            new KeyValuePair<>("Bob", 2033),
            new KeyValuePair<>("Bob", 2055)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map = stream.collect(
                HashMap::new,
                (hashMap, value) -> {
                    hashMap.put(value.getKey(), value.getValue());
                },
                HashMap::putAll
                );
        // --end-->


        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2055, map.get("Bob").intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = stream.collect(HashMap::new, (hashMap, valuePair) -> {
            if (!hashMap.containsKey(valuePair.getKey())) {
                List<Integer> years = new ArrayList<>();
                years.add(valuePair.getValue());
                hashMap.put(valuePair.getKey(), years);
            } else {
                List<Integer> years = hashMap.get(valuePair.getKey());
                years.add(valuePair.getValue());
                hashMap.put(valuePair.getKey(), years);
            }

        }, (original, later) -> {
            later.forEach( (key, value) -> {
                if (original.containsKey(key)) {
                    List<Integer> list = original.get(key);
                    list.addAll(value);
                    original.put(key, list);
                } else {
                  original.put(key,value);
                }
            });
        });
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        Map<String, List<Integer>> map = stream.collect(Collectors.
                groupingBy(
                    KeyValuePair::getKey,
                        Collectors.mapping(
                        KeyValuePair::getValue,
                           Collectors.toList()
                )
        ));
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
            .iterate(1, i -> i + 1)
            .limit(100)
            .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = numbers.stream().reduce((sum, number) -> sum+number);
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = words.stream().mapToInt(String::length).reduce(0, (sum,s) -> sum+s);
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */