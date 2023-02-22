package DIContainer;

import java.lang.reflect.Type;

public class Service {
    public Class<?> interfaceType;
    public Class<?> implementationType;
    public ServiceLifeTime lifeTime;
    public Object instance;


    public Service(Class<?> interfaceType, Class<?> implementationType, ServiceLifeTime lifeTime) {
        this.interfaceType = interfaceType;
        this.implementationType = implementationType;
        this.lifeTime = lifeTime;
    }
}
