package com.sumeet.stash.bookmark;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Bookmark> listAll(int page, int size) {
        List<Bookmark> list = new ArrayList<>(bookmarks.values());

        int startIndex = page * size;
        if (startIndex >= list.size()) {
            return new ArrayList<>();
        }

        int endIndex = Math.min(startIndex + size, list.size());
        return new ArrayList<>(list.subList(startIndex, endIndex));
    }

    public Optional<Bookmark> update(Long id, String url, String title) {
        if (!bookmarks.containsKey(id)) {
            return Optional.empty();
        }
        Bookmark updated = new Bookmark(id, url, title);
        bookmarks.put(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(Long id) {
        if (!bookmarks.containsKey(id)) {
            return false;
        }

        Bookmark b = bookmarks.remove(id);
        return b != null;
    }
}
