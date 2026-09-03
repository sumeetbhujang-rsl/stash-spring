package com.sumeet.stash.bookmark;

import com.sumeet.stash.config.StashProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookmarkController {
    public final BookmarkService bookmarkService;
    public final StashProperties stashProperties;

    public BookmarkController(BookmarkService bookmarkService, StashProperties stashProperties) {
        this.bookmarkService = bookmarkService;
        this.stashProperties = stashProperties;
    }

    @GetMapping("/bookmarks")
    public List<String> getBookmarkService() {
        return bookmarkService.bookmarkTitles();
    }

    @GetMapping("/info")
    public StashProperties getInfo() {
        return stashProperties;
    }

    @PostMapping("/bookmarks")
    public ResponseEntity<BookmarkRecord> createBookmark(@RequestBody CreateBookmarkRequest request) {
        BookmarkRecord created = bookmarkService.create(request.url(), request.title());
        return ResponseEntity
                .created(URI.create("api/v1/bookmarks/" + created.id()))
                .body(created);
    }
}
