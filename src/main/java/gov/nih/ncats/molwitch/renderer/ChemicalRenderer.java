/*
 * NCATS-MOLWITCH-RENDERER
 *
 * Copyright 2019 NIH/NCATS
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gov.nih.ncats.molwitch.renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import gov.nih.ncats.molwitch.Chemical;

public class ChemicalRenderer {

	private final NchemicalRenderer renderer;
	
	public ChemicalRenderer() {
		this(RendererOptions.createDefault());
	}
	public ChemicalRenderer(RendererOptions options){
		this.renderer = new NchemicalRenderer(Objects.requireNonNull(options));
	}
	public ChemicalRenderer setBackgroundColor(Color bg) {
		this.renderer.setBackgroundColor(bg);
		return this;
	}
	public ChemicalRenderer setBorderColor(Color bg) {
		this.renderer.setBorderColor(bg);
		return this;
	}
	
	public RendererOptions getOptions() {
		return renderer.getOptions();
	}
	public ChemicalRenderer setShadowVisible(boolean visible) {
		this.renderer.setShadowVisible(visible);
		return this;
	}
	public ChemicalRenderer setBorderVisible(boolean visible) {
		this.renderer.setBorderVisible(visible);
		return this;
	}
	public void render(Graphics2D g2d, File inputMol, int x, int y, int width, int height, boolean round) throws IOException{
		render(g2d, Chemical.parseMol(inputMol), x,y, width, height, round);
	}
	public void render(Graphics2D g2d, String inputMol, int x, int y, int width, int height, boolean round) throws IOException{
		render(g2d, Chemical.parseMol(inputMol), x,y, width, height, round);
	}
	public void render(Graphics2D g2d, Chemical c, int x, int y, int width, int height, boolean round) {
		renderer.renderChem (g2d, c, x,y, width, height, round);
		getOptions().captionBottom(c)
				.ifPresent(caption ->renderer.drawText(g2d,x,y,width,height,caption,1)); // 1 is bottom, 0 is top);;
		getOptions().captionTop(c)
		.ifPresent(caption ->renderer.drawText(g2d,x,y,width,height,caption,0)); // 1 is bottom, 0 is top);;

	}
	public BufferedImage createImage (String inputMol, int size) throws IOException{
		return createImage (Chemical.parseMol(inputMol), size, size, true);
	}
	public BufferedImage createImage (File inputMol, int size) throws IOException{
		return createImage (Chemical.parseMol(inputMol), size, size, true);
	} 
	public BufferedImage createImage (Chemical c, int size) {
    		return createImage (c, size, size, true);
    }  
	public BufferedImage createImage (File inputMol, int width, int height, boolean round) throws IOException{
	    return createImage(Chemical.parseMol(inputMol), width, height, round);
	}
	public BufferedImage createImage (String inputMol, int width, int height, boolean round) throws IOException{
	    return createImage(Chemical.parseMol(inputMol), width, height, round);
	}
	public BufferedImage createImage (Chemical c, int width, int height, boolean round) {
        BufferedImage img = new BufferedImage (width, height, BufferedImage.TYPE_INT_ARGB);
            
        Graphics2D g2 = img.createGraphics();
        render (g2, c,0,0, width, height, round);
        g2.dispose();
    

         return img;
    } 
	
}
