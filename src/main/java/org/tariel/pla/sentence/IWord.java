/*
 * Copyright 2015 Nikita Gerasimov <tariel-x@ya.ru>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tariel.pla.sentence;

import java.util.ArrayList;
import java.util.List;
import org.tariel.pla.logics.IFunction;
import org.tariel.pla.logics.classic.ICFunction;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public interface IWord
{
    /**
     * Link with parent word
     */
    enum Link
    {
	ROOT,
        SUBJ,
        OBJ,
        AMOD,
        PREP,
        POBJ,
        NEG,
	NONE
    }
    
    /**
     * Part of speech types
     */   
    public final List<String> Pos = new ArrayList<String>()
	{
	    {
		add("A");
		add("ADV");
		add("ADVPRO");
		add("ANUM");
		add("APRO");
		add("COM");
		add("CONJ");
		add("INTJ");
		add("NUM");
		add("PART");
		add("PR");
		add("S");
		add("SPRO");
		add("V");
	    }
	};
    
    public final String[] TenseVariants = 
    {
	"PST",
        "NPST",
        "PRS"
    };
    
    public final String[] LCaseVariants = 
    {
	"NOM",
        "GEN",
        "DAT",
        "ACC",
        "INS",
        "PREP",
        "GEN2",
        "LOC",
    };
    
    public final String[] NumberVariants = 
    {
	"SG",
        "PL",
    };
    
    public final String[] VerbReprVariants = 
    {
	"INF",
        "ADJP",
        "ADVP",
        "REAL",
        "IMP",
    };
    
    public final String[] AdjDegreeVariants = 
    {
	"COMP",
        "SUPL",
    };
    
    public final String[] VerbFaceVariants = 
    {
	"1P",
        "2P",
        "3P",
    };
    
    public final String[] GenderVariants = 
    {
	"F",
        "M",
        "N",
    };
    
    public final String[] AspectVariants = 
    {
	"IMPERF",
        "PERF",
    };
    
    public final String[] VoiceVariants = 
    {
	"PASS",
    };
    
    public final String[] AnimacityVariants = 
    {
	"ANIM",
        "INAN",
    };
    

    /**
     * Returns all one level below words
     * @return list of words
     */
    public List<IWord> getSubWords();
    
    /**
     * Returns all one level below words with mentined linktype
     * @param linktype
     * @return list of words
     */
    public List<IWord> getSubWords(Link linktype);
    
    /**
     * Returns subject of current word (if it is verb) or damn object if doesn't exist
     * @Nullable
     * @return verb's subject
     */
    public IWord getSubject();
    
    /**
     * Returns object of current word (if it is verb) or damn object if doesn't exist
     * @Nullable
     * @return verb's object
     */
    public IWord getObject();
    
    /**
     * Check if it is damn object
     * @return 
     */
    public Boolean isBlank();
    
    /**
     * Checks if the word is proposition
     * @return 
     */
    public Boolean isProposition();

    public Link getLink();

    /**
     * Parses CONLL-coded sentence in to words tree
     * @param conllSentence 
     */
    public void fromConll(String conllSentence);
    
    /**
     * Returns conll of this sentence
     * Conll string returns if only Word instance was made from conll
     * @return conll string
     */
    public String getConll();
    
    /**
     * Converts sentence structure into PLA formula
     * @return PLA formula
     */
    public IFunction toPLA();
    
    /**
     * Converts subsentence structure into PLA formula
     * @return PLA formula from subwords
     */
    public IFunction toSubPLA();
    
    /**
     * Applies classic logics formula to conll string
     * @param func
     * @return final text
     */
    public String resolveAnaphora(ICFunction func);

    /**
     * Get origin word
     *
     * @return word
     */
    public String getWord();

    /**
     * Get word lexem
     *
     * @return lexem
     */
    public String getLex();

    public String getPos();

    public String getTense();

    public String getCase();

    public String getNumber();

    public String getVerbRepr();

    public String getVerbMood();

    public String getAdjForm();

    public String getAdjDegree();

    public String getVerbFace();

    public String getGender();

    public String getAspect();

    public String getVoice();

    public String getAnimacy();

    public String getTransitivity();

    public String getAdditionalInfo();
    
    public Link getLinktype();
    
    public Integer getId();
    
    public String getUuid();
    
    public String getSentence();

    public void setLex(String value);
    
    public void setWord(String value);

    public void setPos(String value);

    public void setTense(String value);

    public void setCase(String value);

    public void setNumber(String value);

    public void setVerbRepr(String value);

    public void setVerbMood(String value);

    public void setAdjForm(String value);

    public void setAdjDegree(String value);

    public void setVerbFace(String value);

    public void setGender(String value);

    public void setAspect(String value);

    public void setVoice(String value);

    public void setAnimacy(String value);

    public void setTransitivity(String value);

    public void setAdditionalInfo(String value);
    
    public void setLinktype(Link link);
    
    public void setId(Integer id);
    
    public void setUuid(String uuid);
}
