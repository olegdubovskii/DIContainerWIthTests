package DIContainer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;



public class ServiceContainer {
    private ArrayList<Service> registered;


    public ServiceContainer() {
        this.registered = new ArrayList<>();
    }

    public <TInterface, TImplementation> void addSingleton(TInterface tInterface, TImplementation tImplementation) {
        registered.add(new Service(tInterface.getClass(), tImplementation.getClass(), ServiceLifeTime.SINGLETON));
    }

    public <TInterface, TImplementation> void addTracient(TInterface tInterface, TImplementation tImplementation) {
        registered.add(new Service(tInterface.getClass(), tImplementation.getClass(), ServiceLifeTime.TRACIENT));
    }

    public <TInterface> TInterface getIstance (TInterface tInterface) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (TInterface) gs(tInterface.getClass());
    }

    private Object gs(Class<?> serviceType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Optional<Service> tempServ = registered.stream().filter(s->s.interfaceType == serviceType).findAny();
        Service service = tempServ.get();
        if (service.instance != null) {
            return service.instance;
        }
        Class<?> actType = service.implementationType == null? service.interfaceType : service.implementationType;
        Constructor<?> constructor = actType.getConstructor();
        Object[] params = Arrays.stream(constructor.getParameters()).filter(s->gs(s.getType())).map().toArray();
        var instance = constructor.newInstance(params);
        if (service.lifeTime == ServiceLifeTime.SINGLETON) {
            service.instance = instance;
        }
        return instance;
    }
}
