package app;

import entities.Post;
import entities.User;

import java.util.HashSet;
import java.util.Set;

public class Feed {
    static public Set<Post> generate(User sessionUser) {

        Set<Post> feed = new HashSet<>(sessionUser.getPosts());

        for (User foll:sessionUser.getFollowing()) {
            feed.addAll(foll.getPosts());
        }
        return feed;
    }
}
