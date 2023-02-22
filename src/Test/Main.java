package Test;

import DIContainer.ServiceContainer;

public class Main {
    public static void main(String[] args) {
        ServiceContainer serviceContainer = new ServiceContainer();
        serviceContainer.addSingleton(ITest.class, Test.class);
        var testS = serviceContainer.getInstance(ITest.class);
        test.out();

        ServiceContainer serviceContainerNext = new ServiceContainer();
        serviceContainer.addTracient(ITest.class, Test.class);
        var testF = serviceContainer.getInstance(ITest.class);
        testF.out();
    }
}
