package com.sumeet.stash.bookmark;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookmarkService {
    private final List<BookmarkRecord> bookmarks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<String> bookmarkTitles() {
        return List.of("Sumeet", "Bhujang");
    }

    public BookmarkRecord create(String url, String title) {
        BookmarkRecord bookmark = new BookmarkRecord(idCounter.getAndIncrement(), url, title);
        bookmarks.add(bookmark);
        return bookmark;
    }
}
