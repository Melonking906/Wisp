package me.nonit.wisp.actions;

import me.nonit.wisp.WispObject;

public abstract class Action
{
    private final String actionName;

    protected WispObject wisp;

    public Action( String actionName )
    {
        this.actionName = actionName;
    }

    public abstract boolean go();

    public String getActionName()
    {
        return actionName;
    }
}