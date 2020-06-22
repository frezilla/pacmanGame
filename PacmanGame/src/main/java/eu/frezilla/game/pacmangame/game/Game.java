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
package eu.frezilla.game.pacmangame.game;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Game {
    private static final int DEFAULT_FPS = 60;
    private static Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private boolean isRunning;
    private final List<Renderable> renderableList;
    private int targetFps;
    private long targetNanoPerFrame;

    private Game(List<Renderable> l) {
        isRunning = false;
        renderableList = l;
    }

    private void computeNanoPerFrame() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        int refreshRate = dm.getRefreshRate();

        targetFps = refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN ? DEFAULT_FPS : refreshRate;
        targetNanoPerFrame = (long) (1000000000.0 / targetFps);
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public int getTargetFps() {
        if (!isRunning) {
            return DEFAULT_FPS;
        }
        return targetFps;
    }

    private void render() {
        renderableList.forEach(r -> {
            r.render();
        });
    }

    public void run() {
        if (!isRunning) {
            computeNanoPerFrame();
            isRunning = true;

            long lastTime = System.nanoTime();

            while (isRunning) {
                long nowTime = System.nanoTime();
                if ((nowTime - lastTime) < targetNanoPerFrame) {
                    continue;
                }
                lastTime = nowTime;

                render();

                try {
                    TimeUnit.NANOSECONDS.sleep(System.nanoTime() - lastTime);
                } catch (InterruptedException e) {
                    LOGGER.error("Interrupted exception while sleeping {}", e);
                }
            }
        } else {
            throw new RuntimeException("Game is already running");
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GameBuilder {

        List<Renderable> renderableList = new ArrayList<>();

        public GameBuilder add(@NonNull Renderable renderable) {
            renderableList.add(renderable);
            return this;
        }

        public Game build() {
            return new Game(renderableList);
        }
    }

}
