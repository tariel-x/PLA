package org.tariel.pla;

import org.tariel.pla.sentence.ConllWord;
import org.tariel.pla.sentence.IWord;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
	IWord word = new ConllWord();
	word.fromConll("1	Нефть	нефть	S	S	NOM|SG|F|INAN	2	SUBJ\n" +
			"2	поднимется	подниматься	V	V	NPST|SG|REAL|3P|PERF	0	ROOT\n" +
			"3	выше	высоко	ADV	ADV	COMP	2	POBJ\n" +
			"4	100	100	NUM	NUM	_	5	ROOT\n" +
			"5	долларов	доллар	S	S	GEN|PL|M|INAN	3	ROOT");
    }
}
