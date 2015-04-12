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
import org.tariel.pla.logics.classic.ICFunction;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class Quantifer implements IFunction
{
    private IVariable var;
    
    private List<IFunction> sub_functions;
    
    public Quantifer()
    {
	this.var = new LogicVariable();
	this.var.setName(java.util.UUID.randomUUID().toString());
	this.sub_functions = new ArrayList<>();
    }
    
    public IVariable getVar()
    {
	return this.var;
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
	String ret = "∃" + this.getVar().toStrRepresentation();
	List<String> sub_strs = new ArrayList<>();
	for (IFunction sub: this.getSub())
	{
	    sub_strs.add(sub.toStrRepresentation());
	}
	ret = ret + "(" + String.join(" ⋀ ", sub_strs) + ")";
	return ret;
    }

    @Override
    public ICFunction toClassicLogic()
    {
	IFunction current_quantifer = (IFunction) this.clone();
	current_quantifer.cleanSubs();
	for (IFunction sub : this.getSub())
	{
//	    current_quantifer.addSub(sub.toClassicLogic());
	}
//	return current_quantifer;
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cleanSubs()
    {
	this.sub_functions = new ArrayList<>();
    }

    @Override
    public IFunction clone()
    {
	Quantifer new_quant = new Quantifer();
	new_quant.setVar(this.var);
	new_quant.setSubs(this.sub_functions);
	return new_quant;
    }
    
    protected void setVar(IVariable newvar)
    {
	this.var = newvar;
    }
    
    protected void setSubs(List<IFunction> new_subs)
    {
	this.sub_functions = new_subs;
    }
    
}
