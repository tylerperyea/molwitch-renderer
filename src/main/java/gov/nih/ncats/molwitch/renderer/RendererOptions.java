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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import gov.nih.ncats.molwitch.Chemical;
import gov.nih.ncats.molwitch.renderer.Graphics2DParent.ColorParent;

public class RendererOptions {

	public enum DrawOptions{
		DRAW_BONDS(true, "PROP_KEY_DRAW_BONDS"),
		DRAW_SYMBOLS(true, "PROP_KEY_DRAW_SYMBOLS"),
		DRAW_CARBON(false, "PROP_KEY_DRAW_CARBON"),
		DRAW_IMPLICIT_HYDROGEN(true, "PROP_KEY_DRAW_IMPLICIT_HYDROGEN"),
		DRAW_TERMINAL_CARBON(true, "PROP_KEY_DRAW_TERMINAL_CARBON"),
		DRAW_STEREO_BONDS(true, "PROP_KEY_DRAW_STEREO_BONDS"),
		DRAW_PROPORTIONAL_ATOM_RADIUS(false, "PROP_KEY_DRAW_PROPORTIONAL_ATOM_RADIUS"),
		DRAW_PROPORTION_AVERAGE_BOND_LENGTH(true, "PROP_KEY_DRAW_PROPORTION_AVERAGE_BOND_LENGTH"),
		DRAW_CENTER_ALL_DOUBLE_BONDS(false, "PROP_KEY_DRAW_CENTER_ALL_DOUBLE_BONDS"),
		DRAW_ATOM_COLOR_ON_BONDS(true, "PROP_KEY_DRAW_ATOM_COLOR_ON_BONDS"),
		DRAW_CONSTANT_DASH_WIDTH(true, "PROP_KEY_DRAW_CONSTANT_DASH_WIDTH"),
		DRAW_STEREO_DASH_AS_WEDGE(true, "PROP_KEY_DRAW_STEREO_DASH_AS_WEDGE"),
		DRAW_CENTER_NONRING_DOUBLE_BONDS(false, "PROP_KEY_DRAW_CENTER_NONRING_DOUBLE_BONDS"),
		
		
		
		DRAW_GREYSCALE(false, "PROP_KEY_DRAW_GREYSCALE"),
		DRAW_STEREO_LABELS(false, "PROP_KEY_DRAW_STEREO_LABELS"),
		DRAW_STEREO_GIVEN_BY_MAP(false, "PROP_KEY_DRAW_STEREO_GIVEN_BY_MAP"),
		DRAW_HIGHLIGHT_MAPPED(false, "PROP_KEY_DRAW_HIGHLIGHT_MAPPED"),
		DRAW_HIGHLIGHT_WITH_HALO(false, "PROP_KEY_DRAW_HIGHLIGHT_WITH_HALO"),
		DRAW_HIGHLIGHT_MONOCHROMATIC(true, "PROP_KEY_DRAW_HIGHLIGHT_MONOCHROMATIC"),
		DRAW_HIGHLIGHT_SHOW_ATOM(false, "PROP_KEY_DRAW_HIGHLIGHT_SHOW_ATOM"),
		DRAW_SHOW_MAPPED(false, "PROP_KEY_DRAW_SHOW_MAPPED"),
		DRAW_STEREO_LABELS_AS_ATOMS(false, "PROP_KEY_DRAW_STEREO_LABELS_AS_ATOMS"),
		DRAW_STEREO_LABELS_AS_RELATIVE(false, "PROP_KEY_DRAW_STEREO_LABELS_AS_RELATIVE"),
		DRAW_STEREO_LABELS_AS_STARRED(false, "PROP_KEY_DRAW_STEREO_LABELS_AS_STARRED"),
		DRAW_STEREO_LABELS_PARENTHESES(false, "PROP_KEY_DRAW_STEREO_LABELS_PARENTHESES"),
		DRAW_STEREO_FORCE_MONOCHROMATIC(false, "PROP_KEY_DRAW_STEREO_FORCE_MONOCHROMATIC");
		
		
		private final boolean defaultValue;
		
		/**
		 * This is the name from the old NChemicalRenderer DisplayParams
		 * that is sometimes used in legacy NCATS API calls
		 * to override rendering options.  This is used
		 * only in {@link #safeValueOf(String)}
		 * to maintain backwards compatibility.
		 */
		private final String legacyName;
		
		private static final Map<String, DrawOptions> nameMap;
		
		static {
			nameMap = new HashMap<>();
			for(DrawOptions opt : values()) {
				nameMap.put(opt.name(), opt);
				nameMap.put(opt.legacyName, opt);
			}
		}
		DrawOptions(boolean dv, String legacyName){
			defaultValue= dv;
			this.legacyName = legacyName;
		}
		/**
		 * This is a version of {@link #valueOf(String)}
		 * but if the given String input is not an enum value
		 * then it will return null instead of throwing an exception.
		 * 
		 * @param name
		 * @return
		 */
		public static DrawOptions safeValueOf(String name) {
			return nameMap.get(name);
		}
		public boolean defaultValue() {
			return defaultValue;
		}
	}
	
