package net.blockscape.helper;

import processing.core.PApplet;
import processing.core.PImage;

public class IconHelper {

	private static PApplet host;
	/**
	 * initializes the iconhelper
	 * @param host_
	 */
	public static void init(PApplet host_){
		host = host_;
	}
	/**
	 * loads a block file with the given name and returns the PImage
	 * @param imageFile name of file
	 * @return the image
	 */
	public static PImage convertStringToBlockImage(String imageFile){
		return host.loadImage("block\\"+imageFile);
	}
	
}
