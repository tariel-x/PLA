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
import org.tariel.pla.logics.classic.CTerm;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class Term implements IFunction
{
    private List<IVariable> variables = new ArrayList<IVariable>();
    private String name;

    public List<IVariable> getVars()
    {
	return this.variables;
    }

    public void addVar(IVariable newvar)
    {
	this.variables.add(newvar);
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getName()
    {
	return this.name;
    }

    @Override
    public void addSub(IFunction sub)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IFunction> getSub()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toStrRepresentation()
    {
	String ret = this.getName(); 
	ret = ret.substring(0,1).toUpperCase() + ret.substring(1);
	List<String> sub_vars = new ArrayList<>();
	for (IVariable var : this.getVars())
	{
	    sub_vars.add(var.toStrRepresentation());
	}
	ret = ret +  "(" + String.join(",", sub_vars) + ")";
	return ret;
    }

    @Override
    public ICFunction toClassicLogic()
    {
	CTerm classic_term = new CTerm();
	for (IVariable var : this.getVars())
	{
	    classic_term.addVar(var);
	}
	return classic_term;
    }

    @Override
    public void cleanSubs()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IFunction clone()
    {
	Term new_term = new Term();
	new_term.setName(this.name);
	for (IVariable var : this.getVars())
	{
	    new_term.addVar(var);
	}
	return new_term;
    }
}
