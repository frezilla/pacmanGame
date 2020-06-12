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
import eu.frezilla.game.pacmangame.states.Space;
import eu.frezilla.game.pacmangame.states.SpaceTypeId;
import eu.frezilla.game.pacmangame.states.StaticElement;
import eu.frezilla.game.pacmangame.states.World;
import eu.frezilla.game.pacmangame.test.utils.NumberUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class WorldTest {
    
    private Dimensions worldDimensions;
    private World world;
    
    @BeforeEach
    public void createWorld() {
        worldDimensions = Dimensions.of(NumberUtils.generateRandomNumber(3, 10), NumberUtils.generateRandomNumber(3, 10));
        world = new World(Dimensions.of(worldDimensions));
    }
    
    @Test
    public void access() {
        int worldHeight = worldDimensions.getHeight();
        int worldWidth = worldDimensions.getWidth();
        for (int i = 0; i < 10; i++) {
            int x = NumberUtils.generateRandomNumber(0, worldWidth);
            int y = NumberUtils.generateRandomNumber(0, worldHeight);
            world.set(x, y, Space.newInstance(SpaceTypeId.GUM));
            
            StaticElement se = world.get(x, y);
            Assertions.assertTrue(se instanceof Space);
            
            Space space = (Space) se;
            Assertions.assertEquals(space.getSpaceTypeId(), SpaceTypeId.GUM);
        }
    }
    
    @Test
    public void create() {
        Assertions.assertTrue(world.getDimensions().equals(worldDimensions));
    }
    
    @Test
    public void iterator() {
        List<StaticElement> staticElementList = new ArrayList<>();
        SpaceTypeId[] spaceTypeIdArray = SpaceTypeId.values();
        int nbSpaceTypeId = spaceTypeIdArray.length;
        
        for (int y = 0 ; y < worldDimensions.getHeight(); y++) {
            for (int x = 0; x < worldDimensions.getWidth(); x++) {            
                Space space = Space.newInstance(spaceTypeIdArray[NumberUtils.generateRandomNumber(0, nbSpaceTypeId)]);
                staticElementList.add(space);
                world.set(x, y, space);
            }
        }
        
        Iterator<StaticElement> it = world.iterator();
        int currentIndex = 0;
        while (it.hasNext()) {
            StaticElement staticElementIt = it.next();
            StaticElement staticElementEl = staticElementList.get(currentIndex);
            Assertions.assertEquals(staticElementIt, staticElementEl);
            currentIndex++;
        }
        
        if (currentIndex != staticElementList.size()) {
            Assertions.fail();
        }
    }
}
