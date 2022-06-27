package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        for (Item item : items) {
            Boolean isLegendary = item.name.equals("Sulfuras, Hand of Ragnaros");
            item.quality = isLegendary && item.quality != 80 ? 80
                    : !isLegendary && item.quality > 50 ? 50
                            : !isLegendary && item.quality < 0 ? 0 : item.quality;
        }
        this.items = items;
    }

    private void setItemQuality(Item item) {
        Boolean isExpired = item.sellIn < 0;
        Boolean isMaxQuality = item.quality == 50;

        if (item.name.equals("Aged Brie")) {
            if (isMaxQuality)
                return;
            item.quality += 1;
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (isExpired)
                item.quality = 0;
            else {
                if (isMaxQuality)
                    return;
                int increaseValue = item.sellIn <= 5 ? 3 : item.sellIn <= 10 ? 2 : 1;
                item.quality = item.quality + increaseValue > 50 ? 50 : item.quality + increaseValue;
            }
        } else {
            Boolean isConjured = item.name.contains("Conjured");
            int expiredRatio = isExpired ? 2 : 1;
            int decreaseValue = isConjured ? 2 * expiredRatio : 1 * expiredRatio;
            item.quality = item.quality - decreaseValue < 0 ? 0 : item.quality - decreaseValue;

        }
    }

    private void setItemSellIn(Item item) {
        item.sellIn -= 1;
    }

    public void updateQuality() {
        for (Item item : this.items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros"))
                continue;

            setItemSellIn(item);
            setItemQuality(item);

        }
    }
}