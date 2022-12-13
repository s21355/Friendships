package pl.edu.pjwstk;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class FriendshipsHeTest {
    private Friendships friendships;

    @BeforeEach
    void setUp() {
        friendships = new Friendships();
    }

    @Test
    public void testIfAdded() {
        friendships.makeFriends("Dawid", "Michał");
        assertThat(friendships.getFriendsList("Dawid"),Matchers.allOf(Matchers.contains("Michał")));
    }

    @Test
    public void testIfFriends() {
        friendships.makeFriends("Dawid", "Michał");
        assertThat(friendships.areFriends("Dawid", "Michał"),Matchers.allOf(Matchers.not(false), Matchers.notNullValue()));
    }

    @Test
    public void testIfNotFriend() {
        friendships.makeFriends("Rafał", "Adam");
        friendships.makeFriends("Adam", "Michał");
        assertThat(friendships.areFriends("Rafał", "Michał"),Matchers.not(true));
    }

    @Test
    public void testIfFriendNull() {
        friendships.makeFriends("Rafał", "Daniel");
        assertThat(friendships.getFriendsList("Rafał"),Matchers.notNullValue());
    }

    @Test
    public void testIfGroupOfFriends() {
        friendships.makeFriends("Rafał", "Adam");
        friendships.makeFriends("Adam", "Michał");
        friendships.makeFriends("Adam", "Grażyna");
        assertThat(friendships.getFriendsList("Adam"), Matchers.allOf(Matchers.containsInAnyOrder("Rafał", "Michał", "Grażyna")));
    }

    @Test
    public void testSizeGroupOfFriends() {
        friendships.makeFriends("Rafał", "Adam");
        friendships.makeFriends("Adam", "Michał");
        friendships.makeFriends("Adam", "Grażyna");
        assertThat(friendships.getFriendsList("Adam"), Matchers.hasSize(3));
    }

    @AfterEach
    void tearDown() {
        friendships = null;
    }
}
