package listUtils;

import javafx.scene.Parent;

public abstract class BaseElement<T>
{
    protected T data;
    protected Parent element;

    public T getData() {
        return data;
    }

    protected abstract Parent createParent();

    public BaseElement(T data)
    {
        this.data=data;
        element=createParent();
    }

    public Parent getElement()
    {
        return element;
    }
}
