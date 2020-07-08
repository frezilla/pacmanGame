/*
 * The MIT License
 *
 * Copyright 2020 frezilla.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.frezilla.game.pacmangame.gui;

import eu.frezilla.game.pacmangame.common.Position2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
final class AnimatedSprites {

    private final AnimatedSprite[] animatedSprites;
    
    private int currentTileIndex;
    private long nextUpdate;

    private AnimatedSprites(AnimatedSprite tiles[]) {
        animatedSprites = tiles;
        
        currentTileIndex = 0;
        nextUpdate = animatedSprites[0].getFrameDuration();
    }

    public static AnimatedSpriteBuilder getBuilder() {
        return new AnimatedSpriteBuilder();
    }

    public void display(Graphics g, Position2D pos, long currentTime) {
        AnimatedSprite aSprite = animatedSprites[currentTileIndex];
        aSprite.display(g, pos);
        
        if (nextUpdate < currentTime) {
            nextUpdate = currentTime + aSprite.getFrameDuration();
            currentTileIndex++;
            if (currentTileIndex >= animatedSprites.length) {
                currentTileIndex = 0;
            }
        }
    }

    public static class AnimatedSpriteBuilder {

        private final List<AnimatedSprite> animatedSpriteList;

        AnimatedSpriteBuilder() {
            animatedSpriteList = new ArrayList<>();
        }

        public AnimatedSpriteBuilder addSprite(@NonNull AnimatedSprite aSprite) {
            animatedSpriteList.add(aSprite);
            return this;
        }

        public AnimatedSprites build() {
            AnimatedSprite animatedSprites[] = new AnimatedSprite[animatedSpriteList.size()];
            for (int i = 0; i < animatedSprites.length; i++) {
                animatedSprites[i] = animatedSpriteList.get(i);
            }
            return new AnimatedSprites(animatedSprites);
        }
    }
}
