package com.charanajayworks.iplplayers.Models;

public class GridLayoutModel {
    String gridName;
    int imageLink;

    public GridLayoutModel(String gridName, int imageLink) {
        this.gridName = gridName;
        this.imageLink = imageLink;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public int getImageLink() {
        return imageLink;
    }

    public void setImageLink(int imageLink) {
        this.imageLink = imageLink;
    }
}
