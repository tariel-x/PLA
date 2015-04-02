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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.tariel.pla.logics.IFunction;
import org.tariel.pla.logics.LogicFunction;
import org.tariel.pla.logics.Quantifer;
import org.tariel.pla.logics.Term;

/**
 *
 * @author nikita
 */
public class ConllWord implements IWord
{
    public List<IWord> subwords;
    public Link linktype;
    
    public String word;
    public String lex;
    public String pos;
    public String tense;
    public String lcase;
    public String number;
    public String verberp;
    public String verbmod;
    public String adjform;
    public String adjdegree;
    public String verbface;
    public String verbrepr;
    public String gender;
    public String aspect;
    public String voise;
    public String animacity;
    public String transitivity;
    public String addditionalinfo;
    public String other;
    
    public Integer id;
    
    @Override
    public List<IWord> getSubWords()
    {
        return this.subwords;
    }

    @Override
    public Link getLink()
    {
        return this.linktype;
    }
    
    @Override
    public String getWord()
    {
	return this.word;
    }

    @Override
    public String getLex()
    {
	return this.lex;
    }

    @Override
    public String getPos()
    {
	return this.pos;
    }

    @Override
    public String getTense()
    {
	return this.tense;
    }

    @Override
    public String getCase()
    {
	return this.lcase;
    }

    @Override
    public String getNumber()
    {
	return this.number;
    }

    @Override
    public String getVerbRepr()
    {
	return this.verberp;
    }

    @Override
    public String getVerbMood()
    {
	return this.verbmod;
    }

    @Override
    public String getAdjForm()
    {
	return this.adjform;
    }

    @Override
    public String getAdjDegree()
    {
	return this.adjdegree;
    }

    @Override
    public String getVerbFace()
    {
	return this.verbface;
    }

    @Override
    public String getGender()
    {
	return this.gender;
    }

    @Override
    public String getAspect()
    {
	return this.aspect;
    }

    @Override
    public String getVoice()
    {
	return this.voise;
    }

    @Override
    public String getAnimacy()
    {
	return this.animacity;
    }

    @Override
    public String getTransitivity()
    {
	return this.transitivity;
    }

    @Override
    public String getAdditionalInfo()
    {
	return this.addditionalinfo;
    }
    
    @Override
    public Link getLinktype()
    {
	return this.linktype;
    }

    @Override
    public void setLex(String value)
    {
	this.lex = value;
    }

    @Override
    public void setPos(String value)
    {
	this.pos = value;
    }

    @Override
    public void setTense(String value)
    {
	this.tense = value;
    }

    @Override
    public void setCase(String value)
    {
	this.lcase = value;
    }

    @Override
    public void setNumber(String value)
    {
	this.number = value;
    }

    @Override
    public void setVerbRepr(String value)
    {
	this.verberp = value;
    }

    @Override
    public void setVerbMood(String value)
    {
	this.verbmod = value;
    }

    @Override
    public void setAdjForm(String value)
    {
	this.adjform = value;
    }

    @Override
    public void setAdjDegree(String value)
    {
	this.adjdegree = value;
    }

    @Override
    public void setVerbFace(String value)
    {
	this.verbface = value;
    }

    @Override
    public void setGender(String value)
    {
	this.gender = value;
    }

    @Override
    public void setAspect(String value)
    {
	this.aspect = value;
    }

    @Override
    public void setVoice(String value)
    {
	this.voise = value;
    }

    @Override
    public void setAnimacy(String value)
    {
	this.animacity = value;
    }

    @Override
    public void setTransitivity(String value)
    {
	this.transitivity = value;
    }

    @Override
    public void setAdditionalInfo(String value)
    {
	this.addditionalinfo = value;
    }
    
    @Override
    public void setWord(String value)
    {
	this.word = value;
    }
    
    @Override
    public void setLinktype(Link link)
    {
	this.linktype = link;
    }

    @Override
    public void fromConll(String conllSentence)
    {
	List<String> conllstrings = Arrays.asList(conllSentence.split("\n"));
	//find root
	int root = 0;
	for (int i=0; i<conllstrings.size(); i++)
	{
	    String[] parts = conllstrings.get(i).split("\t");
	    if (Integer.parseInt(parts[6]) == 0)
	    {
		//fill this with root
		this.id = Integer.parseInt(parts[0]);
		this.word = parts[1];
		this.lex = parts[2];
		this.linktype = ConllWord.decodeLink(parts[7]);
		if (this.Pos.contains(parts[3]))
		    this.pos = parts[3];
		else
		    this.pos = "OTHER";
		this.decodeParams(parts[5]);
		root = i;
		break;
	    }
	}
	this.fromConll(conllSentence, root+1);
    }
  
    public void decodeParams(String params)
    {
	List<String> parts = Arrays.asList( StringUtils.split(params, "|"));
	for (Field field : IWord.class.getDeclaredFields())
	{
	    if (field.getName().contains("Variants"))
	    {
		try
		{
		    String name = field.getName().replace("Variants", "");
		    List<String> values = Arrays.asList((String[])field.get(this));
		    for (String part : parts)
		    {
			if (values.contains(part))
			{
			    String fieldName = name.toLowerCase();
			    Field thisField = ConllWord.class.getField(fieldName);
			    thisField.set(this, part);
			}
		    } 
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException ex)
		{
		    ex.printStackTrace();
		}
	    }
	}
    }
    
