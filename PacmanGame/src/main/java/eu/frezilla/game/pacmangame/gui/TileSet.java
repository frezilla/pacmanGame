package eu.frezilla.game.pacmangame.gui;

import eu.frezilla.game.pacmangame.common.AbsolutePosition2D;
import eu.frezilla.game.pacmangame.common.Position2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.Getter;
import lombok.NonNull;

@Getter
public final class TileSet {
    
    private final int horizontalSpace;
    private final int marginLeft;
    private final int marginTop;
    private final int nbTexturesHeight;
    private final int nbTexturesWidth;
    private final BufferedImage texture;
    private final int tileHeight;
    private final String tilesPath;
    private final int tileWidth;
    private final int verticalSpace;
    
    private TileSet(String tilesPath, int tileWidth, int tileHeight, int horizontalSpace, int verticalSpace, int marginLeft, int marginTop) throws IOException {
        checkParameter(tileWidth, "tileWidth");
        checkParameter(tileHeight, "tileHeight");
        checkParameter(horizontalSpace, "horizontalSpace");
        checkParameter(verticalSpace, "verticalSpace");
        checkParameter(marginLeft, "marginLeft");
        checkParameter(marginTop, "marginTop");
        
        this.tilesPath = tilesPath;
        this.horizontalSpace = horizontalSpace;
        this.verticalSpace = verticalSpace;
        this.marginLeft = marginLeft;
        this.marginTop = marginTop;
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        
        texture = ImageIO.read(getClass().getClassLoader().getResource(tilesPath));
        nbTexturesWidth = (texture.getWidth() - marginLeft) / (tileWidth + horizontalSpace);
        nbTexturesHeight = (texture.getHeight() - marginTop) / (tileHeight + verticalSpace);
    }
    
    private void checkParameter(int p, String s) {
        if (p < 0) {
            throw new IllegalArgumentException(String.format("%s can not be negative", s));
        }
    }
    
    public void displayTile(@NonNull Graphics g, @NonNull Position2D pos, @NonNull AbsolutePosition2D tilePos) {
        int tileX = tilePos.getX();
        int tileY = tilePos.getY();
        int x = pos.getX();
        int y = pos.getY();
        
        int textureSrcX = tileX * (tileWidth + horizontalSpace) + marginLeft;
        int textureSrcY = tileY * (tileHeight + verticalSpace) + marginTop;
        
        g.drawImage(texture, 
                x, y, x + tileWidth, y + tileHeight,
                textureSrcX, textureSrcY, textureSrcX + tileWidth, textureSrcY + tileWidth,
                null);
    }
    
    public static TileSetBuilder getBuilder(String tilesPath) {
        return new TileSetBuilder(tilesPath);
    }
    
    static class TileSetBuilder {
        
        private int horizontalSpace;
        private int marginLeft;
        private int marginTop;
        private final String tilesPath;
        private int tileHeight;
        private int tileWidth;
        private int verticalSpace;
        
        private TileSetBuilder(String tilesPath) {
            this.tilesPath = tilesPath;
            
            horizontalSpace = 0;
            marginLeft = 0;
            marginTop = 0;
            tileHeight = 24;
            tileWidth = 24;
            verticalSpace = 0;
        }
        
        public TileSet build() throws IOException {
            return new TileSet(tilesPath, tileWidth, tileHeight, horizontalSpace, verticalSpace, marginLeft, marginTop);
        }

        public TileSetBuilder setHorizontalSpace(int horizontalSpace) {
            this.horizontalSpace = horizontalSpace;
            return this;
        }
        
        public TileSetBuilder setMarginLeft(int marginLeft) {
            this.marginLeft = marginLeft;
            return this;
        }
        
        public TileSetBuilder setMarginTop(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }
        
        public TileSetBuilder setTileHeight(int tileHeight) {
            this.tileHeight = tileHeight;
            return this;
        }
        
        public TileSetBuilder setTileWidth(int tileWidth) {
            this.tileWidth = tileWidth;
            return this;
        }
        
        public TileSetBuilder setVerticalSpace(int verticalSpace) {
            this.verticalSpace = verticalSpace;
            return this;
        }
    }
    
}
