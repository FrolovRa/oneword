package app;

import entities.Post;
import entities.User;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Feed {
    static public SortedSet<Post> generate(User sessionUser) {
        SortedSet<Post> feed = new TreeSet<>();

        for (User foll:sessionUser.getFollowing()) {
            feed.addAll(foll.getPosts());
        }
        return feed;
    }
}
