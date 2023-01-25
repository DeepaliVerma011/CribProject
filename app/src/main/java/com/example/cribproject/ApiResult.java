package com.example.cribproject;

public class ApiResult {

    public class Thumbnail {
        private String source;
        private Integer width;
        private Integer height;
        public String getSource() {
            return source;
        }
        public void setSource(String source) {
            this.source = source;
        }
        public Integer getWidth() {
            return width;
        }
        public void setWidth(Integer width) {
            this.width = width;
        }
        public Integer getHeight() {
            return height;
        }
        public void setHeight(Integer height) {
            this.height = height;
        }
    }
}
