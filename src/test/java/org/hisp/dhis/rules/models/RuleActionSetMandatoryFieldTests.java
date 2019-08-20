package org.hisp.dhis.rules.models;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.hisp.dhis.rules.models.RuleActionSetMandatoryField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith( JUnit4.class )
public class RuleActionSetMandatoryFieldTests
{

        @Test
        public void createMustThrowOnNullArgument()
        {
                try
                {
                        RuleActionSetMandatoryField.Companion.create( null );
                }
                catch ( NullPointerException nullPointerException )
                {
                        // noop
                }
        }

        @Test
        public void equalsAndHashcodeMustConformToContract()
        {
                EqualsVerifier.forClass( RuleActionSetMandatoryField.Companion.create( "" ).getClass() )
                    .suppress( Warning.NULL_FIELDS )
                    .verify();
        }
}
