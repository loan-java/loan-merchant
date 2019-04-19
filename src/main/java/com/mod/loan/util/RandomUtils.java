package com.mod.loan.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

public class RandomUtils {
	public final static String randStr = "0123456789abcdefghijklmnopqrstuvwxyz"; // 写入你所希望的所有的字母A-Z,a-z,0-9
	public final static String randNumStr = "123456789"; // 
	
	public static StringBuffer generatePassword(int length) {
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int randStrLength = length; // 接收需要生成随机数的长度
		for (int i = 0; i < randStrLength; i++) {
			int randNum = rand.nextInt(36);
			generateRandStr.append(randStr.substring(randNum, randNum + 1));
		}
		return generateRandStr; // 返回生成的随机数
	}

	public static String generateRandomNum(int length) {
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int randStrLength = length; // 接收需要生成随机数的长度
		for (int i = 0; i < randStrLength; i++) {
			int randNum = rand.nextInt(9);
			generateRandStr.append(randNumStr.substring(randNum, randNum + 1));
		}
		return generateRandStr.toString(); // 返回生成的随机数
	}
	
	/*
	 *生成指定位数的数字字符串 
	 */
	public static String generateConfirmStr(String str) {
		int a = str.length();
		int b= 6 - a;
		System.out.println(b);
		for(int i=0;i<=b-1;i++) {
			str = "0" + str;
		}
		return str;
		
	}

	// 处理图片时使用的随机数
	private static Random random = new Random();

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	private static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}
	
	private static void shearX(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(2);
		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);
		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {
		int period = random.nextInt(40) + 10; // 50;
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}

	/**
	 * 扭曲图片
	 * 
	 * @param g
	 * @param w1
	 * @param h1
	 * @param color
	 */
	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}
	
	/**
	 * Base64编码的验证码图片
	 * 
	 * @param w
	 * @param h
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String imageToBase64(int w, int h, String code) throws Exception {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA,
				Color.ORANGE, Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		g2.setColor(Color.GRAY);// 设置边框色
		g2.fillRect(0, 0, w, h);

		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 2, w, h - 4);

		// 绘制干扰线
		Random random = new Random();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}

		// 添加噪点
		float yawpRate = 0.05f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		shear(g2, w, h, c);// 使图片扭曲

		g2.setColor(getRandColor(100, 160));
		int fontSize = h - 10;
		Font font = new Font("Arial", Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
		}
		g2.dispose();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		Encoder base64 = Base64.getEncoder();
		return base64.encodeToString(baos.toByteArray());
	}
	
	public static void main(String[] args) {
		//String str = generateConfirmStr("8187779");
		StringBuffer aa = generatePassword(4);
		//String aa = generateRandomNum(4);
		System.out.println(aa);
		try {
			System.out.println(imageToBase64(48, 40, aa.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
