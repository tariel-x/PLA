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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

/**
 *
 * @author nikita
 */
public class ConllWord implements IWord
{
    public List<IWord> subwords;
    public Link linktype;
    
    private String word;
    private String lex;
    private String pos;
    private String tense;
    private String lcase;
    private String number;
    private String verberp;
    private String verbmod;
    private String adjform;
    private String adjdegree;
    private String verbface;
    private String gender;
    private String aspect;
    private String voise;
    private String animacity;
    private String transitivity;
    private String addditionalinfo;
    private String other;
    
    private Integer id;
    
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
    
    public String getWord()
    {
	return this.word;
    }

    public String getLex()
    {
	return this.lex;
    }

    public String getPos()
    {
	return this.pos;
    }

    public String getTense()
    {
	return this.tense;
    }

    public String getCase()
    {
	return this.lcase;
    }

    public String getNumber()
    {
	return this.number;
    }

    public String getVerbRepr()
    {
	return this.verberp;
    }

    public String getVerbMood()
    {
	return this.verbmod;
    }

    public String getAdjForm()
    {
	return this.adjform;
    }

    public String getAdjDegree()
    {
	return this.adjdegree;
    }

    public String getVerbFace()
    {
	return this.verbface;
    }

    public String getGender()
    {
	return this.gender;
    }

    public String getAspect()
    {
	return this.aspect;
    }

    public String getVoice()
    {
	return this.voise;
    }

    public String getAnimacy()
    {
	return this.animacity;
    }

    public String getTransitivity()
    {
	return this.transitivity;
    }

    public String getAdditionalInfo()
    {
	return this.addditionalinfo;
    }
    
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
	System.out.println("end");
    }
  
    public void decodeParams(String params)
    {
	List<String> parts = Arrays.asList(params.split("|"));
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
			    Field thisField = ConllWord.class.getField(name.toLowerCase());
			    thisField.set(this, part);
			}
		    } 
		} catch (Exception ex)
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
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class SimpleWord
{
    public int id;
    public int parent;
    public String word;
    public String lex;
    public String params;
    public String link;
    public String part;
    
    public SimpleWord(String instr)
    {
	String[] parts = instr.split("\t");
	this.id = Integer.parseInt(parts[0]);
	this.parent = Integer.parseInt(parts[6]);
	this.word = parts[1];
	this.lex = parts[2];
	this.params = parts[5];
	this.link = parts[7];
	this.part = parts[3];
    }
}