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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.tariel.pla.logics.ConjunctionContainer;
import org.tariel.pla.logics.IFunction;
import org.tariel.pla.logics.Proposition;
import org.tariel.pla.logics.Quantifer;
import org.tariel.pla.logics.Term;
import org.tariel.pla.logics.VariableStorage;
import org.tariel.pla.logics.classic.CQuantifer;
import org.tariel.pla.logics.classic.CQuantifiedContainer;
import org.tariel.pla.logics.classic.ICFunction;

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
    public String conll_string = null;
    private Map<String, String> conll_strings;
    public String uuid;

    @Override
    public List<IWord> getSubWords()
    {
	return this.subwords;
    }

    @Override
    public void fromConll(String conllSentence)
    {
	this.conll_string = "";
	this.conll_strings = new HashMap<>();
	List<String> conllstrings = Arrays.asList(conllSentence.split("\n"));
	for (String str : conllstrings)
	{
	    String uuid = java.util.UUID.randomUUID().toString();
	    this.conll_string += str + "\t" + uuid + "\n";
	    this.conll_strings.put(uuid, str);
	}
	conllSentence = this.conll_string;
	conllstrings = Arrays.asList(conllSentence.split("\n"));
	//find root
	int root = 0;
	for (int i = 0; i < conllstrings.size(); i++)
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
		{
		    this.pos = parts[3];
		} else
		{
		    this.pos = "OTHER";
		}
		this.decodeParams(parts[5]);
		this.setUuid(parts[10]);
		root = i;
		break;
	    }
	}
	//find another words
	this.fromConll(conllSentence, root + 1);
    }

    public void decodeParams(String params)
    {
	List<String> parts = Arrays.asList(StringUtils.split(params, "|"));
	for (Field field : IWord.class.getDeclaredFields())
	{
	    if (field.getName().contains("Variants"))
	    {
		try
		{
		    String name = field.getName().replace("Variants", "");
		    List<String> values = Arrays.asList((String[]) field.get(this));
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
	for (int i = 0; i < conllstrings.size(); i++)
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
		{
		    tmp.setPos(parts[3]);
		} else
		{
		    tmp.setPos("OTHER");
		}
		tmp.decodeParams(parts[5]);
		tmp.setUuid(parts[10]);
		tmp.fromConll(conllSentence, i + 1);
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
	    {
		retwords.add(subword);
	    }
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
	    
	    //Find verb's subject
	    IWord subj_word = this.getSubject();
	    //Search for verb's objects
	    IWord obj_word = this.getObject();
	    
	    //variables for ∃xSx
	    Quantifer quant_subj = null;//Base quantifer - root element for this part
	    Term subj_term = null;

	    //variables for ∃xOx
	    Quantifer quant_obj = null;
	    Term obj_predic = null;
	    
	    //prepositions
	    Proposition p1 = null;
	    Proposition p2 = null;
	    
	    //construnct ∃xSx
	    if (!subj_word.isBlank() && !subj_word.isProposition())
	    {
		//Subject term with quantifer, introducing subject
		//∃x
		quant_subj = new Quantifer();
		//Sx
		subj_term = new Term();
		subj_term.addVar(quant_subj.getVar());
		subj_term.setName(subj_word.getLex());
		quant_subj.getVar().setSourceId(subj_word.getUuid());
	    } 
	    else if (!subj_word.isBlank() && subj_word.isProposition())
	    {
		//Creates subject proposition, if it exists
		p1 = new Proposition();
		p1.setSourceId(subj_word.getUuid());
	    }
	    else
	    {
		//nothing will be happened here
		//maybe look deeper?
		IFunction cont = new ConjunctionContainer();
		cont = this.subsToPla(cont);
		if (cont.getSub().size() > 1 || cont.getSub().size() == 0)
		    return cont;
		else
		    return cont.getSub().get(0);
	    }

	    //constructs ∃yOy
	    if (!obj_word.isBlank() && !subj_word.isProposition())
	    {
		//Object term with quantifer
		//∃y
		quant_obj = new Quantifer();
		//Oy
		obj_predic = new Term();
		obj_predic.addVar(quant_obj.getVar());
		obj_predic.setName(obj_word.getLex());
		quant_obj.getVar().setSourceId(obj_word.getUuid());
	    }
	    //Creates object propostion if it exists
	    if (!obj_word.isBlank() && subj_word.isProposition())
	    {
		p2 = new Proposition();
		p2.setSourceId(obj_word.getUuid());
	    }

	    //Add subject and object variables to predicate (if they exist)
	    //P(?) -> Pxy or Px or Py or nothing
	    //if there are prepositions - they will be added instead of variables
	    if (quant_subj != null && p1 == null)
	    {
		predic_predic.addVar(quant_subj.getVar());
	    }
	    if (p1 != null)
	    {
		predic_predic.addVar(p1);
	    }
	    if (quant_obj != null && p2 == null)
	    {
		predic_predic.addVar(quant_obj.getVar());
	    }
	    if (p2 != null)
	    {
		predic_predic.addVar(p2);
	    }

	    //Object quantifer for Object and Predicate
	    //∃y(Oy ⋀ Pxy) or nothing
	    if (quant_obj != null)
	    {
		quant_obj.addSub(obj_predic);
		quant_obj.addSub(predic_predic);
	    }

	    //Subject first for previous quantifer and Subject term
	    //∃x(Sx ⋀ ∃y(Oy ⋀ Pxy)) or ∃x(Sx ⋀ Px) or Pp1,p2
	    IFunction this_result = null;
	    if (quant_obj != null)
	    {
		quant_subj.addSub(subj_term);
		quant_subj.addSub(quant_obj);
		this_result = quant_subj;
	    } 
	    else if (quant_subj != null)
	    {
		quant_subj.addSub(subj_term);
		quant_subj.addSub(predic_predic);
		this_result = quant_subj;
	    }
	    else
	    {
		//If  no object, subject -> return current predicate
		this_result = predic_predic;
	    }

	    //If will be found something deeped - this will be for conjunction of current and deeper
	    IFunction total = new ConjunctionContainer();
	    //add current result
	    total.addSub(this_result);
	    //result of object's subcalculations
	    IFunction obj_underlogic = null;
//	    IFunction subj_underlogic = subj_word.toPLA();
	    IFunction subj_underlogic = subj_word.toSubPLA();
	    if (!obj_word.isBlank())
	    {
//		obj_underlogic = obj_word.toPLA();
		obj_underlogic = obj_word.toSubPLA();
	    }
	    if (subj_underlogic != null)
	    {
		total.addSub(subj_underlogic);
	    }
	    if (obj_underlogic != null)
	    {
		total.addSub(obj_underlogic);
	    }
	    total = this.subsToPla(total);
	    //if total contains only current result and no sublogic - return only current
	    if (total.getSub().size() > 1)
	    {
		return total;
	    } else
	    {
		return this_result;
	    }
	} else if (this.pos.equals("S"))
	{
	    //construnct ∃xSx
	    Quantifer quant = new Quantifer();
	    Term word = new Term();
	    word.setName(this.lex);
	    quant.addSub(word);

	    IFunction total = new ConjunctionContainer();
	    total.addSub(quant);
	    total = this.subsToPla(total);
	    //if total contains only current result and no sublogic - return only current
	    if (total.getSub().size() > 1)
	    {
		return total;
	    } else
	    {
		return quant;
	    }
	} else
	{
	    IFunction total = new ConjunctionContainer();
	    total = this.subsToPla(total);
	    //if total contains one result and no sublogic - return only one
	    if (total.getSub().size() > 1)
	    {
		return total;
	    } else if (total.getSub().size() > 0)
	    {
		return total.getSub().get(0);
	    } else
	    {
		return null;
	    }
	}
    }

    /**
     * Finds logic constructions and returns input formula AND subformula
     *
     * @param current
     * @return
     */
    public IFunction toPLA(IFunction current)
    {
	IFunction total = new ConjunctionContainer();
	IFunction new_funcs = this.toPLA();
	if (new_funcs != null)
	{
	    total.addSub(current);
	    total.addSub(new_funcs);
	    return total;
	}
	return current;
    }

    @Override
    public IWord getSubject()
    {
	List<IWord> objects = this.getSubWords(IWord.Link.SUBJ);
	for (IWord obj : objects)
	{
	    //If object is noun - it is good
	    if (obj.getPos().equals("S") || obj.getPos().equals("SPRO"))
		    return obj;
	    else
	    {
		for (IWord subsubword : obj.getSubWords())
		{
		    //TODO: check for other potentional linktypes except PREP
		    if (subsubword.getLink() == IWord.Link.PREP)
		    {
			if (subsubword.getPos().equals("S") || subsubword.getPos().equals("SPRO"))
			    return subsubword;
		    }
		}
	    }
	}
	IWord ret = new ConllWord();
	ret.setLinktype(IWord.Link.NONE);
	return ret;
    }

    @Override
    public IWord getObject()
    {
	List<IWord> objects = this.getSubWords(IWord.Link.OBJ);
	for (IWord obj : objects)
	{
	    //If object is noun - it is good
	    if (obj.getPos().equals("S") || obj.getPos().equals("SPRO"))
		    return obj;
	    else
	    {
		for (IWord subsubword : obj.getSubWords())
		{
		    //TODO: check for other potentional linktypes except PREP
		    if (subsubword.getLink() == IWord.Link.PREP)
		    {
			if (subsubword.getPos().equals("S") || subsubword.getPos().equals("SPRO"))
			    return subsubword;
		    }
		}
	    }
	}
	IWord ret = new ConllWord();
	ret.setLinktype(IWord.Link.NONE);
	return ret;
    }
    
    /**
     * Returns all subobject except OBJ and SUBJ
     * @return list of subwords
     */
    public List<IWord> getOtherSubwords()
    {
	List<IWord> objects = this.getSubWords();
	List<IWord> ret = new ArrayList<>();
	for (IWord obj : objects)
	{
	    //If object is noun - it is good
	    if (obj.getLinktype() != IWord.Link.SUBJ && obj.getLinktype() != IWord.Link.OBJ)
		ret.add(obj);
	    
	}
	return ret;
    }
    
    public IFunction subsToPla(IFunction tmp_result)
    {
	List<IWord> subs = this.getOtherSubwords();
	for (IWord sub : subs)
	{
	    IFunction tmp_sublogic = sub.toPLA();
	    if (tmp_sublogic != null)
		tmp_result.addSub(tmp_sublogic);
	}
	return tmp_result;
    }

    @Override
    public Boolean isBlank()
    {
	return (this.linktype == IWord.Link.NONE);
    }
    
    @Override
    public Boolean isProposition()
    {
	return this.getPos().equals("SPRO");
    }

    @Override
    public IFunction toSubPLA()
    {
	IFunction total = new ConjunctionContainer();
	total = this.subsToPla(total);
	//if total contains one result and no sublogic - return only one
	if (total.getSub().size() > 1)
	{
	    return total;
	} else if (total.getSub().size() > 0)
	{
	    return total.getSub().get(0);
	} else
	{
	    return null;
	}
    }

    @Override
    public String getConll()
    {
	return this.conll_string;
    }

    @Override
    public Integer getId()
    {
	return this.id;
    }

    @Override
    public void setId(Integer id)
    {
	this.id = id;
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
    public void setUuid(String uuid)
    {
	this.uuid = uuid;
    }

    @Override
    public String getUuid()
    {
	return this.uuid;
    }

    @Override
    public String resolveAnaphora(ICFunction func)
    {
	List<Proposition> props = VariableStorage.getPopostionList();
	//if we get quantified formula - set each next var to each next prop
	if (func.getClass().getTypeName().equals("org.tariel.pla.logics.classic.CQuantifiedContainer"))
	{
	    String ret = "";
    
	    List<String> conllstrings = Arrays.asList(this.conll_string.split("\n"));
	    for (String str : conllstrings)
	    {
		String[] parts = str.split("\t");
		ret += " " + parts[1];
		Proposition prop = VariableStorage.getPropByUuid(parts[10]);
		if (!prop.isEmpty())
		{
		    ret += "<" + prop.getName() + ">";
		}
		    
	    }
	    return ret;
	}
	else
	{
	    return this.getSentence();
	}
    }

    @Override
    public String getSentence()
    {
	String ret = "";
	List<String> conllstrings = Arrays.asList(this.conll_string.split("\n"));
	for (String str : conllstrings)
	{
	    String[] parts = str.split("\t");
	    ret += parts[1] + " ";
	}
	return ret;
    }
}
