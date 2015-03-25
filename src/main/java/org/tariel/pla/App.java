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
	word.fromConll("1	И	и	CONJ	CONJ	_	0	ROOT	0	ROOT\n" +
			"2	несмотря на	несмотря на	PR	PR	_	1	ROOT	1	ROOT\n" +
			"3	альтернативный	альтернативный	A	A	ACC|SG|M|INAN	4	AMOD	4	AMOD\n" +
			"4	проект	проект	S	S	ACC|SG|M|INAN	2	PREP	2	PREP\n" +
			"5	разработанный	разрабатывать	VADJ	VADJ	PST|ACC|SG|ADJP|M|PERF|INAN	4	AMOD	4	AMOD\n" +
			"6	юристами	юрист	S	S	INS|PL|M|ANIM	5	ROOT	5	ROOT\n" +
			"7	Совета	совет	S	S	GEN|SG|M|INAN	6	ROOT	6	ROOT");
    }
}
