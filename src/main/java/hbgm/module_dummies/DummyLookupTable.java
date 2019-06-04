package hbgm.module_dummies;

import at.spengergasse.hbgm.module_interfaces.Builder;
import at.spengergasse.hbgm.module_interfaces.ILookupTable;
import at.spengergasse.hbgm.module_interfaces.IObservable;
import at.spengergasse.hbgm.module_interfaces.IObserver;
import gui.Viewer;

public class DummyLookupTable implements ILookupTable {
    @Override
    public int argb(int value) {
        return 0;
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
