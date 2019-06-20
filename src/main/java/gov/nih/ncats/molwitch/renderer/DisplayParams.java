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

import gov.nih.ncats.molwitch.renderer.Graphics2DParent.ColorParent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * This is probably can be removed
 * since it's been refactored out to {@link RendererOptions}
 * but for now just make it package private.
 */

class DisplayParams implements Cloneable{
		/**
		 * 
		 */
		//These are all the properties
	//Those with "DRAW" are boolean
	//Others are floating points
		final public static int PROP_KEY_DRAW_BONDS=1;
		final public static int PROP_KEY_DRAW_SYMBOLS=2;
		final public static int PROP_KEY_DRAW_CARBON=3;
		final public static int PROP_KEY_DRAW_IMPLICIT_HYDROGEN=4;
		final public static int PROP_KEY_DRAW_TERMINAL_CARBON=5;
		final public static int PROP_KEY_DRAW_STEREO_BONDS=6;
		final public static int PROP_KEY_DRAW_PROPORTIONAL_ATOM_RADIUS=7;
		final public static int PROP_KEY_DRAW_PROPORTION_AVERAGE_BOND_LENGTH=8;
		final public static int PROP_KEY_DRAW_CENTER_ALL_DOUBLE_BONDS=9;
		final public static int PROP_KEY_DRAW_ATOM_COLOR_ON_BONDS=10;
		final public static int PROP_KEY_DRAW_CONSTANT_DASH_WIDTH=11;
		final public static int PROP_KEY_DRAW_STEREO_DASH_AS_WEDGE=12;
		final public static int PROP_KEY_DRAW_CENTER_NONRING_DOUBLE_BONDS=13;
		final public static int PROP_KEY_ATOM_LABEL_FONT_FRACTION=16;
		final public static int PROP_KEY_ATOM_LABEL_BOND_GAP_FRACTION=19;
		final public static int PROP_KEY_BOND_EXPECTED_LENGTH=14;
		final public static int PROP_KEY_BOND_STROKE_WIDTH_FRACTION=15;
		final public static int PROP_KEY_BOND_DOUBLE_GAP_FRACTION=17;
		final public static int PROP_KEY_BOND_DOUBLE_LENGTH_FRACTION=18;
		final public static int PROP_KEY_BOND_STEREO_WEDGE_ANGLE=20;
		final public static int PROP_KEY_BOND_OVERLAP_SPACING_FRACTION=21;
		final public static int PROP_KEY_BOND_STEREO_DASH_NUMBER=23;
		final public static int PROP_KEY_DRAW_GREYSCALE=24;
		final public static int PROP_KEY_DRAW_STEREO_LABELS=25;
		final public static int PROP_KEY_DRAW_STEREO_GIVEN_BY_MAP=26;
		final public static int PROP_KEY_DRAW_HIGHLIGHT_MAPPED=27;
		final public static int PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO=28;
		final public static int PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC=29;
		final public static int PROP_KEY_DRAW_HIGHLIGHT_SHOW_ATOM=30;
		final public static int PROP_KEY_DRAW_SHOW_MAPPED = 31;
		final public static int PROP_KEY_DRAW_STEREO_LABELS_AS_ATOMS = 32;
		final public static int PROP_KEY_DRAW_STEREO_LABELS_AS_RELATIVE = 33;
		final public static int PROP_KEY_DRAW_STEREO_LABELS_AS_STARRED = 34;
		final public static int PROP_KEY_DRAW_STEREO_LABELS_PARENTHESES = 35;
		final public static int PROP_KEY_DRAW_STEREO_FORCE_MONOCHROMATIC = 36;
		
		
		public float DEF_BOND_AVG=1.0f;
		public float DEF_STROKE_PERCENT=.095f;
		public float DEF_FONT_PERCENT=.66f;
		public float DEF_DBL_BOND_GAP=.30f;
		public float DEF_DBL_BOND_DISTANCE=.66f;
		public float DEF_FONT_GAP_PERCENT=1.0f;
		public float DEF_WEDGE_ANG=(float)Math.PI/12f;
		public float DEF_SPLIT_RATIO = 1.75f;
		public float ATOM_OVER_ALPHA=1;
		public int DEF_NUM_DASH=6;
		//static final int PROP_KEY_COLOR_HIGHLIGHT_PALLETE = 33;
		//static final int PROP_KEY_COLOR_ATOM_PALLETE = 34;
		
		
		final public static Map<String,Integer> PROP_KEYS = new HashMap<String,Integer>();
		final public static Map<String,Class> PROP_KEYS_CLASS = new LinkedHashMap<String,Class>();
		
		
		public Map<Integer,Object> PROP_KEYS_VALUES = new LinkedHashMap<Integer,Object>();
		
		
		public static ColorParent[] dcolors = new ColorParent[12];
		