	public enum DrawProperties{
		ATOM_LABEL_FONT_FRACTION(2/3D, "DEF_FONT_PERCENT"),
		ATOM_LABEL_BOND_GAP_FRACTION(1, "DEF_FONT_GAP_PERCENT"),
		
		BOND_EXPECTED_LENGTH(1, "DEF_BOND_AVG"),
		BOND_STROKE_WIDTH_FRACTION(.095D, "DEF_STROKE_PERCENT"),
		BOND_DOUBLE_GAP_FRACTION(.30D, "DEF_DBL_BOND_GAP"),
		BOND_DOUBLE_LENGTH_FRACTION(2/3D, "DEF_DBL_BOND_DISTANCE"),
		BOND_STEREO_WEDGE_ANGLE( Math.PI/12, "DEF_WEDGE_ANG"),
		BOND_OVERLAP_SPACING_FRACTION(1.75D, "DEF_SPLIT_RATIO"),
		BOND_STEREO_DASH_NUMBER(6, "DEF_NUM_DASH");
		
		private final double defaultValue;
		/**
		 * This is the name from the old NChemicalRenderer DisplayParams
		 * that is sometimes used in legacy NCATS API calls
		 * to override renderering options.  This is used
		 * only in {@link #safeValueOf(String)}
		 * to maintain backwards compatibility.
		 */
		private final String legacyName;
		

		private static final Map<String, DrawProperties> nameMap;
		
		static {
			nameMap = new HashMap<>();
			for(DrawProperties opt : values()) {
				nameMap.put(opt.name(), opt);
				nameMap.put(opt.legacyName, opt);
			}
		}
		
		DrawProperties(double dv, String legacyName){
			defaultValue = dv;
			this.legacyName = legacyName;
		}
		
		/**
		 * This is a version of {@link #valueOf(String)}
		 * but if the given String input is not an enum value
		 * then it will return null instead of throwing an exception.
		 * 
		 * @param name
		 * @return
		 */
		public static DrawProperties safeValueOf(String name) {
			return nameMap.get(name);
		}
		public double defaultValue() {
			return defaultValue;
		}
	}
	
	
	private static final List<ColorParent> DEFAULT_HIGHLIGHT_COLORS=Arrays.asList(
																	new ColorParent(255,179,179,255),
																	new ColorParent(194,255,179,255),
																	new ColorParent(179,209,255,255),
																	new ColorParent(255,179,224,255),
																	new ColorParent(240,255,179,255),
																	new ColorParent(179,255,255,255),
																	new ColorParent(240,179,255,255),
																	new ColorParent(255,224,179,255),
																	new ColorParent(179,255,209,255),
																	new ColorParent(194,179,255,255),
																	new ColorParent(255,179,179,255),
																	new ColorParent(194,255,179,255)
																	);
		
	 
	
	private  final EnumMap<DrawOptions, Boolean> drawOptions = new EnumMap<>(DrawOptions.class);
	
	private  final EnumMap<DrawProperties, Double> drawProps = new EnumMap<>(DrawProperties.class);
	
	private List<ColorParent> highlightColors = new ArrayList<>(DEFAULT_HIGHLIGHT_COLORS);
	
	
	private Function<Chemical, String> bottomCaptionFunction=null;
	private Function<Chemical, String> topCaptionFunction=null;
	public RendererOptions() {
		_useDefauls();
	}
	
	public List<ColorParent> getHighlightColors(){
		return Collections.unmodifiableList(new ArrayList<>(highlightColors));
	}
	
	public int getNumberOfHighlightColors() {
		return highlightColors.size();
	}
	public RendererOptions addHighlightColor(ColorParent color) {
		highlightColors.add(Objects.requireNonNull(color));
		return this;
	}
	
	
	public RendererOptions setHighlightColors(List<ColorParent> colors) {
		Optional<?> nullColor= colors.stream().filter(Objects::isNull).findAny();
		if(nullColor.isPresent()) {
			throw new NullPointerException("color list can not contain nulls");
		}
		highlightColors = new ArrayList<>(colors);
		return this;
	}
	public RendererOptions addHighlightColor(int offset, ColorParent color) {
		highlightColors.add(offset, Objects.requireNonNull(color));
		return this;
	}
	
	
	
	public boolean getDrawOption(DrawOptions o) {
		return drawOptions.get(o);
	}
	
	public RendererOptions setDrawOption(DrawOptions option, boolean value) {
		drawOptions.put(option, value);
		return this;
	}
	
