package org.tariel.pla;

import org.tariel.pla.sentence.ConllWord;
import org.tariel.pla.sentence.IWord;
import org.tariel.pla.logics.IFunction;
import org.tariel.pla.logics.classic.ICFunction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
	
	PLA pla_inst = new PLA();
	pla_inst.fromConll("1	Жила	жить	V	V	PST|SG|REAL|F|IMPERF	0	ROOT	_	_\n" +
"2	была	быть	V	V	PST|SG|REAL|F|IMPERF	1	ROOT	_	_\n" +
"3	королева	королева	S	S	SG|F|ANIM	2	SUBJ	_	_");
	
	pla_inst.addConll("1	Её	она	SPRO	SPRO	ACC|SG|3P|F	3	ROOT	_	_\n" +
"2	сын	сын	S	S	SG|M|ANIM	3	SUBJ	_	_\n" +
"3	влюбился	влюбляться	V	V	PST|SG|REAL|M|PERF	0	ROOT	_	_\n" +
"4	в	в	PR	PR	_	3	OBJ	_	_\n" +
"5	лягушку	лягушка	S	S	ACC|SG|F|ANIM	4	PREP	_	_");
	
	pla_inst.addConll("1	Он	он	SPRO	SPRO	SG|3P|M	2	SUBJ	_	_\n" +
"2	поцеловал	поцеловать	V	V	PST|SG|REAL|M|PERF	0	ROOT	_	_\n" +
"3	её	она	SPRO	SPRO	ACC|SG|3P|F	2	OBJ	_	_\n" +
"4	и	и	CONJ	CONJ	_	3	ROOT	_	_\n" +
"5	она	она	SPRO	SPRO	SG|3P|F	6	SUBJ	_	_\n" +
"6	рассердилась	рассердиться	V	V	PST|SG|REAL|F|PERF	4	ROOT	_	_");
	
	String ret = pla_inst.toLogicsString();
	System.out.println(ret);
	pla_inst.toClassicLogics();
	String classic_ret = pla_inst.toClassicLogicsString();
	System.out.println(classic_ret);
	System.out.println("fin");
    }
    
}
