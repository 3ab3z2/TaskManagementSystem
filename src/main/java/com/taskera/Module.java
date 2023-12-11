package com.taskera;

import java.io.IOException;

public abstract class Module {
    User currentUser;
    
    public abstract void startModule() throws IOException;
}
