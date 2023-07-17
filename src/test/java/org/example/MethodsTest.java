package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.suite.api.*;

public class MethodsTest {

    public static enum ExampleLists {
        LIST_WITH_YOUNG_PEOPLE(
                List.of(
                        new User("Alex", "Bloom", 15),
                        new User("Alice", "Broom", 25)
                ),
                20
        ),
        LIST_WITH_ADULTS(
                List.of(
                        new User("Beata", "Bloom", 33),
                        new User("Dorota", "Bloom", 20),
                        new User("Fryderyk", "Chopin", 66)
                ),
                39.666666666666664
        );

        ExampleLists(List<User> users, double precalculatedAgeAverage) {
            this.users = users;
            this.precalculatedAgeAverage = precalculatedAgeAverage;
        }
        List<User> users;
        double precalculatedAgeAverage;
    }

    private List<User> LIST_OF_OLD_USERS;
    @BeforeEach
    public void before() {
        LIST_OF_OLD_USERS = new ArrayList<User>(
                List.of(
                        new User("George", "Duda", 60),
                        new User("Peter", "Singer", 80),
                        new User("Bob", "Big", 70)
                )
        );
    }
    @AfterEach
    public void after() {
        LIST_OF_OLD_USERS = null;
    }

    @Test
    @DisplayName("MyTest")
    void sortByAgeIntoNewCollectionTest1() {
        List<User> sortedList = Methods.sortByAgeIntoNewCollection(ExampleLists.LIST_WITH_ADULTS.users);
        assertTrue(sortedList.get(0).getAge() < sortedList.get(1).getAge());
        assertEquals(sortedList.get(0).getFirstName(), "Dorota");
    }

    @ParameterizedTest
    @EnumSource(ExampleLists.class)
    public void sortByAgeIntoNewCollection_Test2(ExampleLists list) {
        List<User> sortedList = Methods.sortByAgeIntoNewCollection(list.users);
        assertEquals(sortedList.size(), list.users.size());
        assertTrue(sortedList.get(0).getAge() < sortedList.get(1).getAge());
    }

    @RepeatedTest(10)
    public void sortByAgeIntoNewCollection_Test3() {
        List<User> sortedList = Methods.sortByAgeIntoNewCollection(ExampleLists.LIST_WITH_ADULTS.users);
        assertTrue(sortedList.get(0).getAge() < sortedList.get(1).getAge());
    }

    @Test
    public void calculateAverageAge_Test1() {
        ExampleLists ex = ExampleLists.LIST_WITH_ADULTS;
        double average = Methods.calculateAverageAge(ex.users);
        assertEquals(average, ex.precalculatedAgeAverage);
    }

    @ParameterizedTest
    @EnumSource(ExampleLists.class)
    public void calculateAverageAge_Test2(ExampleLists list) {
        double average = Methods.calculateAverageAge(list.users);
        assertEquals(average, list.precalculatedAgeAverage);
    }

    @RepeatedTest(10)
    public void calculateAverageAge_Test3() {
        ExampleLists ex = ExampleLists.LIST_WITH_YOUNG_PEOPLE;
        double average = Methods.calculateAverageAge(ex.users);
        assertEquals(average, ex.precalculatedAgeAverage);
    }


    @RepeatedTest(10)
    public void sortByFirstNameInPlace_Test1() {
        List<User> list = LIST_OF_OLD_USERS;
        Methods.sortByFirstNameInPlace(list);
        assertEquals("Bob", list.get(0).getFirstName());
    }

    @ParameterizedTest
    @EnumSource(ExampleLists.class)
    public void sortByFirstNameInPlace_Test2(ExampleLists ex) {
        // Copy the list to make sure the sorting method doesn't change the original one
        List<User> list = new ArrayList<>(ex.users);
        Methods.sortByFirstNameInPlace(list);
        assertTrue(list.get(0).getFirstName().compareTo(list.get(1).getFirstName()) < 0);
    }

    @Test
    public void sortByFirstNameInPlace_Test3() {
        // Copy the list to make sure the sorting method doesn't change the original one
        List<User> list = new ArrayList<>(ExampleLists.LIST_WITH_YOUNG_PEOPLE.users);
        Methods.sortByFirstNameInPlace(list);
        assertEquals("Alex", list.get(0).getFirstName());
    }


    @Test
    public void areThereAnyUsersWithLastNameStartingWithSorA_Test1() {
        assertFalse(Methods.areThereAnyUsersWithLastNameStartingWithSorA(ExampleLists.LIST_WITH_YOUNG_PEOPLE.users));
    }

    @ParameterizedTest
    @EnumSource(ExampleLists.class)
    public void areThereAnyUsersWithLastNameStartingWithSorA_Test2(ExampleLists ex) {
        assertFalse(Methods.areThereAnyUsersWithLastNameStartingWithSorA(ex.users));
    }


    @RepeatedTest(10)
    public void areThereAnyUsersWithLastNameStartingWithSorA_Test3() {
        assertTrue(Methods.areThereAnyUsersWithLastNameStartingWithSorA(LIST_OF_OLD_USERS));
    }

    @Test
    public void areAllUsersOver18_Test1() {
        boolean areAllUsersOver18 = Methods.areAllUsersOver18(ExampleLists.LIST_WITH_YOUNG_PEOPLE.users);
        assertFalse(areAllUsersOver18);
    }

    @ParameterizedTest
    @EnumSource(ExampleLists.class)
    public void areAllUsersOver18_Test2(ExampleLists ex) {
        boolean areAllUsersOver18 = Methods.areAllUsersOver18(ex.users);
        List<User> fromYoungestToOldest = Methods.sortByAgeIntoNewCollection(ex.users);
        assertEquals(areAllUsersOver18, fromYoungestToOldest.get(0).getAge() >= 18);
    }

    @RepeatedTest(10)
    public void areAllUsersOver18_Test3() {
        boolean areAllUsersOver18 = Methods.areAllUsersOver18(ExampleLists.LIST_WITH_ADULTS.users);
        assertTrue(areAllUsersOver18);
    }
}