	public double getDrawPropertyValue(DrawProperties p) {
		return drawProps.get(p);
	}
	public RendererOptions captionBottom(Function<Chemical, String> captionFunction) {
		this.bottomCaptionFunction = captionFunction;
		return this;
	}
	public RendererOptions captionTop(Function<Chemical, String> captionFunction) {
		this.topCaptionFunction = captionFunction;
		return this;
	}
	public RendererOptions setDrawPropertyValue(DrawProperties p, double value) {
		drawProps.put(p, value);
		return this;
	}
	public RendererOptions withSubstructureHighlight() {
		
		drawOptions.put(DrawOptions.DRAW_HIGHLIGHT_MAPPED, true);
		drawOptions.put(DrawOptions.DRAW_HIGHLIGHT_WITH_HALO, true);
		drawOptions.put(DrawOptions.DRAW_HIGHLIGHT_MONOCHROMATIC, true);
		
		return this;
	}
	
	Optional<String> captionBottom(Chemical c){
		if(bottomCaptionFunction!=null) {
			return Optional.ofNullable(bottomCaptionFunction.apply(c));
		}
		return Optional.empty();
	}
	
	Optional<String> captionTop(Chemical c){
		if(topCaptionFunction!=null) {
			return Optional.ofNullable(topCaptionFunction.apply(c));
		}
		return Optional.empty();
	}
	public static RendererOptions createDefault() {
		return new RendererOptions();
	}
	
	public static RendererOptions createBallAndStick() {
		RendererOptions opts =new RendererOptions();
		
		opts.setDrawOption(DrawOptions.DRAW_BONDS, true);
		opts.setDrawOption(DrawOptions.DRAW_SYMBOLS, false);
		opts.setDrawOption(DrawOptions.DRAW_CARBON, true);
		opts.setDrawOption(DrawOptions.DRAW_CENTER_ALL_DOUBLE_BONDS, true);
		opts.setDrawOption(DrawOptions.DRAW_STEREO_BONDS, false);
		return opts;
	}
	
	public static RendererOptions createINNLike() {
		RendererOptions opts =new RendererOptions();
		
		opts.setDrawOption(DrawOptions.DRAW_GREYSCALE, true);
		opts.setDrawOption(DrawOptions.DRAW_STEREO_DASH_AS_WEDGE, false);
		
		opts.setDrawPropertyValue(DrawProperties.BOND_STEREO_WEDGE_ANGLE, Math.PI/12);
		opts.setDrawPropertyValue(DrawProperties.BOND_STROKE_WIDTH_FRACTION, 0.050D);
		opts.setDrawPropertyValue(DrawProperties.BOND_DOUBLE_GAP_FRACTION, .15D);
		opts.setDrawPropertyValue(DrawProperties.ATOM_LABEL_FONT_FRACTION,0.5D);
		return opts;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drawOptions == null) ? 0 : drawOptions.hashCode());
		result = prime * result + ((drawProps == null) ? 0 : drawProps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RendererOptions)) {
			return false;
		}
		RendererOptions other = (RendererOptions) obj;
		if (drawOptions == null) {
			if (other.drawOptions != null) {
				return false;
			}
		} else if (!drawOptions.equals(other.drawOptions)) {
			return false;
		}
		if (drawProps == null) {
			if (other.drawProps != null) {
				return false;
			}
		} else if (!drawProps.equals(other.drawProps)) {
			return false;
		}
		return true;
	}

	private void _useDefauls() {
		for(DrawOptions o : DrawOptions.values()) {
			drawOptions.put(o, o.defaultValue());
		}
		
		for(DrawProperties o : DrawProperties.values()) {
			drawProps.put(o, o.defaultValue());
		}
		
		highlightColors = new ArrayList<>(DEFAULT_HIGHLIGHT_COLORS);
	}
	
	public RendererOptions resetToDefaults() {
		_useDefauls();
		return this;
	}
	
	public RendererOptions turnOffStereo() {
		setDrawOption(DrawOptions.DRAW_STEREO_LABELS, false);
		setDrawOption(DrawOptions.DRAW_STEREO_LABELS_PARENTHESES, false);
		return this;
	}
	
	public RendererOptions turnOnStereo() {
		
		setDrawOption(DrawOptions.DRAW_STEREO_LABELS, true);
		setDrawOption(DrawOptions.DRAW_STEREO_LABELS_PARENTHESES, true);
		return this;
	}
	
	public RendererOptions changeSettings(Map<String, ?> map) {
		for(Entry<String, ?> entry: map.entrySet()) {
			DrawOptions opts = DrawOptions.safeValueOf(entry.getKey());
			if(opts ==null) {
				DrawProperties props = DrawProperties.safeValueOf(entry.getKey());
				if(props !=null) {
					if(Number.class.isAssignableFrom(entry.getValue().getClass())){
						setDrawPropertyValue(props,((Number)entry.getValue()).doubleValue());
					}else if(String.class.isAssignableFrom(entry.getValue().getClass())){
						setDrawPropertyValue(props,Double.parseDouble((String)entry.getValue()));
					}
				}
			}else {
				if(Boolean.class.isAssignableFrom(entry.getValue().getClass())){
					setDrawOption(opts, ((Boolean) entry.getValue()).booleanValue());
				}else if(String.class.isAssignableFrom(entry.getValue().getClass())){
					setDrawOption(opts,Boolean.parseBoolean((String)entry.getValue()));
				}
				
			}
		}
		return this;
	}
}