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
package eu.frezilla.game.pacmangame.common;

import lombok.NonNull;

public final class AbsolutePosition2D {
    
    private final Position2D position2D;
    
    public AbsolutePosition2D() {
        position2D = Position2D.of(0, 0);
    }
    
    public int getX() {
        return position2D.getX();
    }
    
    public int getY() {
        return position2D.getY();
    }
    
    public static AbsolutePosition2D of(@NonNull AbsolutePosition2D position) {
        return of(position.getX(), position.getY());
    }
    
    public static AbsolutePosition2D of(int x, int y) {
        AbsolutePosition2D p = new AbsolutePosition2D();
        p.setX(x);
        p.setY(y);
        return p;
    }
    
    public void setX(int x) {
        if (x < 0) {
            throw new IllegalArgumentException();
        }
        position2D.setX(x);
    }
    
    public void setY(int y) {
        if (y < 0) {
            throw new IllegalArgumentException();
        }
        position2D.setY(y);
    }
    
}
