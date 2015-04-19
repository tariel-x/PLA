/*
 * Copyright 2015 Nikita Gerasimov <n@tariel.ru>.
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

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class VariableStorage
{
    private static Map<String, String> vars_names = new HashMap<>();
    private static Map<String, String> propositions_names = new HashMap<>();
    private static Map<String, Proposition> prop_map = new HashMap<>();
    private static List<Proposition> prop_index = new ArrayList<>();

    public VariableStorage()
    {
    }
    
    public static String getVar(LogicVariable var)
    {
	if (vars_names.containsKey(var.getSourceId()))
	{
	    return vars_names.get(var.getSourceId());
	}
	else
	{
	    vars_names.put((String)var.getSourceId(), 
		    String.copyValueOf(Character.toChars(97 + vars_names.size())));
	    return vars_names.get(var.getSourceId());
	}
    }
    
    public static String getProposition(Proposition prop)
    {
	if (propositions_names.containsKey(prop.getSourceId()))
	{
	    return propositions_names.get(prop.getSourceId());
	}
	else
	{
	    prop_index.add(prop);
	    prop_map.put((String)prop.getSourceId(), prop);
	    propositions_names.put((String)prop.getSourceId(), "p"+
		    String.valueOf(propositions_names.size()));
	    return propositions_names.get(prop.getSourceId());
	}
    }
    
    public static Proposition getPropByUuid(String uuid)
    {
	if (prop_map.containsKey(uuid))
	    return prop_map.get(uuid);
	else
	    return new Proposition();	
    }
    
    public static List<Proposition> getPopostionList()
    {
	return prop_index;
    }
    
    public static void clear()
    {
	vars_names = new HashMap<>();
	propositions_names = new HashMap<>();
    }
}
