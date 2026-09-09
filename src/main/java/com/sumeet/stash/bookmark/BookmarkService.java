package com.sumeet.stash.bookmark;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Bookmark create(String url, String title) {
        Bookmark bookmark = new Bookmark(url, title);
        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> listAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        Page<Bookmark> result = bookmarkRepository.findAll(p);
        return result.getContent();
    }

    public Optional<Bookmark> findByID(Long id) {
        return bookmarkRepository.findById(id);
    }

    public Optional<Bookmark> update(Long id, String url, String title) {
        Optional<Bookmark> existing = bookmarkRepository.findById(id);
        if (existing.isPresent()) {
            Bookmark bookmark = existing.get();
            bookmark.setTitle(title);
            bookmark.setUrl(url);
            return Optional.of(bookmarkRepository.save(bookmark));
        }

        return Optional.empty();
    }

    public boolean delete(Long id) {
        if (!bookmarkRepository.existsById(id)) {
            return false;
        }
        bookmarkRepository.deleteById(id);
        return true;
    }

    /*
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
     */
}
