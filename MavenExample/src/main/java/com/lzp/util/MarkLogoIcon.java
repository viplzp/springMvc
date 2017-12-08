/*************************************************************************************
 * Copyright (C) 2015 Shenzhen Zhubaodai Internet Financial Services Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市珠宝贷互联网金融服务股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件。
 *************************************************************************************/
/*************************************************************************************
 * Copyright (C) 2015 Shenzhen Zhubaodai Internet Financial Services Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市珠宝贷互联网金融服务股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件。
 *************************************************************************************/

package com.lzp.util;
/**
 * 类名: MarkLogoIcon
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-11-30 上午11:51:24
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: MarkLogoIcon
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-11-30 上午11:51:24
 *
 * @author lzp
 * @version 
 * @since JDK 1.6
 */

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;


public class MarkLogoIcon {
	public static void markByIcon(String iconPath, String srcImagePath, String targetPath) {
		markByIcon(iconPath, srcImagePath, targetPath, null);
	}
	public static void markByIcon(String iconPath, String srcImagePath, String tagetPath, Integer degree) {
		OutputStream os = null;
		try {
			Image srcImage = ImageIO.read(new File(srcImagePath));
			BufferedImage buffImg = new BufferedImage(srcImage.getWidth(null), srcImage.getHeight(null), BufferedImage.TYPE_INT_BGR);
			Graphics2D g = buffImg.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImage.getScaledInstance(srcImage.getWidth(null), srcImage.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

			if (degree != null) {
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth(null) / 2, (double) buffImg.getHeight(null) / 2);
			}
			ImageIcon imageIcon = new ImageIcon(iconPath);
			Image img = imageIcon.getImage();
			float alpha = 0.5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
			g.drawImage(img, 100, 100, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

			g.dispose();

			os = new FileOutputStream(tagetPath);

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
			System.out.println("图片水印添加成功。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) {
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
	    File f2 = new File(MarkLogoIcon.class.getResource("/").getPath());

		String path=f2.getAbsolutePath()+File.separator;
		String srcImagePath = path+"timg.jpg";
		String targetImagePath = path+"timg_1.jpg";
		String logoText = "hello world";
		String iconPath = path+"yz.png";
		String targetImagePath2 = "f:/Music/photo/3.jpg";
		System.out.println(srcImagePath);
		markByIcon(iconPath, srcImagePath, targetImagePath);
//		markByIcon(iconPath, srcImagePath, targetImagePath2, -45);
		System.out.println(path);
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
	}
}
