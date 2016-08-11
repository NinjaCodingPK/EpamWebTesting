/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wookie.epamwebtesting.controllers.commands;

/**
 * List of commands from Command pattern.
 */
public enum CommandList {
    //SHOW_SUBJECT(new ShowSubject()),
    ADD_SUBJECT(new AddSubject()),
    //ADMIN_PAGE(new AdminRedirect()),
    USER_PAGE(new UserRedirect()),
    TO_ADD_TASK(new ToAddTask()),
    TO_UPDATE_TASK(new ToUpdateTask()),
    TO_UPDATE_TEST(new ToUpdateTest()),
    UPDATE_TEST(new UpdateTest()),
    ADD_TASK(new AddTask()),
    ADD_TEST(new AddTest()),
    SHOW_TESTS_BY_SUBJECT(new ShowTestsBySubject()),
    SHOW_TESTS_BY_TUTOR(new ShowTestsByTutor()),
    GET_TEST(new GetTest()),
    //SHOW_NEW_USER_PAGE(null),
    SHOW_RESULT(new ShowResult()),
    LOGIN_USER(new LoginUser()),
    DELETE_TASK(new DeleteTask()),
    DELETE_TEST(new DeleteTest()),
    DELETE_SUBJECT(new DeleteSubject()),
    SHOW_TUTORS_TESTS(new ShowTutorsTests()),
    //REDIRECT_ADMIN_PAGE(new AdminRedirect()),
    LOGOUT(new Logout()),
    SHOW_PROFILE(new ShowProfile()),
    SHOW_STATISTIC(new ShowStatistic());

    private CommandList(Command command) {
        this.command = command;
    }

    private Command command;

    public Command getCommand() {
        return command;
    }
}
