package org.tariel.pla;

import org.tariel.pla.sentence.ConllWord;
import org.tariel.pla.sentence.IWord;
import org.tariel.pla.logics.IFunction;

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
	word.fromConll("1	Он	он	SPRO	SPRO	SG|3P|M	2	SUBJ	_	_\n" +
"2	поцеловал	поцеловать	V	V	PST|SG|REAL|M|PERF	0	ROOT	_	_\n" +
"3	её	она	SPRO	SPRO	ACC|SG|3P|F	2	OBJ	_	_\n" +
"4	и	и	CONJ	CONJ	_	3	ROOT	_	_\n" +
"5	она	она	SPRO	SPRO	SG|3P|F	6	SUBJ	_	_\n" +
"6	рассердилась	рассердиться	V	V	PST|SG|REAL|F|PERF	4	ROOT	_	_");
	IFunction fin = word.toPLA();
	String formula = fin.toStrRepresentation();
	System.out.println(formula);
	System.out.println("fin");
    }
    
}
