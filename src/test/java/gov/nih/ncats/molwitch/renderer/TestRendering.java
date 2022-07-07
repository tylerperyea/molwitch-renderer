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

import gov.nih.ncats.molwitch.Chemical;
import gov.nih.ncats.molwitch.MolWitch;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Ignore
public class TestRendering {

    @Test
    public void renderOverlap() throws Exception{
        ChemicalRenderer renderer = new ChemicalRenderer();
        Chemical c = Chemical.parseMol(new File(getClass().getResource("/overlap.mol").getFile()));
        ImageIO.write(renderer.createImage(c, 600), "PNG", new File(MolWitch.getModuleName() +"_overlap.png"));        

    }

    @Test
    public void renderMol() throws Exception{
        ChemicalRenderer renderer = new ChemicalRenderer();
        Chemical c = Chemical.parseMol(new File(getClass().getResource("/benzoic_acid.mol").getFile()));
        renderer.setBackgroundColor(Color.white);
        BufferedImage image=renderer.createImage(c, 600);

        boolean result1 =ImageIO.write(image, "PNG", new File(MolWitch.getModuleName() +"_benzoic_acid4.png"));
        Assert.assertTrue(result1);

    }

    @Test
    public void renderMols() throws Exception{
        String folder ="images\\";
        List<String> molNames = Arrays.asList("EU9DD7762T", "NH393K3YNR" /*tall*/, "MNJ7VPT2R5" /*long*/, "MNJ7VPT2R5_mult",
                "Structure2D_CID_118984375"/*insulin -- large!*/, "water", "charged_radical_isotopic_water",
                "water_double2", "water_double2close" /*35 units apart*/, "water_double_near" /*2 units apart*/, "Y9WL8QN3ZB" /*polymer*/,
                "P88XT4IS4D", "ethane", "benzoic_acid");
        molNames.forEach(mol->{
            System.out.println("Going to render " + mol);
            ChemicalRenderer renderer = new ChemicalRenderer();
            Chemical c = null;
            try {
                c = Chemical.parseMol(new File(getClass().getResource("/" + mol + ".mol").getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            renderer.setBackgroundColor(Color.white);
            BufferedImage image=renderer.createImageAutoAdjust(c, 500, 200, 500, 200, 25);
                    //createImageAutoAdjust(c, 500, 200, 500, 200, 5);
                    //renderer.createImageAutoAdjust(c, 300);
            System.out.println("completed image creation");
            boolean result1 = false;
            try {
                result1 = ImageIO.write(image, "PNG", new File(folder +MolWitch.getModuleName()
                        + mol +"_5parm25_nw0.95.png"));
                System.out.println("wrote");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(result1);

        });

    }

    @Test
    public void testComputeAverageInterAtomDistance() {
        String mol="water_triple";
        Chemical c = null;
        try {
            c = Chemical.parseMol(new File(getClass().getResource("/" + mol + ".mol").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        double min = ChemicalRenderer.computeLowestInterAtomDistance(c).get();
        System.out.println("lowest interatomic distance " + min);
        Assert.assertTrue(min>= 100);
    }

    @Test
    public void testComputeBounds() throws Exception{
        String folder ="images\\";
        List<String> molNames = Arrays.asList("EU9DD7762T", "NH393K3YNR" /*tall*/, "MNJ7VPT2R5" /*long*/, "MNJ7VPT2R5_mult",
                "Structure2D_CID_118984375"/*insulin -- large!*/, "water", "charged_radical_isotopic_water",
                "water_double2", "water_double2close", "Y9WL8QN3ZB" /*polymer*/,
                "P88XT4IS4D", "ethane", "benzoic_acid");
        molNames.forEach(mol->{
            System.out.println("Going to handle " + mol);
            ChemicalRenderer renderer = new ChemicalRenderer();
            Chemical c = null;
            try {
                c = Chemical.parseMol(new File(getClass().getResource("/" + mol + ".mol").getFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Rectangle2D.Double rectangle =ChemicalRenderer.computeAtomicCoordinateBounds(c);
            System.out.printf("box for %s: x=%f, y=%f, width=%f, height=%f\n", mol, rectangle.x,
                    rectangle.y, rectangle.width, rectangle.height);

            Assert.assertTrue(rectangle !=null);
        });
    }

}

