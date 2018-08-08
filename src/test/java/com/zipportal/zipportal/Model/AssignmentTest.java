package com.zipportal.zipportal.Model;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AssignmentTest {
    Assignment test = new Assignment();
    User instructor = new User();
    Repo parent = new Repo();

    @Test
    public void getId() {
        test.setId(1L);
        assertTrue(test.getId()==1L);
    }

    @Test
    public void getAssignmentUrl() {
        test.setAssignmentUrl("http://totallyreal.assignment.com");
        assertTrue(test.getAssignmentUrl().equalsIgnoreCase("http://totallyreal.assignment.com"));
    }

    @Test
    public void getInstructor() {
        test.setInstructor(instructor);
        assertTrue(test.getInstructor().equals(instructor));
    }

    @Test
    public void getParent() {
        test.setParent(parent);
        assertTrue(test.getParent().equals(parent));
    }

    @Test
    public void getAssignment_Url() {
    }

    @Test
    public void getAssigned_on() {
        test.setAssigned_on(new Date("11/12/13"));
        assertTrue(test.getAssigned_on().equals(new Date("11/12/13")));
    }
}