    public static Link decodeLink(String link)
    {
	switch (link)
	{
	    case "ROOT":
		return Link.ROOT;
	    case "SUBJ":
		return Link.SUBJ;
	    case "OBJ":
		return Link.OBJ;
	    case "AMOD":
		return Link.AMOD;
	    case "PREP":
		return Link.PREP;
	    case "POBJ":
		return Link.POBJ;
	    case "NEG":
		return Link.NEG;
	    default:
		return Link.NONE;
	}
    }
    
    public void fromConll(String conllSentence, int me)
    {
	List<String> conllstrings = Arrays.asList(conllSentence.split("\n"));
	//find every child word
	int root = 0;
	this.subwords = new ArrayList<>();
	for (int i=0; i<conllstrings.size(); i++)
	{
	    String[] parts = conllstrings.get(i).split("\t");
	    if (Integer.parseInt(parts[6]) == me)
	    {
		ConllWord tmp = new ConllWord();
		//fill new child
		tmp.id = Integer.parseInt(parts[0]);
		tmp.setWord(parts[1]);
		tmp.setLex(parts[2]);
		tmp.setLinktype(ConllWord.decodeLink(parts[7]));
		if (this.Pos.contains(parts[3]))
		    tmp.setPos(parts[3]);
		else
		    tmp.setPos("OTHER");
		tmp.decodeParams(parts[5]);
		tmp.fromConll(conllSentence, i+1);
		this.subwords.add(tmp);
	    }
	}
    }

    @Override
    public List<IWord> getSubWords(Link linktype)
    {
	//TODO: make optimisation with preindexing
	List<IWord> retwords = new ArrayList<>();
	for (IWord subword : this.subwords)
	{
	    if (subword.getLink() == linktype)
		retwords.add(subword);
	}
	return retwords;
    }
        
    @Override
    public IFunction toPLA()
    {
	if (this.pos.equals("V"))
	{
	    //Predicate term (verb)
	    //P(?)
	    Term predic_predic = new Term();
	    predic_predic.setName(this.getLex());
	    //Search for noun subjects
	    List<IWord> subjects = this.getSubWords(IWord.Link.SUBJ);
	    Term subj_term = null;
	    //Find subject
	    IWord subj_word = this.getSubject();
	    if (!subj_word.isBlank())
	    {
		//Subject term with quantifer, introducing subject
		//∃x
		Quantifer quantifer = new Quantifer();
		//Sx
		subj_term = new Term();
		subj_term.addVar(quantifer.getVar());
		subj_term.setName(subj_word.getLex());
		//Search for verb's objects
		IWord obj_word = this.getObject();
		if (!obj_word.isBlank())
		{
		    //Object term with quantifer
		    //∃y
		    Quantifer quant_obj = new Quantifer();
		    //Oy
		    Term obj_predic = new Term();
		    obj_predic.addVar(quant_obj.getVar());
		    obj_predic.setName(obj_word.getLex());

		    //Add subject and object variables to predicate
		    //P(?) -> Pxy
		    predic_predic.addVar(quantifer.getVar());
		    predic_predic.addVar(quant_obj.getVar());

		    //Object quantifer for Object and Predicate
		    //∃y(Oy ⋀ Pxy)
		    quant_obj.addSub(obj_predic);
		    quant_obj.addSub(predic_predic);

		    //Subject first for previous quantifer and Subject term
		    //∃x(Sx ⋀ ∃y(Oy ⋀ Pxy))
		    quantifer.addSub(subj_term);
		    quantifer.addSub(quant_obj);

		    //TODO: вынести по возможности конструирование формулы из условия
		    //чтобы при отсутствии pobj формула сохранялась

		    IFunction total = null;
		    for (IWord subword : this.subwords)
		    {
			ConllWord conll_sub = (ConllWord)subword;
			total = conll_sub.toPLA(quantifer);
		    }
		    return total;
		}
	    }
	    
	}
	else if (this.pos.equals("S"))
	{
	    Quantifer quant = new Quantifer();
	    Term word = new Term();
	    word.setName(this.lex);
	    quant.addSub(word);
	    IFunction total = null;
	    for (IWord subword : this.subwords)
	    {
		ConllWord conll_sub = (ConllWord)subword;
		total = conll_sub.toPLA(quant);
	    }
	    return total;
	}
	else
	{
	    for (IWord subword : this.subwords)
	    {
		subword.toPLA();
	    }
	}
	return null;
    }
    
    /**
     * Finds logic constructions and returns input formula AND subformula
     * @param current
     * @return 
     */
    public IFunction toPLA(IFunction current)
    {
	IFunction new_funcs = this.toPLA();
	if (new_funcs != null)
	{
	    LogicFunction conjuct = new LogicFunction();
	    conjuct.addSub(current);
	    conjuct.addSub(new_funcs);
	    return conjuct;
	}
	return current;
    }

    @Override
    public IWord getSubject() {
	List<IWord> objects = this.getSubWords(IWord.Link.SUBJ);
	for (IWord obj : objects)
	{
	    //If object is noun - it is good
	    if (obj.getPos().equals("S"))
	    {
		return obj;
	    }
	}
	IWord ret = new ConllWord();
	ret.setLinktype(IWord.Link.NONE);
	return ret;
    }

    @Override
    public IWord getObject() {
	List<IWord> objects = this.getSubWords(IWord.Link.OBJ);
	for (IWord obj : objects)
	{
	    //If object is noun - it is good
	    if (obj.getPos().equals("S"))
	    {
		return obj;
	    }
	}
	IWord ret = new ConllWord();
	ret.setLinktype(IWord.Link.NONE);
	return ret;
    }

    @Override
    public Boolean isBlank() {
	return (this.linktype == IWord.Link.NONE);
    }
}