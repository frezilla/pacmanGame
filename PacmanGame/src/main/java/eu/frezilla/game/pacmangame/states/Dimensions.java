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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public final class Dimensions {
    
    private int height;
    private int width;
    
    public Dimensions() {
        height = 0;
        width = 0;
    }
    
    public static Dimensions of(@NonNull Dimensions dimensions) {
        return of(dimensions.getWidth(), dimensions.getHeight());
    }

    public static Dimensions of(int width, int height) {
        Dimensions d = new Dimensions();
        d.setHeight(height);
        d.setWidth(width);
        return d;
    }
    
    public void setHeight(int height) {
        if (height < 0)  {
            throw new IllegalArgumentException();
        }
        this.height = height;
    }
    
    public void setWidth(int width) {
        if (width < 0)  {
            throw new IllegalArgumentException();
        }
        this.width = width;
    }
    
}
