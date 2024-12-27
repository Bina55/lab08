package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;


class TestDeathNote {

    @Test 
    public void NoNegativeRules() {
        DeathNote d1 = new DeathNoteImplementation();
        try {
            d1.getRule(0);
        } catch (IllegalArgumentException e) {

        }
        try {
            d1.getRule(-1);
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void NoEmptyRules() {
        for(String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertNotEquals(rule, "");
            assertNotEquals(rule, " ");
        }
    }

    @Test
    public void humanDied() {
        DeathNoteImplementation d2 = new DeathNoteImplementation();
        String personToKill = "Mario";
        assertFalse(d2.getNames().containsKey(personToKill));
        d2.writeName(personToKill);
        assertTrue(d2.getNames().containsKey(personToKill));
        assertEquals(d2.getNames().keySet(), Set.of(personToKill));
        try {
            d2.writeName(null);
        } catch (NullPointerException e) {

        }
        try {
            d2.writeName("");
        } catch (NullPointerException e) {
        }
    }


    @Test
    public void causeOfDeath() throws InterruptedException {
        DeathNoteImplementation d3 = new DeathNoteImplementation();
        String PersonToKill = "Mario";
        try {
            d3.writeDeathCause(PersonToKill, "Accident");
        } catch (IllegalStateException e) {
        }
        d3.writeName(PersonToKill);
        assertEquals("heart attack", d3.getNames().get(PersonToKill));
        String secondPersonToKill = "Haley";
        d3.writeName(secondPersonToKill);
        assertTrue(d3.writeDeathCause(secondPersonToKill, "karting accident"));
        assertEquals("karting accident", d3.getNames().get(secondPersonToKill));
        Thread.sleep(100L);
        assertFalse(d3.writeDeathCause(secondPersonToKill, "run for to long"));
    }

    @Test
    public void detailsOfDeath() throws InterruptedException {
        DeathNoteImplementation d4 = new DeathNoteImplementation();
        String personToKill = "Mario";
        try{
            d4.writeDetails(personToKill, "Killed by a car at 17.05 today");
        } catch (IllegalStateException e) {
        }
        d4.writeName(personToKill);
        assertEquals(d4.getDetails().get(personToKill), null);
        String details1 = "ran for too long";
        assertTrue(d4.writeDetails(personToKill, details1));
        assertEquals(d4.getDetails().get(personToKill), details1);
        String personToKill2 = "Haley";
        d4.writeName(personToKill2);
        Thread.sleep(6100L);
        assertFalse(d4.writeDetails(personToKill2, details1));
    }

}