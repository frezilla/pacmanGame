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

import eu.frezilla.game.pacmangame.game.Renderable;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class AwtGUI implements Renderable {

    private static final int CANVASHEIGHT = 600;
    private static final int CANVASWIDTH = 800;

    private final Canvas canvas;
    private final Frame frame;

    public AwtGUI() {
        frame = new Frame("Affichage et contrôle AWT");
        canvas = new Canvas();

        initFrame();
        initCanvas();

        frame.add(canvas);
    }

    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initCanvas() {
        Dimension d = new Dimension(CANVASWIDTH, CANVASHEIGHT);
        canvas.setPreferredSize(d);
        canvas.setMinimumSize(d);
        canvas.setMaximumSize(d);
    }

    private void initFrame() {
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                frame.dispose();
            }
        });
    }

    @Override
    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }
        Graphics g = null;
        try {
            g = bs.getDrawGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, CANVASWIDTH, CANVASHEIGHT);
            bs.show();
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }
}
