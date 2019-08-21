package org.hisp.dhis.rules.functions

/*
 * Copyright (c) 2004-2018, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hisp.dhis.rules.RuleVariableValue
import org.hisp.dhis.rules.RuleVariableValueBuilder
import org.junit.Test
import java.util.*
import kotlin.collections.HashMap
import kotlin.test.assertFailsWith

class RuleFunctionMaxValueTests {

    private val variableValues = HashMap<String, RuleVariableValue>()

    private val maxValueFunction = RuleFunctionMaxValue.create()

    @Test
    fun throw_Exception_If_ValueMap_Null() {
        assertFailsWith<IllegalArgumentException> {
            maxValueFunction.evaluate(listOf("1"), null, null)
        }
    }

    @Test
    fun throw_Exception_If_Argument_Has_More_Than_One_Element() {
        assertFailsWith<IllegalArgumentException> {
            maxValueFunction.evaluate(listOf(), variableValues, null)
        }
    }

    @Test
    fun return_Max_Value() {
        val variableNameOne = "test_variable_one"
        val value = "5.0"

        variableValues[variableNameOne] = RuleVariableValueBuilder.create()
                .withValue(value)
                .withCandidates(listOf(value, "6", "7"))
                .withEventDate(Date().toString())
                .build()

        assertThat(maxValueFunction.evaluate(listOf(variableNameOne), variableValues, null), `is`("7.0"))
    }

    @Test
    fun return_Empty_String_If_Value_Absent() {
        val variableNameOne = "test_variable_one"

        assertThat(maxValueFunction.evaluate(listOf(variableNameOne), variableValues, null), `is`(""))
    }
}
