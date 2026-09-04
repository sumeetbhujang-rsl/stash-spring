package com.sumeet.stash.bookmark;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookmarkService {
    private final Map<Long, Bookmark> bookmarks = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public Bookmark create(String url, String title) {
        Bookmark created = new Bookmark(idCounter.getAndIncrement(), url, title);
        bookmarks.put(created.id(), created);
        return created;
    }

    public Optional<Bookmark> findByID(Long id) {
        return Optional.ofNullable(bookmarks.get(id));
    }

    public ArrayList<Bookmark> listAll() {
        return new ArrayList<>(bookmarks.values());
    }
    public Optional<Bookmark> update(Long id, String url, String title) {
        if (!bookmarks.containsKey(id)) {
            return Optional.empty();
        }
        Bookmark updated = new Bookmark(id, url, title);
        bookmarks.put(id, updated);
        return Optional.of(updated);
    }
}
