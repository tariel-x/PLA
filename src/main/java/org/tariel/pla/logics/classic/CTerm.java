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

package org.tariel.pla.logics.classic;

import java.util.ArrayList;
import java.util.List;
import org.tariel.pla.logics.IFunction;
import org.tariel.pla.logics.IVariable;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class CTerm implements ICFunction
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
    public void addSub(ICFunction sub)
    {
	throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public List<ICFunction> getSub()
    {
	throw new UnsupportedOperationException("Not supported.");
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
    public void cleanSubs()
    {
	throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void resolveAnaphora()
    {
	throw new UnsupportedOperationException("Not supported.");
    }
    
}
