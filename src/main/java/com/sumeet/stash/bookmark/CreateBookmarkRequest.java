package com.sumeet.stash.bookmark;

import jakarta.validation.constraints.NotBlank;

public record CreateBookmarkRequest(
        @NotBlank String url,
        @NotBlank String title
) {}