		static {			
			//for(int i=0;i<dcolors.length;i++){
			//	dcolors[i]=DefaultColor(i);
			//}
			dcolors[0]= new ColorParent(255,179,179,255);
			dcolors[1]= new ColorParent(194,255,179,255);
			dcolors[2]= new ColorParent(179,209,255,255);
			dcolors[3]= new ColorParent(255,179,224,255);
			dcolors[4]= new ColorParent(240,255,179,255);
			dcolors[5]= new ColorParent(179,255,255,255);
			dcolors[6]= new ColorParent(240,179,255,255);
			dcolors[7]= new ColorParent(255,224,179,255);
			dcolors[8]= new ColorParent(179,255,209,255);
			dcolors[9]= new ColorParent(194,179,255,255);
			dcolors[10]= new ColorParent(255,179,179,255);
			dcolors[11]= new ColorParent(194,255,179,255);
		}
		
		//public static ColorParent DefaultColor(int map){
		//	
		//	return ColorParent.getHSBColor(0.3f * map, .30f, 1f);
		//}
		
		static{
			Class c1 =DisplayParams.class;
		    Map fieldMap = new HashMap();
		    Field[] valueObjFields = c1.getDeclaredFields();
		    for(Field f:valueObjFields){
		    	String s=f.getName();
		    	int o;
				try {
					
					
			    	if(s.startsWith("PROP_KEY_")){
			    		o = f.getInt(null);
			    		PROP_KEYS.put(s, o);
			    		if(s.contains("PROP_KEY_DRAW")){
			    			PROP_KEYS_CLASS.put(s, Boolean.class);
			    		}else if(s.contains("PROP_KEY_COLOR")){
			    			
			    			
			    			
			    			
			    			
			    			PROP_KEYS_CLASS.put(s, List.class);
			    			
			    		}else{
			    			PROP_KEYS_CLASS.put(s, Float.class);
			    		}
			    		
			    		//System.out.println(s + ":" +o);
			    	}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		
		

		public DisplayParams(){
			changeProperty(PROP_KEY_DRAW_BONDS,true);
			changeProperty(PROP_KEY_DRAW_SYMBOLS,true);
			changeProperty(PROP_KEY_DRAW_TERMINAL_CARBON,true);
			changeProperty(PROP_KEY_DRAW_IMPLICIT_HYDROGEN,true);
			changeProperty(PROP_KEY_DRAW_STEREO_BONDS,true);
			changeProperty(PROP_KEY_DRAW_PROPORTION_AVERAGE_BOND_LENGTH,true);
			changeProperty(PROP_KEY_DRAW_ATOM_COLOR_ON_BONDS,true);
			changeProperty(PROP_KEY_DRAW_CONSTANT_DASH_WIDTH,true);
			changeProperty(PROP_KEY_DRAW_STEREO_DASH_AS_WEDGE,true);
			changeProperty(PROP_KEY_DRAW_CENTER_NONRING_DOUBLE_BONDS,true);
			changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,false);
			changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,true);
			changeProperty(PROP_KEY_DRAW_SHOW_MAPPED,false);
			changeProperty(PROP_KEY_DRAW_STEREO_LABELS_AS_ATOMS,false);
			setup();
		}
		
		public static DisplayParams BALL_AND_STICK(){
			DisplayParams dp =new DisplayParams();
			dp.changeProperty(PROP_KEY_DRAW_BONDS,true);
			dp.changeProperty(PROP_KEY_DRAW_SYMBOLS,false);
			dp.changeProperty(PROP_KEY_DRAW_CARBON,true);
			dp.changeProperty(PROP_KEY_DRAW_CENTER_ALL_DOUBLE_BONDS,true);
			dp.changeProperty(PROP_KEY_DRAW_STEREO_BONDS,false);
			return dp;
		}
		public static DisplayParams INN_LIKE(){
			DisplayParams dp = new DisplayParams();
			dp.DEF_STROKE_PERCENT = 0.050f;
			dp.DEF_DBL_BOND_GAP=.15f;
			dp.DEF_DBL_BOND_DISTANCE=.76f;
			dp.DEF_FONT_PERCENT=.50f;
			dp.DEF_WEDGE_ANG=(float)Math.PI/24f;
			dp.changeProperty(PROP_KEY_DRAW_GREYSCALE,true);
			dp.changeProperty(dp.PROP_KEY_DRAW_STEREO_DASH_AS_WEDGE,false);
			dp.setup();
			return dp;
		}
		public DisplayParams withSpecialColor(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,false);
			dp.setup();
			return dp;
		}
		public DisplayParams withSpecialColorMON(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,true);
			dp.setup();
			return dp;
		}
		public DisplayParams withSpecialColor2(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,false);
			dp.DEF_STROKE_PERCENT=.095f;
			dp.setup();
			return dp;
		}
		public DisplayParams withSpecialColor3(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_SHOW_ATOM,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,true);
			dp.setup();
			return dp;
		}
		public DisplayParams withSpecialColor4(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,false);
			dp.setup();
			return dp;
		}
		public DisplayParams withSubstructureHighlight(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,true);
			dp.setup();
			return dp;
		}
		public DisplayParams withSpecialColor5(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,false);
			dp.changeProperty(PROP_KEY_DRAW_PROPORTION_AVERAGE_BOND_LENGTH,false);
			dp.changeProperty(PROP_KEY_ATOM_LABEL_FONT_FRACTION,1);
			dp.setup();
			return dp;
		}
		public DisplayParams withNumbering(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MAPPED,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO,true);
			dp.changeProperty(PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC,false);
			dp.changeProperty(PROP_KEY_DRAW_SHOW_MAPPED,true);
			dp.setup();
			return dp;
		}
		public DisplayParams withStereo(){
			DisplayParams dp =this.clone2();
			dp.changeProperty(DisplayParams.PROP_KEY_DRAW_STEREO_LABELS,true);
			dp.setup();
			return dp;
		}
		
		public static DisplayParams DEFAULT(){
			return new DisplayParams();
		}
