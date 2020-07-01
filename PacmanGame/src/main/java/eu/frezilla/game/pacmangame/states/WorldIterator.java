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
package eu.frezilla.game.pacmangame.states;

import eu.frezilla.game.pacmangame.common.Dimensions;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class WorldIterator implements Iterator<StaticElement> {
    
    private final World world;
    private int x;
    private int y;
    
    WorldIterator(World world) {
        this.world = world;
        x = -1;
        y = 0;
    }

    @Override
    public boolean hasNext() {
        Dimensions dimensions = Objects.requireNonNull(world.getDimensions());
        return x+1 < dimensions.getWidth() || y+1 < dimensions.getHeight();
    }

    @Override
    public StaticElement next() {
        Dimensions dimensions = Objects.requireNonNull(world.getDimensions());
        
        if (x+1 < dimensions.getWidth()) {
            x++;
        }
        else if (y+1 < dimensions.getHeight()) {
            x = 0;
            y++;
        } else {
            throw new NoSuchElementException();
        }
        return world.get(x, y);
    }
    
}
