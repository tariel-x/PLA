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
package org.tariel.pla.logics;

import java.util.ArrayList;
import java.util.List;
import org.tariel.pla.logics.classic.CQuantifiedContainer;
import org.tariel.pla.logics.classic.CQuantifer;
import org.tariel.pla.logics.classic.ICFunction;
import org.tariel.pla.logics.classic.CConjunctionContainer;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class ConjunctionContainer implements IFunction
{
    private List<IFunction> sub_functions;
    
    public ConjunctionContainer()
    {
	this.sub_functions = new ArrayList<>();
    }
    
    @Override
    public List<IFunction> getSub() {
	return this.sub_functions;
    }

    @Override
    public void addSub(IFunction sub) {
	this.sub_functions.add(sub);
    }

    @Override
    public String toStrRepresentation()
    {
	String result = "";
	List<String> sub_strs = new ArrayList<>();
	for (IFunction sub: this.getSub())
	{
	    if (sub != null)
		sub_strs.add(sub.toStrRepresentation());
	}
	result = String.join(" ⋀ ", sub_strs);
	return result;
    }

    @Override
    public ICFunction toClassicLogic()
    {
	
	List<CQuantifer> reverse_quants = new ArrayList<>();
	List<ICFunction> new_subfunctions = new ArrayList<>();
	
	for (IFunction sub : this.getSub())
	{
	    //Make classic logics from subfunctions
	    if (sub == null)
		continue;
	    ICFunction tmp_classic = sub.toClassicLogic();
	    if (tmp_classic.getClass().getTypeName().equals("org.tariel.pla.logics.classic.CQuantifiedContainer"))
	    {
		//If sub is quantified formula
		CQuantifiedContainer old_quants = (CQuantifiedContainer) tmp_classic;
		//take all quantifers
		List<CQuantifer> old_quants_list = old_quants.getQuantifers();
		//look it backwards
		for (int i = old_quants_list.size()-1; i >= 0; i--)
		{
		    //add to final quantifers list in reverse order
		    CQuantifer tmp_quant = new CQuantifer();
		    tmp_quant.setVar(old_quants_list.get(i).getVar());
		    reverse_quants.add(tmp_quant);
		    //then it will be reversed
		}
		//save all subfunctions
		CConjunctionContainer subcont = new CConjunctionContainer();
		for (ICFunction quant_sub : old_quants.getSub())
		{
		    subcont.addSub(quant_sub);
		}
		new_subfunctions.add(subcont);
	    }
	    else //Other variant - CTerm or CConjunctionContainer
	    {
		//simple add it
		new_subfunctions.add(tmp_classic);
	    }
	}
	if (reverse_quants.size() > 0)
	{
	    CQuantifiedContainer container = new CQuantifiedContainer();
	    for (ICFunction func : new_subfunctions)
	    {
		container.addSub(func);
	    }
	    for (int i = reverse_quants.size()-1; i >= 0; i--)
	    {
		//add to quantifers container in reverse order
		CQuantifer tmp_quant = new CQuantifer();
		tmp_quant.setVar(reverse_quants.get(i).getVar());
		container.addQuantifer(tmp_quant);
	    }
	    return container;
	}
	else
	{
	    CConjunctionContainer container = new CConjunctionContainer();
	    for (ICFunction func : new_subfunctions)
	    {
		container.addSub(func);
	    }
	    return container;
	}
    }

    @Override
    public void cleanSubs()
    {
	this.sub_functions = new ArrayList<>();
    }

    @Override
    public IFunction clone()
    {
	ConjunctionContainer new_cont = new ConjunctionContainer();
	new_cont.setSubs(this.sub_functions);
	return new_cont;
    }
    
    protected void setSubs(List<IFunction> new_subs)
    {
	this.sub_functions = new_subs;
    }

}
