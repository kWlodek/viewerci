package hbgm.module_dummies;

import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.IFileLoader;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import gui.Viewer;

import java.io.File;

public class DummyFileLoader implements IFileLoader {
    @Override
    public void loadFolder(File folder) throws Exception {

    }

    @Override
    public void configure(Builder builder) {

    }

    @Override
    public void registerObserver(IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void changed(IObservable o) {

    }
}
