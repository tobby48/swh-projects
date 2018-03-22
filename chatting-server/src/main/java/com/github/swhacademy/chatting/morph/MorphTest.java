/**
 * 패키지명 : chat
 * 파일명 : MorphTest.java
 * 설명 : 
 * 
 * </pre>
 * 
 * @since : 2018. 2. 5.
 * @author : tobby48
 * @version : v1.0
 */
package com.github.swhacademy.chatting.morph;

import java.util.List;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;

public class MorphTest {

	public MorphTest(){
		Analyzer.resetUserDict();
	}
	public MorphTest(List<String> koyu){
		Analyzer.setUserDict(koyu.iterator());
	}
	
	public void start(){
		
		for (LNode node : Analyzer.parseJava("나한테 욕해봐.")) {
            System.out.println(node);
            System.out.println(node.morpheme().surface());
            System.out.println(node.morpheme().feature().apply(0));	//	NNG, NNP
            //	(NNG or NNP) and 단어명 
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MorphTest test = new MorphTest();
		test.start();
	}

}
