package com.example.demo.community.config;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

import javax.imageio.ImageIO;

public class IconTest {
	  
	
	
	
	public static void main(String[] args) {
		
		File file = new File("D:\\icon\\");
		File[] subList = file.listFiles();
		
		for(int i = 1 ; i<subList.length ; i++) {
			
			
			String fileName = subList[i].getName();
			System.out.println(fileName);
			String transName =fileName.replaceAll(fileName, i+".jpg");
			System.out.println(transName);
			subList[i].renameTo(new File("D:\\icon1\\"+transName));
		}
		
		
		
	}
	
	
	
}
