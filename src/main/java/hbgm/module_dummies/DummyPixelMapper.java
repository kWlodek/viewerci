package hbgm.module_dummies;

import at.spengergasse.hbgm.entities.Instance;
import gui.Viewer;
import hbgm.module_interfaces.Builder;
import hbgm.module_interfaces.IObservable;
import hbgm.module_interfaces.IObserver;
import hbgm.module_interfaces.IPixelMapper;

import java.awt.image.BufferedImage;

public class DummyPixelMapper implements IPixelMapper {
    @Override
    public BufferedImage map(Instance instance) throws Exception {
        return null;
    }

    @Override
    public void configure(Builder builder) {

    }

    @Override
    public void registerObserver(hbgm.module_interfaces.IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void changed(IObservable o) {

    }
}
