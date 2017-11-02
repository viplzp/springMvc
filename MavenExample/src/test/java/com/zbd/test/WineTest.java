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

package com.zbd.test;

import java.util.List;
/**
 * 类名: JiuTest
 * 类描述: TODO (用一句话描述这个类做什么).
 * 创建日期: 2017-11-2 上午11:13:52
 * @author lzp
 * @version
 * @since JDK 1.6
 * @see
 */
/**
 * 类名: JiuTest 类描述: TODO (用一句话描述这个类做什么). 创建日期: 2017-11-2 上午11:13:52
 * 
 * @author lzp
 * @version
 * @since JDK 1.6
 */

public class WineTest {

	/**
	 * Creates a new instance of JiuTest.
	 * 
	 */
	/*
	 * private int allEmptyBottle;//总空瓶 private int allLid;//总瓶盖; private int
	 * allWine;//总瓶数量
	 */
	// 5瓶酒
	// 1瓶酒=一个空瓶+一个瓶盖
	//
	private int breakCount = 1;
	public static void calDrinkCount(int initCount) {
		/*
		 * List<Wine> wList=new ArrayList<Wine>(); for (int i = 0; i <
		 * initCount; i++) { Wine wine=new Wine(); wine.setEmptyBottle(1);
		 * wine.setLid(1); wList.add(wine); }
		 */

	}
	// 消费者
	public void producer(List<Wine> wList) {

	}
	// 生产者
	// -1瓶酒价格=2空瓶=4瓶盖
	public void consumer(int emptyBottle, int lid, int allWine) {
		breakCount++;
		if (emptyBottle >= 2) {
			int newWineCount = emptyBottle / 2;// 产生新瓶酒数量
			int leftEmptyBottle = emptyBottle % 2;// 剩余空瓶数量
			emptyBottle = leftEmptyBottle + newWineCount;
			lid = lid + newWineCount;
			allWine = allWine + newWineCount;
			System.out.println("第" + breakCount + "次,本次消费空瓶数量:" + newWineCount * 2 + "得到酒：" + newWineCount + "；喝酒后-空瓶剩余数量：" + emptyBottle + "，瓶盖剩余数量：" + lid + ",总瓶数：" + allWine);
			consumer(emptyBottle, lid, allWine);
		} else if (lid >= 4) {
			int newLidCount = lid / 4;// 产生新瓶酒数量
			int leftLid = lid % 4;// 剩余瓶盖数量
			emptyBottle = emptyBottle + newLidCount;
			lid = leftLid + newLidCount;
			allWine = allWine + newLidCount;
			System.out.println("第" + breakCount + "次,本次消费瓶盖数量:" + newLidCount * 4 + "得到酒：" + newLidCount + "；喝酒后-空瓶剩余数量：" + emptyBottle + "，瓶盖剩余数量：" + lid + ",总瓶数：" + allWine);
			consumer(emptyBottle, lid, allWine);
		}
		if (emptyBottle < 2 && lid < 4) {
			System.out.println("结束啦");
			System.out.println("空瓶剩余数量：" + emptyBottle);
			System.out.println("瓶盖剩余数量：" + lid);
			System.out.println("喝酒最终数量：" + allWine);
		}
	}

	public static void main(String[] args) {
		WineTest jiu = new WineTest();
		int emptyBottle = 5;
		int lid = 5;
		int allWine = 5;
		System.out.println("=============第1次==============");
		System.out.println("先喝" + allWine + "瓶，得到" + emptyBottle + "个空瓶和" + lid + "个瓶盖");
		jiu.consumer(emptyBottle, lid, allWine);
	}

}
class Wine {
	private int emptyBottle = 1;// 空瓶
	private int lid = 1;// 瓶盖;
	/**
	 * emptyBottle.
	 * 
	 * @return the emptyBottle
	 * @since JDK 1.6
	 */
	public int getEmptyBottle() {
		return emptyBottle;
	}
	/**
	 * emptyBottle.
	 * 
	 * @param emptyBottle
	 *            the emptyBottle to set
	 * @since JDK 1.6
	 */

	public void setEmptyBottle(int emptyBottle) {
		this.emptyBottle = emptyBottle;
	}
	/**
	 * lid.
	 * 
	 * @return the lid
	 * @since JDK 1.6
	 */
	public int getLid() {
		return lid;
	}
	/**
	 * lid.
	 * 
	 * @param lid
	 *            the lid to set
	 * @since JDK 1.6
	 */

	public void setLid(int lid) {
		this.lid = lid;
	}

}
