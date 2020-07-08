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

import eu.frezilla.game.pacmangame.common.AbsolutePosition2D;
import eu.frezilla.game.pacmangame.common.Position2D;
import java.awt.Graphics;
import lombok.NonNull;

final class Sprite {
    
    private AbsolutePosition2D position2D;
    private final TileSet tileSet;
    
    public Sprite(@NonNull AbsolutePosition2D p, @NonNull TileSet t) {
        position2D = AbsolutePosition2D.of(p);
        tileSet = t;
    }
    
    public AbsolutePosition2D getPosition2D() {
        return AbsolutePosition2D.of(position2D);
    }
    
    public void display(@NonNull Graphics g, @NonNull Position2D pos) {
        tileSet.displayTile(g, pos, position2D);
    }
}
