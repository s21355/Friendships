package pl.edu.pjwstk;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class FriendshipsAjTest {

    private Friendships friendships;

    @BeforeEach
    public void setUp() {
        friendships = new Friendships();
    }

    @Test
    public void testIfAdded() {
        friendships.makeFriends("Dawid", "Michał");
        assertThat(friendships.getFriendsList("Dawid")).contains("Michał");
    }

    @Test
    public void testIfFriends() {
        friendships.makeFriends("Dawid", "Michał");
        assertThat(friendships.areFriends("Dawid", "Michał")).isTrue();
    }

    @Test
    public void testIfNotFriend() {
        friendships.makeFriends("Rafał", "Adam");
        friendships.makeFriends("Adam", "Michał");
        assertThat(friendships.areFriends("Michał","Rafał")).isFalse();
    }

    @Test
    public void testIfFriendNull() {
        friendships.makeFriends("Rafał", "Daniel");
        assertThat(friendships.getFriendsList("Rafał")).isNotNull();
    }

    @Test
    public void testIfGroupOfFriends() {
        friendships.makeFriends("Rafał", "Adam");
        friendships.makeFriends("Adam", "Michał");
        friendships.makeFriends("Adam", "Grażyna");
        assertThat(friendships.getFriendsList("Adam")).contains("Michał").contains("Rafał").contains("Grażyna").hasSize(3);
    }

    @Test
    public void testThrowException(){
        assertThatThrownBy(() -> friendships.getFriendsList(null)).isInstanceOf(NullPointerException.class);
    }

    @AfterEach
    void tearDown() {
        friendships = null;
    }
}