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

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class CQuantifiedContainer implements ICFunction
{
    private List<CQuantifer> quantifers;
    private List<ICFunction> sub_functions;
    
    public CQuantifiedContainer()
    {
	this.quantifers = new ArrayList<>();
	this.sub_functions = new ArrayList<>();
    }
    
    @Override
    public List<ICFunction> getSub()
    {
	return this.sub_functions;
    }

    @Override
    public void addSub(ICFunction sub)
    {
	this.sub_functions.add(sub);
    }

    @Override
    public String toStrRepresentation()
    {
	String result = "";
	for (CQuantifer quant : this.getQuantifers())
	{
	    result += quant.toStrRepresentation();
	}
	List<String> sub_strs = new ArrayList<>();
	for (ICFunction sub : this.getSub())
	{
	    sub_strs.add(sub.toStrRepresentation());
	}
	result = String.join(" ⋀ ", sub_strs);
	return result;
    }

    @Override
    public void cleanSubs()
    {
	this.sub_functions = new ArrayList<>();
    }
    
    public void addQuantifer(CQuantifer quant)
    {
	this.quantifers.add(quant);
    }
    
    public List<CQuantifer> getQuantifers()
    {
	return this.quantifers;
    }
    
    public void cleanQuantifers()
    {
	this.quantifers = new ArrayList<>();
    }
}
