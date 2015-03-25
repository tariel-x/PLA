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

import java.util.List;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public interface IWord
{

    enum Link
    {

        SUBJ,
        OBJ,
        AMOD,
        PREP,
        POBJ,
        NEG
    }

    public List<IWord> getSubWords();

    public Link getLink();

    public void fromConll(String conllstring);

    /**
     * Get origin word
     *
     * @return word
     */
    public String getWord();

    /**
     * Get word lexem
     *
     * @return lexem
     */
    public String getLex();

    public String getPos();

    public String getTense();

    public String getCase();

    public String getNumber();

    public String getVerbRepr();

    public String getVerbMood();

    public String getAdjForm();

    public String getAdjDegree();

    public String getVerbFace();

    public String getGender();

    public String getAspect();

    public String getVoice();

    public String getAnimacy();

    public String getTransitivity();

    public String getAdditionalInfo();

    public void setLex(String value);

    public void setPos(String value);

    public void setTense(String value);

    public void setCase(String value);

    public void setNumber(String value);

    public void setVerbRepr(String value);

    public void setVerbMood(String value);

    public void setAdjForm(String value);

    public void setAdjDegree(String value);

    public void setVerbFace(String value);

    public void setGender(String value);

    public void setAspect(String value);

    public void setVoice(String value);

    public void setAnimacy(String value);

    public void setTransitivity(String value);

    public void setAdditionalInfo(String value);
}
