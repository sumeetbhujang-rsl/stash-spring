package com.sumeet.stash.bookmark;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    public List<String> bookmarkTitles() {
        return List.of("Sumeet", "Bhujang");
    }
}
