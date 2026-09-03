package com.sumeet.stash.bookmark;

import com.sumeet.stash.config.StashProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
