package Exeptions;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ServiceError
{
    public ArrayList<Class<?>> fields = new ArrayList<>();
    public ArrayList<Class<?>> dependencyList = new ArrayList<>();

    public boolean IsContainsCycle(Class<?> objType)
    {
        fields.add(objType);
        var objectFields = objType.getFields();
        ArrayList<Class<?>> fieldsList = new ArrayList<>();
        for (Field field : objectFields)
        {
            if (!field.getType().isPrimitive())
            {
                if (!fieldsList.contains(field.getType()))
                    fieldsList.add(field.getType());
            }
        }
        for (var field2 : fieldsList)
        {
            if (!fields.contains(field2))
            {
                if (IsContainsCycle(field2))
                {
                    dependencyList.add(field2);
                    return true;
                }
            }
            else
            {
                dependencyList.add(field2);
                return true;
            }
        }
        return false;
    }

    public String CheckForCycles(Class<?> objType)
    {
        if (IsContainsCycle(objType))
        {
            String errMsg = "Cycle: {dependencyList.Last().Name}";
            for (var dependency : dependencyList)
            {
                errMsg += "{dependency.Name} ";
            }
            return errMsg;
        }
        return null;
    }
}
