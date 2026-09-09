package com.sumeet.stash.bookmark;

import com.sumeet.stash.config.StashProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public List<Bookmark> getBookmarkService(@RequestParam(defaultValue = "0") @Min(0) int page,
                                             @RequestParam(defaultValue = "10") @Min(1) int size) {
        return bookmarkService.listAll(page, size);
    }

    @GetMapping("/bookmarks/{id}")
    public ResponseEntity<Bookmark> getBookmark(@PathVariable Long id) {
        Optional<Bookmark> optionalBookmark = bookmarkService.findByID(id);
        return optionalBookmark
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/bookmarks/{id}")
    public ResponseEntity<Bookmark> updateBookmark(@PathVariable Long id, @RequestBody CreateBookmarkRequest request) {
        return bookmarkService.update(id, request.url(), request.title())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/info")
    public StashProperties getInfo() {
        return stashProperties;
    }

    @PostMapping("/bookmarks")
    public ResponseEntity<Bookmark> createBookmark(@Valid @RequestBody CreateBookmarkRequest request) {
        Bookmark bookmark = bookmarkService.create(request.url(), request.title());
        return ResponseEntity
                .created(URI.create("api/v1/bookmarks/" + bookmark.getId()))
                .body(bookmark);
    }

    @DeleteMapping("/bookmarks/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        boolean deleted = bookmarkService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
