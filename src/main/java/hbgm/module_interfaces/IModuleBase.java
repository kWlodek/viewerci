package hbgm.module_interfaces;


/**
 * base interface for all module_interfaces:
 * essentially every module shall implement oberver- and observable
 * functionality.
 * Additionally configure(...) is defined which is called after
 * UI is assembled.
 */
public interface IModuleBase extends IObserver, IObservable {
    /**
     * configure will be called by the central builder instance.
     * each module can get access to each other module via the builder.
     * @param builder
     */
    public void configure(Builder builder);
}