//		public void changeSettings(String s){
//			Gson gson=new Gson(); 
//			Map<String,Object> map=new HashMap<String,Object>();
//			map=(Map<String,Object>) gson.fromJson(s, map.getClass());
//			changeSettings(map);
//			
//		}
		public void changeSettings(Map settings){
			for(Object e:settings.entrySet()){
				Object k=((Entry)e).getKey();
				Object v=((Entry)e).getValue();
				changeSettings(k,v);
			}
		}
		public void changeSettings(Object key, Object val){
			//System.out.println("CHANGING s " + key.toString() + " TO " +val.toString());
			Integer keyv=PROP_KEYS.get(key);
			if(keyv==null){
				if(Number.class.isAssignableFrom(key.getClass())){
					keyv = ((Number)key).intValue();				
				}else if(String.class.isAssignableFrom(key.getClass())){
					try{
						keyv = Integer.parseInt((String)key);
					}catch(Exception e){
						
					}
				}
			}
			if(keyv!=null){
				changeProperty(keyv.intValue(),val);
			}			
			
		}
		public void changeProperty(int key, Object val){
			//System.out.println("CHANGING " + key + " TO " +val.toString());
			float v1=0f;
			boolean v=true;
			if(val==null)return;
			if(Number.class.isAssignableFrom(val.getClass())){
				v1 = ((Number)val).floatValue();				
				PROP_KEYS_VALUES.put(key,v1);
			}
			if(String.class.isAssignableFrom(val.getClass())){
				try{
					v1=(float)Double.parseDouble((String)val);
					PROP_KEYS_VALUES.put(key,v1);
				}catch(Exception e){
					
				}
				try{
					v=Boolean.parseBoolean((String)val);
					PROP_KEYS_VALUES.put(key,v);
				}catch(Exception e){
					
				}
			}
			if(val.getClass().equals(Boolean.class)){
				try{
					v=(Boolean)val;
					PROP_KEYS_VALUES.put(key,v);
				}catch(Exception e){
					
				}
			}
			
			switch(key){
			
			case PROP_KEY_BOND_EXPECTED_LENGTH:
				DEF_BOND_AVG = v1;
				break;
			case PROP_KEY_BOND_STROKE_WIDTH_FRACTION:
				DEF_STROKE_PERCENT = v1;
				break;
			case PROP_KEY_ATOM_LABEL_FONT_FRACTION:
				DEF_FONT_PERCENT = v1;
				break;
			case PROP_KEY_BOND_DOUBLE_GAP_FRACTION:
				DEF_DBL_BOND_GAP = v1;
				break;
			case PROP_KEY_BOND_DOUBLE_LENGTH_FRACTION:
				DEF_DBL_BOND_DISTANCE = v1;
				break;
			case PROP_KEY_ATOM_LABEL_BOND_GAP_FRACTION:
				DEF_FONT_GAP_PERCENT = v1;
				break;
			case PROP_KEY_BOND_STEREO_WEDGE_ANGLE:
				DEF_WEDGE_ANG = v1;
				break;
			case PROP_KEY_BOND_OVERLAP_SPACING_FRACTION:
				DEF_SPLIT_RATIO = v1;
				break;
			case PROP_KEY_BOND_STEREO_DASH_NUMBER:
				DEF_NUM_DASH = (int)v1;
				break;
			}
			
		}
		public Object getProperty(int propertyKey){
			return PROP_KEYS_VALUES.get(propertyKey);
		}
		public double getPropertyAsDouble(int propertyKey){
			return (Double)PROP_KEYS_VALUES.get(propertyKey);
		}
		public boolean getPropertyAsBoolean(int propertyKey){
			Boolean b= (Boolean)PROP_KEYS_VALUES.get(propertyKey);
			if(b==null)return false;
			return b;
		}
		public void setup(){
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_EXPECTED_LENGTH,DEF_BOND_AVG );
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_STROKE_WIDTH_FRACTION,DEF_STROKE_PERCENT );
			 PROP_KEYS_VALUES.put( PROP_KEY_ATOM_LABEL_FONT_FRACTION,DEF_FONT_PERCENT );
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_DOUBLE_GAP_FRACTION,DEF_DBL_BOND_GAP );
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_DOUBLE_LENGTH_FRACTION,DEF_DBL_BOND_DISTANCE );
			 PROP_KEYS_VALUES.put( PROP_KEY_ATOM_LABEL_BOND_GAP_FRACTION,DEF_FONT_GAP_PERCENT );
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_STEREO_WEDGE_ANGLE,DEF_WEDGE_ANG );
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_OVERLAP_SPACING_FRACTION,DEF_SPLIT_RATIO );
			 PROP_KEYS_VALUES.put( PROP_KEY_BOND_STEREO_DASH_NUMBER,DEF_NUM_DASH );
		}
		public DisplayParams clone2() {
			//System.out.println("START CLONED");
			DisplayParams dp2 = new DisplayParams();
			dp2.changeSettings(PROP_KEYS_VALUES);
			//System.out.println("CLONED");
			return dp2;
		}

		public ColorParent[] getHighlightPallete() {
			return dcolors;
		}
	}