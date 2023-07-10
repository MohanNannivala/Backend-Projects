package com.ooothla.blogapi.commons;

import java.util.Locale;

public class SlugGenerator {
 public static String generateSlug(String input) {

            // Convert the input string to lowercase
            String slug = input.toLowerCase();

            // Remove special characters and replace them with a hyphen
            slug = slug.replaceAll("[^a-z0-9\\s-]", "")
                    .replaceAll("\\s+", "-");

            // Remove any leading or trailing hyphens
            slug = slug.replaceAll("^-+|-+$", "");

            // Remove any remaining non-alphanumeric characters
            slug = slug.replaceAll("[^a-z0-9\\-]", "");

            return slug;
        }
}
