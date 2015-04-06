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
    private static Map<String, String> vars = new HashMap<>();
    private static Map<String, String> propositions = new HashMap<>();
    
    public static String getVar(String hash)
    {
	if (vars.containsKey(hash))
	{
	    return vars.get(hash);
	}
	else
	{
	    vars.put(hash, String.copyValueOf(Character.toChars(97 + vars.size())));
	    return vars.get(hash);
	}
    }
    
    public static String getProposition(String hash)
    {
	if (propositions.containsKey(hash))
	{
	    return propositions.get(hash);
	}
	else
	{
	    propositions.put(hash, "p"+String.valueOf(propositions.size()));
	    return propositions.get(hash);
	}
    }
}
