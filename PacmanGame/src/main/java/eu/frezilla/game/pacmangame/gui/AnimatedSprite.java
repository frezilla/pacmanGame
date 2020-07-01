/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.frezilla.game.pacmangame.gui;

import eu.frezilla.game.pacmangame.common.AbsolutePosition2D;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
final class AnimatedSprite {

    private final long frameDuration;
    private final AbsolutePosition2D tiles[];
    
    private int currentTileIndex;
    private long nextUpdate;

    private AnimatedSprite(long frameDuration, AbsolutePosition2D tiles[]) {
        this.frameDuration = frameDuration;
        this.tiles = tiles;
        
        currentTileIndex = 0;
        nextUpdate = frameDuration;
    }

    public static AnimatedSpriteBuilder getBuilder() {
        return new AnimatedSpriteBuilder();
    }

    public AbsolutePosition2D getTile(long currentTime) {
        AbsolutePosition2D p = AbsolutePosition2D.of(tiles[currentTileIndex]);
        
        if (nextUpdate < currentTime) {
            nextUpdate = currentTime + frameDuration;    
            currentTileIndex++;
            if (currentTileIndex >= tiles.length) {
                currentTileIndex = 0;
            }
        }
        
        return p;
    }

    public static class AnimatedSpriteBuilder {

        private long frameDuration;
        private final List<AbsolutePosition2D> tilesList;

        AnimatedSpriteBuilder() {
            frameDuration = (long) 1000000000.0;
            tilesList = new ArrayList<>();
        }

        public AnimatedSpriteBuilder addTiles(@NonNull AbsolutePosition2D p) {
            tilesList.add(p);
            return this;
        }

        public AnimatedSprite build() {
            AbsolutePosition2D tiles[] = new AbsolutePosition2D[tilesList.size()];
            for (int i = 0; i < tiles.length; i++) {
                tiles[i] = AbsolutePosition2D.of(tilesList.get(i));
            }
            return new AnimatedSprite(frameDuration, tiles);
        }

        public AnimatedSpriteBuilder setFrameDuration(long frameDuration) {
            this.frameDuration = frameDuration;
            return this;
        }
    }
}
