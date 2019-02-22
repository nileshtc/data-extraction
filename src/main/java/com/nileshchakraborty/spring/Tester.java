package com.nileshchakraborty.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tester {
    //Custom comparator Comparator
    private static final class MyComparitor {
        public int compare(final Integer a, final Integer b) {
            return a.compareTo(b);
        }
    }

    public static void main(String[] args) {
        System.out.println("\nLambda!");
        Runnable runner = () -> System.out.println("hello World");
        runner.run();

        System.out.println("\nFunctional Interface");
        ArithmaticSquare sq = Math::sqrt;

        System.out.println(sq.root(400));
        System.out.println("\nDefault method");
        System.out.println(sq.square(100));
        System.out.println("\nStatic method");
        System.out.println(ArithmaticSquare.cube(100));


        List<User> ls = new ArrayList<>();
        ls.add(new User("asn", 32));
        ls.add(new User("zaxg", 45));
        ls.add(new User("ga", 65));
        ls.add(new User("hs", 5));

        System.out.println("\nComparing int using lambda");
        ls.sort((u1, u2) -> u1.getAge() - u2.getAge());
        ls.stream().forEach(a -> System.out.println(a));

        System.out.println("\nComparing int using Comparator.comparingInt() and method reference");
        ls.sort(Comparator.comparingInt(User::getAge));
        ls.stream().forEach(a -> System.out.println(a));

        System.out.println("\nComparing string using lambda");
        ls.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
        ls.stream().forEach(a -> System.out.println(a));

        System.out.println("\nComparing string using Comparator.comparing() and method reference");
        ls.sort(Comparator.comparing(User::getName));
        System.out.println(ls);

        //Function return second type
        System.out.println("\njava.util.function api");
        System.out.println("\n------------------------");
        System.out.println("\nFunction interface takes input of the type in Generic type T and returns in Generic Type R");
        Function<String, Character> nameToC = s -> s.charAt(0);
        System.out.println(nameToC.apply("God"));

        //Predicate return boolean
        System.out.println("\nPredicate interface takes input in Generic type T and returns as boolean");
        Predicate<User> check = u -> u.getAge() > 35;
        ls.parallelStream().forEach(u -> System.out.println(u.getName() + " is > 35 : " + check.test(u)));

        //Consumer return void
        System.out.println("\nConsumer interface takes input as Generic type T and returns void");
        Consumer<List<User>> consumer = us -> us.stream().forEach(Tester::print);
        consumer.accept(ls);

        //Consumer using method reference
        System.out.println("\nConsumer interface using method reference (in method reference the method has to be in static)");
        Consumer<List<User>> consumer2 = Tester::streamthru;
        consumer2.accept(ls);

        //Supplier return Obj
        System.out.println("\nSupplier interface takes no input returns Generic Type R");
        Supplier<User> supplier = () -> ls.get(0);
        System.out.println(supplier.get());

        //Method reference
        System.out.println("\nMethod reference");
        ls.forEach(Tester::print);

        System.out.println("\nReference to an instance method of a particular object");
        final List<Integer> list = Arrays.asList(1, 9, 10, 4, 3, 8, 2, 5, 7, 6);
        final MyComparitor myComparitor = new MyComparitor();
        System.out.println("\nUsing Lambda");
        Collections.sort(list, (a, b) -> myComparitor.compare(a, b));
        list.forEach(System.out::println);

        System.out.println("\nUsing method reference");
        Collections.sort(list, myComparitor::compare);
        list.forEach(System.out::println);

        System.out.println("\nReferencing instance method of a arbitary object of a particular type");
        ls.forEach(User::printName);

        System.out.println("\nReference to constructor");
        System.out.println("\nSingle object no param");
        Supplier<Tester> testerSupplier = Tester::new;
        Tester tester = testerSupplier.get();
        System.out.println(tester.testString());

        System.out.println("\nSingle object two param");
        BiFunction<String, Integer, User> biFunction = User::new;
        System.out.println(biFunction.apply("ngas", 22));

        System.out.println("\nOptionals");
        User u = ls.get(0);
        Optional<User> optional = Optional.ofNullable(u);
        System.out.println(optional.isPresent());
        if (optional.isPresent()) {
            System.out.println(optional.get().getName());
        }

        optional.ifPresent(System.out::println);

        System.out.println("\nStreams");
        ls.stream().filter(us -> us.getAge() > 35).map(User::getName).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("\nWays to create a stream");
        Stream.of(arrayOfUser);
        usrList.stream();
        Stream.of(arrayOfUser[0], arrayOfUser[1]);

        Stream.Builder<User> empStreamBuilder = Stream.builder();

        empStreamBuilder.accept(arrayOfUser[0]);
        empStreamBuilder.accept(arrayOfUser[1]);
        empStreamBuilder.accept(arrayOfUser[2]);

        Stream<User> empStream = empStreamBuilder.build();

        //Find first
        System.out.println("\nFind First");
        User us = ls.stream().findFirst().get();
        System.out.println(us);
        System.out.println("\nTo Array");
        User[] userArr = ls.stream().toArray(User[]::new);
        Stream.of(userArr).forEach(System.out::println);


        System.out.println("\nFlatMap");
        List<List<String>> namesNested = Arrays.asList(
                Arrays.asList("Jeff", "Bezos"),
                Arrays.asList("Bill", "Gates"),
                Arrays.asList("Mark", "Zucker   berg"));

        List<String> namesFlatStream = namesNested.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        namesFlatStream.stream().forEach(System.out::println);
        System.out.println("\nPeek");
        ls.stream().peek(usr -> usr.setAge(usr.getAge() + 10)).peek(System.out::println).collect(Collectors.toList());

        System.out.println("\nSkip & Limit");
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        List<Integer> collect = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        System.out.println("\nSorted");
        namesFlatStream.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("\nMin & Max");
        User u1 = ls.stream()
                .min(Comparator.comparingInt(User::getAge))
                .orElseThrow(NoSuchElementException::new);
        User u2 = ls.stream()
                .max(Comparator.comparing(User::getAge))
                .orElseThrow(NoSuchElementException::new);

        System.out.println(u1);
        System.out.println(u2);

        System.out.println("\nDistinct");
        List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
        List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctIntList);

        System.out.println("\nMapOfInt returns IntStream " +
                "where as Map returns Stream<Integer> and " +
                "Stream.of(1,2,3) returns Stream<Integer> " +
                "where as IntStream.of(1,2,3) returns IntStream");
        int max = ls.stream()
                .mapToInt(User::getAge)
                .max()
                .orElseThrow(NoSuchElementException::new);
        System.out.println(max);

        System.out.println("\nAll, Any, None Match");
        List<Integer> intListL1 = Arrays.asList(2, 4, 5, 6, 8);

        boolean allEven = intListL1.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = intListL1.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = intListL1.stream().noneMatch(i -> i % 3 == 0);
        System.out.println(allEven);
        System.out.println(oneEven);
        System.out.println(noneMultipleOfThree);

        System.out.println("\nIntStream, LondStream, DoubleStream");
        IntStream.range(10, 20).forEach(System.out::println);

        IntStream.of(1, 2, 3).forEach(System.out::println);


    }

    private static User[] arrayOfUser = {
            new User("Jeff Bezos", 10),
            new User("Bill Gates", 20),
            new User("Mark Zuckerberg", 30)
    };
    private static List<User> usrList = Arrays.asList(arrayOfUser);


    public String testString() {
        return "This is from tester object";
    }

    public static void streamthru(List<User> obj) {
        obj.stream().forEach(Tester::print);
    }

    public static void print(User arg) {
        System.out.println(arg);
    }

}

class User {
    private int age;
    private String name;

    public User(String name, int age) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "User [age=" + age + ", name=" + name + "]";
    }

}

//Function Interface
@FunctionalInterface
interface ArithmaticSquare {
    double root(int l);

    // static method
    static int cube(int a) {
        return a * a * a;
    }

    // default method
    default double square(double d) {
        return d * d;
    }
}
