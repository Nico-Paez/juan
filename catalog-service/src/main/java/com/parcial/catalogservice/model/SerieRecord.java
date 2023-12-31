package com.parcial.catalogservice.model;

import java.util.List;

public record SerieRecord(String id, String name, String genre, List<Season> seasons) {
    public record Season(Integer seasonNumber, List<Chapter> chapters) {

        public record Chapter(String name, Integer number, String urlStream) {
        }
    }
}
