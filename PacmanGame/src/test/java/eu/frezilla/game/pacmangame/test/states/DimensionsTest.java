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
package eu.frezilla.game.pacmangame.test.states;

import eu.frezilla.game.pacmangame.states.Dimensions;
import eu.frezilla.game.pacmangame.test.utils.ExceptionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class DimensionsTest {

    /**
     * Teste la création de dimensions
     */
    @Test
    public void create() {
        Dimensions dimensions1 = new Dimensions();
        Assertions.assertEquals(0, dimensions1.getHeight());
        Assertions.assertEquals(0, dimensions1.getWidth());

        Dimensions dimensions2 = Dimensions.of(1, 2);
        Assertions.assertEquals(2, dimensions2.getHeight());
        Assertions.assertEquals(1, dimensions2.getWidth());

        Dimensions dimensions3 = Dimensions.of(dimensions2);
        Assertions.assertEquals(2, dimensions3.getHeight());
        Assertions.assertEquals(1, dimensions3.getWidth());
    }

    /**
     * Teste l'affectation des valeurs à une dimensions
     */
    @Test
    public void setValues() {
        Dimensions dimensions = new Dimensions();
        
        ExceptionUtils.expectException(() -> {
            dimensions.setHeight(-1);
        }, IllegalArgumentException.class);
        
        ExceptionUtils.expectException(() -> {
            dimensions.setWidth(-2);
        }, IllegalArgumentException.class);
        
        ExceptionUtils.expectException(() -> {
            Dimensions.of(-1, 0);
        }, IllegalArgumentException.class);
        
        ExceptionUtils.expectException(() -> {
            Dimensions.of(0, -2);
        }, IllegalArgumentException.class);
        
        ExceptionUtils.expectException(() -> {
            Dimensions.of(-1, -2);
        }, IllegalArgumentException.class);

        dimensions.setHeight(1);
        Assertions.assertEquals(1, dimensions.getHeight());
        dimensions.setWidth(2);
        Assertions.assertEquals(2, dimensions.getWidth());
    }
}
